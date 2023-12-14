package asia.sustech.happymatch.NetUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpResult {
    private final int code;
    private final String message;
    private final String token;
    private final JSONObject data;

    public HttpResult(int code, String message, String token, JSONObject data) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public JSONObject getData() {
        return data;
    }
}
