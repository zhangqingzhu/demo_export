<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
var meg = '${status}';
if( meg != '' ){
	  alert("下载成功！");
	  parent.main_right.location="<%=basePath%>userByWord";
	  parent.layer.closeAll();
}
</script>
<style type="text/css">
	/* body{
		text-align: center;
	} */
	.div{
		margin: 0 auto;
		margin-top: 150px;
		width: 500px;
		height: 100px;
		border: 1px;
	}
</style>
<title>用户列表</title>
</head>
<body>
<h3>请选择导出方式</h3>
<ul>
<a href="<%=basePath%>userByWord/word_export">1.导出到Word</a></br>

</ul>
<div class="div">
	<table border="1">
		<tbody>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>手机号</th>
			</tr>
			<c:if test="${!empty userLists }">
				<c:forEach items="${userLists}" var="user">
					<tr>
						<td>${user.id }</td>
						<td>${user.name }</td>
						<td>${user.age }</td>
						<td>${user.phone }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	</div>
</body>
</html>