<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">


    	<title> - Bootstrap Table</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">

    	<link rel="shortcut icon" href="favicon.ico"> <link href="${ctxPath }/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
   		<link href="${ctxPath }/static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    	<link href="${ctxPath }/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/animate.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/style.css?v=4.1.0" rel="stylesheet">
    	<!-- Toastr style -->
		<link href="${ctxPath }/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
		<link href="${ctxPath }/static/css/plugins/select2/select2.min.css" rel="stylesheet">
		<!-- Ladda style -->
		<link href="${ctxPath }/static/css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">
		<link href="${ctxPath }/static/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
		<!-- Sweet Alert -->
		<link href="${ctxPath }/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
		<link href="${ctxPath }/static/css/plugins/ztree/metroStyle/metroStyle.css" rel="stylesheet">
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="${ctxPath }/static/welcome.jsp">首页</a>
					</li>
					<li>
						<a href="rolMgmt">角色管理</a>
					</li>
					<li>
						<a href="funList">功能列表</a>
					</li>
				</ol>
			</div>
		</div>
		<br/><br/>
		<div class="row">
 			<div class="col-xs-4">
 				<button id="sys_add" type="button" class="btn btn-primary create">保存</button>
 				<button id="btn_add" type="button" class="btn btn-danger delete">刷新</button>
 			</div>
 		</div>
 		<br/><br/>
		<div id="sys_add_div" style="margin-left:1% ;">
 			<form id="form">
				<input name="sys" type="checkbox" value=""/>系统服务<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 3%;"/>域管理<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 3%;"/>用户权限<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>用户管理
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>角色管理
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>资源管理
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>数据权限组管理
				<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 3%;"/>系统维护<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>系统日志
				<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 3%;"/>RPM<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>客户管理<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>客户-新增
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>客户-删除
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>客户-编辑
				<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 3%;"/>FTP<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 6%;"/>曲线管理<br/><br/>
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>曲线-新增
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>曲线-删除
				<input name="sys" type="checkbox" value="" style="margin-left: 9%;"/>曲线-编辑

 			</form>
 		</div>
	</body>
</html>
