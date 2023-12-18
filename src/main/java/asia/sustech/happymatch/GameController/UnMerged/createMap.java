package asia.sustech.happymatch.GameController.UnMerged;

import java.util.Random;

public class createMap {
    Random random = new Random();//随机生成
    private final int rol = 8;//8*8的地图
    private final int col = 8;//

    //
    public int getRol() {
        return rol;
    }

    public int getCol() {
        return col;
    }

    public int map[][] = new int[rol][col];

    //创造地图
    public int[][] createMap(int map[][]) {
        //尝试地图库
        int allMap[][][] = new int[100][rol][col];
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < rol; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = random.nextInt(1, 4);
                    } else map[i][j] = -1;
                }
            }
            allMap[k] = map;//存入尝试地图库
        }
        return getBestMap(allMap);
    }

    public int[][] getMap() {
        return map;
    }

    //寻找最佳地图
    public static int[][] getBestMap(int[][][] allMap) {
        int count[] = new int[100];
        int bestMap[][] = new int[8][8];
        for (int i = 0; i < 100; i++) {
            int map[][] = new int[8][8];
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if (map[j][k] == -1) {
                        map[j][k] = -1;
                    } else map[j][k] = allMap[i][j][k];
                }
            }
            // 对每个二维数组执行你的操作
            while (eliminate.canBeEliminate(map)) {
                map = eliminate.dropArray(eliminate.eliminate(map));
            }
            count[i] = eliminate.countOfTheEliminateTime(map);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                bestMap[i][j] = allMap[findTheIndexOfMaxValue(count)][i][j];
            }
        }
        return bestMap;
    }
//寻找数字最大的数以及其位置
    public static int findTheIndexOfMaxValue(int[] array) {
        int max = Integer.MIN_VALUE;  // 当前的最大值
        int maxIndex = -1;  // 最大值的索引
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
