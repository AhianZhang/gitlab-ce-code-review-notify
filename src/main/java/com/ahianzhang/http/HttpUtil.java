package com.ahianzhang.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/12/21 6:46 PM
 **/
public class HttpUtil {
    public static void post(String data) throws Exception {
        URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=cce265517886dac508bcde78cacdccf52dca845920f29758ce4721b85f4e48e8");
        URLConnection con = url.openConnection();
    System.out.println(data);
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        byte[] out =  data.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
    }
}
