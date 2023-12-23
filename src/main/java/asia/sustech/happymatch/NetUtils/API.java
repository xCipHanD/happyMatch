package asia.sustech.happymatch.NetUtils;

public enum API {
    LOGIN, REGISTER, FORGET_PWD, GET_CODE, USER_INFO, SIGN_IN, RANKLIST, CHANGE_AVATAR, UPDATE_PROCESS,
    GET_GOODS_LIST, BUY, API, GET_ITEMS, MAP_GET_PROCESS, MAP_SAVE_PROCESS, DIY_MAP_SAVE, MAP_GET;

    @Override
    public String toString() {
        String url = "http://47.107.81.179:5614";
        if (this == API)
            return url;
        return url + switch (this) {
            case MAP_GET_PROCESS -> "/map/getProcess?token=%s";
            case MAP_SAVE_PROCESS -> "/map/saveProcess";
            case MAP_GET -> "/map/get?token=%s&mapId=%s";
            case DIY_MAP_SAVE -> "/map/saveDiy";
            case LOGIN -> "/user/login?user=%s&pwd=%s";
            case REGISTER -> "/user/register?user=%s&pwd=%s&email=%s";
            case FORGET_PWD -> "/user/changePWD?email=%s&newPWD=%s&code=%s";
            case GET_CODE -> "/user/getCode?email=%s";
            case USER_INFO -> "/user/info?token=%s";
            case SIGN_IN -> "/user/signin?token=%s";
            case RANKLIST -> "/ranklist?token=%s";
            case CHANGE_AVATAR -> "/user/changeAvatar";
            case UPDATE_PROCESS -> "/user/updateProcess?token=%s&level=%s";
            case GET_GOODS_LIST -> "/shop/getGoodsList?token=%s";
            case BUY -> "/shop/buy?token=%s&itemId=%s";
            case GET_ITEMS -> "/user/getProperty?token=%s";
            default -> "";
        };
    }
}