<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
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
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
	</head>
	<body>
	<!--取出上一个页面上带来的page的值-->

		<div id="addAll">
			<div id="nav">
				<p>用户管理>申请重置密码</p>
			</div>

			<div id="table">
				<form action="${pageContext.request.contextPath}/user/resetPwd.action" enctype="multipart/form-data"
					  method="post">
					<table>
						<tr>
							<td class="one">用户名</td>
							<td><input type="text" name="username" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pnameerr"></span></td>
						</tr>
						<tr>
							<td class="one">电话</td>
							<td><input type="text" name="phone" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="phoneerr"></span></td>
						</tr>
						<tr>
							<td class="one">邮箱</td>
							<td><input type="text" name="email" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="emailerr"></span></td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<a href="/user/login.action">
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