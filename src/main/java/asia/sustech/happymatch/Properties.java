package asia.sustech.happymatch;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;

import java.util.Optional;

public class Properties {
    public static Item[] properties;

    public void updateProperties(Item item) {
        //跟据item的id更新item的count,如果没有该item则添加
        for (Item property : properties) {
            if (property.getId() == item.getId()) {
                property.setCount(item.getCount());
                return;
            }
        }
        //没有该item则添加
        Item[] newProperties = new Item[properties.length + 1];
        System.arraycopy(properties, 0, newProperties, 0, properties.length);
        newProperties[properties.length] = item;
        properties = newProperties;
    }

    public static void initProperties() {
        //从服务器获取用户的道具信息
    }
}
