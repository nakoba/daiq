package daiq.core.persistence;

import daiq.core.server.Database;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.dozer.DozerBeanMapper;

public class TxInterceptor implements MethodInterceptor {

    private Database db;
    private DozerBeanMapper mapper = new DozerBeanMapper();
    
    public TxInterceptor(Database db) {
        this.db = db;
    }
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            db.begin();
            Object ret = invocation.proceed();
            db.commit();
            return mapper.map(ret, invocation.getMethod().getReturnType());
        } finally {
            db.rollback();
        }

    }
}
