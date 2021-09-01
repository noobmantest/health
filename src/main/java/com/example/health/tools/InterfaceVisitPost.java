package com.example.health.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 通过post方式访问接口
 * 解决中文乱码
 * 提供map转json格式方法
 * */
public class InterfaceVisitPost {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String post(String strURL, Map<String, String> map) {
        System.out.println(map + "=============" + strURL);
        try {
            String params = map2Json(map);
            BufferedReader reader = null;
            logger.info("访问接口 ==== " + strURL + " ==== " + params);
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (IOException e) {
            logger.error("访问接口异常，捕捉异常 ==== " + strURL + " ==== ");
            e.printStackTrace();
            return "InterfaceVisitPost_post_error";
        }


    }

    public String map2Json(Map<String, String> map) {
        JSONObject jsonObj = new JSONObject(map);
        return jsonObj.toString();
    }
}
