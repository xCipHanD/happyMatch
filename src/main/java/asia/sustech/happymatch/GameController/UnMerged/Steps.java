package asia.sustech.happymatch.GameController.UnMerged;

public class Steps {
    private int level;
    //构建level（关卡级别）
    public Steps(int level){
        this.level=level;
    }
    //计算各关总步数
    public static int totalStep(int level){
        int totalStepOfEachLevel[]=new int[level];
        int totalStep=25;
        for (int i = 0; i < level; i++) {
            totalStepOfEachLevel[i]=totalStep-level/5;
        }
        return totalStepOfEachLevel[level];
    }
    //计算游戏当前步数
    public static int currentStep(int level){
        int currentStep=0;
        for (int i = 0; i < totalStep(level); i++) {
            currentStep=i+1;
        }
        return currentStep;
    }
}
