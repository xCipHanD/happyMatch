package asia.sustech.happymatch;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ImageView register_img;
    public ImageView login_img;
    public Text clear;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userName;
    @FXML
    private ImageView close_window;
    @FXML
    private Button register_bt;
    @FXML
    private Button login_bt;
    @FXML
    private Text forget_pwd;

    //清空文本
    @FXML
    void setClear(MouseEvent event) {
        userName.setText("");
        passWord.setText("");
    }
    //退出游戏
    @FXML
    void setClose_window(MouseEvent event){
        String info = "是否退出？";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES), new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert.setHeaderText(null);
        alert.setTitle("提示");
        alert.showAndWait();
        if (alert.getResult().getButtonData().equals(ButtonBar.ButtonData.YES))
            System.exit(0);
    }
    //登录按钮按下
    @FXML
    void setLogin_bt_pressed(MouseEvent event) {
        //图片框透明度变化
        login_img.setOpacity(0.7);
    }
    //登录按钮松开
    @FXML
    void  setLogin_bt_released(MouseEvent event){
        //图片框透明度变化
        login_img.setOpacity(1);
        String username = userName.getText();
        String password = passWord.getText();
        if (username.isEmpty() || password.isEmpty()) {
            String info = "用户名或密码不能为空！";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
        } else {
            //登录成功
            String info = "登录成功！";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
        }
    }
    //注册按钮按下
    @FXML
    void setRegister_bt_pressed(MouseEvent event) {
        //图片框透明度变化
        register_img.setOpacity(0.7);
    }
    //注册按钮松开
    @FXML
    void setRegister_bt_released(MouseEvent event) {
        //图片框透明度变化
        register_img.setOpacity(1);
        //跳转注册页面


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //设置按钮图片
    }
}