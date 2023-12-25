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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

public class ForgetPwdController {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;
    private boolean isCodeSent;

    @FXML
    private PasswordField passWord;

    @FXML
    private TextField code;

    @FXML
    private ImageView getCode_img;

    @FXML
    private ImageView back;

    @FXML
    private Button resetPwd_bt;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord1;

    @FXML
    private TextField email;

    @FXML
    private Button getCode_bt;

    @FXML
    private ImageView resetPwd_img;

    @FXML
    void onResetPwdBtPressed(MouseEvent event) {
        //设置透明度
        resetPwd_img.setOpacity(0.7);
    }

    @FXML
    void onResetPwdBtReleased(MouseEvent event) {
        SoundsPlayer.playSound_btnClick1();
        //设置透明度
        resetPwd_img.setOpacity(1);
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
        if (FormatValidator.isCodeInvalid(code.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("验证码格式错误,请重新输入");
            alert.showAndWait();
            return;
        }
        if (!isCodeSent) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("请先获取验证码");
            alert.showAndWait();
            return;
        }
        //修改密码
        Thread thread = new Thread(() -> {
            //发起修改密码请求
            Platform.runLater(() -> {
                Optional<HttpResult> result = HttpRequests.changePwd(email.getText(), passWord.getText(),
                        code.getText());
                result.ifPresentOrElse(httpResult -> {
                    if (httpResult.getCode() == 200) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCESS");
                        alert.setHeaderText("密码修改成功");
                        alert.showAndWait();
                        //返回主窗口
                        try {
                            setBack(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
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
    void setBack(MouseEvent event) throws IOException {
        SoundsPlayer.playSound_btnClick1();
        Stage primaryStage = (Stage) back.getScene().getWindow();
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
    void onGetCodeBtPressed() {
        //设置透明度
        getCode_img.setOpacity(0.7);
    }

    @FXML
    void onGetCodeBtReleased() {
        SoundsPlayer.playSound_btnClick1();
        //设置透明度
        getCode_img.setOpacity(1);
        //格式判断
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
                //发送验证码
                Optional<HttpResult> result = HttpRequests.getCode(email.getText());
                result.ifPresentOrElse(httpResult -> {
                    if (httpResult.getCode() == 200) {
                        isCodeSent = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCESS");
                        alert.setHeaderText("验证码已发送");
                        alert.showAndWait();
                    } else {
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
    void setOnKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            setBack(null);
        }
    }

    @FXML
    void setOnMousePressed(MouseEvent event) {
        Stage primaryStage = (Stage) back.getScene().getWindow();

        oldStageX = primaryStage.getX();
        oldStageY = primaryStage.getY();
        oldScreenX = event.getScreenX();
        oldScreenY = event.getScreenY();
    }

    @FXML
    void setOnMouseDrag(MouseEvent event) {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
        primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
    }
}
