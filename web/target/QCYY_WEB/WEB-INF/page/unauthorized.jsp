<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<html>

<head>
<title>没有权限</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	 <p class="error">您无权访问该地址</p>	
</body>
</html>