package asia.sustech.happymatch.GameController.UnMerged;


public class tips {
    public static int[] tips(int map[][]) {
        int row[][] = rowScan(map);
        int col[][] = colScan(map);
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row.length; j++) {
                if (row[i][j] == 9) {
                    count++;
                }
                if (col[i][j] == 9) {
                    count++;
                }
            }
        }
        int tips[][] = new int[count][2];
        int count1 = 0;
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row.length; j++) {
                if (row[i][j] == 9) {
                    tips[count1][0] = i;
                    tips[count1][1] = j;
                    count1++;
                }
                if (col[i][j] == 9) {
                    tips[count1][0] = i;
                    tips[count1][1] = j;
                    count1++;
                }
            }
        }
        int tip[] = new int[2];
        tip[0] = tips[0][0];
        tip[1] = tips[0][1];
        return tip;
    }

    public static int[][] rowScan(int map[][]) {
        int arr1[][] = eliminate.printWall(map);
        int bigMap[][] = eliminate.printWall(map);
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                //五消aa aa
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                    arr1[i + 3][j] = 9;
                    arr1[i + 4][j] = 9;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                    arr1[i + 3][j] = 9;
                    arr1[i + 4][j] = 9;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                    arr1[i + 3][j] = 9;

                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                    arr1[i + 3][j] = 9;

                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j + 1] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;

                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 3][j] = 9;

                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;

                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j - 1] = 9;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j + 1] = 9;
                    arr1[i + 2][j + 1] = 9;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                }
            }
        }
        return arr1;
    }

    public static int[][] colScan(int map[][]) {
        int[][] bigMap1;
        bigMap1 = eliminate.printWall(map);
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
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                    arr1[i + 3][j] = 9;
                    arr1[i + 4][j] = 9;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                    arr1[i + 3][j] = 9;
                    arr1[i + 4][j] = 9;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                    arr1[i + 3][j] = 9;
                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                    arr1[i + 3][j] = 9;
                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j + 1] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;
                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 3][j] = 9;
                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 2][j] = 9;
                    arr1[i + 3][j] = 9;
                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j] = 9;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j - 1] = 9;
                    arr1[i + 2][j - 1] = 9;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j + 1] = 9;
                    arr1[i + 2][j + 1] = 9;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j + 1] = 9;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    arr1[i][j] = 9;
                    arr1[i + 1][j] = 9;
                    arr1[i + 2][j - 1] = 9;
                }
            }
        }
        return arr1;
    }
}
