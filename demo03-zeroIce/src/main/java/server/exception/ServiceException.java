package server.exception;

/**
 * 服务异常
 *
 * @author hunter
 */
public class ServiceException extends RuntimeException {
    /**
     * 国际化编码
     */
    private final Error error;

    public ServiceException(Throwable cause, Error error) {
        super(cause);
        this.error = error;
    }

    public ServiceException(Error error) {
        this.error = error;
    }

    // ----------------------------- get set -----------------------------

    public Error getError() {
        return error;
    }
}
