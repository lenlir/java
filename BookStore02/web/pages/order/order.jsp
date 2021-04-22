<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@include file="/WEB-INF/include/loginOrlogout.jsp"%>
	</div>
	
	<div id="main">

		<c:if test="${empty requestScope.orders}">
			<br><br><br><br><br><br><br><br>
			<div align="center">您还未在此商城下单☺</div>
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
								未发货
							</c:if>
							<c:if test="${order.state == 1}">
								<a href="OrderClientServlet?methodName=delivery&id=${order.id}">确认收货</a>
							</c:if>
							<c:if test="${order.state == 2}">
								交易完成
							</c:if>
						</td>
						<td><a href="OrderManagerServlet?methodName=getOrderItems&orderId=${order.id}">查看详情</a></td>
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

