<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">


    	<title> - 用户管理</title>
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

		<link rel="stylesheet" href="${ctxPath }/static/css/bootstrap-multiselect.css"/>
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
						<a href="usrMgmt">用户管理</a>
					</li>
				</ol>
			</div>
		</div>
		<hr />
		
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="btn_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="btn_del" type="button" class="btn btn-danger delete">删除</button>
 				<button id="btn_reset" type="button" class="btn btn-reset delete" style="margin:0 0 0 1px">重置密码</button>
 			</div>
			<div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input id="iptSearch" type="text" placeholder="输入用户编码/用户名称" style="width: 200px;" class="form-control">
					</div>
					<button id="search" class="btn btn-default search"><i class="fa fa-search"></i></button>
				</div>
			</div>
 		</div>
 		<br/>
		
 		<table id="table"></table>
 		
 		<div class="wrapper" id="sys_add_div" style="display:none;width:100%;margin:0px;"><br />
 		<div class="col-sm-12"> 
 			<form ng-app="myApp"
 				ng-controller="validateCtrl"
 				id="form" 
 				name="myForm"
 				action="" 
 				method="post" 
 				novalidate>
 				<div class="col-sm-12">
 				<div class="form-group col-sm-6">
					<label>所属域</label>
					<select class="form-control col-sm-6" name="domain" id="domain"></select>
				</div>
				<div class="form-group col-sm-6">
					<label>所属机构</label>
					<select class="form-control" nullName="所属机构" name="organization" id="organization"></select>
				</div>
				
 				<div class="form-group col-sm-6">
 					<input id="uuid" 
 						name="uuid" 
 						type="hidden" 
 					/>
					<label>用户编码</label> 
					<input name="scopeCode" 
						   type="text"
						   class="form-control notNull"
						   nullName="用户编码"	
						   id="scopeCode"
						   placeholder="1~30位数字/字母"
						   ng-model="scopeCode"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="error" ng-if="(myForm.scopeCode.$error.minlength ||
						myForm.scopeCode.$error.maxlength) && 
						myForm.scopeCode.$touched">
					用户编码长度应在1~30位之间</p>
					<p class="error" ng-if="myForm.scopeCode.$error.pattern &&
						myForm.scopeCode.$touched">
					只能是字母/数字组合</p>
					<p id="chkUserError" class="error"></p>
				</div>
				<div class="form-group col-sm-6">
					<label>用户名称</label> 
					<input name="usrName" 
						   type="text" 
						   id="usrName"
						   class="form-control notNull" 
						   nullName="用户名称"
						   placeholder="请输入用户名称"
						   ng-model="usrName"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="error" ng-if="(myForm.usrName.$error.minlength ||
						myForm.usrName.$error.maxlength) && 
						myForm.usrName.$touched">
				 	长度应在1~30位之间</p>
				</div>
				<div class="form-group col-sm-6">
					<input name="usrName" style="display:none"/>
				</div>
				<div class="form-group col-sm-6">
					<label>角色</label>
					<select class="form-control" nullName="角色" name="role" id="role" multiple></select>
				</div>
				<div class="form-group col-sm-6">
					<label>手机号</label> 
					<input name="phone" 
						   type="text" 
						   id="phone"
						   class="form-control" 
						   ng-model="phone"
						   ng-pattern="/^1[34578]\d{9}$/"
						   required
					/>
					<p class="error" ng-if="myForm.phone.$error.pattern &&
						myForm.phone.$touched">
					手机号填写错误</p>
				</div>
				<div class="form-group col-sm-6">
					<label>邮箱</label> 
					<input name="email" 
						   type="text" 
						   id="email"
						   class="form-control" 
						   ng-model="email"
						   ng-pattern="/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/"
						   required
					/>
					<p class="error" ng-if="myForm.email.$error.pattern &&
						myForm.email.$touched">
					邮箱填写错误</p>
				</div>
				</div>
 			</form>
 			</div>
 		</div>

	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctxPath }/static/js/angular.min.js"></script>
    <script src="${ctxPath }/static/js/jquery-form.js"></script>
    <script src="${ctxPath }/static/js/placeholder_plugin.js"></script>
    <!-- 自定义js -->
    <script src="${ctxPath }/static/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${ctxPath }/static/js/bootstrap-multiselect.js"></script>
    
    <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>

    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/usrMgmt.js"></script>
    <script>
    $(document).ready(function () { 
		 inittable();
		 //条件搜索
		 $("#search").bind("click", inittable);
		 $('input').placeholder();
	})
	//表单验证
	var app = angular.module('myApp', []);
	app.controller('validateCtrl',function($scope){
	});
    </script>
	</body>
</html>
