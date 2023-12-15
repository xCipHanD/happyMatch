package asia.sustech.happymatch;

import asia.sustech.happymatch.NetUtils.API;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class User {
    private static User user;
    private static String userName;
    private static int uid;
    private static String email;
    private static String avatarURL;
    private static int level;
    private static int exp;
    private static String token;
    private static int coins;

    private User(String userName, int uid, String email, String avatarURL, int level, int exp, String token,
                 int coins) {
        User.userName = userName;
        User.uid = uid;
        User.email = email;
        User.avatarURL = avatarURL;
        User.level = level;
        User.exp = exp;
        User.token = token;
        User.coins = coins;
    }

    public static User getUser(String userName, int uid, String email, String avatarURL, int level, int exp,
                               String token,
                               int coins) {
        // 如果实例为空，创建一个新实例
        if (user == null) {
            user = new User(userName, uid, email, avatarURL, level, exp, token, coins);
        }
        // 返回实例
        return user;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static int getUid() {
        return uid;
    }

    public static void setUid(int uid) {
        User.uid = uid;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getAvatarURL() {
        return avatarURL;
    }

    public static void setAvatarURL(String avatarURL) {
        User.avatarURL = avatarURL;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        User.level = level;
    }

    public static int getExp() {
        return exp;
    }

    public static void setExp(int exp) {
        User.exp = exp;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        User.coins = coins;
    }

    public static Image getAvatar() {
        try (InputStream inputStream = new URL(API.API + avatarURL).openStream()) {
            Image image = new Image(inputStream);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}