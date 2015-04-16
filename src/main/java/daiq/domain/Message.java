package daiq.domain;

public enum Message implements daiq.core.lang.AppException.Message {
    ConflictAccountId("Conflict account id.", "409")
    ;


    public String text() {
        return text;
        
    }
    public String code() {
        return code;
    }
    
    final String text;
    final String code;

    private Message(String text, String code) {
        this.text = text;
        this.code = code;
    }
}
