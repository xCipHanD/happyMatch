package asia.sustech.happymatch.NetUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class HttpRequests {
    private static final String url = "http://47.107.81.179:5614/";
    //登录接口
    public static HttpResult login(String username, String password){
        try {
            String result =
                    HttpRequest.sendGetRequest(url+String.format("user/login?user=%s&pwd=%s",username,password));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            String data = jsonObject.getString("data");
            String token = jsonObject.getString("token");
            return new HttpResult(code,message,token);
        } catch (IOException e) {
            return new HttpResult(-1,"","");
        }
    }
    //注册接口
    public static HttpResult register(String username, String password,String email){
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format("http://happymatch.sustech" +
                            ".asia/user/register?user=%s&pwd=%s&email=%s",username,password,email));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("message");
            String data = jsonObject.getString("data");
            return new HttpResult(code,message,data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
