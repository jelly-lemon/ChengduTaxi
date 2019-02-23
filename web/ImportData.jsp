<%--
  Created by IntelliJ IDEA.
  User: Jelly_Lemon
  Date: 2019/2/21
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImportData</title>
</head>
<body>

<form method="post" action="./ImportDataServlet" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="上传" />
</form>

</body>
</html>
