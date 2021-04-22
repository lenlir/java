<%--
  Created by IntelliJ IDEA.
  User: 雷志刚
  Date: 2020/10/19
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <a href="bookServlet?methodName=getBooksbyPage">图书管理</a>
    <a href="OrderServlet?methodName=getOrders">订单管理</a>
    <a href="${pageContext.request.contextPath}/index.jsp">返回商城</a>
</div>