package hxl.resultUtil;

/**
 * 有结果返回
 *
 * @author hunter
 */
public class ResultResponse<T> implements Response {

    private final T result;

    private ResultResponse(T result) {
        this.result = result;
    }

    public static <T> ResultResponse<T> ok(T result) {
        return new ResultResponse<T>(result);
    }

    @Override
    public int getResCode() {
        return NoResultResponse.INSTANCE.getResCode();
    }

    @Override
    public String getResMessage() {
        return NoResultResponse.INSTANCE.getResMessage();
    }

    public T getResult() {
        return result;
    }
}
