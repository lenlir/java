<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		    $(function () {
		    	//给清空按钮绑定单击事件
		    	$("#emptyBtn").click(function () {
					return confirm("是否清空购物车?")
				});
		    	$(".deleteBtn").click(function () {
		    		var booktitle =$(this).attr("id");
					  return confirm("是否删除<<"+booktitle+">>这本书?")
				});
		    	$(".inputCount").change(function () {
		    		//得到刚来这个页面的值
					var defvalue = this.defaultValue;
		    		//得到一本书总数量
		    		  var count = $(this).val();
					  var id = $(this).attr("id");
					  var stock =$(this).attr("name");
					  var parseNum1 = parseInt(count);
					  var parseNum2 = parseInt(stock);
					//设置正则表达式
					var reg =/^[1-9]\d*$/;
					if(!reg.test(count)){
						alert("请输入正整数!")
						this.value = defvalue;
					}
					  if(parseNum1 > parseNum2){
					  	  alert("库存不足");
					  	  //如果超库存 将最大库存设置为默认值
					  	  this.defaultValue = stock;
					  	  this.value = stock;
					  	  count = stock;
					  }else{
					  	//如果没有超库存还是正整数,就将这个数储设置为默认值
						 this.defaultValue = count;
					  }
					//获取设置购物项的金额小计
					var amountTd = $(this).parent().next().next();
 					//设置url
					var url ="cartServlet?methodName=updateItemByAjax";
					var param = {"bookid":id,"count":count};
					$.post(url,param,function (res) {
                        //获取购物项的金额
						var amount = res.amount;
						//将金额设置进去
						amountTd.text(amount);
						//获取购物车中的总金额
						var totalAmount =res.totalAmount;
						//将总金额设置进去
						$("#sp2").text(totalAmount);
						//获取购物车中的总数量
						var totalCount = res.totalCount;
						//将总数量设置进去
						$("#sp1").text(totalCount);
					},"json")
				//window.location.href ="cartServlet?methodName=updateItem&bookid="+id+"&count="+count;
				});
			});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/WEB-INF/include/loginOrlogout.jsp"%>
	</div>
	<div id="main">
		<c:if test="${empty sessionScope.cart.cartItems}">
			<br><br><br><br><br><br><br><br><br><br>
			<div align="center" style="font-size: 25px">您的购物车空空如也,快去<a href="index.jsp" style="color: red">购物</a>吧!!!</div>
		</c:if>

	    <c:if test="${!empty sessionScope.cart.cartItems}">
		<table>

			<tr>
				<td >商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach var="item" items="${sessionScope.cart.cartItems}">
			<tr>
				<td>${item.book.title}</td>
				<td>
					<input style=" width:50px ;text-align: center"  value="${item.count}" id="${item.book.id}" class="inputCount" name="${item.book.stock}"></td>
				<td>${item.book.price}</td>
				<td>${item.amount}</td>
				<td><a href="cartServlet?methodName=deleteItem&bookid=${item.book.id}"  class="deleteBtn" id="${item.book.title}">删除</a></td>
			</tr>
			</c:forEach>
		</table>

		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count" id="sp1">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price" id="sp2">${sessionScope.cart.totalAmount}</span>元</span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="cartServlet?methodName=emptyCart" id="emptyBtn">清空购物车</a></span>
			<span class="cart_span"><a href="OrderClientServlet?methodName=checkOut">去结账</a></span>
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