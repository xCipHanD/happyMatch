package asia.sustech.happymatch.GameController.UnMerged;

public class stateOfTheMap {

    //判断是否还有可消
    public static boolean stateOfTheMap(int map[][]) {
        if (!stateOfRowScan(map) && !stateOfColScan(map)) {
            return false;
        } else return true;
    }
//行是否可消
    public static boolean stateOfRowScan(int map[][]) {
        boolean flag = false;
        int bigMap[][] = eliminate.printWall(map);
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                //五消aa aa
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    flag = true;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    flag = true;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    flag = true;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    flag = true;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    flag = true;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    flag = true;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    flag = true;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    flag = true;
                }
            }
        }
        return flag;
    }
//列是否可消
    public static boolean stateOfColScan(int map[][]) {
        int bigMap1[][];
        boolean flag = false;
        bigMap1 = eliminate.printWall(map);
        int bigMap[][] = new int[bigMap1.length][bigMap1.length];
        for (int i = 0; i < bigMap1.length; i++) {
            for (int j = 0; j < bigMap1.length; j++) {
                bigMap[i][j] = bigMap1[j][i];
            }
        }
        for (int i = 3; i < 13; i++) {
            for (int j = 3; j < 13; j++) {
                //五消aa aa
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    flag = true;
                }
                //     a
                //五消aa aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j] && bigMap[i + 4][j] == bigMap[i][j]) {
                    flag = true;
                }
                //四消a aa
                //    a
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //四消aa a
                //     a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //     a
                //四消aa a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //    a
                //四消a aa
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消aa a情况
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消a aa情况
                if (bigMap[i][j] == bigMap[i + 2][j] && bigMap[i][j] == bigMap[i + 3][j]) {
                    flag = true;
                }
                //三消a a情况
//                    a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    flag = true;
                }
                //    a
//                三消a a情况
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j]) {
                    flag = true;
                }
                //a
                // aa
                if (bigMap[i][j] == bigMap[i + 1][j - 1] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    flag = true;
                }
                // aa
                //a
                if (bigMap[i][j] == bigMap[i + 1][j + 1] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    flag = true;
                }
                //aa
                //  a
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j + 1]) {
                    flag = true;
                }
                //  a
                //aa
                if (bigMap[i][j] == bigMap[i + 1][j] && bigMap[i][j] == bigMap[i + 2][j - 1]) {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
