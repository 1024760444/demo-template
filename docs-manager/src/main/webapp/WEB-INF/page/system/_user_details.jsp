<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>角色信息</title>
<meta name="keywords" content="角色，信息">
<meta name="description" content="展示角色信息列表的页面">
<link rel="shortcut icon" href="${domain}/hplus/favicon.ico">
<link href="${domain}/hplus/css/bootstrap.min.css?v=3.3.5"
	rel="stylesheet">
<link href="${domain}/hplus/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="${domain}/hplus/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${domain}/hplus/css/animate.min.css" rel="stylesheet">
<link href="${domain}/hplus/css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>个人资料</h5>
	                        <div class="ibox-tools">
								<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
								</a> <a class="close-link"> <i class="fa fa-times"></i>
								</a>
							</div>
	                    </div>
	                    <div>
	                        <div class="ibox-content">
	                        	<div class="row">
			                        <div role="form">
										<input type="hidden" name="uid" id="uid" value="${uid}">
										<div class="form-group">
											<label>登录名</label> <input type="text" name="userName" id="userName" 
												 readonly="readonly" placeholder="请输入您注册的登录名" class="form-control">
										</div>
										<div class="form-group">
											<label>密码</label> <input type="password" placeholder="请输入密码"
												name="userPasswd" id="userPasswd" class="form-control">
										</div>
										<div class="form-group">
											<label>角色编号</label> <input type="text" placeholder="角色编号"
												 readonly="readonly" name="roleId" id="roleId" class="form-control">
										</div>
										<div class="form-group">
											<label>角色名称</label> <input type="text" placeholder="角色名称"
												 readonly="readonly" name="roleName" id="roleName" class="form-control">
										</div>
										<div class="form-group">
											<label>角色别名</label> <input type="text" placeholder="角色别名"
												 readonly="readonly" name="roleByname" id="roleByname" class="form-control">
										</div>
										<div class="form-group">
											<label>分组编号</label> <input type="text" placeholder="分组编号"
												 readonly="readonly" name="teamId" id="teamId" class="form-control">
										</div>
										<div class="form-group">
											<label>分组名称</label> <input type="text" placeholder="分组名称"
												 readonly="readonly" name="teamName" id="teamName" class="form-control">
										</div>
										<div class="form-group">
											<label>分组别名</label> <input type="text" placeholder="分组别名"
												 readonly="readonly" name="teamByname" id="teamByname" class="form-control">
										</div>
										<div class="form-group">
											<label>邮箱</label> <input type="email" placeholder="请输入您注册的E-mail"
												 name="userEmail" id="userEmail" class="form-control">
										</div>
										<div class="form-group">
											<label>手机号码</label> <input type="text" placeholder="请输入手机号码"
												 name="userPhone" id="userPhone" class="form-control">
										</div>
										<div class="form-group">
											<button class="btn btn-sm btn-primary pull-right m-t-n-xs glyphicon glyphicon-floppy-save" 
												data-dismiss="modal" aria-label="Close" >
											</button>
										</div>
									</div>
								</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${domain}/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${domain}/hplus/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${domain}/hplus/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="${domain}/hplus/js/content.min.js?v=1.0.0"></script>
	<script src="${domain}/hplus/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${domain}/hplus/js/demo/peity-demo.min.js"></script>
	<script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
	<script type="text/javascript">
		// 1 文件加载展示数据
		$.ajax({
			type : "GET",
			url : "/docs-manager/user/userDetails",
			data : {
				'uid' : $("#uid").val(),
			},
			dataType : "json",
			success : function(data) {
				setUserDetails(data);
			},
			error : function() {
				alert("error");
			}
		});
		
		// 2 设置用户详细信息
		function setUserDetails(data) {
			$("#userName").val(data['userName']);
			$("#userPasswd").val("");
			$("#roleId").val(data['roleId']);
			$("#roleName").val(data['roleName']);
			$("#roleByname").val(data['roleByname']);
			$("#teamId").val(data['teamId']);
			$("#teamName").val(data['teamName']);
			$("#teamByname").val(data['teamByname']);
			$("#userEmail").val(data['userEmail']);
			$("#userPhone").val(data['userPhone']);
		}
		
	    // 3  推送数据，更新或者新增用户
		$(".glyphicon-floppy-save").click(function() {
			$.ajax({
				type : "POST",
				url : "/docs-manager/user/updateCurrUser",
				data : {
					'uid' : $("#uid").val(),
					'userName' : $("#userName").val(),
					'userPasswd' : $("#userPasswd").val(),
					'roleId' : $("#roleId").val(),
					'teamId' : $("#teamId").val(),
					'userEmail' : $("#userEmail").val(),
					'userPhone' : $("#userPhone").val(),
				},
				dataType : "json",
				success : function(data) {
					setUserDetails(data);
				},
				error : function() {
					alert("error");
				}
			});
		});
	</script>
</body>

</html>