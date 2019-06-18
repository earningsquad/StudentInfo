<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 2019/6/5
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传</title>
    <script type="text/javascript">
        //添加javascript方法 addfile() 在页面中境加input标签、
        function addfile(){
            var file = document.createElement("input");
            file.type="file";
            file.name="file";
            document.getElementById("fileList").appendChild(file);
            document.getElementById("fileList").appendChild(document.createElement("br"));
        }
    </script>
</head>
<body>

<script>
    window.location.href="/stuw/pages/login/login.html"
</script>


</body>

</html>
