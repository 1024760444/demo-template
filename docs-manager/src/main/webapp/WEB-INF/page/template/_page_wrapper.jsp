<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<div id="page-wrapper" class="gray-bg dashbard-1">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        </nav>
    </div>
    <div class="row content-tabs">
        <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
        </button>
        <nav class="page-tabs J_menuTabs">
            <div class="page-tabs-content">
                <a href="javascript:;" class="active J_menuTab" data-id="file/toFileList">首页</a>
            </div>
        </nav>
        <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
        </button>
        <div class="btn-group roll-nav roll-right">
            <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

            </button>
            <ul role="menu" class="dropdown-menu dropdown-menu-right">
                <li class="J_tabShowActive"><a>定位当前选项卡</a>
                </li>
                <li class="divider"></li>
                <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                </li>
                <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                </li>
            </ul>
        </div>
        <a href="logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
    </div>
    <div class="row J_mainContent" id="content-main">
        <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="file/toFileList?uid=<%=userId%>" frameborder="0" data-id="file/toFileList?uid=<%=userId%>" seamless></iframe>
    </div>
</div>