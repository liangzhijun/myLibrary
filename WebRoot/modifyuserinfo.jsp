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
						<li><a href="http://localhost:8080/MyLibrary/Retrieval.jsp">书目检索</a>
						</li>
						<li><a href="#about">分类浏览</a>
						</li>
						<li><a href="#about">期刊导航</a>
						</li>
						<li><a href="#contact">新书通报</a>
						</li>
						<li><a href="http://localhost:8080/MyLibrary/Library.jsp">图书目库</a>
						</li>
						<li><a href="#about">读者荐购</a>
						</li>
						<li><a href="http://localhost:8080/MyLibrary/myLibrary">我的图书馆</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	
	
	<div class="container">
	
		<h3 style="color: rgb(226, 106, 39)">修改个人信息</h3>
		
		<form class="form-signin" action="modifyuserinfo" method="post">
  	
  	  	手机： <input type="text" name="mobilePhone" value="${
							sessionScope.user.mobilePhone}"> <br>
  	  	电话：<input type="text" name="phone" value="${
							sessionScope.user.phone}"> <br>
  		住址： <input type="text" name="address" value="${
							sessionScope.user.address}"> <br>
  		Email地址：<input type="text" name="email" value="${
							sessionScope.user.email}"> <br>
  		
  		<input class="btn btn-primary" type="submit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;
  		<a class="btn btn-primary" href="http://localhost:8080/MyLibrary/myLibrary.jsp" target= "_blank">取消</a>
  		
  		</form>


		</div>
    
    
  </body>
</html>
