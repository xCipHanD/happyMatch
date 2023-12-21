package asia.sustech.happymatch.GameController;

import asia.sustech.happymatch.Particles.ExplosionEffect;
import asia.sustech.happymatch.Utils.BGMPlayer;
import asia.sustech.happymatch.Utils.SoundsPlayer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;

    @FXML
    private Button swapBtn;

    @FXML
    private Text stepsText;

    @FXML
    private ImageView bg;

    @FXML
    private Button nextStepBtn;

    @FXML
    private ImageView back;

    @FXML
    private VBox chessboard;

    @FXML
    private ImageView bgmBt;

    @FXML
    private ImageView prop2;

    @FXML
    private ImageView prop1;

    @FXML
    private Text levelText;

    @FXML
    private Text scoreText;

    @FXML
    private Button tipsBtn;

    @FXML
    private ImageView swapImg2;

    @FXML
    private ToggleButton autoModeBtn;

    @FXML
    private GridPane board;

    @FXML
    private ImageView swapImg1;

    @FXML
    private ImageView cb;
    //选中的方块
    private int selectedBlockX1 = -1;
    private int selectedBlockY1 = -1;
    private int selectedBlockX2 = -1;
    private int selectedBlockY2 = -1;

    //自动模式
    private boolean autoMode = false;

    //背景音乐控制按钮
    @FXML
    void bgmBtPressed(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //切换背景音乐
        if (BGMPlayer.getInstance().isMute()) {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Game/v_on.png"))));
            BGMPlayer.getInstance().setMute(false);
        } else {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Game/v_off.png"))));
            BGMPlayer.getInstance().setMute(true);
        }
    }

    //初始化
    @FXML
    void initialize() {
//        //弹出成功提示框
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("提示");
//        alert.setHeaderText("成功");
//        alert.setContentText(Map.mapId + "");
//        alert.showAndWait();
        //初始化文字
        //声音按钮
        if (BGMPlayer.getInstance().isMute()) {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Game/v_off.png"))));
        } else {
            bgmBt.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Game/v_on.png"))));
        }
        if (Map.mapId != 0) {
            levelText.setText("第" + Map.mapId + "关");
        } else {
            levelText.setText("自定义关卡");
        }
        scoreText.setText(String.format("分数 : %s/%s", Map.currentScore, Map.targetScore));
        stepsText.setText(String.format("剩余 %s 步", Map.maxStep - Map.currentStep));
        //绑定点击事件
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getPaneByGridPaneCoordinates(board, i, j).setOnMouseReleased(this::blockBtnReleased);
            }
        }
        //生成地图
        MapController.createMap(Map.mapData, Map.blockCount);
        //打印地图
        for (int i = 0; i < Map.mapData.length; i++) {
            for (int j = 0; j < Map.mapData.length; j++) {
                System.out.printf(Map.mapData[i][j] + " ");
            }
            System.out.println();
        }
        //渲染地图
        System.out.println(Arrays.deepToString(Map.mapData));
        Render(Map.mapData);
    }

    private void blockBtnReleased(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick2();

        //获取点击的方块的坐标
        int row = GridPane.getRowIndex((Node) event.getSource()) == null ? 0 :
                GridPane.getRowIndex((Node) event.getSource());
        int col = GridPane.getColumnIndex((Node) event.getSource()) == null ? 0 :
                GridPane.getColumnIndex((Node) event.getSource());
        //选择的逻辑,如果二者不在一起，就取消前者的选择
        if (Map.mapData[row][col] != -1 && Map.mapData[row][col] != 0) {
            if (selectedBlockX1 == -1 && selectedBlockY1 == -1) {
                selectedBlockX1 = row;
                selectedBlockY1 = col;
                changeBlockState(row, col);
            } else if (selectedBlockX2 == -1 && selectedBlockY2 == -1 && (Math.abs(selectedBlockX1 - row) + Math.abs(selectedBlockY1 - col) == 1)) {
                selectedBlockX2 = row;
                selectedBlockY2 = col;
                changeBlockState(row, col);
            } else {
                changeBlockState(selectedBlockX1, selectedBlockY1);
                changeBlockState(selectedBlockX2, selectedBlockY2);
                selectedBlockX1 = row;
                selectedBlockY1 = col;
                selectedBlockX2 = -1;
                selectedBlockY2 = -1;
                changeBlockState(row, col);
            }
        }
        //渲染地图
        Render(Map.mapData);
    }

    //切换方块状态
    private void changeBlockState(int row, int col) {
        if (row == -1 || col == -1) {return;}
        if (Map.mapData[row][col] != 0 && Map.mapData[row][col] != -1) {
            if (Map.mapData[row][col] < 10) {
                Map.mapData[row][col] += 10;
            } else {
                Map.mapData[row][col] -= 10;
            }
        }
    }

    //自动按钮按下
    @FXML
    void autoModeBtnReleased() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //切换自动模式
        autoMode = !autoMode;
        //切换文字
        if (autoMode) {
            autoModeBtn.setText("自动 开");
        } else {
            autoModeBtn.setText("自动 关");
        }
    }

    //交换按钮按下
    @FXML
    void swapBtnReleased() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //如果有下一步，则提示用户点击下一步按钮
        if (MapController.hasNextStep(Map.mapData)) {
            //提示
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("提示");
//            alert.setHeaderText("请点击下一步按钮");
//            alert.showAndWait();
//            return;
            swapBtn.setDisable(true);
            tipsBtn.setDisable(true);
            nextStepBtn.setDisable(false);
        }
        //如果没有选中两个方块，就不执行
        if (selectedBlockX1 == -1 || selectedBlockY1 == -1 || selectedBlockX2 == -1 || selectedBlockY2 == -1) {
            //提示
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("请选择两个方块");
            alert.showAndWait();
            return;
        }
        //重置位置和图片
        swapImg1.setLayoutX(0);
        swapImg1.setLayoutY(0);
        swapImg2.setLayoutX(0);
        swapImg2.setLayoutY(0);
        swapImg1.setImage(null);
        swapImg2.setImage(null);
        // 对选择进行排序
        if (selectedBlockX1 > selectedBlockX2 || selectedBlockY1 > selectedBlockY2) {
            int temp = selectedBlockX1;
            selectedBlockX1 = selectedBlockX2;
            selectedBlockX2 = temp;
            temp = selectedBlockY1;
            selectedBlockY1 = selectedBlockY2;
            selectedBlockY2 = temp;
        }
        //存入临时变量
        int temp1 = Map.mapData[selectedBlockX1][selectedBlockY1];
        int temp2 = Map.mapData[selectedBlockX2][selectedBlockY2];
        // 取消选择
        changeBlockState(selectedBlockX1, selectedBlockY1);
        changeBlockState(selectedBlockX2, selectedBlockY2);
        // 渲染地图
        Render(Map.mapData);
        // 复制ImageView控件
        swapImg1.setImage(getImageViewByGridPaneCoordinates(board, selectedBlockX1, selectedBlockY1).getImage());
        swapImg2.setImage(getImageViewByGridPaneCoordinates(board, selectedBlockX2, selectedBlockY2).getImage());
        ImageView imageView1 = getImageViewByGridPaneCoordinates(board, selectedBlockX1, selectedBlockY1);
        ImageView imageView2 = getImageViewByGridPaneCoordinates(board, selectedBlockX2, selectedBlockY2);
        // 清空原有ImageView控件
        Map.mapData[selectedBlockX1][selectedBlockY1] = 0;
        Map.mapData[selectedBlockX2][selectedBlockY2] = 0;
        //渲染地图
        Render(Map.mapData);
        //设置可视
        swapImg1.setVisible(true);
        swapImg2.setVisible(true);
        // 创建TranslateTransition动画，设置持续时间和目标位置
        TranslateTransition transition1 = new TranslateTransition(Duration.millis(400), swapImg1);
        transition1.setFromX(getRelativeBoundsToWindow(imageView1).getMinX());
        transition1.setFromY(getRelativeBoundsToWindow(imageView1).getMinY());
        transition1.setToX(getRelativeBoundsToWindow(imageView2).getMinX());
        transition1.setToY(getRelativeBoundsToWindow(imageView2).getMinY());
        transition1.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition transition2 = new TranslateTransition(Duration.millis(400), swapImg2);
        transition2.setFromX(getRelativeBoundsToWindow(imageView2).getMinX());
        transition2.setFromY(getRelativeBoundsToWindow(imageView2).getMinY());
        transition2.setToX(getRelativeBoundsToWindow(imageView1).getMinX());
        transition2.setToY(getRelativeBoundsToWindow(imageView1).getMinY());
        transition2.setInterpolator(Interpolator.EASE_BOTH);
        // 锁定按钮
        swapBtn.setDisable(true);
        tipsBtn.setDisable(true);
        // 播放动画
        transition1.play();
        transition2.play();
        // 创建计数器，用于跟踪完成的动画数量
        AtomicInteger animationCount = new AtomicInteger();
        // 设置动画完成时的回调函数
        transition1.setOnFinished(e -> {
            animationCount.getAndIncrement();
        });
        transition2.setOnFinished(e -> {
            animationCount.getAndIncrement();
            //动画结束判断
            if (animationCount.get() == 2) {
                //交换方块
                Map.mapData[selectedBlockX1][selectedBlockY1] = temp2;
                Map.mapData[selectedBlockX2][selectedBlockY2] = temp1;
                //渲染交换后的地图
                Render(Map.mapData);
                //如果没有可以消除的方块，就交换回来
                if (MapController.calcCountsAfterMatches(Map.mapData) == 0) {
                    //重置位置和图片
                    swapImg1.setLayoutX(0);
                    swapImg1.setLayoutY(0);
                    swapImg2.setLayoutX(0);
                    swapImg2.setLayoutY(0);
                    swapImg1.setImage(null);
                    swapImg2.setImage(null);
                    // 对选择进行排序
                    if (selectedBlockX1 > selectedBlockX2 || selectedBlockY1 > selectedBlockY2) {
                        int temp = selectedBlockX1;
                        selectedBlockX1 = selectedBlockX2;
                        selectedBlockX2 = temp;
                        temp = selectedBlockY1;
                        selectedBlockY1 = selectedBlockY2;
                        selectedBlockY2 = temp;
                    }
                    //存入临时变量
                    int temp3 = Map.mapData[selectedBlockX1][selectedBlockY1];
                    int temp4 = Map.mapData[selectedBlockX2][selectedBlockY2];
                    // 取消选择
                    changeBlockState(selectedBlockX1, selectedBlockY1);
                    changeBlockState(selectedBlockX2, selectedBlockY2);
                    // 渲染地图
                    Render(Map.mapData);
                    // 复制ImageView控件
                    swapImg1.setImage(getImageViewByGridPaneCoordinates(board, selectedBlockX1, selectedBlockY1).getImage());
                    swapImg2.setImage(getImageViewByGridPaneCoordinates(board, selectedBlockX2, selectedBlockY2).getImage());
                    ImageView imageView3 = getImageViewByGridPaneCoordinates(board, selectedBlockX1, selectedBlockY1);
                    ImageView imageView4 = getImageViewByGridPaneCoordinates(board, selectedBlockX2, selectedBlockY2);
                    // 清空原有ImageView控件
                    Map.mapData[selectedBlockX1][selectedBlockY1] = 0;
                    Map.mapData[selectedBlockX2][selectedBlockY2] = 0;
                    //渲染地图
                    Render(Map.mapData);
                    //设置可视
                    swapImg1.setVisible(true);
                    swapImg2.setVisible(true);
                    // 创建TranslateTransition动画，设置持续时间和目标位置
                    TranslateTransition transition3 = new TranslateTransition(Duration.millis(400), swapImg1);
                    transition3.setFromX(getRelativeBoundsToWindow(imageView3).getMinX());
                    transition3.setFromY(getRelativeBoundsToWindow(imageView3).getMinY());
                    transition3.setToX(getRelativeBoundsToWindow(imageView4).getMinX());
                    transition3.setToY(getRelativeBoundsToWindow(imageView4).getMinY());
                    transition3.setInterpolator(Interpolator.EASE_BOTH);

                    TranslateTransition transition4 = new TranslateTransition(Duration.millis(400), swapImg2);
                    transition4.setFromX(getRelativeBoundsToWindow(imageView4).getMinX());
                    transition4.setFromY(getRelativeBoundsToWindow(imageView4).getMinY());
                    transition4.setToX(getRelativeBoundsToWindow(imageView3).getMinX());
                    transition4.setToY(getRelativeBoundsToWindow(imageView3).getMinY());
                    transition4.setInterpolator(Interpolator.EASE_BOTH);
                    // 锁定按钮
                    swapBtn.setDisable(true);
                    tipsBtn.setDisable(true);
                    // 播放动画
                    transition3.play();
                    transition4.play();
                    // 创建计数器，用于跟踪完成的动画数量
                    AtomicInteger animationCount1 = new AtomicInteger();
                    // 设置动画完成时的回调函数
                    transition3.setOnFinished(ev -> {
                        animationCount1.getAndIncrement();
                    });
                    transition4.setOnFinished(ev -> {
                        animationCount1.getAndIncrement();
                        //动画结束判断
                        if (animationCount1.get() == 2) {
                            //交换方块
                            Map.mapData[selectedBlockX1][selectedBlockY1] = temp4;
                            Map.mapData[selectedBlockX2][selectedBlockY2] = temp3;
                            //渲染交换后的地图
                            Render(Map.mapData);
                            //重置选择
                            selectedBlockX1 = -1;
                            selectedBlockY1 = -1;
                            selectedBlockX2 = -1;
                            selectedBlockY2 = -1;
                            //重置位置和图片
                            swapImg1.setLayoutX(0);
                            swapImg1.setLayoutY(0);
                            swapImg2.setLayoutX(0);
                            swapImg2.setLayoutY(0);
                            swapImg1.setImage(null);
                            swapImg2.setImage(null);
                            //设置不可视
                            swapImg1.setVisible(false);
                            swapImg2.setVisible(false);
                            //解锁按钮
                            swapBtn.setDisable(false);
                            tipsBtn.setDisable(false);
                            System.out.println("完成");
//                            //打印地图
//                            for (int i = 0; i < Map.mapData.length; i++) {
//                                for (int j = 0; j < Map.mapData.length; j++) {
//                                    System.out.printf(Map.mapData[i][j] + " ");
//                                }
//                                System.out.println();
//                            }
                        }
                    });
                } else {
                    //计算分数和步数
                    int scoreToBeAdded = MapController.calcCountsAfterMatches(Map.mapData);
                    Map.currentScore += scoreToBeAdded;
                    Map.currentStep++;
                    //更新文字
                    updateText();
                    //播放音效
                    SoundsPlayer.playSound_match();
                    //消除方块
                    MapController.getEliminatedMap(Map.mapData, 0);
                    //播放特效
                    for (int i = 0; i < Map.mapData.length; i++) {
                        for (int j = 0; j < Map.mapData[0].length; j++) {
                            if (Map.mapData[i][j] == 0) {
                                ExplosionEffect.playParticleEffect(board, i, j);
                            }
                        }
                    }
                    //渲染地图
                    Render(Map.mapData);
                    //重置选择
                    selectedBlockX1 = -1;
                    selectedBlockY1 = -1;
                    selectedBlockX2 = -1;
                    selectedBlockY2 = -1;
                    //重置位置和图片
                    swapImg1.setLayoutX(0);
                    swapImg1.setLayoutY(0);
                    swapImg2.setLayoutX(0);
                    swapImg2.setLayoutY(0);
                    swapImg1.setImage(null);
                    swapImg2.setImage(null);
                    //设置不可视
                    swapImg1.setVisible(false);
                    swapImg2.setVisible(false);
                    //解锁按钮
                    nextStepBtn.setDisable(false);
                    //如果自动模式开启，则自动点击重复点击下一步按钮，间隔200ms。如果下一步按钮被禁用了，则停止
                    if (autoMode) {
                        new Thread(() -> {
                            while (true) {
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException event) {
                                    event.printStackTrace();
                                }
                                if (nextStepBtn.isDisabled() || Map.currentScore >= Map.targetScore || Map.currentStep >= Map.maxStep) {
                                    nextStepBtnReleased();
                                    break;
                                }
                                nextStepBtnReleased();
                            }
                        }).start();
                    }
                    System.out.println("完成");
                }
            }
        });
    }

    public static Bounds getRelativeBoundsToWindow(Node node) {
        Bounds nodeBounds = node.getBoundsInLocal();
        double relativeX = node.localToScene(nodeBounds.getMinX(), nodeBounds.getMinY()).getX();
        double relativeY = node.localToScene(nodeBounds.getMinX(), nodeBounds.getMinY()).getY();
        double relativeWidth = nodeBounds.getWidth();
        double relativeHeight = nodeBounds.getHeight();
        return new BoundingBox(relativeX, relativeY, relativeWidth, relativeHeight);
    }

    void Render(int[][] map) {
        //打印地图
        System.out.println("渲染后的地图");
        for (int i = 0; i < Map.mapData.length; i++) {
            for (int j = 0; j < Map.mapData.length; j++) {
                System.out.printf(Map.mapData[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < Map.row; i++) {
            for (int j = 0; j < Map.col; j++) {
                ImageView imageView = getImageViewByGridPaneCoordinates(board, i, j);
                switch (map[i][j]) {
                    case -1:
                        imageView.setImage(Blocks.ICE.getImage());
                        break;
                    case 1:
                        imageView.setImage(Blocks.B1.getImage());
                        break;
                    case 2:
                        imageView.setImage(Blocks.B2.getImage());
                        break;
                    case 3:
                        imageView.setImage(Blocks.B3.getImage());
                        break;
                    case 4:
                        imageView.setImage(Blocks.B4.getImage());
                        break;
                    case 5:
                        imageView.setImage(Blocks.B5.getImage());
                        break;
                    case 6:
                        imageView.setImage(Blocks.B6.getImage());
                        break;
                    case 11:
                        imageView.setImage(Blocks.B11.getImage());
                        break;
                    case 12:
                        imageView.setImage(Blocks.B12.getImage());
                        break;
                    case 13:
                        imageView.setImage(Blocks.B13.getImage());
                        break;
                    case 14:
                        imageView.setImage(Blocks.B14.getImage());
                        break;
                    case 15:
                        imageView.setImage(Blocks.B15.getImage());
                        break;
                    case 16:
                        imageView.setImage(Blocks.B16.getImage());
                        break;
                    case 0:
                    default:
                        imageView.setImage(null);
                        break;
                }
            }
        }
    }

    public static ImageView getImageViewByGridPaneCoordinates(GridPane gridPane, int row, int col) {
        ImageView imageView = null;

        // 获取GridPane中指定坐标的Pane
        Pane pane = getPaneByGridPaneCoordinates(gridPane, row, col);

        // 在Pane中找到ImageView
        if (pane != null) {
            for (Node node : pane.getChildren()) {
                if (node instanceof ImageView) {
                    imageView = (ImageView) node;
                    break;
                }
            }
        }

        return imageView;
    }

    private static Pane getPaneByGridPaneCoordinates(GridPane gridPane, int row, int col) {
        Pane result = null;

        // 遍历GridPane中的所有节点
        for (Node node : gridPane.getChildren()) {
            int rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            int colIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            // 检查节点是否在指定的行和列上
            if (rowIndex == row && colIndex == col) {
                if (node instanceof Pane) {
                    result = (Pane) node;
                }
                break;
            }
        }

        return result;
    }

    //esc退出
    @FXML
    void setOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            //询问是否退出
            String info = "是否退出？";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES)
                    , new ButtonType("取消", ButtonBar.ButtonData.NO));
            alert.setHeaderText(null);
            alert.setTitle("提示");
            alert.showAndWait();
            if (alert.getResult().getButtonData().equals(ButtonBar.ButtonData.YES))
                System.exit(0);
        }
    }

    //拖动窗口
    @FXML
    void setOnMousePressed(MouseEvent event) {
        try {
            Stage primaryStage = (Stage) board.getScene().getWindow();

            oldStageX = primaryStage.getX();
            oldStageY = primaryStage.getY();
            oldScreenX = event.getScreenX();
            oldScreenY = event.getScreenY();
        } catch (Exception e) {
            //do nothing
        }
    }

    //拖动窗口
    @FXML
    void setOnMouseDrag(MouseEvent event) {
        Stage primaryStage = (Stage) board.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - oldScreenX + oldStageX);
        primaryStage.setY(event.getScreenY() - oldScreenY + oldStageY);
    }

    @FXML
    public void bgmBtPressed() {}

    @FXML
    public void setBack() {
    }

    @FXML
    public void nextStepBtnReleased() {
        //播放音效
//        SoundsPlayer.playSound_btnClick1();
        //取消选择
        changeBlockState(selectedBlockX1, selectedBlockY1);
        changeBlockState(selectedBlockX2, selectedBlockY2);
        //重置选择
        selectedBlockX1 = -1;
        selectedBlockY1 = -1;
        selectedBlockX2 = -1;
        selectedBlockY2 = -1;
        //如果可以下落，就下落
        if (MapController.hasFloatingBlocks(Map.mapData)) {
            System.out.println("下落");
            MapController.fall(Map.mapData);
            //渲染地图
            Render(Map.mapData);
            //播放音效
            SoundsPlayer.playSound_fall();
        } else if (MapController.calcCountsAfterMatches(Map.mapData) != 0) {//如果可以消除，就消除
            //计算分数
            int scoreToBeAdded = MapController.calcCountsAfterMatches(Map.mapData);
            Map.currentScore += scoreToBeAdded;
            //更新文字
            updateText();
            //播放音效
            SoundsPlayer.playSound_match();
            //消除方块
            int particleMark = -2;
            MapController.getEliminatedMap(Map.mapData, particleMark);//-2为特效标记
            //播放特效
            for (int i = 0; i < Map.mapData.length; i++) {
                for (int j = 0; j < Map.mapData[0].length; j++) {
                    if (Map.mapData[i][j] == particleMark) {
                        ExplosionEffect.playParticleEffect(board, i, j);
                    }
                }
            }
            //消除特效标记
            MapController.delParticleMark(Map.mapData, particleMark);
            //渲染地图
            Render(Map.mapData);
            //打印地图
            System.out.println("消除后的地图");
            for (int i = 0; i < Map.mapData.length; i++) {
                for (int j = 0; j < Map.mapData.length; j++) {
                    System.out.printf(Map.mapData[i][j] + " ");
                }
                System.out.println();
            }
        } else if (MapController.hasEmpty(Map.mapData)) {//是否需要生成新的方块
            System.out.println("生成新的方块");
            MapController.fillEmpty(Map.mapData, Map.blockCount);
            //渲染地图
            Render(Map.mapData);
        } else {//如果没有可以下落的方块，就提示用户点击交换按钮
            //TODO 判断地图是否可玩，道具是否可用
            if (MapController.getTips(Map.mapData) == null) {
                //提示
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText("没有可以消除的方块，请使用道具");
                    alert.showAndWait();
                });
            }
        }
        //判断是否有下一步
        if (MapController.hasNextStep(Map.mapData)) {
            nextStepBtn.setDisable(false);
            swapBtn.setDisable(true);
            tipsBtn.setDisable(true);
        } else {
            nextStepBtn.setDisable(true);
            swapBtn.setDisable(false);
            tipsBtn.setDisable(false);
        }
        //判断是否完成
        if (Map.currentScore >= Map.targetScore) {
            //提示
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("恭喜你，你完成了本关");
                alert.showAndWait();
            });
        } else if (Map.currentStep >= Map.maxStep) {

            Platform.runLater(() -> {
                //禁止按钮
                nextStepBtn.setDisable(true);
                swapBtn.setDisable(true);
                tipsBtn.setDisable(true);
                //提示
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText("很遗憾，你没有完成本关");
                alert.showAndWait();
            });

        }
    }

    //更新文字
    public void updateText() {
        //更新文字
        scoreText.setText(String.format("分数 : %s/%s", Map.currentScore, Map.targetScore));
        stepsText.setText(String.format("剩余 %s 步", Map.maxStep - Map.currentStep));
    }

    @FXML
    void tipsBtnReleased() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //获取提示
        int[][] tips = MapController.getTips(Map.mapData);
        if (tips == null) {
            //提示
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("没有可以消除的方块，请使用道具");
            alert.showAndWait();
            return;
        } else {
            //取消选择
            changeBlockState(selectedBlockX1, selectedBlockY1);
            changeBlockState(selectedBlockX2, selectedBlockY2);
            //重置选择
            selectedBlockX1 = tips[0][0];
            selectedBlockY1 = tips[0][1];
            selectedBlockX2 = tips[1][0];
            selectedBlockY2 = tips[1][1];
            //切换方块状态
            changeBlockState(selectedBlockX1, selectedBlockY1);
            changeBlockState(selectedBlockX2, selectedBlockY2);
            //播放特效
            ExplosionEffect.playParticleEffect(board, selectedBlockX1, selectedBlockY1);
            ExplosionEffect.playParticleEffect(board, selectedBlockX2, selectedBlockY2);
            //渲染地图
            Render(Map.mapData);
        }
    }
}
