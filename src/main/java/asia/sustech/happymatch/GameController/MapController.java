package asia.sustech.happymatch.GameController;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
            MapController.getEliminatedMap(map, 0);
        } while (MapController.calcCountsAfterMatches(map) != 0 || Arrays.deepToString(map).contains("0"));
    }

    //填补空白
    public static void fillEmpty(int[][] map, int blockCount) {
        for (int i = 0; i < rol; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = new Random().nextInt(1, blockCount + 1);
                }
            }
        }
    }

    //获得消除后的地图
    // 此处的p为被消除标记，用于特效播放,此值不能与正常方块id重合
    public static void getEliminatedMap(int[][] map, int p) {
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
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    map[i][j + 3] = p;
                    map[i][j + 4] = p;
                    map[i][j + 5] = p;
                    map[i][j + 6] = p;
                    map[i][j + 7] = p;
                    j += 7;
                    continue;
                }
                // 七连
                if (j < map[i].length - 6 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]
                        && map[i][j] == map[i][j + 6]) {
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    map[i][j + 3] = p;
                    map[i][j + 4] = p;
                    map[i][j + 5] = p;
                    map[i][j + 6] = p;
                    j += 6;
                    continue;
                }
                // 六连
                if (j < map[i].length - 5 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]
                        && map[i][j] == map[i][j + 5]) {
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    map[i][j + 3] = p;
                    map[i][j + 4] = p;
                    map[i][j + 5] = p;
                    j += 5;
                    continue;
                }
                // 五连
                if (j < map[i].length - 4 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]) {
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    map[i][j + 3] = p;
                    map[i][j + 4] = p;
                    j += 4;
                    continue;
                }
                // 四连
                if (j < map[i].length - 3 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3]) {
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    map[i][j + 3] = p;
                    j += 3;
                    continue;
                }
                // 三连
                if (j < map[i].length - 2 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]) {
                    map[i][j] = p;
                    map[i][j + 1] = p;
                    map[i][j + 2] = p;
                    j += 2;
                    continue;
                }
            }
        }
        // 列消除
        for (int i = 0; i < map1[0].length; i++) {
            for (int j = 0; j < map1.length; j++) {
                // 为 0 或 -1
                if (map1[j][i] == 0 || map1[j][i] == -1) {
                    continue;
                }
                // 八连
                if (j < map1.length - 7 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i] && map1[j][i] == map1[j + 7][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    map1[j + 3][i] = p;
                    map1[j + 4][i] = p;
                    map1[j + 5][i] = p;
                    map1[j + 6][i] = p;
                    map1[j + 7][i] = p;
                    j += 7;
                    continue;
                }
                // 七连
                if (j < map1.length - 6 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    map1[j + 3][i] = p;
                    map1[j + 4][i] = p;
                    map1[j + 5][i] = p;
                    map1[j + 6][i] = p;
                    j += 6;
                    continue;
                }
                // 六连
                if (j < map1.length - 5 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    map1[j + 3][i] = p;
                    map1[j + 4][i] = p;
                    map1[j + 5][i] = p;
                    j += 5;
                    continue;
                }
                // 五连
                if (j < map1.length - 4 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    map1[j + 3][i] = p;
                    map1[j + 4][i] = p;
                    j += 4;
                    continue;
                }
                // 四连
                if (j < map1.length - 3 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    map1[j + 3][i] = p;
                    j += 3;
                    continue;
                }
                // 三连
                if (j < map1.length - 2 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]) {
                    map1[j][i] = p;
                    map1[j + 1][i] = p;
                    map1[j + 2][i] = p;
                    j += 2;
                    continue;
                }

            }
        }
        // 整合
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = (map[i][j] == p || map1[i][j] == p) ? p : map[i][j];
            }
        }
    }

    public static void delParticleMark(int[][] map, int p) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == p) {
                    map[i][j] = 0;
                }
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
        // 3. 是否有可消除的
        if (MapController.calcCountsAfterMatches(map) != 0) {
            return true;
        }

        return false;
    }

    //存在空白
    public static boolean hasEmpty(int[][] map) {
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //存在可掉落的方块
    public static boolean hasFloatingBlocks(int[][] map) {
        int width = map[0].length;
        int height = map.length;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((map[j][i] != 0 && map[j][i] != -1) && (j + 1 < height && map[j + 1][i] == 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    //下落算法
    public static int[][] fall(int[][] map) {
        int width = map[0].length;
        int height = map.length;

        for (int x = 0; x < width; x++) {
            int emptyY = -1;
            for (int y = height - 1; y >= 0; y--) {
                if (map[y][x] == 0) {
                    if (emptyY == -1) {
                        emptyY = y;
                    }
                } else if (map[y][x] != -1) {
                    if (emptyY != -1) {
                        map[emptyY][x] = map[y][x];
                        map[y][x] = 0;
                        emptyY--;
                    }
                } else if (map[y][x] == -1) {
                    emptyY = -1;
                }
            }
        }
        return map;
    }

    //提示算法
    public static int[][] getTips(int[][] map) {
        //克隆一个map，防止内存污染
        int[][] map1 = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, map1[i], 0, map.length);
        }
        //map预处理
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1.length; j++) {
                map1[i][j] = (map1[i][j] < 10) ? map1[i][j] : map1[i][j] - 10;
            }
        }

        int width = map1[0].length;
        int height = map1.length;
        int[][] directions = {{0, 1}, {1, 0}}; // 只检查右边和下边的方块

        int maxScore = 0;
        int[][] maxScoreSwap = null;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (newX >= 0 && newX < width && newY >= 0 && newY < height && map1[y][x] != -1 && map1[newY][newX] != -1) {
                        int temp = map1[y][x];
                        map1[y][x] = map1[newY][newX];
                        map1[newY][newX] = temp;

                        if (calcCountsAfterMatches(map1) != 0) {
                            int score = calcCountsAfterMatches(map1);
                            if (score > maxScore) {
                                maxScore = score;
                                maxScoreSwap = new int[][]{{y, x}, {newY, newX}};
                            }
                        }
                        temp = map1[y][x];
                        map1[y][x] = map1[newY][newX];
                        map1[newY][newX] = temp;
                    }
                }
            }
        }
        if (maxScoreSwap != null) {
            int[][] tips = new int[2][2];
            tips[0][0] = maxScoreSwap[0][0];
            tips[0][1] = maxScoreSwap[0][1];
            tips[1][0] = maxScoreSwap[1][0];
            tips[1][1] = maxScoreSwap[1][1];
            return tips;
        } else {
            return null;
        }
    }

    public static void shuffle(int[][] mapData) {
        //先将除了-1的方块改为0
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData.length; j++) {
                if (mapData[i][j] != -1) {
                    mapData[i][j] = 0;
                }
            }
        }
        //再生成地图
        MapController.createMap(mapData, Map.blockCount);
    }

    public static boolean saveMap(int[][] mapData, int mapId) {
        String info =
                mapId + " " + Map.blockCount + " " + Map.currentStep + " " + Map.maxStep + " " + Map.currentScore +
                        " " + Map.targetScore + " " + Map.swapMapItemUsedCount + "\\n";
        //将地图数据转换为字符串
        StringBuilder sb = new StringBuilder();
        sb.append(info);
        for (int[] ints : mapData) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\\n");
        }
        //将地图数据上传到服务器
        HttpResult result = HttpRequests.saveMap(User.getToken(), sb.toString());
        return result.getCode() == 200;
    }
}
