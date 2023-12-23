package asia.sustech.happymatch.Ending;


public class EndingController {
    public static int totalScore;
    public static int targetScore;
    public EndingController(int totalScore,int targetScore){
        EndingController.targetScore =targetScore;
        EndingController.totalScore =totalScore;
    }
    public static boolean isItSuccess(int totalScore,int targetScore){
        return totalScore >= targetScore;
    }
    public static void main(String[] args) {
        // 在其他方法中调用launchGameResult()方法来显示游戏结算界面
        //成功界面
        if (isItSuccess(totalScore,targetScore)){
            showGameResult();
        }
        //失败界面
        else GameResult.launchGameResult();
    }

    public static void showGameResult() {
        EndingStage.launchGameResult();
    }
}




