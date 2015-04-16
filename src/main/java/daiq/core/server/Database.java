package daiq.core.server;

import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import java.util.List;

import static daiq.Config.*;

public class Database {

    public final static ThreadLocal<OObjectDatabaseTx> tl = ThreadLocal.withInitial(() -> null);

    private final static OObjectDatabaseTx db;
    static {
        db = new OObjectDatabaseTx(DATABASE_URL).create();
        db.setAutomaticSchemaGeneration(true);
        ENTITY_CLASSES.forEach(db.getEntityManager()::registerEntityClass);
    }
    
    public void begin() {
        if (tl.get() != null) return;
        OObjectDatabaseTx tx = OObjectDatabasePool.global()
                .acquire(DATABASE_URL, DATABASE_USER_NAME, DATABASE_USER_PASS);
        tl.set(tx);
    }
    
    public void commit() {
        if (tl.get() == null) throw new RuntimeException("xx");
        try {
            tl.get().commit();
        } finally {
            clean();
        }
    }
    
    public void rollback() {
        if (tl.get() == null) return;
        try {
            tl.get().rollback();
        } finally {
            clean();
        }
    }
    
    private void clean() {
        if (tl.get() == null) return;
        tl.get().close();
        tl.remove();
    }

    public <R> R save(Object entity) {
        return tl.get().save(entity);
    }


    public <R extends List<?>> R query(final OQuery<?> iCommand, final Object... iArgs) {
        return tl.get().query(iCommand, iArgs);
    }
}
