package project;

import java.util.Random;

public class createMap {
    Random random = new Random();//随机生成
    private final int rol = 10;//10*10的地图
    private final int col = 10;//

    //
    public int getRol() {
        return rol;
    }

    public int getCol() {
        return col;
    }

    public int map[][] = new int[rol][col];
    //尝试地图库
    public int allMap[][][] = new int[100][rol][col];

    //创造地图
    public createMap() {
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < rol; i++) {
                for (int j = 0; j < col; j++) {
                    map[i][j] = random.nextInt(1, 4);
                }
            }
            allMap[k] = map;//存入尝试地图库
        }
        this.map=getBestMap(allMap);
    }

    public int[][] getMap() {
        return map;
    }

    //
    public static int[][] getBestMap(int[][][] allMap) {
        int count[] = new int[100];
        int bestMap[][] = new int[10][10];
        for (int i = 0; i < 100; i++) {
            int map[][] = new int[10][10];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    map[j][k] = allMap[i][j][k];
                }
            }
            // 对每个二维数组执行你的操作
            while (eliminate.canBeEliminate(map)) {
                map = eliminate.dropArray(eliminate.eliminate(map));
            }
            count[i] = eliminate.countOfTheEliminateTime(map);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                bestMap[i][j] = allMap[findTheIndexOfMaxValue(count)][i][j];
            }
        }
        return bestMap;
    }

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
