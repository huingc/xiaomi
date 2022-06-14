<%--
  Created by IntelliJ IDEA.
  User: kyf
  Date: 2022/6/9
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
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
        var ischeck = $("#all").prop("checked");
        //将此状态赋值给每个商品列表里的复选框
        $("input[name=ck]").each(function () {
            this.checked = ischeck;
        });
    }

    function ckClick() {
        //取得所有name=ck的被选中的复选框
        var length = $("input[name=ck]:checked").length;
//取得所有name=ck的复选框
        var len = $("input[name=ck]").length;
        //比较
        if (len == length) {
            $("#all").prop("checked", true);
        } else {
            $("#all").prop("checked", false);
        }
    }
</script>
<body>
<div id="brall">
    <div id="nav">
        <p>账单管理>账单列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform">
            账单编号：<input type="text" name="bid" id="bid">&nbsp;&nbsp;&nbsp;&nbsp;
            用户id：<input type="text" name="uid" id="uid">
            <input type="button" value="查询" onclick="condition()">
        </form>
    </div>
    <br>
    <div id="table">

        <c:choose>
            <c:when test="${info.list.size()!=0}">

                <div id="top">
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <input type="button" class="btn btn-warning" id="btn1"
                           value="批量删除" onclick="deleteBatch()">
                </div>
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped" >
                        <tr>
                            <th></th>
                            <th>账单编号</th>
                            <th>用户编号</th>
                            <th>商品总数</th>
                            <th>账单总额</th>
                            <th>账单创建日期</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${info.list}" var="p">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck"
                                                                          value="${p.bid}" onclick="ckClick()"></td>
                                <td><a href="/bill/billinfo.action?bid=${p.bid}">${p.bid}</a></td>
                                <td>${p.uid}</td>
                                <td>${p.count}</td>
                                <td>${p.total}</td>
                                <td><fmt:formatDate value='${p.date}' pattern='yyyy-MM-dd'/></td>
                                    <%--<td><a href="${pageContext.request.contextPath}/admin/product?flag=delete&pid=${p.pId}" onclick="return confirm('确定删除吗？')">删除</a>--%>
                                    <%--&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/product?flag=one&pid=${p.pId}">修改</a></td>--%>
                                <td>
                                    <a href="/bill/billinfo.action?bid=${p.bid}">
                                        <button type="button" class="btn btn-info ">
                                            详情
                                        </button>
                                    </a>
                                    <button type="button" class="btn btn-warning" id="mydel" onclick="del(${p.bid},${info.pageNum})">
                                        删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <li>
                                            <%--                                        <a href="${pageContext.request.contextPath}/prod/split.action?page=${info.prePage}" aria-label="Previous">--%>
<%--                                        上一页--%>
                                        <a href="javascript:ajaxsplit(${info.prePage})" aria-label="Previous">

                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <c:forEach begin="1" end="${info.pages}" var="i">
                                        <c:if test="${info.pageNum==i}">
                                            <li>
                                                    <%--                                                <a href="${pageContext.request.contextPath}/prod/split.action?page=${i}" style="background-color: grey">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: grey">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${info.pageNum!=i}">
                                            <li>
                                                    <%--                                                <a href="${pageContext.request.contextPath}/prod/split.action?page=${i}">${i}</a>--%>
<%--                                                下一页--%>
                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                            <%--  <a href="${pageContext.request.contextPath}/prod/split.action?page=1" aria-label="Next">--%>
                                        <a href="javascript:ajaxsplit(${info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">暂时没有符合条件的商品！</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

<script type="text/javascript">
    function mysubmit() {
        $("#myform").submit();
    }

    //批量删除
    function deleteBatch() {

        //取得所有被选中删除商品的pid
        var cks = $("input[name=ck]:checked");
        var str = "";
        var id = "";
        if (cks.length == 0) {
            alert("请选择将要删除的商品！");
        } else {
            // 有选中的商品，则取出每个选 中商品的ID，拼提交的ID的数据
            if (confirm("您确定删除" + cks.length + "条商品吗？")) {
                //拼接ID
                $.each(cks, function () {

                    id = $(this).val(); //每一个被选中的id
                    if (id != null) //非空判断
                        str += id + ",";  //22,33,44
                });
                //发送请求到服务器端
                window.location="${pageContext.request.contextPath}/bill/deletebatch.action?pids="+str;

            }
        }
    }

    //单个删除
    function del(bid,page) {
        if (confirm("确定删除吗")) {
            console.log(bid)
            //查询条件
            var uid = $("#uid").val();
            var str = "?bid=" + bid + "&uid=" + uid  + "&page=" + page;
            //向服务器提交请求完成删除
            <%--window.location = "${pageContext.request.contextPath}/prod/delete.action?pid=" + pid;--%>
            window.location = "${pageContext.request.contextPath}/bill/delete.action" + str;
        }
    }

</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    function ajaxsplit(page) {
        var uid = $("#uid").val();
        //异步ajax分页请求
        $.ajax({
            url: "${pageContext.request.contextPath}/bill/ajaxsplit.action",
            data: {"page": page,"uid": uid},
            type: "post",
            success: function () {
                //重新加载分页显示的组件table
                //location.href---->http://localhost:8080/admin/login.action
                $("#table").load("http://localhost:8080/admin/bill.jsp #table");
            }
        })
    };

    //条件查询
    function condition(){
        var bid = $("#bid").val();
        var uid = $("#uid").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/bill/ajaxsplit.action",
            data: {"bid": bid,"uid": uid},
            type: "post",
            success:function () {
                // location.reload();
                //刷新页面数据
                $("#table").load("http://localhost:8080/admin/bill.jsp #table");
            }
        });
    }

</script>

</html>
