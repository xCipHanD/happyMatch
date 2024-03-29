package asia.sustech.happymatch.Hall;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.Utils.SoundsPlayer;
import asia.sustech.happymatch.User;
import com.alibaba.fastjson.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class ShopController {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    @FXML
    private Text desp1;

    @FXML
    private ImageView buyBt;

    @FXML
    private ImageView bg;

    @FXML
    private ImageView coins;

    @FXML
    private Text costText;

    @FXML
    private Text coinsText;

    @FXML
    private ImageView back;

    @FXML
    private Text name1;

    @FXML
    private Text processText;

    //back
    @FXML
    void setBack(MouseEvent event) throws IOException {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        Stage primaryStage = (Stage) back.getScene().getWindow();
        //加载fxml文件
        URL url = getClass().getResource("/Hall.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
    }

    @FXML
    public void initialize() {
        //商店仅作功能展示，无需动态添加物品
        Optional<HttpResult> result = HttpRequests.getItemsList(User.getToken());
        result.ifPresentOrElse(httpResult -> {
            name1.setText(httpResult.getData().getJSONObject("1").getString("name"));
            desp1.setText(httpResult.getData().getJSONObject("1").getString("description"));
            costText.setText("花费 : " + httpResult.getData().getJSONObject("1").getString("price"));
            //设置coins
            coinsText.setText(String.valueOf(User.getCoins()));
            //获取背包
            Optional<HttpResult> result1 = HttpRequests.getProperty(User.getToken());
            result1.ifPresentOrElse(httpResult1 -> {
                JSONObject data = JSONObject.parseObject(httpResult1.getData().toString());
                int count = data.getInteger("count") == null ? 0 : data.getInteger("count");
                //设置process
                processText.setText("已拥有 : " + count);
            }, () -> {
                //网络错误
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("网络错误");
                alert.showAndWait();
            });
        }, () -> {
            //网络错误
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("网络错误");
            alert.showAndWait();
        });
    }

    //购买1号物品（仅作功能展示）
    @FXML
    void buyBtnReleased() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        // 购买
        Optional<HttpResult> result = HttpRequests.buyItem(User.getToken(), 1);
        result.ifPresentOrElse(httpResult -> {
            //判断是否购买成功
            if (httpResult.getMessage().equals("成功")) {
                //购买成功
                //设置coins
                User.setCoins(User.getCoins() - 10);
                coinsText.setText(String.valueOf(User.getCoins()));
                //设置process
                processText.setText("已拥有 : " + JSONObject.parseObject(httpResult.getData().getString("1")).getString(
                        "count"));
            } else {
                //购买失败
                //提示
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("购买失败");
                alert.setHeaderText(null);
                alert.setContentText("金币不足");
                alert.showAndWait();
            }
        }, () -> {
            //网络错误
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("网络错误");
            alert.showAndWait();
        });
    }

    //esc退出
    @FXML
    void setOnKeyPressed(KeyEvent event) {
        try {
            setBack(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //拖动窗口
    @FXML
    void setOnMousePressed(MouseEvent event) {
        try {
            Stage primaryStage = (Stage) back.getScene().getWindow();

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
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
        primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
    }

}
