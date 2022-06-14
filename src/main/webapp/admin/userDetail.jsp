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
				<p>用户详情</p>
			</div>

			<div id="table">
				<form action="${pageContext.request.contextPath}/user/updateUser.action">
					<input type="hidden" value="${user.uid}" name="uid" enctype="multipart/form-data"  method="post">
					<table>
						<tr>
							<td class="one">用户名</td>
							<td><input type="text" name="username" class="two" value="${user.username}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="username"></span></td>
						</tr>
						<tr>
							<td class="one">电话</td>
							<td><input type="text" name="phone" class="two" value="${user.phone}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="phone"></span></td>
						</tr>
						<tr>
							<td class="one">邮箱</td>
							<td><input type="text" name="email" class="two" value="${user.email}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="email"></span></td>
						</tr>

						<tr>
							<td class="one">性别</td>
							<td><input type="text" name="gender" class="two" value="${user.gender}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="gender"></span></td>
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
						<tr>
							<td><a href="/user/queryUserById1.action?uid=${uid}">
								<input type="button" value="修改密码" class="btn btn-default">
							</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>