package asia.sustech.happymatch.GameController;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MapControllerTest {
    public static void main(String[] args) {
        String s = "2 3 2 3 -1 2 2 3 \n" +
                "3 1 1 2 2 1 2 1 \n" +
                "1 3 -1 3 2 4 -1 3 \n" +
                "3 3 2 2 4 3 2 2 \n" +
                "-1 1 2 3 2 4 1 1 \n" +
                "4 -1 1 4 1 2 4 3 \n" +
                "4 2 2 1 3 4 2 2 \n" +
                "2 3 2 4 2 2 4 2 \n";
        Scanner sc = new Scanner(s);
        int[][] map = new int[8][8];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = sc.nextInt();
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(MapController.calcCountsAfterMatches(map));
//        MapController.getEliminatedMap(map, 0);
//        System.out.println();
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                System.out.printf("%d ", map[i][j]);
//            }
//            System.out.println();
//        }
    }

}