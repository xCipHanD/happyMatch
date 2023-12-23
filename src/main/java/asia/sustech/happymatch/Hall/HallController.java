package asia.sustech.happymatch.Hall;

import asia.sustech.happymatch.GameController.Map;
import asia.sustech.happymatch.Properties;
import asia.sustech.happymatch.Utils.BGMPlayer;
import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.Utils.FormatValidator;
import asia.sustech.happymatch.Utils.SoundsPlayer;
import asia.sustech.happymatch.User;
import com.alibaba.fastjson.JSONObject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;
import javax.imageio.ImageIO;

public class HallController {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    @FXML
    private GridPane rightPane;

    @FXML
    private GridPane leftPane;

    @FXML
    private ImageView coins;

    @FXML
    private Text levelText;

    @FXML
    private Text userLevelText;
    @FXML
    private Text coinsText;

    @FXML
    private ImageView avatar;
    @FXML
    private ImageView editBt;

    @FXML
    private Text userName;

    @FXML
    private ImageView bgmBt;

    //排行榜按钮
    @FXML
    void rankBtPressed(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //排行榜
        HttpResult result = HttpRequests.getRankList(User.getToken());
        if (result.getCode() == 200) {
            //成功
            //解析result
            String rank1 = JSONObject.parseObject(result.getData().getString("1")).getString("username");
            String rank1_level = JSONObject.parseObject(result.getData().getString("1")).getString("level");
            String rank2 = JSONObject.parseObject(result.getData().getString("2")).getString("username");
            String rank2_level = JSONObject.parseObject(result.getData().getString("2")).getString("level");
            String rank3 = JSONObject.parseObject(result.getData().getString("3")).getString("username");
            String rank3_level = JSONObject.parseObject(result.getData().getString("3")).getString("level");
            //提示框
            String info = String.format("第一名：%s，等级：%s\n第二名：%s，等级：%s\n第三名：%s，等级：%s", rank1, rank1_level, rank2,
                    rank2_level, rank3, rank3_level);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("排行榜");
            alert.showAndWait();
        } else {
            //失败
            //提示框
            String info = "获取排行榜失败！" + result.getMessage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
        }
    }

    //商店按钮
    @FXML
    void shopBtPressed(MouseEvent event) throws IOException {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //跳转页面
        Stage primaryStage = (Stage) coinsText.getScene().getWindow();
        //加载fxml文件
        URL url = getClass().getResource("/Shop.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
    }

    //签到按钮
    @FXML
    void signInBtPressed() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //执行签到请求
        HttpResult result = HttpRequests.signIn(User.getToken());
        if (result.getCode() == 200) {
            //更新金币
            HttpResult result1 = HttpRequests.getUserInfo(User.getToken());
            User.setCoins(result1.getData().getInteger("coins"));
            genderUserInfo();
            //成功提示框
            String info = "签到成功！";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                    ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
        } else {
            //失败提示框
            String info = "签到失败！" + result.getMessage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                    ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
        }
    }

    //自定义地图按钮
    @FXML
    void diyMapBtPressed(MouseEvent event) throws IOException {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //跳转页面
        Stage primaryStage = (Stage) coinsText.getScene().getWindow();
        //加载fxml文件
        URL url = getClass().getResource("/DiyMap.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

    }

    //修改头像按钮
    @FXML
    void editAvatarBtPressed() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //选择图片
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMG", "*.jpg", "*.png"));
        fc.setTitle("选择头像");
        File file = fc.showOpenDialog(editBt.getScene().getWindow());
        //读取图片
        if (file != null) {
            try {
                // 读取原始图像
                BufferedImage originalImage = ImageIO.read(file);

                // 创建一个缩放后的图像
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();
                int newWidth = 128;
                int newHeight = 128;
                if (width > height) {
                    newHeight = (int) (newWidth * ((double) height / width));
                } else {
                    newWidth = (int) (newHeight * ((double) width / height));
                }
                BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = scaledImage.createGraphics();
                graphics2D.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                graphics2D.dispose();

                //转为base64编码
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(scaledImage, "jpg", baos);
                byte[] bytes = baos.toByteArray();
                String base64 = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(bytes);

                Thread thread = new Thread(() -> {
                    //修改头像请求
                    Platform.runLater(() -> {
                        HttpResult result = HttpRequests.changeAvatar(User.getToken(), base64);
                        if (result.getCode() == 200) {
                            //成功
                            //提示框
                            String info = "修改头像成功！";
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                                    ButtonBar.ButtonData.YES));
                            alert.setHeaderText(null);
                            alert.setTitle("提示");
                            alert.showAndWait();
                            //更新头像
                            User.setAvatarURL(result.getData().getString("avatarURL"));
                            avatar.setImage(User.getAvatar());
                        } else {
                            //失败
                            //提示框
                            String info = "修改头像失败！" + result.getMessage();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                                    ButtonBar.ButtonData.YES));
                            alert.setHeaderText(null);
                            alert.setTitle("提示");
                            alert.showAndWait();
                        }
                    });
                });
                thread.start();
            } catch (IOException e) {
                //提示
                String info = "读取图片失败！";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                        ButtonBar.ButtonData.YES));
                alert.setHeaderText(null);
                alert.setTitle("提示");
                alert.showAndWait();
            }
        }
    }

    //背景音乐控制按钮
    @FXML
    void bgmBtPressed(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //切换背景音乐
        if (BGMPlayer.getInstance().isMute()) {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hall/v_on.png"))));
            BGMPlayer.getInstance().setMute(false);
        } else {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hall/v_off.png"))));
            BGMPlayer.getInstance().setMute(true);
        }
    }

    //初始化信息
    @FXML
    public void initialize() {
        genderUserInfo();
    }

    //渲染用户信息
    public void genderUserInfo() {
        userName.setText(User.getUserName());
        userLevelText.setText("Lv." + String.valueOf(User.getLevel()));
        levelText.setText(String.valueOf(User.getLevel()));
        coinsText.setText(String.valueOf(User.getCoins()));
        avatar.setImage(User.getAvatar());
        if (BGMPlayer.getInstance().isMute()) {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hall/v_off.png"))));
        } else {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hall/v_on.png"))));
        }
    }

    //esc退出
    @FXML
    void setOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            //询问是否退出
            String info = "是否退出？";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES)
                    , new ButtonType("取消", ButtonBar.ButtonData.NO));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
            if (alert.getResult().getButtonData().equals(ButtonBar.ButtonData.YES))
                System.exit(0);
        }
    }

    //退出按钮
    @FXML
    void onExistBtnPressed() {
        setOnKeyPressed(new KeyEvent(null, null, null, null, null, KeyCode.ESCAPE, false, false, false, false));
    }

    //拖动窗口
    @FXML
    void setOnMousePressed(MouseEvent event) {
        try {
            Stage primaryStage = (Stage) rightPane.getScene().getWindow();

            oldStageX = primaryStage.getX();
            oldStageY = primaryStage.getY();
            oldScreenX = event.getScreenX();
            oldScreenY = event.getScreenY();
        } catch (Exception e) {
            //do nothing
        }
    }

    //拖动窗口
    @FXML
    void setOnMouseDrag(MouseEvent event) {
        Stage primaryStage = (Stage) rightPane.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
        primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
    }

    //开始按钮按下
    @FXML
    void onStartBtnReleased(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //获取进度信息
        HttpResult result = HttpRequests.getProcessMap(User.getToken());
        if (result.getCode() != 200) {
            //提示框
            String info = "获取进度失败！";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                    ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
            return;
        }
        //清空地图
        Map.clearMap();
        //获取地图信息
        JSONObject mapInfo = result.getData();
        int saved_Level = mapInfo.getInteger("level");
        String saved_Map = mapInfo.getString("map");
        if (saved_Map.isEmpty()) {
            //获取对应地图
            Map.getMap(Integer.parseInt(levelText.getText()));
            System.out.println("关卡:" + Map.mapId + ",无进度");
            goToGamePage();
        } else {
            System.out.println("关卡:" + saved_Level + ",有进度");
            if (saved_Level == Integer.parseInt(levelText.getText())) {
                //提示框
                String info = "是否继续上次游戏？";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                        ButtonBar.ButtonData.YES), new ButtonType("取消", ButtonBar.ButtonData.NO));
                alert.setHeaderText(null);
                alert.setTitle("提示");
                alert.showAndWait();
                //如果是，加载进度
                if (alert.getResult().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                    //获取对应地图
                    System.out.println(saved_Map);
                    Map.parseMap(saved_Map);
                    System.out.println("关卡:" + Map.mapId + ",有进度");
                    goToGamePage();
                    return;
                }
            }
            //否则，重新开始
            Map.getMap(Integer.parseInt(levelText.getText()));
            System.out.println("关卡:" + Map.mapId + ",无进度");
            goToGamePage();
        }
    }

    //左调关卡按钮
    @FXML
    void leftLevelReleased(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //调整关卡
        int level = Integer.parseInt(levelText.getText());
        if (level > 1) {
            level--;
            levelText.setText(String.valueOf(level));
        }
    }

    //右调关卡按钮
    @FXML
    void rightLevelReleased(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //调整关卡
        int level = Integer.parseInt(levelText.getText());
        if (level < User.getLevel()) {
            level++;
            levelText.setText(String.valueOf(level));
        }
    }

    //自定义地图开始按钮
    @FXML
    void onDiyStartBtnReleased(MouseEvent event) throws IOException {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //弹出输入框
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("自定义地图");
        dialog.setHeaderText("请输入地图ID");
        dialog.setContentText("地图信息：");
        //获取输入
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            //检查输入
            if (FormatValidator.isDiyCodeInvalid(result.get())) {
                //提示框
                String info = "地图信息格式错误！";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定",
                        ButtonBar.ButtonData.YES));
                alert.setHeaderText(null);
                alert.setTitle("提示");
                alert.showAndWait();
                return;
            }
            //获取地图信息
            Map map = Map.getDiyMap(result.get());
            System.out.printf("关卡:%d\n", Map.mapId);
            System.out.println(Arrays.deepToString(Map.mapData));

        }
    }

    //切换到游戏场景
    private void goToGamePage() {
        //跳转页面
        Stage primaryStage = (Stage) coinsText.getScene().getWindow();
        //加载fxml文件
        URL url = getClass().getResource("/Game.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
    }
}
