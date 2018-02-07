<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <link href="${contextPath}/resources/style/more.css" rel="stylesheet">
    <body>
        <div id="usercard">
            <div id="usercard_avatar" class="blocks"></div>
            <div class="blocks" id="usercard_info">
                <p id="name">Ilia Prokopev</p>
                <p id="settings_bottom">Settings</p>
                <p class="usercard_info_text1" style="margin-top:-8px">Status:</p>
                <p class="usercard_info_text1" style="margin-top:22px">fine:</p>
                <p class="usercard_info_text1" style="margin-top:52px">Chlen:</p>
                <p class="usercard_info_text2" style="margin-top:-8px">Student</p>
                <p class="usercard_info_text2" style="margin-top:22px">228$</p>
                <p class="usercard_info_text2" style="margin-top:52px">Bolshoi</p>
            </div>
            <div class="blocks" id="history">History is empty</div>
        </div>
    <!-- /container -->
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    </body>
</html>