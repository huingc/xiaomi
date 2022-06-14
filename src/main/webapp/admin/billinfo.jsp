<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <br>
    <div id="table">
        <h1>订单   ${infobid}     的具体商品</h1>
        <c:choose>
            <c:when test="${info.size()!=0}">
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped" >
                        <tr>
<%--                            <th></th>--%>
                            <th>商品名</th>
                            <th>商品介绍</th>
                            <th>定价（元）</th>
                            <th>商品图片</th>
                            <th>商品数量</th>
                        </tr>
                        <c:forEach items="${info}" var="p">
                            <tr>
<%--                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck"--%>
<%--                                                                          value="${p.bid}" onclick="ckClick()"></td>--%>
                                <td>${p.productInfo.pName}</td>
                                <td>${p.productInfo.pContent}</td>
                                <td>${p.productInfo.pPrice}</td>
                                <td><img width="55px" height="45px"
                                         src="${pageContext.request.contextPath}/image_big/${p.productInfo.pImage}"></td>
                                <td>${p.num}</td>
                                    <%--<td><a href="${pageContext.request.contextPath}/admin/product?flag=delete&pid=${p.pId}" onclick="return confirm('确定删除吗？')">删除</a>--%>
                                    <%--&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/product?flag=one&pid=${p.pId}">修改</a></td>--%>
                            </tr>
                        </c:forEach>
                    </table>
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
                window.location="${pageContext.request.contextPath}/prod/deletebatch.action?pids="+str;

            }
        }
    }

    //单个删除
    function del(pid,page) {
        if (confirm("确定删除吗")) {
            //查询条件
            var pname = $("#pname").val();
            var typeid = $("#typeid").val();
            var lprice = $("#lprice").val();
            var hprice = $("#hprice").val();
            var str = "?pid=" + pid + "&pname=" + pname + "&typeid=" + typeid
                + "&lprice=" + lprice + "&hprice=" + hprice + "&page=" + page;
            //向服务器提交请求完成删除
            <%--window.location = "${pageContext.request.contextPath}/prod/delete.action?pid=" + pid;--%>
            window.location = "${pageContext.request.contextPath}/prod/delete.action" + str;
        }
    }

    function one(pid, page) {
        //查询条件
        var pname = $("#pname").val();
        var typeid = $("#typeid").val();
        var lprice = $("#lprice").val();
        var hprice = $("#hprice").val();

        var str = "?pid=" + pid + "&pname=" + pname + "&typeid=" + typeid
            + "&lprice=" + lprice + "&hprice=" + hprice + "&page=" + page;
        location.href = "${pageContext.request.contextPath}/prod/one.action" + str;
    }
</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    function ajaxsplit(page) {
        var pname = $("#pname").val();
        var typeid = $("#typeid").val();
        var lprice = $("#lprice").val();
        var hprice = $("#hprice").val();
        //异步ajax分页请求
        $.ajax({
            url: "${pageContext.request.contextPath}/prod/ajaxsplit.action",
            data: {"page": page,"pname": pname,"typeid": typeid,"lprice": lprice,"hprice": hprice},
            type: "post",
            success: function () {
                //重新加载分页显示的组件table
                //location.href---->http://localhost:8080/admin/login.action
                $("#table").load("http://localhost:8080/admin/product.jsp #table");
            }
        })
    };

    //条件查询
    function condition(){
        var pname = $("#pname").val();
        var typeid = $("#typeid").val();
        var lprice = $("#lprice").val();
        var hprice = $("#hprice").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/prod/ajaxsplit.action",
            data: {"pname": pname,"typeid": typeid,"lprice": lprice,"hprice": hprice},
            type: "post",
            success:function () {
                // location.reload();
                //刷新页面数据
                $("#table").load("http://localhost:8080/admin/product.jsp #table");
            }
        });
    }

</script>

</html>