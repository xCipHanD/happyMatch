package asia.sustech.happymatch.Login;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.Utils.SoundsPlayer;
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
import java.util.Optional;

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
    private Button register_bt;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord1;

    @FXML
    private ImageView back_img;

    @FXML
    private TextField email;

    @FXML
    void registerBtnPressed(MouseEvent event) {
        //设置透明度
        login_img.setOpacity(0.7);
    }

    @FXML
    void initialize() {
        userName.requestFocus();
    }

    @FXML
    void registerBtnReleased(MouseEvent event) {
        SoundsPlayer.playSound_btnClick1();
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
            //发起注册请求
            Platform.runLater(() -> {
                Optional<HttpResult> result = HttpRequests.register(userName.getText(), passWord.getText(),
                        email.getText());
                result.ifPresentOrElse(httpResult -> {
                    //注册成功
                    if (httpResult.getMessage().equals("注册成功")) {
                        //提示用户结果
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("提示");
                        alert.setHeaderText("注册成功");
                        alert.showAndWait();
                        //跳转到登录界面
                        try {
                            setBack_bt_released(null);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        //注册失败
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(httpResult.getMessage());
                        alert.showAndWait();
                    }
                }, () -> {
                    //网络错误
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("网络错误");
                    alert.showAndWait();
                });
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
        SoundsPlayer.playSound_btnClick1();
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

    @FXML
    void onKeyReleased1(KeyEvent event) {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.TAB) {
            passWord.requestFocus();
        } else if (event.getCode() == KeyCode.ENTER) {
            registerBtnReleased(null);
        }
    }

    @FXML
    void onKeyReleased2(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            passWord1.requestFocus();
        } else if (event.getCode() == KeyCode.ENTER) {
            registerBtnReleased(null);
        }
    }

    @FXML
    void onKeyReleased3(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            email.requestFocus();
        } else if (event.getCode() == KeyCode.ENTER) {
            registerBtnReleased(null);
        }
    }

    @FXML
    void onKeyReleased4(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            userName.requestFocus();
        } else if (event.getCode() == KeyCode.ENTER) {
            registerBtnReleased(null);
        }
    }

}
