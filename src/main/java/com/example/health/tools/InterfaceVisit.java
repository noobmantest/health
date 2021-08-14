package com.example.health.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class InterfaceVisit {

    public static void main(String[] args) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("user", "20177720415");
            map.put("password", "07024612");

//            System.out.println(post("http://www.baidu.com",map));
            String res = new InterfaceVisit().post("http://192.168.123.175:8888/login", map);
            System.out.println(res);
            //报错测试
            //System.out.println(post("https://api.ssllabs.com",null));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 访问指定端口
    public String post(String path,Map<String, String> parameters) throws IOException{
        String resStr = "";

        URL url = new URL(path);
        if (parameters != null) {
            System.out.println(url.toString() + buildGetParameterString(parameters));

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
