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
	  parent.main_right.location="<%=basePath%>user";
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
<a href="<%=basePath%>user/excel_exportHtml">1.导出到excel(poi_网页版)</a></br>
<a href="<%=basePath%>user/excel_exportXlsx">2.导出到excel(poi_xlsx版)</a></br>
<a href="<%=basePath%>user/excel_exportHtmlByJxl">3.导出到excel(jxl_网页版)</a></br>
<a href="<%=basePath%>user/excel_exportXls">4.导出到excel(jxl_xls版)</a></br>
</ul>
<div class="div">
<div>
    <a href="<%=basePath%>user/excel_export">导出到excel</a>
    <a style="margin-left:60px" href="<%=basePath%>user/word_export">导出到word</a>
</div>
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