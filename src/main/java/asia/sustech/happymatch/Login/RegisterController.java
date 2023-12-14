package asia.sustech.happymatch.Login;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.Utils.FormatValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class RegisterController {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    @FXML
    private PasswordField passWord;

    @FXML
    private Button back_bt;

    @FXML
    private ImageView login_img;

    @FXML
    private Button login_bt;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord1;

    @FXML
    private ImageView back_img;

    @FXML
    private TextField email;

    @FXML
    void setLogin_bt_pressed(MouseEvent event) {
        //设置透明度
        login_img.setOpacity(0.7);
    }

    @FXML
    void setLogin_bt_released(MouseEvent event) {
        //设置透明度
        login_img.setOpacity(1);
        //格式判断
        if (FormatValidator.isUserNameInvalid(userName.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("用户名格式错误,应为4-10位字母或数字");
            alert.showAndWait();
            return;
        }
        if (FormatValidator.isPasswordInvalid(passWord.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("密码格式错误,应为6-16位字母或数字");
            alert.showAndWait();
            return;
        }
        if (!passWord1.getText().equals(passWord.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("两次密码不一致,请重新输入");
            alert.showAndWait();
            return;
        }
        if (FormatValidator.isEmailInvalid(email.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("邮箱格式错误,请重新输入");
            alert.showAndWait();
            return;
        }
        Thread thread = new Thread(() -> {
            //发起登录请求
            Platform.runLater(() -> {
                HttpResult result = HttpRequests.register(userName.getText(), passWord.getText(), email.getText());
                String info;
                //提示用户结果
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(result.getMessage());
                alert.showAndWait();
            });
        });

        thread.start();
    }

    @FXML
    void setBack_bt_pressed(MouseEvent event) {
        //设置透明度
        back_img.setOpacity(0.7);
    }

    @FXML
    void setBack_bt_released(MouseEvent event) throws IOException {
        //设置透明度
        back_img.setOpacity(1);
        Stage primaryStage = (Stage) back_bt.getScene().getWindow();
        //加载fxml文件
        URL url = getClass().getResource("/LoginStage.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
    }

    @FXML
    void setOnKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            setBack_bt_released(null);
        }
    }

    @FXML
    void setOnMousePressed(MouseEvent event) {
        Stage primaryStage = (Stage) back_img.getScene().getWindow();

        oldStageX = primaryStage.getX();
        oldStageY = primaryStage.getY();
        oldScreenX = event.getScreenX();
        oldScreenY = event.getScreenY();
    }

    @FXML
    void setOnMouseDrag(MouseEvent event) {
        Stage primaryStage = (Stage) back_img.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
        primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
    }
}
