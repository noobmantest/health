# health
郑州大学健康打卡系统自动健康打卡脚本
## 记得右上角 Star 支持一下

### 使用selenium 创建的物理脚本 关键代代码:
 仅供学习使用
 脚本使用的Firefox浏览器需要相应的驱动文件 相关链接 https://github.com/mozilla/geckodriver/releases


## 更新添加, java使用使用selenium不稳定, 更新添加使用python的requests库进行访问打卡
访问其他接口提供的自动打卡服务,本项目提供定时打卡和数据库访问模块,为前端提供相应的接口,
自动打卡服务在 https://github.com/noobmantest/ZZUAutoPunchPythonInterface

## 更新解决详细位置中文乱码问题，解决定时小bug
添加post访问工具类，向打卡接口发送json数据，解决定时执行小bug，添加访问post端口工具类，后端发送post请求（post方式中文没有乱码）

## 更新定时器，在任务中执行获取全部用户信息再进行打卡，否则在定时器启动后再有用户添加将不会更新用户信息
解决方案：打卡方法解耦合，定时器解耦合
创建打卡方法，在每个用户进行打卡时调用方法
向定时器中传入TimerTask 在定时器外部TimerTask中书写代码获取相应的全部用户信息进行循环遍历打卡

# 前端页面地址 http://81.70.250.230:8081/autoPunch/#/

