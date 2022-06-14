<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<head>
    <meta charset="UTF-8">

    <script type="text/javascript">
        if ("${msg}" != "") {
            alert("${msg}");
        }
    </script>

    <c:remove var="msg"></c:remove>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bright.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <title></title>
</head>
<script type="text/javascript">
    function allClick() {
        //取得全选复选框的选中未选 中状态
        var ischeck=$("#all").prop("checked");
        //将此状态赋值给每个商品列表里的复选框
        $("input[name=ck]").each(function () {
            this.checked=ischeck;
        });
    }

    function ckClick() {
        //取得所有name=ck的被选中的复选框
        var length=$("input[name=ck]:checked").length;
//取得所有name=ck的复选框
        var len=$("input[name=ck]").length;
        //比较
        if(len == length){
            $("#all").prop("checked",true);
        }else
        {
            $("#all").prop("checked",false);
        }
    }
</script>
<body>
<div id="brall">
    <div id="nav">
        <p>行政管理>账户列表</p>
    </div>
    <div id="condition" style="text-align: center">
    </div>
    <br>
    <div id="table">
                <div id="top">
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <a href="${pageContext.request.contextPath}/user/getAllUser.action">

                        <input type="button" class="btn btn-warning" id="btn1"
                               value="返回">
                    </a>
                </div>
                <div id="middle">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th></th>
                            <th>用户名</th>
                            <th>电话</th>
                            <th>邮箱</th>
                            <th>是否重置</th>
                        </tr>
                        <c:forEach items="${resetList}" var="reset">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck" value="${reset.rid}" onclick="ckClick()"></td>
                                <input type="hidden" value="${reset.rid}"/>
                                <td>${reset.username}</td>
                                <td>${reset.phone}</td>
                                <td>${reset.email}</td>
                                <td>
                                    <button type="button" class="btn btn-info " ><a href="${pageContext.request.contextPath}/user/updateReset.action?username=${reset.username}">确认</a>
                                    </button>
                                    <button type="button" class="btn btn-warning"><a href="${pageContext.request.contextPath}/user/deleteReset.action?rid=${reset.rid}">取消</a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
    </div>
</div>
</body>

</html>
