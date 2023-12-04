package asia.sustech.happymatch.Login;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginStage extends Application {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;

    public static void main(String[] args) {
        launch(args);  //调用start
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //加载fxml文件
        URL url = getClass().getResource("/LoginStage.fxml");
        //加载完fxml文件后，获取其中的root
        Parent root = FXMLLoader.load(Objects.requireNonNull(url));
        //设置窗体
        primaryStage.setResizable(false);
        primaryStage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //
        //显示窗体
        primaryStage.show();


        //鼠标按下事件(窗口自由拖动)
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                oldStageX = primaryStage.getX();
                oldStageY = primaryStage.getY();
                oldScreenX = event.getScreenX();
                oldScreenY = event.getScreenY();
            }
        });
        //鼠标拖拽(窗口自由拖动)
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //新位置
                //拖拽前后的鼠标差值加上原始窗体坐标值
                primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
                primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
            }
        });
        //esc 退出
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                    //弹出弹框，询问是否退出，如果选择是，则退出
                    String info = "是否退出？";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES), new ButtonType("取消", ButtonBar.ButtonData.NO));
                    alert.setHeaderText(null);
                    alert.setTitle("提示");
                    alert.showAndWait();
                    if (alert.getResult().getButtonData().equals(ButtonBar.ButtonData.YES))
                        System.exit(0);
                }
            }
        });
    }
}
