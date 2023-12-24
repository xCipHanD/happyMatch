package asia.sustech.happymatch.Ending;

import asia.sustech.happymatch.Hall.HallController;
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



public class EndingStage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 加载图片
        Image backgroundImage = new Image("C:\\Users\\myx\\Desktop\\happyMatch\\src\\main\\resources\\Game\\end.jpg");

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
        Label resultLabel = new Label("游戏成功！");

        Label scoreLabel = new Label("得分：");//加上你的得分

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
        String musicFile = "C:\\Users\\myx\\Desktop\\happyMatch\\src\\main\\resources\\Sounds\\endMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        // 点击"重新开始"按钮时的事件处理
        playAgainButton.setOnAction(event -> {
            // 关闭当前窗口
            primaryStage.close();
            // 初始化界面
            initGame();
            mediaPlayer.stop();
        });

    }











    //重点





    //返回你写的主界面
    public void initGame() {
        // 创建并设置初始化界面的根布局、控件等
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Label titleLabel = new Label("初始化界面");
        titleLabel.setStyle("-fx-font-size: 24;");

        Button startButton = new Button("开始游戏");
        startButton.setStyle("-fx-font-size: 18;");
        startButton.setOnAction(event -> {
            HallController hallController=new HallController();
            hallController.goToGamePage();
        });

        root.getChildren().addAll(titleLabel, startButton);

        // 创建场景
        Scene scene = new Scene(root, 800, 600);

        // 创建新的主舞台
        Stage stage = new Stage();
        stage.setTitle("初始化界面");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void launchGameResult() {
        EndingStage gameResult = new EndingStage();
        gameResult.start(new Stage());
    }
}