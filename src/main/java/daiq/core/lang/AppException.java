package daiq.core.lang;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AppException extends RuntimeException {

    private final Message message;
    private final List<String> keys;
    
    public interface Message {
        String text();
        String code();
    }
    
    public AppException(Message message, String...keys) {
        super(message.text());
        this.message = message;
        this.keys = Arrays.asList(keys);
    }

    public AppException(Message message, Throwable cause, String...keys) {
        super(message.text(), cause);
        this.message = message;
        this.keys = Arrays.asList(keys);
    }
    
    public Message message() {
        return message;
    }

    
    public static Optional<Message> of(Exception e) {
        if (e instanceof AppException) 
            return Optional.of(AppException.class.cast(e).message);
        return Optional.empty();
    }
}
