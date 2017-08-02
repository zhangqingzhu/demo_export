<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
<base href="<%=basePath%>"> 
<title>首页</title>  
</head>  
<body>  
    <h1>  
    	欢迎：${sessionScope.name}用户<br/>
       <a href="<%=basePath%>user">1、查看所有用户信息(Excel导出)</a><br/>
       <a href="<%=basePath%>userByWord">2、查看所有用户信息(Word导出)</a><br/>  
    </h1>  
</body>  
</html> 