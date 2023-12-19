package asia.sustech.happymatch.GameController;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CreateMapTest {
    public static void main(String[] args) {
        String s = "2 3 3 1 0 1 1 2 \n" +
                "1 2 0 0 0 3 4 2 \n" +
                "3 3 2 1 0 1 1 4 \n" +
                "0 3 2 3 0 2 0 3 \n" +
                "0 2 -1 2 2 -1 0 2 \n" +
                "0 2 4 4 3 4 0 3 \n" +
                "3 4 -1 4 2 3 4 4 \n" +
                "2 2 1 -1 4 -1 2 2 \n";
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

    }

}