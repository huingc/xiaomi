<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<title></title>
		
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="${pageContext.request.contextPath}/images/cloud.jpg" /><span>REGIST</span>
			</div>
			<div id="bottom">
				<form action="${pageContext.request.contextPath}/user/regist.action" method="get">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td><input type="text" placeholder="UserName" class="td2" name="username"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" placeholder="Password" class="td2" name="password"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="注册" class="td3">
								<input type="reset" value="取消" class="td3	">
							</td>
						</tr>
					</table>
				</form>
				${msg}
			</div>

		</div>
	</body>

</html>