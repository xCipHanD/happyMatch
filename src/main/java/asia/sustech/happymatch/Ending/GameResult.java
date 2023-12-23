package asia.sustech.happymatch.Ending;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class GameResult extends Application {

    private Stage primaryStage; // 保存主舞台的引用

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // 保存主舞台的引用

        // 加载背景图片
        Image backgroundImage = new Image("file:///path/to/your/image.jpg");

        // 创建背景图像对象
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // 创建根布局容器
        StackPane root = new StackPane();
        root.setBackground(new Background(background));

        // 创建游戏结算界面的控件
        Label resultLabel = new Label("游戏失败！");
        Label scoreLabel = new Label("得分："+1);
        Button playAgainButton = new Button("重新开始");

        // 设置控件的样式和布局
        resultLabel.setStyle("-fx-font-size: 24;");
        scoreLabel.setStyle("-fx-font-size: 18;");
        playAgainButton.setStyle("-fx-font-size: 18;");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(resultLabel, scoreLabel, playAgainButton);

        root.getChildren().add(vbox);

        // 创建场景
        Scene scene = new Scene(root, 800, 600);

        // 设置主舞台的标题和场景
        primaryStage.setTitle("游戏结算");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 播放音乐
        String musicFile = "file:///path/to/your/music.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        // 点击"重新开始"按钮时的事件处理
        playAgainButton.setOnAction(event -> {
            // 关闭当前窗口
            primaryStage.close();
            // 初始化界面
            initGame();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initGame() {
        // 在这里实现初始化界面的逻辑
        Stage stage = new Stage();
        // 创建并设置初始化界面的根布局、控件等
        // ...
        stage.setTitle("初始化界面");
        stage.show();
    }
    public static void launchGameResult() {
        GameResult gameResult = new GameResult();
        gameResult.start(new Stage());
    }
}