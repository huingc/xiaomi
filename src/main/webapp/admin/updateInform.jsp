<%--
  Created by IntelliJ IDEA.
  User: 13647
  Date: 2022/6/13
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="centercontent tables">
    <div class="pageheader notab">
        <h1 class="pagetitle">修改公告</h1>
    </div><!--pageheader-->

    <div id="contentwrapper" class="contentwrapper">
        <div>
            <h3 class="contenttitle2">修改公告信息</h3>
        </div><!--contenttitle-->

        <form method="post" action="/info/updateInform.action">
            <input type="hidden" value="${inform.IId}" name="iId">
            <p><label>公告名称</label>
                <span class="field"><input type="text" name="iName" class="longinput" value="${inform.IName }"/></span>
            </p>

            <p><label>公告内容</label>
                <span class="field"><input type="text" name="iContent" class="longinput" value="${inform.IContent}"/></span>
            </p>
                <input type="submit" class="submit radius2" value="提交" />
                <input type="reset" class="reset radius2" value="重置" />
        </form>
        <br />

    </div><!--subcontent-->

</div><!--contentwrapper-->

</div><!-- centercontent -->

</body>
</html>
