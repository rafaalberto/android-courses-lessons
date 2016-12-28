package ra.com.br.justjava;

public class SystemException extends RuntimeException {

    private String message;

    public SystemException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
