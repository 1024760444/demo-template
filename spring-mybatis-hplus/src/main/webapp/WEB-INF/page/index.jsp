<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="template/_head.jsp"></jsp:include>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <jsp:include page="template/_navbar_default.jsp"></jsp:include>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <jsp:include page="template/_page_wrapper.jsp"></jsp:include>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <jsp:include page="template/_right_sidebar.jsp"></jsp:include>
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
        <jsp:include page="template/_small_chat_box.jsp"></jsp:include>
    </div>
</body>
</html>