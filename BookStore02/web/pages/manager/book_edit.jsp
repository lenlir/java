<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
		<%@include file="/WEB-INF/include/book.jsp"%>
		</div>
		
		<div id="main">
			<form action="bookServlet?methodName=addorupdateBook" method="post">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value=""/></td>
						<td><input name="price" type="text" value=""/></td>
						<td><input name="author" type="text" value=""/></td>
						<td><input name="sales" type="text" value=""/></td>
						<td><input name="stock" type="text" value=""/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>		
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>