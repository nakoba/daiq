package daiq.core.lang;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Langs {

    public static boolean not(boolean bool) {
        return !bool;
    }

    public static boolean exist(Collection c) {
        return c != null && !c.isEmpty();
    }
    
    public static boolean empty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
        
    }

    public static <T> T uncheck(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uncheck(RunnableExc r) {
        try {
            r.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String str(String templ, Object... objects) {
        return String.format(templ.replaceAll(Pattern.quote("{}"), "%s"), objects);
    }



    public static void throwWith(AppException.Message message) {
        throw new AppException(message);
    }
    
    public interface RunnableExc {
        void run() throws Exception;
    }
}
