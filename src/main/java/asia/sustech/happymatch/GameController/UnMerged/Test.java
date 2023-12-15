package project;

import java.util.Arrays;
//9-->要消去的方块
//1,2,3,4-->方块
//5,6,7-->墙
//-1-->死方块
public class Test {
    public static void main(String[] args) {
        createMap createMap=new createMap();
        System.out.println(Arrays.deepToString(createMap.allMap));
    }
}
