package asia.sustech.happymatch.GameController;

import asia.sustech.happymatch.GameController.Map;
import asia.sustech.happymatch.Login.ForgetPwdStage;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GameStage extends Application {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;

    public static void main(String[] args) {
        //加载界面
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        //forest初始化

        //加载fxml文件
        URL url = getClass().getResource("/Game.fxml");
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

        // 获取FXML文件中的VBox（假设VBox是根节点，你可以根据实际情况修改）
        VBox vbox = (VBox) root.lookup("#chessboard");

        // 创建按钮并添加到布局中
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        vbox.getChildren().addAll(button1, button2, button3);

        // 设置舞台标题和场景
        primaryStage.setTitle("Button Slide Animation");
        primaryStage.setScene(scene);

        // 显示舞台
        primaryStage.show();

        // 执行按钮滑动动画
        slideButton(button1);
        slideButton(button2);
        slideButton(button3);
    }
    private void slideButton(Button button) {
        // 创建一个 TranslateTransition 动画，设置按钮从上方滑动到下方
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), button);
        transition.setByY(300); // 设置垂直方向的偏移量
        transition.play();
    }

}
