<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雷志刚
  Date: 2020/10/28
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/WEB-INF/include/base.jsp" %>
    <script>
         $(function () {
              $(".btn").click(function () {
                  //得到bookId
                    var bookId = $(this).attr("id");
                    //利用JQuery给servlet发送请求
                  window .location.href = "cartServlet?methodName=addCart&bookId="+bookId;
              });
              $("#query").click(function () {

                  var pageNo = $("#pn_input").val();
                  var minPrice = $("#min").val();
                  var maxPrice = $("#max").val();
                  window.location.href ="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=" + pageNo+"&minPrice="+minPrice+"&maxPrice="+maxPrice;

              });

         })
    </script>

</head>

<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <%@include file="/WEB-INF/include/loginOrlogout.jsp"%>
</div>

<div id="main">

    <c:if test="${empty requestScope.page.list}">
        <h1>没有任何图书</h1>
    </c:if>
    <c:if test="${!empty requestScope.page.list}">
        <div id="book">

            <div class="book_cond">
<%--                <form action="bookClientServlet?methodName=getBooksbyPageAndPrice" method="post">--%>
                    价格：<input type="text" name="min" id="min"> 元 - <input type="text" name="max" id="max"> 元
                    <button id="query" >查询</button>
<%--                </form>--%>
            </div>
            <div style="text-align: center">
<%--                如果session域中cart为空 证明购物车没东西--%>
                <c:if test="${empty sessionScope.cart}">
                    <span>您的购物车空空如也!</span>
                </c:if>
                 <c:if test="${!empty sessionScope.cart}">
                <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span><br>
                 </c:if>
                <c:if test="${!empty sessionScope.message}">
                    <span style="color: red">${sessionScope.message}</span>
                    <c:remove var="message"></c:remove>
                </c:if>

                <c:if test="${!empty sessionScope.booktitle }">
                    <div>
                        您刚刚将<span style="color: red">${sessionScope.booktitle}</span>加入到了购物车中
                        <c:remove var="booktitle"></c:remove>

                    </div>
                </c:if>

            </div>
            <c:forEach items="${requestScope.page.list}" var="book">
                <div class="b_list">
                    <div class="img_div">
                        <img class="book_img" alt="" src="${book.imgPath}"/>
                    </div>
                    <div class="book_info">
                        <div class="book_name">
                            <span class="sp1">书名:</span>
                            <span class="sp2">${book.title}</span>
                        </div>
                        <div class="book_author">
                            <span class="sp1">作者:</span>
                            <span class="sp2">${book.author}</span>
                        </div>
                        <div class="book_price">
                            <span class="sp1">价格:</span>
                            <span class="sp2">${book.price}</span>
                        </div>
                        <div class="book_sales">
                            <span class="sp1">销量:</span>
                            <span class="sp2">${book.sales}</span>
                        </div>
                        <div class="book_amount">

                            <span class="sp1">库存:</span>
                            <span class="sp2">${book.stock}</span>
                        </div>
                        <div class="book_add">
                            <c:if test="${book.stock!=0}">
                                <button id="${book.id}" class ="btn" >加入购物车</button>
                            </c:if>
                            <c:if test="${book.stock==0}">
                                <span style="color: red">小二拼命补货中</span>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>


    </c:if>
</div>
<div id="page_nav">
    <%--    如果当前页等于1 就不会显示上一页和首页--%>
    <c:choose>
        <%--        总页数小于等于5--%>
        <c:when test="${requestScope.page.totalPageNo <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.totalPageNo}"></c:set>

        </c:when>
        <%--        总页数大于5 且当前页小于等于3--%>
        <c:when test="${requestScope.page.pageNo<=3}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="5"></c:set>
        </c:when>
        <%--        总页数大于5 且当前页大于3--%>
        <c:otherwise>
            <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
            <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
            <c:if test="${requestScope.page.pageNo+2 >=requestScope.page.totalPageNo}">
                <c:set var="begin" value="${requestScope.page.totalPageNo-4}"></c:set>
                <c:set var="end" value="${requestScope.page.totalPageNo}"></c:set>
            </c:if>
        </c:otherwise>
    </c:choose>
    <c:if test="${requestScope.page.pageNo != 1}">
        <a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=1&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">首页</a>
        <a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=${requestScope.page.pageNo-1}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">上一页</a>
    </c:if>

    <c:forEach var="index" begin="${pageScope.begin}" end="${pageScope.end}">
        <%--    如果总页数等于当前页 则不会显示下一页和末页--%>
        <c:if test="${requestScope.page.pageNo==index}">
            [<a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=${index}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">${index}</a>]
        </c:if>
        <c:if test="${requestScope.page.pageNo!=index}">
            <a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=${index}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">${index}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo }">
        <a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=${requestScope.page.pageNo+1}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">下一页</a>
        <a href="bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=${requestScope.page.totalPageNo}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}">末页</a>
    </c:if>
    共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="sub">
    <script type="text/javascript">

        $("#sub").click(function () {
            var pageNo = $("#pn_input").val();
            window.location.href = "bookClientServlet?methodName=getBooksbyPageAndPrice&pageNo=" + pageNo;
        })


    </script>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>
