<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

	<script type="text/javascript">
		$(function () {
			//给图片设置单击事件
			$("#image").click(function () {
				//当img标签中的路径发生变化时,浏览器会重新向src的新路径发送请求
				  $(this).attr("src","code?"+Math.random());

			});
			//给注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				//获取用户输入的用户名
				var username = $("#username").val();
				//设置验证用户名的正则表达式
				//用户名必须是3-6位的，而且只能使用数字、字母、下划线或减号
				var usernameReg = /^[a-zA-Z0-9_-]{3,6}$/;
				//验证用户名是否符合要求
				var flag = usernameReg.test(username);
				if(!flag){
					alert("请输入3-6位的字母、数字、下划线或减号的用户名！");
					return false;
				}
				//密码
				var password  = $("#password").val();
				var passwordReg = /^[a-zA-Z0-9_-]{6,18}$/;
				if(!(passwordReg.test(password))){
					alert("请输入6-18位由字母丶数字、下划线或减号组成的密码！")
					$("#password").val("");
					return false;
				}
				//确认密码
				var repwd = $("#repwd").val();
				if(!(repwd==password)){
					alert("两次密码不一致.请重新输入!")
					return false;
				}
			});
              //判断用户名是否已经注册
			$("#username").change(function () {
				var name = $(this).val();
				var usernameReg = /^[a-zA-Z0-9_-]{3,6}$/;
				//验证用户名是否符合要求
				var flag = usernameReg.test(name);
				if(!flag){
					alert("请输入3-6位的字母、数字、下划线或减号的用户名！");
					return false;
				}
				var url ="UserServlet?methodName=isDupName";
				var param = "name="+name;
				$.post(url,param,function(msg){
					$("#em").html(msg);
				});


			})



		});
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg" id="em">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="UserServlet?methodName=regist" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code"  name="imgcode"/>
									<img id="image" alt="" src="code" style="float: right; margin-right: 40px " width="80" height="40">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>