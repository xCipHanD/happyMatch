package asia.sustech.happymatch.Login;

import asia.sustech.happymatch.Utils.BGMPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class LoginStage extends Application {

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
        //从 resources 中获取图片
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Game/blocks/6" +
                ".png"))));
        //设置场景
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //显示窗体
        primaryStage.show();
        //播放bgm
        BGMPlayer.getInstance().play();
    }
}
