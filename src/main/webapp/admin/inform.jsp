<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 13647
  Date: 2022/6/13
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

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
        <p>通知公告>公告列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form action="${pageContext.request.contextPath}/info/informList.action">
            公告查询：<input type="text" name="iName" id="iName"> &nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;
            <input type="submit" value="查询">
            <%--            ajaxsplit(${info.pageNum})--%>
        </form>
    </div>
    <br>
    <div id="table">

        <c:choose>
            <c:when test="${userList.size()!=0}">

                <div id="top">
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <a href="${pageContext.request.contextPath}/info/addInformGo.action">
                        <input type="button" class="btn btn-warning" id="btn1"
                               value="添加公告">
                    </a>
                </div>
                <div id="middle">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th></th>
                            <th>公告名</th>
                            <th>公告内容</th>
                            <th>更新时间</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${informList}" var="inform">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck" value="${inform.IId}" onclick="ckClick()"></td>
                                <input type="hidden" value="${inform.IId}"/>
                                <td>${inform.IName}</td>
                                <td>${inform.IContent}</td>
                                <td>${inform.updateTime}</td>
                                <td class="center">
                                    <a href="/info/deleteInform.action?iId=${inform.IId }" style="color: dodgerblue">删除</a>
                                    <a href="/info/updateInformGo.action?iId=${inform.IId }" style="color: dodgerblue">修改</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>


                </div>
            </c:when>
            <c:otherwise>
                <div>

                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

</html>
