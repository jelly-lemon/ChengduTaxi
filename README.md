# 成都出租车数据分析

前端展示效果图：
![image](https://github.com/jelly-lemon/ChengduTaxi/blob/master/images/%E4%BD%8D%E7%BD%AE%E6%A0%87%E6%B3%A8.png)
流程图：
![image](流程图.png)

重现该项目做法：
<br>1、构建该项目，同时根据 docker-compose.yml 构建镜像；部署项目到 Tomcat 容器中；
<br>2、建立对应的数据库 taxi，建立对应的表 taxi，字段和 taxi.csv文件对应；
<br>2、进入 http://localhost:8080/ChengduTaxi/ImportDataServlet ，导入 taxi.csv 到服务器中；服务器会将该文件里的内容自动保存到 MySQL 数据库中；
<br>3、然后进入 进入 http://localhost:8080/ChengduTaxi/TaxiPlaceServlet 即可看到演示效果；


注意：
<br>1、数据量过大，只加载了前 100 条数据用来显示。
<br>2、csv 文件里出租车的位置信息，应该是出租车轨迹运动，因为间隔几秒钟就有一个记录。


