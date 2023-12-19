package asia.sustech.happymatch.GameController.UnMerged;

public class eliminate {
    private int[][] map;

    //
    //
    public static int countOfTheEliminateTime(int map[][]) {
        int rolTimes = rowScan(map);
        int colTimes = colScan(map);
        return rolTimes + colTimes;
    }

    //
    public static int[][] eliminate(int map[][]) {
        int arr[][] = printWall(map);
        int arr2[][] = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr2[i][j] = arr[i][j];
                arr[i][j] = arr[j][i];
            }
        }
        int arr1[][] = printWall(map);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                //列 aaaaa
                if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2] && arr[i][j] == arr[i][j + 3] && arr[i][j] == arr[i][j + 4]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                    arr1[i][j + 3] = 9;
                    arr1[i][j + 4] = 9;
                }
                //列 aaaa
                if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2] && arr[i][j] == arr[i][j + 3]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                    arr1[i][j + 3] = 9;
                }
                //列 aaa
                if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                }
                //行 aaaaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2] && arr2[i][j] == arr2[i][j + 3] && arr2[i][j] == arr2[i][j + 4]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                    arr1[i][j + 3] = 9;
                    arr1[i][j + 4] = 9;

                }
                //行 aaaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2] && arr2[i][j] == arr2[i][j + 3]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                    arr1[i][j + 3] = 9;
                }
                //行 aaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2]) {
                    arr1[i][j] = 9;
                    arr1[i][j + 1] = 9;
                    arr1[i][j + 2] = 9;
                }
            }
        }
        return arr1;
    }

    //计算有多少分，可以判断是否存在消除，如果存在消除，就可以进行消除
    public static int calcCountsAfterMatches(int[][] map) {
        int count = 0;
        int[][] map1 = map.clone();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // 八连
                if (j < map[i].length - 7 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]
                        && map[i][j] == map[i][j + 6] && map[i][j] == map[i][j + 7]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    map[i][j + 3] = '0';
                    map[i][j + 4] = '0';
                    map[i][j + 5] = '0';
                    map[i][j + 6] = '0';
                    map[i][j + 7] = '0';
                    j += 7;
                    count += 80;
                }
                // 七连
                if (j < map[i].length - 6 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]
                        && map[i][j] == map[i][j + 6]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    map[i][j + 3] = '0';
                    map[i][j + 4] = '0';
                    map[i][j + 5] = '0';
                    map[i][j + 6] = '0';
                    j += 6;
                    count += 70;
                }
                // 六连
                if (j < map[i].length - 5 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4] && map[i][j] == map[i][j + 5]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    map[i][j + 3] = '0';
                    map[i][j + 4] = '0';
                    map[i][j + 5] = '0';
                    j += 5;
                    count += 60;
                }
                // 五连
                if (j < map[i].length - 4 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    map[i][j + 3] = '0';
                    map[i][j + 4] = '0';
                    j += 4;
                    count += 50;
                }
                // 四连
                if (j < map[i].length - 3 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]
                        && map[i][j] == map[i][j + 3]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    map[i][j + 3] = '0';
                    j += 3;
                    count += 40;
                }
                // 三连
                if (j < map[i].length - 2 && map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]) {
                    map[i][j] = '0';
                    map[i][j + 1] = '0';
                    map[i][j + 2] = '0';
                    j += 2;
                    count += 30;
                }
            }
        }

        // 列消除
        for (int i = 0; i < map1[0].length; i++) {
            for (int j = 0; j < map1.length; j++) {
                // 八连
                if (j < map1.length - 7 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i] && map1[j][i] == map1[j + 7][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    map1[j + 3][i] = '0';
                    map1[j + 4][i] = '0';
                    map1[j + 5][i] = '0';
                    map1[j + 6][i] = '0';
                    map1[j + 7][i] = '0';
                    j += 7;
                    count += 80;
                }
                // 七连
                if (j < map1.length - 6 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]
                        && map1[j][i] == map1[j + 6][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    map1[j + 3][i] = '0';
                    map1[j + 4][i] = '0';
                    map1[j + 5][i] = '0';
                    map1[j + 6][i] = '0';
                    j += 6;
                    count += 70;
                }
                // 六连
                if (j < map1.length - 5 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i] && map1[j][i] == map1[j + 5][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    map1[j + 3][i] = '0';
                    map1[j + 4][i] = '0';
                    map1[j + 5][i] = '0';
                    j += 5;
                    count += 60;
                }
                // 五连
                if (j < map1.length - 4 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i] && map1[j][i] == map1[j + 4][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    map1[j + 3][i] = '0';
                    map1[j + 4][i] = '0';
                    j += 4;
                    count += 50;
                }
                // 四连
                if (j < map1.length - 3 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]
                        && map1[j][i] == map1[j + 3][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    map1[j + 3][i] = '0';
                    j += 3;
                }
                // 三连
                if (j < map1.length - 2 && map1[j][i] == map1[j + 1][i] && map1[j][i] == map1[j + 2][i]) {
                    map1[j][i] = '0';
                    map1[j + 1][i] = '0';
                    map1[j + 2][i] = '0';
                    j += 2;
                }

            }
        }
        // 整合
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = (map[i][j] == '0' || map1[i][j] == '0') ? '0' : map[i][j];
            }
        }
        return count;
    }

    //打墙+赋值
    public static int[][] printWall(int[][] map) {
        int[][] bigMap = new int[16][16];//打墙
        int[] index = new int[100];//
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                bigMap[i][j] = 5;//外围打上两层墙
            }
        }
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                bigMap[i][j] = 6;//外围打上两层墙
            }
        }
        for (int i = 2; i < 14; i++) {
            for (int j = 2; j < 14; j++) {
                bigMap[i][j] = 7;//外围打上两层墙
            }
        }
        for (int i = 3; i < 11; i++) {
            System.arraycopy(map[i - 3], 0, bigMap[i], 3, 7);
        }
        return bigMap;
    }

    public static int rowScan(int map[][]) {
        int count = 0;
        int bigMap[][] = printWall(map);
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                //五消aa aa
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    count++;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    count++;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    count++;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    count++;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    count++;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    count++;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    count++;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int colScan(int map[][]) {
        int count = 0;
        int bigMap1[][];
        bigMap1 = printWall(map);
        int bigMap[][] = new int[bigMap1.length][bigMap1.length];
        int arr1[][] = new int[bigMap1.length][bigMap1.length];
        for (int i = 0; i < bigMap1.length; i++) {
            for (int j = 0; j < bigMap1.length; j++) {
                bigMap[i][j] = bigMap1[j][i];
                arr1[i][j] = bigMap[i][j];
            }
        }
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                //五消aa aa
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    count++;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    count++;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    count++;
                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    count++;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    count++;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    count++;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    count++;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    count++;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    count++;
                }
            }
        }
        return count;
    }

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
                    dropIndex = i; // 记录第一个遇到的'0'的位置
                }
            }
        }
        return map;
    }

    //交换方块
    public static void swap(int[][] map, int row1, int col1, int row2, int col2) {
        if (map[row1][col1] != -1 && map[row2][col2] != -1) {
            int temp = map[row1][col1];
            map[row1][col1] = map[row2][col2];
            map[row2][col2] = temp;
        } else {
            map[row2][col2] = map[row2][col2];
            map[row1][col1] = map[row1][col1];
        }
    }
}