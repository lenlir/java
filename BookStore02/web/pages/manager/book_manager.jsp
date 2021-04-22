<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript">
		 $(function () {
		 	$(".delbook").click(function () {
		 		var name  = $(this).attr("id");
                 return  confirm("是否删除<<"+name+">>这本书?");
			})
			 
		 })
	</script>
</head>
<body>
    
	
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/WEB-INF/include/book.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.page.list}">
		<h1>没有任何图书</h1>
	</c:if>
		<c:if test="${not empty requestScope.page.list}">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
		<c:forEach var="book" items="${requestScope.page.list}">
			<tr>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="bookServlet?methodName=getbook&bookId=${book.id}">修改</a></td>
				<td><a  id="${book.title}"  class="delbook" href="bookServlet?methodName=deletebook&bookId=${book.id}">删除</a></td>
			</tr>
		</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_update.jsp">添加图书</a></td>
			</tr>	
		</table>
			<div id="page_nav">
				<c:if test="${requestScope.page.pageNo != 1}">
<%--					如果不是第一页 就可以显示首页和上一页--%>
					<a href="bookServlet?methodName=getBooksbyPage&pageNo=1">首页</a>
					<a href="bookServlet?methodName=getBooksbyPage&pageNo=${requestScope.page.pageNo-1}">上一页</a>
				</c:if>
				<c:forEach var="index" begin="1" end="${requestScope.page.totalPageNo}">

					<c:if test="${requestScope.page.pageNo==index}">
						[<a href="bookServlet?methodName=getBooksbyPage&pageNo=${index}">${index}</a>]
					</c:if>
					<c:if test="${requestScope.page.pageNo!=index}">
						<a href="bookServlet?methodName=getBooksbyPage&pageNo=${index}">${index}</a>
					</c:if>
				</c:forEach>
				<c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo }">
				<a href="bookServlet?methodName=getBooksbyPage&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="bookServlet?methodName=getBooksbyPage&pageNo=${requestScope.page.totalPageNo}">末页</a>
				</c:if>
				共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
				<input type="button" value="确定" id="sub">
				<script type="text/javascript">

					$("#sub").click(function () {
						var pageNo = $("#pn_input").val();
						window.location.href="bookServlet?methodName=getBooksbyPage&pageNo="+pageNo;
					})


				</script>
			</div>
		</c:if>

	</div>

	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>