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
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
 		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="${ctxPath }/static/welcome.jsp">首页</a>
					</li>
					<li>
						<a href="resMgmt">资源管理</a>
					</li>
				</ol>
			</div>
		</div>
		<br /><br /><br /><br />
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="res_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="res_del" type="button" class="btn btn-danger delete">删除</button>
 			</div>
 			<div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input type="text" placeholder="请输入资源名称" style="width: 200px;" id="search" class="form-control">
					</div>
					<button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
				</div>
			</div>
 		</div>
 		<table id="table"></table>
 		<div id="sys_add_div" style="display:none;">
 			<form id="form" ng-submit="submitForm()" action="" method="post"></br>
				<input id="uuid" 
 						name="res.uuid" 
 						type="hidden" 
 				/>
				<label>资源编码</label> <input id="res_id" name="res.res_id" value="${res.res_id }" type="text" placeholder="1~30位字母/数字/下划线" ></br></br><!--字母和数字(1~30位) -->
				<label>资源名称</label> <input id="res_name" name="res.res_name" value="${res.re_name }" type="text" placeholder="请输入角色名" ></br></br>
				<label>上级资源名称</label> <input id="res_up_uuid" name="res.res_up_uuid" value="" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源url</label> <input id="res_url" name="res.res_url" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源类型</label> 
				<select style="width: 120px;">
					<option value="1">叶子</option>
					<option value="0">节点</option>
				</select></br>
				<label>资源CSS</label> <input id="res_class" name="res.res_class" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源颜色</label> 
				<select style="width: 120px;">
					<option value="red">red</option>
					<option value="yello">yello</option>
				</select></br>
				<label>资源图标</label> <input id="res_icon" name="res.res_icon" type="text" placeholder="请输入角色名" ></br></br>
				<label>排序编号</label> <input id="sort_id" name="res.sort_id" type="text" placeholder="请输入角色名" ></br></br>
				<button id="resave" type="submit" class="btn btn-info ladda-button save" >保存</button>
 			</form>
 		</div>
 		<div id="sys_del_div" class="form-group" style="display:none;">
 			<form id="del_form" action="" method="post">
 				<input type="hidden" id="deluuid" name="uuid"/>
					确定要删除该系统信息吗？
				</br>
				<button id="resdelete" type="submit" class="btn btn-danger delete">确定删除</button>
			</form>
		</div>
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/jquery-form.js"></script>
   
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctxPath }/static/js/angular.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctxPath }/static/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
     <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/resMgmt.js"></script>
	</body>
</html>
