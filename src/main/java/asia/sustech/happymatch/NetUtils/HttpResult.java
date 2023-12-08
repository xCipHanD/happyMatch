package asia.sustech.happymatch.NetUtils;

public class HttpResult {
    private final int code;
    private final String message;
    private final String data;

    public HttpResult(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
