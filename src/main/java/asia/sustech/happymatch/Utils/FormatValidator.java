package asia.sustech.happymatch.Utils;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

public class FormatValidator {
    private static final String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final String passwordRegex = "^[a-zA-Z0-9`~!@#$%^&*()_+\\-={}|\\[\\]\\\\:\";'<>?,./]{6,16}$";
    private static final String userNameRegex = "^[a-zA-Z0-9_-]{4,10}$";
    private static final String tokenRegex = "^[a-zA-Z0-9]{32}$";

    private static final String salt = "heLlomYj@VApROJEcTbyXC1Ph4nd";

    public static boolean isEmailInvalid(String email) {
        if (email == null || email.equals("null") || email.length() > 50) return true;
        return !email.matches(emailRegex);
    }

    public static boolean isPasswordInvalid(String password) {
        if (password == null || password.length() > 16 || password.length() < 6) {
            return true;
        }
        return !password.matches(passwordRegex);
    }

    public static boolean isUserNameInvalid(String userName) {
        if (userName == null || userName.length() > 10 || userName.length() < 4) return true;
        return !userName.matches(userNameRegex);
    }

    public static boolean isTokenInvalid(String token) {
        if (token == null || token.length() != 32) return true;
        return !token.matches(tokenRegex);
    }

    public static boolean isAvatarInvalid(String avatar) {
        if (avatar == null) return true;
        //是否为base64编码 且是否小于2M
        return isBase64Encoded(avatar) || isSizeValid(avatar);
    }

    private static boolean isBase64Encoded(String avatar) {
        try {
            // 移除base64编码前缀部分
            String imageData = avatar.split(",")[1];
            // 解码base64数据
            byte[] imageBytes = Base64.getDecoder().decode(imageData);
            // 将字节数组转换为图像
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            ImageIO.read(bis);
            bis.close();
            return false;
        } catch (IllegalArgumentException | IOException e) {
            return true;
        }
    }

    private static boolean isSizeValid(String avatar) {
        // 获取头像字符串的字节数
        int avatarSize = avatar.getBytes().length;

        // 将最大大小转换为字节数
        int maxSizeInBytes = 2 * 1024 * 1024;

        return avatarSize > maxSizeInBytes;
    }
    public static Boolean isMapFormatInvalid(String map) {
        try {
            Scanner sc = new Scanner(map);
            String s1 = sc.nextLine();
            int targetNum = Integer.parseInt(s1.split(" ")[0]);
            int row = Integer.parseInt(s1.split(" ")[1]);
            int col = Integer.parseInt(s1.split(" ")[2]);
            int blocks = Integer.parseInt(s1.split(" ")[3]);
            int currentStep = Integer.parseInt(s1.split(" ")[4]);
            int totalStep = Integer.parseInt(s1.split(" ")[5]);

            String[][] s2 = new String[targetNum][4];
            for (int i = 0; i < targetNum; i++) {
                String s = sc.nextLine();
                System.arraycopy(s.split(" "), 0, s2[i], 0, 4);
            }

            String[][] s3 = new String[row][col];
            for (int i = 0; i < row; i++) {
                String s = sc.nextLine();
                System.arraycopy(s.split(" "), 0, s3[i], 0, col);
            }
            sc.close();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}