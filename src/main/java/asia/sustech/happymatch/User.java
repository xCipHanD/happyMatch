package asia.sustech.happymatch;

public class User {
    private final String userName;
    private final int uid;
    private final String email;
    private String avatarURL;
    private int level;
    private int exp;
    private final String token;
    private int coins;
    public User(String userName, int uid, String email, String avatarURL, int level, int exp, String token, int coins) {
        this.userName = userName;
        this.uid = uid;
        this.email = email;
        this.avatarURL = avatarURL;
        this.level = level;
        this.exp = exp;
        this.token = token;
        this.coins = coins;
    }

    //getters

    public String getUserName() {
        return userName;
    }

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public String getToken() {
        return token;
    }

    public int getCoins() {
        return coins;
    }
    //setters

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
