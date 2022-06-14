<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
	</head>

	<body>
		<div id="addAll">
			<div id="nav">
				<p>修改密码</p>
			</div>

			<div id="table">
				<form action="${pageContext.request.contextPath}/user/updatePwd.action" method="post">
					<input type="hidden" value="${user.uid}" name="uid" enctype="multipart/form-data">
					<table>
						<tr>
							<td class="one">旧密码</td>
							<td><input type="text" name="oldpwd" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="oldpwd"></span></td>
						</tr>
						<tr>
							<td class="one">新密码</td>
							<td><input type="text" name="password" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="password"></span></td>
						</tr>
						<tr>
							<td class="one">确认新密码</td>
							<td><input type="text" name="pwd" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pwd"></span></td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<a href="/user/toindex.action">
									<input type="button" value="取消" class="btn btn-default">
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>