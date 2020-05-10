package mate.academy.internetshop.exceptions;

public class DataBaseAccessException extends RuntimeException {
    public DataBaseAccessException(String msg, Exception ex) {
        super(msg, ex);
    }
}
