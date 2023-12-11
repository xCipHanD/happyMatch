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
    }
}
