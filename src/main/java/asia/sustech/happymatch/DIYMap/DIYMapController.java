package asia.sustech.happymatch.DIYMap;

import asia.sustech.happymatch.GameController.Blocks;
import asia.sustech.happymatch.GameController.GameController;
import asia.sustech.happymatch.GameController.Map;
import asia.sustech.happymatch.Utils.SoundsPlayer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DIYMapController {
    @FXML
    private ImageView bg;

    @FXML
    private ImageView back;

    @FXML
    private VBox chessboard;

    @FXML
    private ImageView bgmBt;

    @FXML
    private ImageView b1;

    @FXML
    private ImageView b2;

    @FXML
    private ImageView b3;

    @FXML
    private ImageView b4;

    @FXML
    private ImageView b5;

    @FXML
    private ImageView b6;

    @FXML
    private ImageView b_ice;

    @FXML
    private ImageView b_cross;

    @FXML
    private HBox prop;

    @FXML
    private ImageView swapImg2;

    @FXML
    private GridPane board;

    @FXML
    private ImageView swapImg1;

    @FXML
    private ImageView cb;

    @FXML
    private Text blockCountsText;

    @FXML
    private TextField stepCounts;

    @FXML
    private TextField targetCounts;

    private int maxBlockCount;

    @FXML
    void setOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void setOnMouseDrag(MouseEvent event) {

    }

    @FXML
    void setOnMousePressed(MouseEvent event) {

    }

    @FXML
    void setBack(MouseEvent event) {

    }

    @FXML
    void bgmBtPressed(MouseEvent event) {

    }

    @FXML
    void prop1BtnReleased(MouseEvent event) {

    }

    @FXML
    void prop2BtnReleased(MouseEvent event) {

    }

    private int selectedBlockX = -1;
    private int selectedBlockY = -1;

    @FXML
    void initialize() {
        //初始化空地图
        Map.clearMap();
        //渲染地图
        Render(Map.mapData);
        //设置方块点击事件
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                GameController.getPaneByGridPaneCoordinates(board, i, j).setOnMouseReleased(this::blockBtnReleased);
            }
        }
        //初始化文字
        updateBlockText();
    }


    private void blockBtnReleased(MouseEvent event) {
        //播放音效
        SoundsPlayer.playSound_btnClick2();

        //获取点击的方块的坐标
        int row = GridPane.getRowIndex((Node) event.getSource()) == null ? 0 :
                GridPane.getRowIndex((Node) event.getSource());
        int col = GridPane.getColumnIndex((Node) event.getSource()) == null ? 0 :
                GridPane.getColumnIndex((Node) event.getSource());
//        System.out.printf("row:%d col:%d\n", row, col);
        //如果点击的是同一个方块
        if (row == selectedBlockX && col == selectedBlockY) {
            changeBlockState(row, col);
            //取消选中
            selectedBlockX = -1;
            selectedBlockY = -1;
        } else {
            changeBlockState(selectedBlockX, selectedBlockY);
            //切换选中方块
            selectedBlockX = row;
            selectedBlockY = col;
            changeBlockState(row, col);
        }
        //渲染地图
        Render(Map.mapData);
    }

    //切换方块状态
    private void changeBlockState(int row, int col) {
        if (row == -1 || col == -1) {return;}
//        System.out.println(row + " " + col);
        if (Map.mapData[row][col] < 7) {
            Map.mapData[row][col] += 10;
        } else {
            Map.mapData[row][col] -= 10;
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

    void Render(int[][] map) {
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
                    case 9:
                        imageView.setImage(Blocks.B9.getImage());
                        break;
                    case 10:
                        imageView.setImage(Blocks.B10.getImage());
                        break;
                    case 0:
                    default:
                        imageView.setImage(null);
                        break;
                }
            }
        }
    }

    public static Pane getPaneByGridPaneCoordinates(GridPane gridPane, int row, int col) {
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

    @FXML
    void p1() {setProp(11);}

    @FXML
    void p2() {setProp(12);}

    @FXML
    void p3() {setProp(13);}

    @FXML
    void p4() {setProp(14);}

    @FXML
    void p5() {setProp(15);}

    @FXML
    void p6() {setProp(16);}

    @FXML
    void p7() {setProp(9);}

    @FXML
    void cross() {setProp(10);}

    void setProp(int prop) {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        //更新地图
        Map.mapData[selectedBlockX][selectedBlockY] = prop;
        //刷新地图
        Render(Map.mapData);
        //更新文字
        updateBlockText();
    }

    void updateBlockText() {
        maxBlockCount = 4;
        for (int i = 0; i < Map.mapData.length; i++) {
            for (int j = 0; j < Map.mapData.length; j++) {
                int current = Map.mapData[i][j] < 7 ? Map.mapData[i][j] : Map.mapData[i][j] - 10;
                if (current > 0 && current < 7 && current > maxBlockCount) {
                    maxBlockCount = current;
                }
            }
        }
        blockCountsText.setText(String.format("已启用 : %s", maxBlockCount));
    }

    @FXML
    void launchBtn() {
        //播放音效
        SoundsPlayer.playSound_btnClick1();
        boolean invalidInput = false;
        //检查输入
        if (stepCounts.getText().isEmpty() || targetCounts.getText().isEmpty()) {
            invalidInput = true;
        }
        try {
            boolean flag1 = Integer.parseInt(stepCounts.getText()) <= 0;
            boolean flag2 = Integer.parseInt(stepCounts.getText()) >= 999;
            boolean flag3 = Integer.parseInt(targetCounts.getText()) <= 0;
            boolean flag4 = Integer.parseInt(targetCounts.getText()) >= 99999;
            if (flag1 || flag2 || flag3 || flag4) {
                invalidInput = true;
            }
        } catch (Exception ignored) {
            invalidInput = true;
        }
        if (invalidInput) {//提示
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("请输入正确的步数和目标分数");
            alert.showAndWait();
            return;
        }
        //构建地图数据
        
    }
}
