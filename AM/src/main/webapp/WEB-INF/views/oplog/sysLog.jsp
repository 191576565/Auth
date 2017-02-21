<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">


    	<title> - 系统日志</title>
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
						<a href="syslog">系统日志</a>
					</li>
				</ol>
			</div>
		</div>
		<br/><br/><br/><br/>
 		<div class="row">
 			<div class="col-xs-4">
 				<!--<button id="sys_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="btn_add" type="button" class="btn btn-danger delete">删除</button>-->
 			</div>
 			<div class="col-xs-9 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input type="text" placeholder="请输入账号搜索" style="width: 180px;" id="search" class="form-control">
					</div>
					<div class="form-group">
						<input type="text" placeholder="操作类型" style="width: 180px;" id="search" class="form-control">
					</div>
					<div class="form-group">
						<input type="text" placeholder="操作时间起" style="width: 180px;" class="form-control layer-date" id="start">
					</div>
					~
					<div class="form-group">
						<input type="text" placeholder="操作时间止" style="width: 180px;" class="form-control layer-date" id="end">
					</div>
					<button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
				</div><br/>
			</div>
 		</div>
 		<table id="table"></table>
 		<div id="sys_add_div" style="display:none;">
 			新增test用户
 		</div>
 		
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctxPath }/static/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    
    <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/sysLog.js"></script>
    
    <!-- layerDate plugin javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/laydate/laydate.js"></script>
    <script>
        //外部js调用
        laydate({
            elem: '#start', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
        laydate({
            elem: '#end', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });
    </script>
	</body>
</html>
