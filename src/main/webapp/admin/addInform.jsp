<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="centercontent tables">
    <div class="pageheader notab">
        <h1 class="pagetitle">添加公告</h1>
    </div><!--pageheader-->

    <div id="contentwrapper" class="contentwrapper">
        <div>
            <h3 class="contenttitle2">填写公告信息</h3>
        </div><!--contenttitle-->
        <form class="stdform stdform2" method="post" action="/info/addInform.action">
            <p><label>公告名字</label>
                <span class="field"><input type="text" name="iName" class="longinput"/></span>
            </p>

            <p><label>公告内容</label>
                <span class="field"><input type="text" name="iContent" class="longinput" /></span>
            </p>

            <p class="stdformbutton">
                <input type="submit" class="submit radius2" value="提交" />
                <input type="reset" class="reset radius2" value="重置" />
            </p>
        </form>
        <br />

    </div><!--subcontent-->

</div><!--contentwrapper-->

</div><!-- centercontent -->

</div><!--bodywrapper-->

</body>
</html>