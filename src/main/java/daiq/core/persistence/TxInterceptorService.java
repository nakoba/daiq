package daiq.core.persistence;

import daiq.core.lang.ApplicationService;
import daiq.core.server.Database;
import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.BuilderHelper;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public class TxInterceptorService implements InterceptionService {

    @Inject Database db;
    
    @Override
    public Filter getDescriptorFilter() {
        return BuilderHelper.allFilter();
    }

    @Override
    public List<MethodInterceptor> getMethodInterceptors(Method method) {

        if (ApplicationService.class.isAssignableFrom(method.getDeclaringClass())) {
            return Arrays.asList(new TxInterceptor(db));
        }
        return null;
    }

    @Override
    public List<ConstructorInterceptor> getConstructorInterceptors(Constructor<?> constructor) {
        return null;
    }
}
