package daiq;

import daiq.application.AdminAppService;
import daiq.core.lang.Utils;
import daiq.core.persistence.TxInterceptorService;
import daiq.core.server.Database;
import daiq.application.AccountAppService;
import daiq.domain.repository.AccountRepository;
import daiq.persistence.AccountRepositoryImpl;
import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

public abstract class Config {

    public static final String APP_NAME = "daiq";

    public static final List<Class<?>> ENTITY_CLASSES = Utils.listClasses(Arrays.asList(
            "daiq.domain.model.shared",
            "daiq.domain.model.party"));
    
    public static final String DATABASE_URL = "memory:app";
    public static final String DATABASE_USER_NAME = "admin";
    public static final String DATABASE_USER_PASS = "admin";

    public static final AbstractBinder BINDER = new AbstractBinder() {
        @Override
        protected void configure() {
            bindAsContract(TxInterceptorService.class).to(InterceptionService.class).in(Singleton.class);
            bindAsContract(Database.class).in(Singleton.class);
            
            // ApplicationService
            bindAsContract(AdminAppService.class);
            bindAsContract(AccountAppService.class);

            // Repository
            bind(AccountRepositoryImpl.class).to(AccountRepository.class).in(Singleton.class);
        }
    };
}
