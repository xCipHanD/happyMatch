package asia.sustech.happymatch.GameController;

import asia.sustech.happymatch.NetUtils.HttpRequest;
import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import asia.sustech.happymatch.User;

import java.io.IOException;

public class Map {
    //地图长宽
    public static final int col = 8;
    public static final int row = 8;
    //地图数据
    public static int[][] mapData = new int[row][col];
    //道具使用情况
    public static int swapMapItemUsedCount = 0;
    //目标分数
    public static int targetScore = 0;
    //当前分数
    public static int currentScore = 0;
    //用到方块的数量
    public static int blockCount = 0;
    //当前步骤
    public static int currentStep = 0;
    //最大步骤
    public static int maxStep = 0;
    //mapid
    public static int mapId = 0;
    public static Map mapObject = null;

    private Map() {

    }

    public static Map getMap(int id) {
        //如果当前地图id和传入的地图id不一致，重新生成地图
        if (Map.mapId != id) {
            //从服务器获取地图数据
            HttpResult result = HttpRequests.getMapByID(User.getToken(), id);
            if (result.getCode() != 200) {
                return null;
            }
            //解析地图数据
            String[] mapDataString = result.getData().getString("map").split("\n");
            String[] fistLine = mapDataString[0].split(" ");
            Map.blockCount = Integer.parseInt(fistLine[1]);
            Map.currentStep = Integer.parseInt(fistLine[2]);
            Map.maxStep = Integer.parseInt(fistLine[3]);
            Map.currentScore = Integer.parseInt(fistLine[4]);
            Map.targetScore = Integer.parseInt(fistLine[5]);
            Map.swapMapItemUsedCount = Integer.parseInt(fistLine[6]);
            for (int i = 0; i < 8; i++) {
                String[] line = mapDataString[i + 1].split(" ");
                for (int j = 0; j < 8; j++) {
                    Map.mapData[i][j] = Integer.parseInt(line[j]);
                }
            }
            Map.mapId = id;
            mapObject = new Map();
        }
        return mapObject;
    }

    //获取自定义地图
    public static Map getDiyMap(String code) throws IOException {
        //从服务器获取地图数据
        String result = HttpRequest.sendGetRequest(String.format("/res/diyMaps/" + code));
        if (result.getCode() != 200) {
            return null;
        }
        //解析地图数据
        String[] mapDataString = result.getData().getString("map").split("\n");
        String[] fistLine = mapDataString[0].split(" ");
        Map.blockCount = Integer.parseInt(fistLine[1]);
        Map.currentStep = Integer.parseInt(fistLine[2]);
        Map.maxStep = Integer.parseInt(fistLine[3]);
        Map.currentScore = Integer.parseInt(fistLine[4]);
        Map.targetScore = Integer.parseInt(fistLine[5]);
        Map.swapMapItemUsedCount = Integer.parseInt(fistLine[6]);
        for (int i = 0; i < 8; i++) {
            String[] line = mapDataString[i + 1].split(" ");
            for (int j = 0; j < 8; j++) {
                Map.mapData[i][j] = Integer.parseInt(line[j]);
            }
        }
        mapObject = new Map();
        return mapObject;
    }
}
