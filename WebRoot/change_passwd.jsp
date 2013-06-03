<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
  <title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
	
}
</style>

</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand">书目检索系统</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="#">书目检索</a>
						</li>
						<li><a href="#about">分类浏览</a>
						</li>
						<li><a href="#about">期刊导航</a>
						</li>
						<li><a href="#contact">新书通报</a>
						</li>
						<li><a href="/getBooks.htm">图书目库</a>
						</li>
						<li><a href="#about">读者荐购</a>
						</li>
						<li><a href="/myLibrary.jsp">我的图书馆</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	
	
	<div class="container">
	
		<h3 style="color: rgb(226, 106, 39)">修改个人密码</h3>
		
		<form class="form-signin" action="changePasswd.htm" method="post">
  	
  	  	原密码 <input type="password" name="password"> <br>
  		新密码 <input type="password" name="newPassword"> <br>
  		确认密码<input type="password" name="repassword"> <br>
  		
  		<input class="btn btn-primary" type="submit" value="修 改">&nbsp;&nbsp;&nbsp;&nbsp;
  		<a class="btn" href="/myLibrary.jsp">返回</a>
  		</form>

		</div>
    
    
  </body>
</html>
