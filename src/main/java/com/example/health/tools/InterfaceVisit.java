package com.example.health.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class InterfaceVisit {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 访问指定端口
    public String post(String path,Map<String, String> parameters) throws IOException{
        String resStr = "";

        URL url = new URL(path);
        if (parameters != null) {
            logger.info("访问接口 ==== " + url.toString() + buildGetParameterString(parameters));
//            System.out.println(url.toString() + buildGetParameterString(parameters));

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
    }
    private static String buildGetParameterString(Map<String, String> parameters)
    {
        String getParameterString = "";

        for(Map.Entry<String, String> param : parameters.entrySet())
        {
            if(param.getValue() == null)
            {
                continue;
            }

            getParameterString += (getParameterString.length() < 1) ? ("?") : ("&");

            getParameterString += param.getKey() + "=" + param.getValue();
        }

        return (getParameterString);
    }
}
