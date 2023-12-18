package asia.sustech.happymatch.GameController.UnMerged;

import static asia.sustech.happymatch.GameController.UnMerged.Steps.totalStep;
import static asia.sustech.happymatch.GameController.UnMerged.eliminate.printWall;

public class Score {
    private int level;
    public Score(int level){
        this.level=level;
    }
    public int[] countScore(int map[][]){
        int score[]=new int[totalStep(level)];
        int arr[][]= printWall(map);
        int arr2[][]=new int[arr.length][arr.length];
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
                    score[currentStep]+=50;
                }
                //列 aaaa
                if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2] && arr[i][j] == arr[i][j + 3]) {
                    score[currentStep]+=40;
                }
                //列 aaa
                if (arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i][j + 2]) {
                    score[currentStep]+=30;
                }
                //行 aaaaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2] && arr2[i][j] == arr2[i][j + 3] && arr2[i][j] == arr2[i][j + 4]) {
                    score[currentStep]+=50;

                }
                //行 aaaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2] && arr2[i][j] == arr2[i][j + 3]) {
                    score[currentStep]+=40;
                }
                //行 aaa
                if (arr2[i][j] == arr2[i][j + 1] && arr2[i][j] == arr2[i][j + 2]) {
                    score[currentStep]+=30;
                }
            }
        }
        return score;
    }
    //
    public static int totalScore(int score[]){
        int totalScore=0;
        for (int i = 0; i < score.length; i++) {
            totalScore+=score[i];
        }
        return totalScore;
    }
}
