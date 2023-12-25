package asia.sustech.happymatch.NetUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Optional;

public class HttpRequests {

    //登录接口
    public static Optional<HttpResult> login(String username, String password) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.LOGIN.toString(), username, password));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            String token = jsonObject.getString("token");
            return Optional.of(new HttpResult(code, message, token, new JSONObject()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //注册接口
    public static Optional<HttpResult> register(String username, String password, String email) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.REGISTER.toString(), username, password, email));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return Optional.of(new HttpResult(code, message, "", new JSONObject()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //getCode接口
    public static Optional<HttpResult> getCode(String email) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.GET_CODE.toString(), email));
            System.out.println(result);
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return Optional.of(new HttpResult(code, message, "", new JSONObject()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //修改密码接口
    public static Optional<HttpResult> changePwd(String email, String newPwd, String vCode) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.FORGET_PWD.toString(), email, newPwd, vCode));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return Optional.of(new HttpResult(code, message, "", new JSONObject()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //获取用户信息接口
    public static Optional<HttpResult> getUserInfo(String token) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.USER_INFO.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            JSONObject data = jsonObject.getJSONObject("data");
            return Optional.of(new HttpResult(code, message, token, data));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //签到接口
    public static Optional<HttpResult> signIn(String token) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.SIGN_IN.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            return Optional.of(new HttpResult(code, message, token, new JSONObject()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //获取排行榜接口
    public static Optional<HttpResult> getRankList(String token) {
        try {
            String result =
                    HttpRequest.sendGetRequest(String.format(API.RANKLIST.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            JSONObject data = jsonObject.getJSONObject("data");
            return Optional.of(new HttpResult(code, message, token, data));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<HttpResult> changeAvatar(String token, String base64) {
        try {
            String result = HttpRequest.sendPostRequest(API.CHANGE_AVATAR.toString(),
                    String.format("{\"token\":\"%s\",\"avatar\":\"%s\"}", token, base64));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //获取商品列表
    public static Optional<HttpResult> getItemsList(String token) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.GET_GOODS_LIST.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //获取背包物品
    public static Optional<HttpResult> getProperty(String token) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.GET_ITEMS.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");
            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //跟据地图id获取地图
    public static Optional<HttpResult> getMapByID(String token, int mapId) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.MAP_GET.toString(), token, mapId));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //跟据token获取进度
    public static Optional<HttpResult> getProcessMap(String token) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.MAP_GET_PROCESS.toString(), token));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    //保存diy地图
    public static Optional<HttpResult> saveDiyMap(String token, String map) {
        try {
            String result = HttpRequest.sendPostRequest(API.DIY_MAP_SAVE.toString(),
                    String.format("{\"token\":\"%s\",\"map\":\"%s\"}", token, map));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<HttpResult> saveMap(String token, String map) {
        try {
            String result = HttpRequest.sendPostRequest(API.MAP_SAVE_PROCESS.toString(),
                    String.format("{\"token\":\"%s\",\"map\":\"%s\"}", token, map));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<HttpResult> buyItem(String token, int i) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.BUY.toString(), token, i));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<HttpResult> updateProcess(String token, int currentLevel) {
        try {
            String result = HttpRequest.sendGetRequest(String.format(API.UPDATE_PROCESS.toString(), token,
                    currentLevel + 1));
            //fastjson解析result
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            String message = jsonObject.getString("msg");

            if (code == 200) {
                JSONObject data = jsonObject.getJSONObject("data");
                return Optional.of(new HttpResult(code, message, token, data));
            } else {
                return Optional.of(new HttpResult(code, message, token, new JSONObject()));
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
