package asia.sustech.happymatch.GameController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class GameController {

    @FXML
    private ImageView bg;

    @FXML
    private VBox chessboard;

    @FXML
    private ImageView cb;

    @FXML
    private GridPane board;

    //初始化
    @FXML
    void initialize() {
        //弹出成功提示框
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText("成功");
        alert.setContentText(Map.mapId + "");
        alert.showAndWait();
        //渲染地图
        System.out.println(Arrays.deepToString(Map.mapData));
        Render(Map.mapData);
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
}
