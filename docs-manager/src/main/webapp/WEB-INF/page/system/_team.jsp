<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>分组信息</title>
<meta name="keywords" content="分组，信息">
<meta name="description" content="展示分组信息列表的页面">
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
			<div class="col-sm-7">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>分组列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-5 m-b-xs">
								<button type="button" class="btn btn-sm btn-danger glyphicon glyphicon-remove">
								</button>
							</div>
							<div class="col-sm-7">
								<div class="row">
									<div class="col-md-3">
									</div>
									<div class="col-md-6">
										<input id="search_input" type="text" placeholder="请输入用户名"
											class="input-sm form-control">
									</div>
									<div class="col-md-3">
										<span class="input-group-btn">
											<button type="button"
												class="btn btn-sm btn-primary glyphicon glyphicon-search"></button>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="table-responsive">
						</div>
						<div class="row">
							<div class="col-sm-5 m-b-xs">
								<label id="count_button" type="button" class="btn btn-primary pull-left">xxx</label>
							</div>
							<div class="col-sm-7 navigation">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>添加分组</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div role="form">
								<div class="form-group">
									<label class="col-sm-2 control-label">名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teamName"> <span
											class="help-block m-b-none">文本形式的英文名称，32个字符以内。</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">别名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="teamByname"> <span
											class="help-block m-b-none">文本形式的中文名称，32个字符以内。</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">描述</label>
									<div class="col-sm-10">
										<textarea rows="2" cols="" class="form-control" id="teamDesc"></textarea> <span
											class="help-block m-b-none">文本形式的中文分组信息描述，64个字符以内。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 col-sm-offset-2">
										<button class="btn btn-primary glyphicon glyphicon-floppy-save" type="submit">保存内容</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除二次确认对话框 -->
			<div class="modal fade" id="delete_dialog" tabindex="-1" role="dialog"
				aria-labelledby="delete_dialog_label">
				<div class="modal-dialog" role="document" style="width: 160px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<h4 class="modal-title" id="delete_dialog_title">New</h4>
						</div>
						<div class="modal-footer">
							<input type="hidden" id="delete_team_id" value="">
							<div>
								<button class="btn btn-primary pull-left delete_post" data-dismiss="modal"
									aria-label="Close">确定</button>
							</div>
							<div>
								<button class="btn btn-primary pull-right" data-dismiss="modal"
									aria-label="Close">取消</button>
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
			url : "/docs-manager/team/scan",
			data : {
				'type' : 1,
			},
			dataType : "json",
			success : function(data) {
				showTeamPage(data);
			},
			error : function() {
				alert("error");
			}
		});
    	
	    // 2  推送数据，更新或者新增用户
		$(".glyphicon-floppy-save").click(function() {
			$.ajax({
				type : "POST",
				url : "/docs-manager/team/add",
				data : {
					'teamName' : $("#teamName").val(),
					'teamByname' : $("#teamByname").val(),
					'teamDesc' : $("#teamDesc").val(),
				},
				dataType : "json",
				success : function(data) {
					showTeamPage(data);
				},
				error : function() {
					alert("error");
				}
			});
			
			$("#teamName").val("");
			$("#teamByname").val("");
			$("#teamDesc").val("");
		});
	    
	    // 3  数据查询操作
		$(".input-group-btn").click(function() {
			$.ajax({
				type : "GET",
				url : "/docs-manager/team/scan",
				data : {
					'currpage' : 1,
					'perpage' : 10,
					'search_teamName' : $("#search_input").val(),
				},
				dataType : "json",
				success : function(data) {
					showTeamPage(data);
				},
				error : function() {
					alert("error");
				}
			});
		});
	    
	    // 4 跳转到删除功能对话框
		$(".glyphicon-remove").click(function() {
			if($(".checked").length < 1) {
				alert('请选择一行数据');
			} else if($(".checked").length > 1) {
				alert('只能选择一行数据');
			} else {
				var tr = $(".checked").parent().parent().parent();
				var td1 = tr.children("td:eq(1)");
				
				// 设置一个删除的用户标识
				$("#delete_team_id").val(td1.text());
				$("#delete_dialog_title").text("删除确认");
				$('#delete_dialog').modal();
			}
		});
	    
		// 5 推送数据，删除分组
		$(".delete_post").click(function() {
			$.ajax({
				type : "POST",
				url : "/docs-manager/team/delete",
				data : {
					'id' : $("#delete_team_id").val(),
				},
				dataType : "json",
				success : function(data) {
					showTeamPage(data);
				},
				error : function() {
					alert("error");
				}
			});
		});
    	
	    // 展示整个页面数据
	    function showTeamPage(data) {
			var datas = eval(data['datas']);
			var count = Number(data['count']);
			var currpage = Number(data['currpage']);
			var totalpage = Number(data['totalpage']);
			var search_teamName = data['search_teamName'];
			
			$("#search_input").val(search_teamName);
			$("#count_button").text("总共 " + count + " 条记录");
			var nav = $(".navigation");
			navigation(nav, currpage, totalpage);
			
			var childOf = $(".table-responsive");
			showTeamTable(childOf, datas);
	    }
	    
	    // 生成翻页导航
	    function navigation(nav, currpage, totalpage) {
	    	// <button type="button" class="btn btn-white" >|&lt;</button>
	    	// <button class="btn btn-white active">2</button>
	    	// thead.appendTo(table);
	    	var childOf = $("<div class='btn-group pull-right'></div>");
	    	nav.html(childOf);
	    	
	    	if(currpage > 1) {
	    		var button_1 = $("<button class='btn btn-white nav_button'>1</button>");
	    		button_1.appendTo(childOf);
	    	}
	    	if(currpage - 2 > 1) {
	    		var button_blank_1 = $("<button class='btn btn-white'>...</button>");
	    		button_blank_1.appendTo(childOf);
	    		var button_2 = $("<button class='btn btn-white nav_button'>" + (currpage - 2) + "</button>");
	    		button_2.appendTo(childOf);
	    	}
	    	if(currpage - 1 > 1) {
	    		var button_3 = $("<button class='btn btn-white nav_button'>" + (currpage - 1) + "</button>");
	    		button_3.appendTo(childOf);
	    	}
	    	var button_curr = $("<button class='btn btn-white active'>" + currpage + "</button>");
	    	button_curr.appendTo(childOf);
	    	if(currpage + 1 < totalpage) {
	    		var button_4 = $("<button class='btn btn-white nav_button'>" + (currpage + 1) + "</button>");
	    		button_4.appendTo(childOf);
	    	}
	    	if(currpage + 2 < totalpage) {
	    		var button_5 = $("<button class='btn btn-white nav_button'>" + (currpage + 2) + "</button>");
	    		button_5.appendTo(childOf);
	    		var button_blank_2 = $("<button class='btn btn-white'>...</button>");
	    		button_blank_2.appendTo(childOf);
	    	}
	    	if(currpage < totalpage) {
	    		var button_6 = $("<button class='btn btn-white nav_button'>" + totalpage + "</button>");
	    		button_6.appendTo(childOf);
	    	}
	    	
	    	// 分页导航事件
	    	$(".nav_button").click(function() {
		    	var currpage = $(this).text();
		    	$.ajax({
					type : "GET",
					url : "/docs-manager/team/scan",
					data : {
						'currpage' : currpage,
						'perpage' : 10,
					},
					dataType : "json",
					success : function(data) {
						showTeamPage(data);
					},
					error : function() {
						alert("error");
					}
				});
		    });
	    }
	    
	    // 根据指定的数据在指定对象下画一个表格
		function showTeamTable(childOf, datas) {
			// 表格整体
			var table = $("<table class='table table-bordered table-hover'></table>");
			childOf.html(table);
			// 表格标题
			var titles = ['分组标识', '分组名称', '分组别名', '分组描述', '创建日期'];
			var thead = $("<thead></thead>");
			thead.appendTo(table);
			var thead_tr = $("<tr></tr>");
			thead_tr.appendTo(thead);
			var th_null = $("<th></th>");
			th_null.appendTo(thead_tr);
			for ( var o in titles) {
				var th = $("<th>" + titles[o] + "</th>");
				th.appendTo(thead_tr);
			}
			
			// 表格数据
			var tbody = $("<tbody></tbody>");
			tbody.appendTo(table);
			for ( var x in datas) {
				var tbody_tr = $("<tr></tr>");
				tbody_tr.appendTo(tbody);
				var td_data = eval(datas[x]);
				
				// <div class="icheckbox_square-green checked" >
				var checkbox_div = $("<div class='icheckbox_square-green'></div>");
				var checkbox_ins = $("<ins class='iCheck-helper'></ins>");
				var input_td = $("<input>",{type:'checkbox', name:'input[]'});
				input_td.addClass('i-checks');
				var checkbox_td = $("<td></td>");
				input_td.appendTo(checkbox_div);
				checkbox_ins.appendTo(checkbox_div);
				checkbox_div.appendTo(checkbox_td);
				checkbox_td.appendTo(tbody_tr);
				for ( var y in td_data) {
					var tbody_tr_td = $("<td>" + td_data[y] + "</td>");
					tbody_tr_td.appendTo(tbody_tr);
				}
			}
			
			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});
		}
	</script>
</body>

</html>