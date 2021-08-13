package com.example.health.punch;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class AutoScript {

    public static String autoScript(String user, String password) {

        System.setProperty("webdriver.gecko.driver", "/usr/firefox/geckodriver");
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\gzc\\Desktop\\upload\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        // 设置无界面
        options.addArguments("--headless");
        // 设置TLS1.0
        options.addPreference("security.tls.version.min", 1);

        System.out.println("准备加载。。。");
        WebDriver driver = new FirefoxDriver(options);

        System.out.println("加载网页ing");

        driver.get("https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/first0");
//        driver.findElement(By.id("enableTls10Button")).click();
        System.out.println(driver.getCurrentUrl());

        //是否弹出安全页面
        WebElement element = null;
        try {
            element = driver.findElement(By.id("details-button"));
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
//        System.out.println(element);
        // 如果不为空则跳过安全相关
        if (element != null) {
            element.click();
            driver.findElement(By.linkText("继续前往jksb.v.zzu.edu.cn（不安全）")).click();
        }

        System.out.println("登录页面加载完成");
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        WebElement confirmCode = null;
        //是否输入验证码
        try {
            confirmCode = driver.findElement(By.xpath("/html/body/form/div/div[2]/div[4]/div[2]"));
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        if (confirmCode.getText().equals("验证码")) {
            System.out.println("弹出验证码页面，终止线程");
            driver.close();
            return "验证码";
        }

        System.out.println("开始登录");
        driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[2]/div[3]/input")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[3]/div[3]/input")).sendKeys(password);

        // 点击登录
        driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[5]/div/input")).click();

        WebElement confirmPassword = null;
//        // 登录密码验证
//        try {
//            confirmPassword = driver.findElement(By.xpath("/html/body/form/div/div[7]/span"));
//        } catch (NoSuchElementException e) {
//            System.out.println(e);
//        }
//        if (confirmCode==null && confirmPassword.getText().equals("对不起，登录失败，未检索到用户账号")) {
//            System.out.println("账号找不到");
//            driver.close();
//            return "账号找不到";
//        } else if (confirmPassword.getText().equals("对不起，你的密码输入错误，登录失败。")) {
//            System.out.println("密码错误");
//            driver.close();
//            return "密码错误";
//        }


        System.out.println("登录成功");

        driver.switchTo().frame("zzj_top_6s");

        // 获取文本查看今日是否填报成功
        String isSuccess = driver.findElement(By.xpath("/html/body/form/div/div[7]/span")).getText();
        System.out.println(isSuccess);

        // 点击本人填报
        driver.findElement(By.xpath("//*[@id=\"bak_0\"]/div[13]/div[5]/div[4]")).click();
        // 点击提交表格
        driver.findElement(By.xpath("//*[@id=\"bak_0\"]/div[7]/div[4]")).click();
        // 点击确定
        driver.findElement(By.xpath("//*[@id=\"bak_0\"]/div[2]/div[2]/div[4]/div[2]")).click();
        // 刷新
        driver.navigate().refresh();

        driver.switchTo().frame("zzj_top_6s");
        // 判断是否成功
        String checkAgain = driver.findElement(By.xpath("/html/body/form/div/div[7]/span")).getText();
        System.out.println(checkAgain);
        if (checkAgain.equals("今日您已经填报过了")){
            driver.close();
            return "成功";
        }
        driver.close();
        return "未知";
    }
}
