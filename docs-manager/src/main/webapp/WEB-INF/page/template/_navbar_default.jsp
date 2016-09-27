<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	HttpSession httpSession = request.getSession();
	String userName = (String) httpSession.getAttribute("userName");
	String roleName = (String) httpSession.getAttribute("roleName");
	String roleByname = (String) httpSession.getAttribute("roleByname");
	int strRoleId = 4;
	try {
		strRoleId = (Integer) httpSession.getAttribute("roleId");
	} catch(Exception e) {
		strRoleId = 4;
	}
	int userId = -1;
	try {
		userId = (Integer) httpSession.getAttribute("userId");
	} catch(Exception e) {
		userId = -1;
	}
%>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                	<%
					if(userName != null && !"".equals(userName)) {
					%>
                    <span><img alt="image" class="img-circle" src="${domain}/hplus/img/profile_small.jpg" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear">
                       <span class="block m-t-xs"><strong class="font-bold"><%=userName %></strong></span>
                        <span class="text-muted text-xs block"><%=roleByname %><b class="caret"></b></span>
                        </span>
                    </a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="user/toUserDetails?uid=<%=userId%>">个人资料</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout">安全退出</a>
                        </li>
                    </ul>
                    <%} else { %>
                    <span><img alt="image" class="img-circle" src="${domain}/hplus/img/profile_small.jpg" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear">
                       <span class="block m-t-xs"><strong class="font-bold">No Body</strong></span>
                        <span class="text-muted text-xs block">No Body<b class="caret"></b></span>
                        </span>
                    </a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="toLogin">点击登录</a>
                        </li>
                    </ul>
                    <% }%>
                </div>
                <div class="logo-element">H+
                </div>
            </li>
            <li>
                <a class="J_menuItem" href="file/toFileList?uid=<%=userId%>"><i class="fa fa-home"></i> <span class="nav-label">文档下载</span></a>
            </li>
            <%if(userName != null && !"".equals(userName)) { %>
            <li>
                <a href="#"><i class="fa fa-file-word-o"></i> <span class="nav-label">我的文档</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="file/toMyFiles?uid=<%=userId%>">我的上传</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-circle-o-notch"></i>
                    <span class="nav-label">系统设置</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                	<li>
                        <a class="J_menuItem" href="user/toUserDetails?uid=<%=userId%>">个人资料</a>
                    </li>
                    <%if(strRoleId <= 2) { %>
                    <li>
                        <a class="J_menuItem" href="role/toRole">角色列表</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="team/toTeam">分组列表</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="user/toUser">用户列表</a>
                    </li>
                    <%} %>
                </ul>
            </li>
            <%} %>
        </ul>
    </div>
</nav>