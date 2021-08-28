package com.example.health.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/*
 * 访问接口的方法
 * 可通过该类定义的方法访问接口接收返回值*/
public class InterfaceVisit {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 访问指定端口
    public String post(String path, Map<String, String> parameters) {
        try {
            String resStr = "";
            URL url = new URL(path);
            if (parameters != null) {
                logger.info("访问接口 ==== " + url.toString() + buildGetParameterString(parameters));
                url = new URL(url.toString() + buildGetParameterString(parameters));
            }
            InputStream inputStream = url.openStream();
            int nextByteOfData = 0;
            StringBuffer apiResponseBuffer = new StringBuffer();
            while ((nextByteOfData = inputStream.read()) != -1) {
                apiResponseBuffer.append((char) nextByteOfData);
            }
            resStr = apiResponseBuffer.toString();
            return resStr;
        } catch (IOException e) {
            logger.error("访问接口异常 ==== " + buildGetParameterString(parameters));
            e.printStackTrace();
            return "InterfaceVisit_post_error";
        }

    }

    private static String buildGetParameterString(Map<String, String> parameters) {
        String getParameterString = "";

        for (Map.Entry<String, String> param : parameters.entrySet()) {
            if (param.getValue() == null) {
                continue;
            }

            getParameterString += (getParameterString.length() < 1) ? ("?") : ("&");

            getParameterString += param.getKey() + "=" + param.getValue();
        }

        return (getParameterString);
    }
}
