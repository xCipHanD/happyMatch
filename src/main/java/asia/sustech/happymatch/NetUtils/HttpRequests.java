package asia.sustech.happymatch.NetUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class HttpRequests {

    //登录接口
    public static HttpResult login(String username, String password) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.LOGIN.toString(), username, password));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            String token = jsonObject.getString("token");
            return new HttpResult(code, message, token, new JSONObject());
        } catch (IOException e) {
            return new HttpResult(-1, "", "", new JSONObject());
        }
    }

    //注册接口
    public static HttpResult register(String username, String password, String email) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.REGISTER.toString(), username, password, email));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return new HttpResult(code, message, "", new JSONObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //getCode接口
    public static HttpResult getCode(String email) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.GET_CODE.toString(), email));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return new HttpResult(code, message, "", new JSONObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResult changePwd(String email, String newPwd, String vCode) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.FORGET_PWD.toString(), email, newPwd, vCode));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return new HttpResult(code, message, "", new JSONObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResult getUserInfo(String token) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.USER_INFO.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            JSONObject data = jsonObject.getJSONObject("data");
            return new HttpResult(code, message, token, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
