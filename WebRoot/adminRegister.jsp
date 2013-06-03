<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Sign in &middot; Twitter Bootstrap</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

<script type="text/javascript">
  		
  			function validate()
  			{		
  				var email = document.getElementsByName("email")[0];
  				var name = document.getElementsByName("name")[0];
  				var username = document.getElementsByName("username")[0];
  				var password = document.getElementsByName("password")[0];
  				var repassword = document.getElementsByName("repassword")[0];
  				var gender = document.getElementsByName("gender");
  				
  				var result = "";
  				
  				if(name.value.length == 0)
  				{
  					result += "姓名不可以为空！";
  				}
  				
  				else if(username.value.length < 4 || username.value.length > 10)
  				{
  					result += "用户名长度应该是4和10之间！";
  				}
  				
  				if(password.value.length < 4 || password.value.length > 10)
  				{
  					result += "密码长度应该是4和10之间！";
  				} 
				
  				else if(repassword.value != password.value)
  				{
  					result += "密码不一致！";
  				}
  				
  				if(email.value.length == 0)
  				{
  					result += "邮箱必须要填写"
  				}
  				

  				if (gender[0].checked == false && gender[1].checked == false) {
  					result += "性别必须要选! ";
  				}
  				
  				if(result != "")
  				{
  					alert(result);
  					return false;
  				}
  					
  				return true;
  			}
  		
  		
  		</script>
</head>

<body>

	<div class="container">
		<h2 style="color: rgb(226, 106, 39)">大学图书馆注册 管理员</h2>
		
		<form class="form-signin" onsubmit="return validate()" action="adminRegister.htm" method="post">
  	
  		<table width="95%" border="0" cellpadding="0" cellspacing="1">
  		 <tbody>
  		 		 <tr>
            		<td align="left">用户名 </td>
            		<td align="left"><input class="input" type="text" name="username" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">密   码 </td>
            		<td align="left"><input class="input" type="password" name="password" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">确认密码 </td>
            		<td align="left"><input class="input" type="password" name="repassword" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">邮箱帐号 </td>
            		<td align="left"><input class="input" type="text" name="email" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">姓 名 </td>
            		<td align="left"><input class="input" type="text" name="name" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">性 别</td>
            		<td align="left">男<input type="radio" name="gender" value="男">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		 女<input type="radio" name= "gender" value="女" >
            		</td>
          		</tr>
          		
          		<tr>
            		<td align="left">单 位 </td>
            		<td align="left"><input class="input" type="text" name="unit" size="20" style="width:200px"></td>
          		</tr>
          		
          		<tr>
            		<td align="left">手机号码 </td>
            		<td align="left"><input class="input" type="text" name="phone" size="20" style="width:200px"></td>
          		</tr>
  		 
  		 </tbody>
  		</table>
  	 
		
  		<input class="btn btn-large btn-primary" type="submit" value="submit">
  		
  		</form>


		</div>





</body>
</html>
