package asia.sustech.happymatch.NetUtils;

import static org.junit.jupiter.api.Assertions.*;
class HttpRequestsTest {
    public static void main(String[] args) {
        HttpResult[] result = new HttpResult[4];
        result[0] = HttpRequests.login("admin","123456");
        result[1] = HttpRequests.login("ad@min","123456");
        result[2] = HttpRequests.login("admin","6");
        result[3] = HttpRequests.login("admin123123123123123123123132","123456");
        for (HttpResult httpResult : result) {
            System.out.printf("%s %s %s\n",httpResult.getCode(),httpResult.getData(),httpResult.getMessage());
        }

    }
  
}