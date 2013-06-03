<%@page import="org.library.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>大学图书馆书目检索系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le styles -->
<link href="/css/bootstrap.css" rel="stylesheet">

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
						<li ><a href="/Retrieval.jsp">书目检索</a></li>
						<li><a href="#about">分类浏览</a></li>
						<li><a href="#about">期刊导航</a></li>
						<li><a href="#contact">新书通报</a></li>
						<li><a href="/getBooks.htm">图书目库</a></li>
						<li><a href="#about">读者荐购</a></li>
						<li><a href="/myLibrary.jsp">我的图书馆</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<hr>
	
	<div class="container">
		<div id="mylib_content">
			<h3 style="color: rgb(226, 106, 39)">${
				sessionScope.user.name}：您好</h3>
			<table width="100%" border="0" cellpadding="5" cellspacing="1"
				style="padding:5px">
				<tbody>
					<tr>
						<td bgcolor="#FFFFFF"><span class="bluetext">读者证件号：</span> ${
							sessionScope.user.username}</td>
						<td><span class="bluetext">姓名：</span> ${
							sessionScope.user.name}</td>
						<td><span class="bluetext">性别：</span> ${
							sessionScope.user.gender}</td>
					</tr>
					<tr>
						<td><span class="bluetext">读者类型：</span>${
							sessionScope.user.type}</td>
						<td><span class="bluetext">出生日期：</span>
						</td>
						<td><span class="bluetext">身份证号： </span>${
							sessionScope.user.idCare}</td>
					</tr>
					<tr>
						<td><span class="bluetext">单位：</span>${
							sessionScope.user.unit}</td>
						<td><span class="bluetext">住址：</span>${
							sessionScope.user.address}</td>
						<td><span class="bluetext">Email：</span> ${
							sessionScope.user.email} <font color="green">已验证</font></td>
					</tr>
					<tr>
						<td><span class="bluetext">电话：</span>${
							sessionScope.user.phone}</td>
						<td><span class="bluetext">手机号码：</span>${
							sessionScope.user.mobilePhone}</td>
						<td><span class="bluetext">累计借书：</span>48册次</td>
					</tr>
					<tr>
						<td><span class="bluetext">办证日期：</span>${
							sessionScope.user.time}</td>
						<td><span class="bluetext">生效日期：</span>${
							sessionScope.user.time}</td>
					</tr>

					<tr>
						<td colspan="3" align="right">
						
						<a href = "/change_passwd.jsp" >
						<input type="button"
							class="select" value="修改个人密码"> 
						</a>
	
						<a href = "/modifyuserinfo.jsp" >
						<input type="button"
							class="select" value="修改联系信息"> 
						</a>
						
					</tr>
				</tbody>
			</table>

		</div>

		</div>
</body>
</html>
