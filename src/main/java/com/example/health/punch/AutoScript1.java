package com.example.health.punch;

import com.example.health.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutoScript1 extends Thread {

    List<User> users;
    public AutoScript1(List<User> users) {
        this.users = users;
    }

    @Override
    public void run() {
        for (User user : users) {

//        System.setProperty("webdriver.gecko.driver", "/usr/firefox/geckodriver");
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\gzc\\Desktop\\upload\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            // 设置无界面
//        options.addArguments("--headless");
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
            if (confirmCode.getText().equals("验证码")){
                System.out.println("弹出验证码页面，终止线程");
                driver.close();
                break;
            }else {

                System.out.println("开始登录");
                driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[2]/div[3]/input")).sendKeys(user.getUser());
                driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[3]/div[3]/input")).sendKeys(user.getPassword());

                // 点击登录
                driver.findElement(By.xpath("//*[@id=\"mt_5\"]/div[5]/div/input")).click();
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

                driver.navigate().refresh();

                driver.switchTo().frame("zzj_top_6s");
                String isSuccess1 = driver.findElement(By.xpath("/html/body/form/div/div[7]/span")).getText();
                System.out.println(isSuccess1);

                driver.navigate().to("https://jksb.v.zzu.edu.cn/vls6sss/zzujksb.dll/first0");
                System.out.println(driver.toString());
                driver.close();
            }

        }

    }
}
