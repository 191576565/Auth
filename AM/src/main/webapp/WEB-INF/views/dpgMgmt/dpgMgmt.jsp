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
		<!-- Ztree style -->
		<link href="${ctxPath }/static/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">

        <style type="text/css">
            label {
                display: inline-block;
                max-width: 100%;
                margin-bottom: 5px;
                font-weight: 700;
            }
        </style>
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
 		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="${ctxPath }/static/welcome.jsp">首页</a>
					</li>
					<li>
						<a href="dpgMgmt">数据权限组管理</a>
					</li>
				</ol>
			</div>
		</div>
		<hr />
 		<div class="row">
 			<div class="col-xs-4" >
 				<button id="sys_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="delete" type="button" class="btn btn-danger delete">删除</button>
 			</div>
 			 <div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input id="iptSearch" type="text" placeholder="输入组编码/组名称" style="width: 200px;" class="form-control">
					</div>
					<button id="search" class="btn btn-default search"><i class="fa fa-search"></i></button>
				</div>
			</div>
		</div>
 		<br />
 		<div class="wrapper" id="sys_add_div" style="display:none;">
 			<form ng-app="myApp"
 				ng-controller="validateCtrl"
 				id="form"
 				name="myForm"
 				novalidate>
 				<input type="hidden" name="uuid" id="uuid"/>
				<div class="form-group col-sm-12">
                     <label>所属域:</label>
                     <div >
                         <select class="form-control" name="domaininfo" style="border-radius: 2px;" id="domain_id"  required>
                         	<option value="">请选择域</option>
                        </select>
                     </div>
                 </div>
                 <div class="form-group col-sm-12">
                     <label>组编码:</label>
                     <div>
                         <input 
                         	name="groupid"
                         	type="text" 
                         	class="form-control notNull"
                         	nullName="组编码"
                         	id="group_id"
                         	ng-model="groupid"
                         	ng-minlength="1"
                         	ng-maxlength="32"
                         	ng-pattern="/^[A-Za-z0-9]+$/"
                         	required /> 
                         	<p class="error" ng-if="(myForm.groupid.$error.minlength ||
						myForm.groupid.$error.maxlength) && 
						myForm.groupid.$touched">
					长度应在1~32位之间</p>
					<p class="error" ng-if="myForm.groupid.$error.pattern &&
						myForm.groupid.$touched">
					只能是字母/数字组合</p>
                     </div>
                 </div>
                 <div class="form-group col-sm-12">
                     <label>组名称:</label>
                     <div>
                         <input 
                         	type="text" 
                         	class="form-control notNull" 
                         	nullName="组名称"
                         	id="group_desc"  
                         	name="groupname" 
                         	ng-model="groupname"
                         	ng-minlength="1"
                         	ng-maxlength="32"
                         	required /> 
                         <p class="error" ng-if="(myForm.groupname.$error.minlength ||
						myForm.groupname.$error.maxlength) && 
						myForm.groupname.$touched">
					长度应在1~32位之间</p>	
                     </div>
                 </div>
 			</form>
 				<div class="form-group col-sm-12">
                     <label>所属用户:</label>
                     <div>
                         <button class="btn btn-block btn-default users">选择用户</button>
                     </div>
                 </div>
 		</div>
 		<table id="table"></table>
 		<div class="t" style="display:none;">
 			<ul id="tree" class="ztree"></ul>
 		</div>
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctxPath }/static/js/angular.min.js"></script>
    <script src="${ctxPath }/static/js/placeholder_plugin.js"></script>
    
    <!-- Bootstrap-Treeview plugin javascript-->
    <script src="${ctxPath }/static/js/bootstrap-treeview.js" type="text/javascript"></script>
   
    <!-- 自定义js -->
    <script src="${ctxPath }/static/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    
   　<!-- ztree -->
    <script src="${ctxPath }/static/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    
    <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
    
    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/dpgMgmt.js"></script>
    <script>
    $(document).ready(function () { 
    	initdpgMgmtlist();
		 //条件搜索
		 $("#search").bind("click", initdpgMgmtlist);
         $('input').placeholder();
	})
	//表单验证
	var app = angular.module('myApp', []);
	app.controller('validateCtrl',function($scope){
	});
    </script>
	</body>
</html>
