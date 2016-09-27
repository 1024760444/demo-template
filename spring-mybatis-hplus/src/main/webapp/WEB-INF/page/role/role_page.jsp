<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>${homeData.title}</title>
<meta name="keywords" content="${homeData.keyword}">
<meta name="description" content="${homeData.desc}">

<link rel="shortcut icon" href="favicon.ico">
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
					<div class="ibox-title">
						<h5>角色信息列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-5 m-b-xs"></div>
							<div class="col-sm-4 m-b-xs"></div>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="table-responsive">
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
		// 1 页面展示时，加载表格数据； 根据返回的数据生成表格
	    $(document).ready(function() {
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/spring-mybatis-hplus/role/scan",
				data : {
					'type' : 1,
				},
				dataType : "json",
				success : function(data) {
					var titles = eval(data['titles']);
					var datas = eval(data['datas']);
					
					var childOf = $(".table-responsive");
					showTable(childOf, titles, datas);
				},
				error : function() {
					alert("网络连接出错！");
				}
			});
		});
		
		// 2  生成简单表格
		function showTable(childOf, titles, datas) {
			// 表格整体
			var table = $("<table class='table table-bordered table-hover'></table>");
			childOf.html(table);
			// table.appendTo(childOf);
			// 表格标题
			var thead = $("<thead></thead>");
			thead.appendTo(table);
			var thead_tr = $("<tr></tr>");
			thead_tr.appendTo(thead);
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
				for ( var y in td_data) {
					var tbody_tr_td = $("<td>" + td_data[y] + "</td>");
					tbody_tr_td.appendTo(tbody_tr);
				}
			}
		}
	</script>
</body>

</html>