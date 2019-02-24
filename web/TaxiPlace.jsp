<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TaxiPlace</title>

    <style type="text/css">
        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0 10px;
        }

        #container {
            height: 100%;
            margin: 20px;
        }

        .info_ul {
            margin: 0 0 5px 0;
            padding: 0.2em 0;
        }

        .info_li {
            line-height: 26px;
            font-size: 15px;
        }

        .info_span {
            width: 100px;
            display: inline-block;
        }
    </style>

    <!-- 调用 百度地图 API -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=YWdGplhYjUGQ3GtpKNeuTM2S"></script>

</head>

<%!
    private int i = 0;
    private String TID;
    private String start_time;
    private String end_time;
    private int selectedTID;
%>

<%
    TID = (String) pageContext.getSession().getAttribute("TID");
    start_time = (String) pageContext.getSession().getAttribute("start_time");
    end_time = (String) pageContext.getSession().getAttribute("end_time");
%>


<body>
<!-- 下拉选择框 -->
<form action="./TaxiPlaceServlet" method="post">
    <label>TID：</label>
    <select name="TID">

<%
    ArrayList<Integer> TIDList = (ArrayList<Integer>) pageContext.getSession().getAttribute("TIDList");
    int n = TIDList.size();

    if (TID.equals("all")) {
%>
        <option value="all" selected="selected">all</option>
<%

        for (int i = 0; i < n; i++) {
%>
        <option value="<%out.print(TIDList.get(i));%>"><%out.print(TIDList.get(i));%></option>
<%
        }
    } else {
%>
        <option value="all">all</option>
<%
        selectedTID = Integer.parseInt(TID);



        for (i = 0; i < n; i++) {
            if (TIDList.get(i) == selectedTID) {
%>
            <option value="<%out.print(TIDList.get(i));%>" selected="selected"><%out.print(TIDList.get(i));%></option>
<%
            } else {
%>
            <option value="<%out.print(TIDList.get(i));%>"><%out.print(TIDList.get(i));%></option>
<%
            }
        }
    }
%>


    </select>

    <label>开始时间：</label>
    <input type="time" name="start_time" value="<%out.print(start_time);%>"/>

    <label>结束时间：</label>
    <input type="time" name="end_time" value="<%out.print(end_time);%>"/>

    <input type="submit" value="提交"/>

</form>


<!-- 地图容器 -->
<div id="container"></div>

<script type="text/javascript">
    var map = new BMap.Map("container"); //初始化地图

    /*var points = [
        {"lng": 116, "lat": 40, "url": "http://www.baidu.com", "id": 50, "name": "p1"},
        {"lng": 117, "lat": 31, "url": "http://www.taobao.com", "id": 2, "name": "p2"},
        {"lng": 116, "lat": 34, "url": "http://www.qq.com", "id": 3, "name": "p3"}
    ];//数据准备*/


    var points = ${sessionScope.point};

    map.centerAndZoom(new BMap.Point(103.388611, 35.563611), 5);//设置中心点和显示级别。中国。

    map.enableScrollWheelZoom();//滚轮放大缩小。

    addMarker(points);//添加标注。

    function addMarker(points) {  // 创建图标对象
        var point, marker;
        // 创建标注对象并添加到地图
        for (var i = 0, pointsLen = points.length; i < pointsLen; i++) {
            point = new BMap.Point(points[i].Lon, points[i].Lat);
            marker = new BMap.Marker(point);
            map.addOverlay(marker);
            //给标注点添加点击事件。使用立即执行函数和闭包
            (function () {
                var thePoint = points[i];
                marker.addEventListener("click", function () {
                    showInfo(this, thePoint);
                });
            })();
        }
    }

    //显示信息窗口，显示标注点的信息。
    function showInfo(thisMaker, point) {
        var sContent =
            '<ul class="info_ul">'

            + '<li class="info_li">'
            + '<span class="info_span">TID：</span>' + point.TID + '</li>'

            + '<li class="info_li">'
            + '<span class="info_span">Time：</span>' + point.Time + '</li>'

            + '<li class="info_li">'
            + '<span class="info_span">Longitude：</span>' + point.Lon + '</li>'

            + '<li class="info_li">'
            + '<span class="info_span">Latitude：</span>' + point.Lat + '</li>'

            + '</ul>';
        var infoWindow = new BMap.InfoWindow(sContent);// 创建信息窗口对象
        thisMaker.openInfoWindow(infoWindow);//图片加载完毕重绘infowindow
    }

</script>

</body>
</html>
