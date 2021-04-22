<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/loginOrlogout.jsp"%>
	</div>

	
	<div id="main">

		<table>

			<tr>
				<td>封面</td>
				<td>名称</td>
				<td>数量</td>
				<td>金额</td>
				<td>作者</td>
				<td>单价</td>
			</tr>
			<c:forEach  items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td><img src="${orderItem.imgPath}" class="book_img"></td>
					<td>${orderItem.title}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.amount}</td>
					<td>${orderItem.author}</td>
					<td>${orderItem.price}</td>
				</tr>
			</c:forEach>
		</table>

		


	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>