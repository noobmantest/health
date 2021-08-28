package com.example.health.test;


import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.tools.*;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class TestSendEmail {

    public void sendEmail(String fromEmail, String fromEmailAuthorizationCode, String toEmail, String title,
                          String message) throws GeneralSecurityException, MessagingException {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host", "smtp.qq.com");

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromEmailAuthorizationCode);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com", fromEmail, fromEmailAuthorizationCode);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(fromEmail));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        //邮件标题
        mimeMessage.setSubject(title);

        //邮件内容
        mimeMessage.setContent(message, "text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }

    public static void main(String[] args) throws MessagingException, GeneralSecurityException {
        String message = "<!DOCTYPE html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<meta name=\"viewport\" content=\"width=350, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0, user-scalable=no;\" />\n" +
                "<META HTTP-EQUIV=\"pragma\" CONTENT=\"no-cache\">\n" +
                "<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache, must-revalidate\">\n" +
                "<META HTTP-EQUIV=\"expires\" CONTENT=\"Wed, 26 Feb 2007 08:21:57 GMT\">\n" +
                "<title>郑州大学数据中台2020</title>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://jksb.v.zzu.edu.cn/imagesss/_xwss_2017.css\" /><style>\n" +
                "#bak_0 {MARGIN:0 auto; left: 0;WIDTH: 350px; min-HEIGHT:540px;}\n" +
                "\n" +
                "</style>\n" +
                "<script Language=\"javascript\">\n" +
                "  function zzjCallOneUrl(us){\n" +
                "    document.getElementById(\"zzj_fun_426s\").src=us;\n" +
                "  }\n" +
                "  \n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"POST\" name=\"myform52\" action=\"\">\n" +
                "<div id=\"bak_0\">  \n" +
                "  <div style=\"width:100%;height:30px;\"></div>\n" +
                "  <div style=\"width:100%;height:260px;\"><div style=\"width:15px;height:100%;float:left;\"></div><div style=\"width:318px;height:100%;float:left;background-color: #F2F2F2;border:1px solid #aaa;\"><div style=\"width:100%;height:15px;\"></div><div style=\"width:100%;height:186px;\"><div style=\"width:10px;height:100%;float:left;\"></div><div style=\"width:296px;height:100%;font-size:14px;color:#333;line-height:26px;float:left;\">　　谷争昌同学，感谢你今日上报健康状况！申新生书记、李宗坤院长将对上报状况进行审核。记着明天继续来报。</div><div style=\"width:10px;height:100%;float:right;\"></div></div><div style=\"width:100%;height:12px;\"></div><div style=\"width:100%;height:32px;\"><div style=\"width:117px;height:100%;float:left;\"></div><div style=\"width:80px;height:100%;font-size:14px;color:#fbff81;float:left;line-height:32px;font-family:黑体;BACKGROUND-color:#b50000;text-align:center;\" \n" +
                " onclick=\"window.location='https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/endok?uid=20177720415'\">确认</div><div style=\"width:117px;height:100%;float:right;\"></div></div><div style=\"width:100%;height:15px;\"></div></div><div style=\"width:15px;height:100%;float:right;\"></div></div><div style=\"width:100%;height:26px;line-height:26px;font-size:12px;color:#555;text-align:center;\"><br />郑大疫情防控领导小组、信息化办公室自主研制，版权所有</div>  \n" +
                "  \n" +
                "</div>\n" +
                "\n" +
                "<div style=\"width:1px;height:1px;\">\n" +
                "  <iframe name=\"zzj_fun_426\" id=\"zzj_fun_426s\" src=\"https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/getsomething?ptopid=sF3E961E8FC724DED8C024FE84E05AAC4&sid=210826094616741434\" marginwidth=\"0\" marginheight=\"0\" height=\"100%\"\n" +
                "    width=\"100%\" scrolling=\"no\" border=\"0\" frameborder=\"0\" allowtransparency=\"true\"></iframe>\n" +
                "</div>\n" +
                "<input type=\"hidden\" name=\"ptopid\" value=\"sF3E961E8FC724DED8C024FE84E05AAC4\"><input type=\"hidden\" name=\"sid\" value=\"210826094616741952\"></form>\n" +
                "\n" +
                "<div style=\"width:100%;height:50px;\"></div>\n" +
                "</body>\n" +
                "</html>\n" +
                "success";

        User user = new User();
        user.setUser("20177720415");
        user.setPassword("07024612");
        user.setDays(30);
        user.setToday("0");
        user.setAddress("河南省郑州市");
        user.setCity_code("410100");
        String res = new AutoPunchOperate().autoPunch(user);
        System.out.println(res);
        new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                "1945716435@qq.com", "测试发送HTML格式邮件", res);
    }
}