<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>展示现有的文件列表</title>
    <meta name="keywords" content="文件,列表">
    <meta name="description" content="展示符合当前权限的文件列表">
    <link rel="shortcut icon" href="${domain}/hplus/favicon.ico"> 
    <link href="${domain}/hplus/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${domain}/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${domain}/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${domain}/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${domain}/hplus/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12 animated fadeInRight">
            	<div class="ibox float-e-margins">
	                <div class="ibox-title">
						<h5>文档列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
	                <div class="ibox-content">
	                	<div class="row">
	                		<div class="col-sm-5">
	                			<button type="button" class="btn btn-sm btn-primary glyphicon glyphicon-plus">
								</button>
								<button type="button" class="btn btn-sm btn-danger glyphicon glyphicon-remove">
								</button>
	                		</div>
							<div class="col-sm-7">
								<div class="row">
									<input type="hidden" name="uid" id="uid" value="${uid}">
									<div class="col-md-3">
										<select class="form-control m-b" name="search_teamId"
											id="search_teamId">
										</select>
									</div>
									<div class="col-md-3">
										<input id="search_input" type="text" placeholder="请输入关键字"
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
		                    <table class="table table-hover table-mail" >
		                    	<thead>
		                    		<tr>
		                    			<th></th>
		                    			<th>文件标识</th>
		                    			<th>文件名称</th>
		                    			<th>所属分组</th>
		                    			<th>展示权限</th>
		                    			<th>下载权限</th>
		                    			<th>下载码</th>
		                    			<th>上传作者</th>
		                    			<th>上传日期</th>
		                    		</tr>
		                    	</thead>
		                    </table>
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
        </div>
        <!-- 新增或者修改对话框 -->
		<div class="modal fade" id="plus_dialog" tabindex="-1" role="dialog"
			aria-labelledby="plus_dialog_label">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="plus_dialog_title">New</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="userId" value="${uid}" >
						<div class="row">
						<div role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">文件名</label> 
								<div class="col-sm-10">
									<input type="text" name="fileName" id="fileName" 
										placeholder="请输入文件名" class="form-control"> 
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">文件描述</label>
								<div class="col-sm-10">
									<textarea rows="2" cols="" class="form-control" id="fileDesc"></textarea> <span
										class="help-block m-b-none">文本形式的中文分组信息描述，64个字符以内。</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">浏览权限</label>
								<div class="col-sm-10">
									<select class="form-control m-b" name="showId" id="showId">
										<option value="0" >仅自己可见</option>
										<option value="1" >仅本组可见</option>
										<option value="2" selected="selected" >所有人可见</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">下载权限</label>
								<div class="col-sm-10">
									<select class="form-control m-b" name="downId" id="downId">
										<option value="0" selected="selected" >仅自己可下载</option>
										<option value="1" >仅本组可下载</option>
										<option value="2" >所有人可下载</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">下载码</label> 
								<div class="col-sm-10">
									<input type="text" name="downCode" id="downCode" 
										placeholder="请输入下载码" class="form-control" value=""> 
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">上传文件</label> 
								<div class="col-sm-10">
									<input type="file" name="uploadFile" id="uploadFile" 
										placeholder="选择上传文件" class="form-control" > 
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
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
    <script src="${domain}/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="${domain}/hplus/js/ajaxfileupload.js"></script>
    <script src="${domain}/hplus/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${domain}/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="${domain}/hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    	// 1 文件加载展示数据
		$.ajax({
			type : "GET",
			url : "/docs-manager/file/scan",
			data : {
				'search_userId' : $("#uid").val(),
			},
			dataType : "json",
			success : function(data) {
				showFilePage(data);
			},
			error : function() {
				alert("error");
			}
		});
    	
	    // 2 跳转到新增数据对话框 
		$(".glyphicon-plus").click(function() {
			$("#fileName").val("");
			$("#fileDesc").val("");
			
			$("#plus_dialog_title").text("新增");
			$('#plus_dialog').modal();
		});
	    
	    // 3 上传文件{ Id: '123', name: 'lunis' }
		$(".glyphicon-floppy-save").click(function() {
			$.ajaxFileUpload({
				type : "POST",
				url : "/docs-manager/file/upload?showId=" + $("#showId").val()
						+ "&downId=" + $("#downId").val() 
						+ "&downCode=" + $("#downCode").val()
						+ "&userId=" + $("#userId").val()
						+ "&fileDesc=" + $("#fileDesc").val(),
				secureuri:false,
				fileElementId:'uploadFile',
				dataType : "json",
				success : function(data) {
					showFilePage(data);
				},
				error : function() {
					alert("error");
				}
			});
		});
		
	    // 4  数据查询操作
		$(".input-group-btn").click(function() {
			$.ajax({
				type : "GET",
				url : "/docs-manager/file/scan",
				data : {
					'currpage' : 1,
					'perpage' : 10,
					'search_fileName' : $("#search_input").val(),
					'search_userId' : $("#uid").val(),
					'search_teamId' : $("#search_teamId").val(),
				},
				dataType : "json",
				success : function(data) {
					showFilePage(data);
					// location.reload();
				},
				error : function() {
					alert("error");
				}
			});
		});
    	
    	// 展示文件列表
    	function showFilePage(data) {
			var filePojoList = eval(data['datas']);
			var count = Number(data['count']);
			var currpage = Number(data['currpage']);
			var totalpage = Number(data['totalpage']);
			var search_fileName = data['search_fileName'];
			var teamIdList = eval(data['teamIdList']);
			
			$("#count_button").text("总共 " + count + " 条记录");
			var nav = $(".navigation");
			navigation(nav, currpage, totalpage);
			
			$("#search_teamId").empty();
			$("#search_teamId").append("<option value='" + -1 + "'>" + "不限" + "</option>"); 
			for ( var o in teamIdList) {
				var teamData = eval(teamIdList[o]);
				$("#search_teamId").append("<option value='" + teamData['id'] + "'>" + teamData['teamByname'] + "</option>"); 
			}
			
			var childOf = $(".table-responsive");
			showFileTable(childOf, filePojoList);
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
					url : "/docs-manager/file/scan",
					data : {
						'currpage' : currpage,
						'perpage' : 10,
					},
					dataType : "json",
					success : function(data) {
						showFilePage(data);
					},
					error : function() {
						alert("error");
					}
				});
		    });
	    }
    	
    	// 根据指定的数据在指定对象下画一个表格
		function showFileTable(childOf, datas) {
			// 表格整体
			var table = $("<table class='table table-hover table-mail'></table>");
			childOf.html(table);
			// 表格标题
			var titles = ['文件标志', '文件名称', '文件大小（k）', '所属分组', '展示权限', '下载权限', '下载码', '上传作者', '上传日期'];
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
				
				// ['文件标志', '文件名称', '所属分组', '展示权限', '下载权限', '下载码', '上传作者', '上传日期'];
				var tbody_tr_td1 = $("<td>" + td_data['id'] + "</td>");
				tbody_tr_td1.appendTo(tbody_tr);
				var tbody_tr_td2 = $("<td>" + td_data['fileName'] + "</td>");
				tbody_tr_td2.appendTo(tbody_tr);
				var tbody_tr_td_size = $("<td>" + td_data['fileDesc'] + "</td>");
				tbody_tr_td_size.appendTo(tbody_tr);
				
				var tbody_tr_td3 = $("<td>" + td_data['teamByname'] + "</td>");
				tbody_tr_td3.appendTo(tbody_tr);
				
				var showId = Number(td_data['showId']);
				var showIdText = '';
				if(showId == 0) {
					showIdText = '仅自己可见';
				} else if(showId == 1) {
					showIdText = '仅本组可见';
				} else {
					showIdText = '所有人可见';
				}
				var tbody_tr_td4 = $("<td>" + showIdText + "</td>");
				tbody_tr_td4.appendTo(tbody_tr);
				
				var downId = Number(td_data['downId']);
				var downIdText = '';
				if(downId == 0) {
					downIdText = '仅自己可下载';
				} else if(downId == 1) {
					downIdText = '仅本组可下载';
				} else {
					downIdText = '所有人可下载';
				}
				var tbody_tr_td5 = $("<td>" + downIdText + "</td>");
				tbody_tr_td5.appendTo(tbody_tr);
				var tbody_tr_td6 = $("<td>" + td_data['downCode'] + "</td>");
				tbody_tr_td6.appendTo(tbody_tr);
				var tbody_tr_td7 = $("<td>" + td_data['userName'] + "</td>");
				tbody_tr_td7.appendTo(tbody_tr);
				var tbody_tr_td8 = $("<td>" + td_data['createDate'] + "</td>");
				tbody_tr_td8.appendTo(tbody_tr);
			}
			
			$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});
		}
    });
    </script>
</body>
</html>