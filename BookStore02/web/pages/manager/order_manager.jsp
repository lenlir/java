<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%@include file="/WEB-INF/include/book.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.orders}">
			<br><br><br><br><br><br><br><br>
			<div align="center">生意太惨淡了┭┮﹏┭┮ 没有一个订单</div>
		</c:if>
		<c:if test="${!empty requestScope.orders}">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>数量</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情<td>
				
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
			<tr>
				<td>${order.id}</td>
				<td>${order.orderTime}</td>
				<td>${order.totalCount}</td>
				<td>${order.totalAmount}</td>
				<td>
					<c:if test="${order.state == 0}">
					<a href="OrderManagerServlet?methodName=sendOrder&id=${order.id}">点击发货</a>
					</c:if>
				    <c:if test="${order.state == 1}">
						已发货
					</c:if>
					<c:if test="${order.state == 2}">
					 交易完成
					</c:if>
				</td>
				<td><a href="OrderServlet?methodName=getOrderItems&orderId=${order.id}">查看详情</a></td>
<%--				<td><a href="#">点击发货</a></td>--%>
			</tr>
			</c:forEach>
			>
		</table>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>