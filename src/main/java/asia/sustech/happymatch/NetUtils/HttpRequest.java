package asia.sustech.happymatch.NetUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class HttpRequest {

    // 忽略SSL证书验证
    static {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 发送GET请求
    public static String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取输入流
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // 读取响应内容
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    // 发送POST请求
    public static String sendPostRequest(String url, String data) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl.openConnection();

            // 设置请求方法为POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 写入请求数据
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取输入流
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // 读取响应内容
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    public static void main(String[] args) {
        try {
            // 例子：发送GET请求
            String getResponse = sendGetRequest("https://api.sustech.online/weather");
            System.out.println("GET Response: " + getResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
