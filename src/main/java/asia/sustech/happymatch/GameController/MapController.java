package asia.sustech.happymatch.GameController;

import java.util.Arrays;
import java.util.Random;

public class MapController {
    private static final int rol = 8;//8*8的地图
    private static final int col = 8;//8*8的地图

    public static void createMap(int[][] map, int blockCount) {
        do {
            for (int i = 0; i < rol; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = new Random().nextInt(1, blockCount + 1);
                    }
                }
            }
            MapController.getEliminatedMap(map);
        } while (MapController.calcCountsAfterMatches(map) != 0 || Arrays.deepToString(map).contains("0"));
    }

    //获得消除后的地图
    public static void getEliminatedMap(int[][] map) {
        //map预处理
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = (map[i][j] < 10) ? map[i][j] : map[i][j] - 10;
            }
        }
        int[][] map1 = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, map1[i], 0, map.length);
        }
        // 行消除
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // 为 0 或 -1
                if (map[i][j] == 0 || map[i][j] == -1) {
                    continue;
                }
                // 八连
                if (j < map[i].length - 7 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]
                        && map[i][j] == map[i][j + 6] && map[i][j] == map[i][j + 7]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    map[i][j + 3] = 0;
                    map[i][j + 4] = 0;
                    map[i][j + 5] = 0;
                    map[i][j + 6] = 0;
                    map[i][j + 7] = 0;
                    j += 7;
                    continue;
                }
                // 七连
                if (j < map[i].length - 6 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]
                        && map[i][j] == map[i][j + 6]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    map[i][j + 3] = 0;
                    map[i][j + 4] = 0;
                    map[i][j + 5] = 0;
                    map[i][j + 6] = 0;
                    j += 6;
                    continue;
                }
                // 六连
                if (j < map[i].length - 5 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]
                        && map[i][j] == map[i][j + 5]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    map[i][j + 3] = 0;
                    map[i][j + 4] = 0;
                    map[i][j + 5] = 0;
                    j += 5;
                    continue;
                }
                // 五连
                if (j < map[i].length - 4 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    map[i][j + 3] = 0;
                    map[i][j + 4] = 0;
                    j += 4;
                    continue;
                }
                // 四连
                if (j < map[i].length - 3 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    map[i][j + 3] = 0;
                    j += 3;
                    continue;
                }
                // 三连
                if (j < map[i].length - 2 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    map[i][j + 2] = 0;
                    j += 2;
                    continue;
                }
            }
        }
        // 列消除
        for (int i = 0; i < map1[0].length; i++) {
            for (int j = 0; j < map1.length; j++) {
                // 为 0 或 -1
                if (map[j][i] == 0 || map[j][i] == -1) {
                    continue;
                }
                // 八连
                if (j < map1.length - 7 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i] && map1[j][i] == map1[j + 7][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    map1[j + 3][i] = 0;
                    map1[j + 4][i] = 0;
                    map1[j + 5][i] = 0;
                    map1[j + 6][i] = 0;
                    map1[j + 7][i] = 0;
                    j += 7;
                    continue;
                }
                // 七连
                if (j < map1.length - 6 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    map1[j + 3][i] = 0;
                    map1[j + 4][i] = 0;
                    map1[j + 5][i] = 0;
                    map1[j + 6][i] = 0;
                    j += 6;
                    continue;
                }
                // 六连
                if (j < map1.length - 5 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    map1[j + 3][i] = 0;
                    map1[j + 4][i] = 0;
                    map1[j + 5][i] = 0;
                    j += 5;
                    continue;
                }
                // 五连
                if (j < map1.length - 4 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    map1[j + 3][i] = 0;
                    map1[j + 4][i] = 0;
                    j += 4;
                    continue;
                }
                // 四连
                if (j < map1.length - 3 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    map1[j + 3][i] = 0;
                    j += 3;
                    continue;
                }
                // 三连
                if (j < map1.length - 2 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]) {
                    map1[j][i] = 0;
                    map1[j + 1][i] = 0;
                    map1[j + 2][i] = 0;
                    j += 2;
                    continue;
                }

            }
        }
        // 整合
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = (map[i][j] == 0 || map1[i][j] == 0) ? 0 : map[i][j];
            }
        }
    }

    //计算有多少分，可以判断是否存在消除，如果存在消除，就可以进行消除
    public static int calcCountsAfterMatches(int[][] map) {
        //map预处理
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = (map[i][j] < 10) ? map[i][j] : map[i][j] - 10;
            }
        }
        // 消除
        int count = 0;
        for (int[] ints : map) {
            for (int j = 0; j < ints.length; j++) {
                // 为 0 或 -1
                if (ints[j] == 0 || ints[j] == -1) {
                    continue;
                }
                // 八连
                if (j < ints.length - 7 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2]
                        && ints[j] == ints[j + 3] && ints[j] == ints[j + 4] && ints[j] == ints[j + 5]
                        && ints[j] == ints[j + 6] && ints[j] == ints[j + 7] && ints[j] > 0) {
                    j += 7;
                    count += 80;
                    continue;
                }
                // 七连
                if (j < ints.length - 6 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2]
                        && ints[j] == ints[j + 3] && ints[j] == ints[j + 4] && ints[j] == ints[j + 5]
                        && ints[j] == ints[j + 6] && ints[j] > 0) {
                    j += 6;
                    count += 70;
                    continue;
                }
                // 六连
                if (j < ints.length - 5 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2]
                        && ints[j] == ints[j + 3] && ints[j] == ints[j + 4] && ints[j] == ints[j + 5]
                        && ints[j] > 0) {
                    j += 5;
                    count += 60;
                    continue;
                }
                // 五连
                if (j < ints.length - 4 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2]
                        && ints[j] == ints[j + 3] && ints[j] == ints[j + 4] && ints[j] > 0) {
                    j += 4;
                    count += 50;
                    continue;
                }
                // 四连
                if (j < ints.length - 3 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2]
                        && ints[j] == ints[j + 3] && ints[j] > 0) {
                    j += 3;
                    count += 40;
                    continue;
                }
                // 三连
                if (j < ints.length - 2 && ints[j] == ints[j + 1] && ints[j] == ints[j + 2] && ints[j] > 0) {
                    j += 2;
                    count += 30;
                    continue;
                }
            }
        }
        // 列消除
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                // 为 0 或 -1
                if (map[j][i] == 0 || map[j][i] == -1) {
                    continue;
                }
                // 八连
                if (j < map.length - 7 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]
                        && map[j][i] == map[j + 3][i] && map[j][i] == map[j + 4][i] && map[j][i] == map[j + 5][i]
                        && map[j][i] == map[j + 6][i] && map[j][i] == map[j + 7][i]) {
                    j += 7;
                    count += 80;
                    continue;
                }
                // 七连
                if (j < map.length - 6 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]
                        && map[j][i] == map[j + 3][i] && map[j][i] == map[j + 4][i] && map[j][i] == map[j + 5][i]
                        && map[j][i] == map[j + 6][i]) {
                    j += 6;
                    count += 70;
                    continue;
                }
                // 六连
                if (j < map.length - 5 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]
                        && map[j][i] == map[j + 3][i] && map[j][i] == map[j + 4][i]
                        && map[j][i] == map[j + 5][i]) {
                    j += 5;
                    count += 60;
                    continue;
                }
                // 五连
                if (j < map.length - 4 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]
                        && map[j][i] == map[j + 3][i] && map[j][i] == map[j + 4][i]) {
                    j += 4;
                    count += 50;
                    continue;
                }
                // 四连
                if (j < map.length - 3 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]
                        && map[j][i] == map[j + 3][i]) {
                    j += 3;
                    count += 40;
                    continue;
                }
                // 三连
                if (j < map.length - 2 && map[j][i] == map[j + 1][i] && map[j][i] == map[j + 2][i]) {
                    j += 2;
                    count += 30;
                    continue;
                }

            }
        }
        return count;
    }

    //是否有下一步
    public static boolean hasNextStep(int[][] map) {
        // 1. 前置检查：目标是否达成，步数是否用完，是否有道具
        if (Map.currentScore >= Map.targetScore || Map.currentStep >= Map.maxStep || Map.swapMapItemUsedCount >= 3) {
            return false;
        }
        // 2. 是否有空位
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    //掉落算法
    public static int[][] dropArray(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        for (int j = 0; j < cols; j++) {
            int dropIndex = -1; // 记录要下落到的位置

            for (int i = rows - 1; i >= 0; i--) {
                if (map[i][j] != 9 || map[i][j] != -1) {
                    if (dropIndex != -1) {
                        map[dropIndex][j] = map[i][j]; // 下落到dropIndex位置
                        map[i][j] = 9; // 当前位置置0
                        dropIndex--; // dropIndex向上移动
                    }
                } else if (dropIndex == -1) {
                    dropIndex = i; // 记录第一个遇到的0的位置
                }
            }
        }
        return map;
    }

    //存在可掉落的方块
    public static boolean hasDrop(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        for (int j = 0; j < cols; j++) {
            int dropIndex = -1; // 记录要下落到的位置

            for (int i = rows - 1; i >= 0; i--) {
                if (map[i][j] != 0 || map[i][j] != -1) {
                    if (dropIndex != -1) {
                        return true;
                    }
                } else if (dropIndex == -1) {
                    dropIndex = i; // 记录第一个遇到的0的位置
                }
            }
        }
        return false;
    }
}
