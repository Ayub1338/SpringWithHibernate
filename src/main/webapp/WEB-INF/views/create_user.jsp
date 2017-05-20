<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.2.1"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/create_user.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/SpringMavenMvc/createUser">
	<div class="container">
	 
		<table width="100%" bgcolor="0099CC" align="center">

			<tr>
				<td colspan=2><center>
						<font size=4><b>HTML Login Page</b></font>
					</center></td>
			</tr>
			
			<tr>
				<td>Username</td>
				<td><input width="40%" class="form-control"
					placeholder="User Name" name="userName" id="userName" type="text"></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input width="40%" class="form-control"
					placeholder="Password" name="password" id="password" type="text"></td>
			</tr>
			<td>Email Id:</td>
			<td><input width="40%" class="form-control"
				placeholder="Email Id" name="emailId" id="emailId" type="text"></td>
			
		</table>
		 <input  align = "center" type="Reset"> 
			 <input type="button"  align = "center" 
				value="create user" id="create_user"> 
			 
		
	</div>
</form>

</body>
</html>