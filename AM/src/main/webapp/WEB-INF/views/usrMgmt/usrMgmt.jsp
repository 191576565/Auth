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
    	<style type="text/css">
			.input_result {
				position: relative;
				top: -27px;
				left: 870px;
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
		<br /><br /><br /><br />
 		<div class="row">
 			<div class="col-xs-4">
 				<!-- <button type="button" class="btn btn-primary create">重置密码</button> -->
 				<button id="btn_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="btn_del" type="button" class="btn btn-danger delete">删除</button>
 			</div>
 <!-- 			<div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input type="text" placeholder="请输入用户编码" style="width: 200px;" id="search" class="form-control">
					</div>
					<button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
				</div>
			</div> -->
 		</div>
 		<table id="table"></table>
 		<div class="wrapper" id="sys_add_div" style="display:none;" ng-app="myApp" ng-controller="SignUpController">
 			<form id="form" name="signUpForm" ng-submit="submitForm()">
 				<div class="form-group" ng-class="{ 'has-success': signUpForm.scopeCode.$valid }">
 					<input id="uuid" 
 						name="uuid" 
 						type="hidden" 
 						ng-model="userdata.uuid"
 					/>
					<label>用户编码</label> 
					<input name="scopeCode" 
						   id="scopeCode"
						   type="text" 
						   class="form-control" 
						   placeholder="1~30位数字/字母"
						   ng-model="userdata.scopeCode"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.scopeCode.$valid"></p>
					<p class="error" ng-if="signUpForm.scopeCode.$error.required &&
						signUpForm.scopeCode.$touched">
					用户编码不可为空</p>
					<p class="error" ng-if="(signUpForm.scopeCode.$error.minlength ||
						signUpForm.scopeCode.$error.maxlength) && 
						signUpForm.scopeCode.$touched">
					用户编码长度应在1~30位之间</p>
					<p class="error" ng-if="signUpForm.scopeCode.$error.pattern &&
						signUpForm.scopeCode.$touched">
					只能是字母/数字组合</p>
					<p id="chkUserError" class="error"></p>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.scopeName.$valid }">
					<label>用户名称</label> 
					<input name="usrName" 
						   type="text" 
						   id="usrName"
						   class="form-control" 
						   placeholder="请输入用户名称"
						   ng-model="userdata.usrName"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.usrName.$valid"></p>
					<p class="error" ng-if="signUpForm.usrName.$error.required &&
						signUpForm.usrName.$touched">
					不可为空</p>
					<p class="error" ng-if="(signUpForm.usrName.$error.minlength ||
						signUpForm.usrName.$error.maxlength) && 
						signUpForm.usrName.$touched">
				 	长度应在1~30位之间</p>
				 	<p class="error" ng-if="signUpForm.usrName.$error.pattern &&
						signUpForm.usrName.$touched">
					只能是字母/数字组合</p>
				</div>
				<div class="form-group">
					<input name="usrName" style="display:none"/>
				</div>
				<div class="form-group">
					<label>所属域</label>
					<select class="form-control" name="domain" id="domain"></select>
				</div>
				<div class="form-group">
					<label>所属机构</label>
					<select class="form-control"  name="organization" id="organization"></select>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.pwd.$valid }">
					<label>密码</label> 
					<input name="pwd" 
						   type="password" 
						   class="form-control" 
						   placeholder="请输入密码"
						   ng-model="userdata.pwd"
						   ng-minlength="6"
						   ng-maxlength="30"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.pwd.$valid"></p>
					<p class="error" ng-if="signUpForm.pwd.$error.required &&
						signUpForm.pwd.$touched">
					不可为空</p>
					<p class="error" ng-if="(signUpForm.pwd.$error.minlength ||
						signUpForm.pwd.$error.maxlength) && 
						signUpForm.pwd.$touched">
				 	长度应在6~30位之间</p>
				</div>
				<div class="form-group">
					<label>角色</label>
					<select class="form-control">
						<option value="role1">管理员</option>
						<option value="role2">用户</option>
					</select>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.phone.$valid }">
					<label>手机号</label> 
					<input name="phone" 
						   type="text" 
						   id="phone"
						   class="form-control" 
						   ng-model="userdata.phone"
						   ng-pattern="/^1[34578]\d{9}$/"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.phone.$valid"></p>
					<p class="error" ng-if="signUpForm.phone.$error.required &&
						signUpForm.phone.$touched">
					不可为空</p>
					<p class="error" ng-if="signUpForm.phone.$error.pattern &&
						signUpForm.phone.$touched">
					手机号填写错误</p>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.email.$valid }">
					<label>邮箱</label> 
					<input name="email" 
						   type="text" 
						   id="email"
						   class="form-control" 
						   ng-model="userdata.email"
						   ng-pattern="/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.email.$valid"></p>
					<p class="error" ng-if="signUpForm.email.$error.required &&
						signUpForm.email.$touched">
					不可为空</p>
					<p class="error" ng-if="signUpForm.email.$error.pattern &&
						signUpForm.email.$touched">
					邮箱填写错误</p>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" type="submit" id="sub" >保存</button>
				</div>
 			</form>
 		</div>
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctxPath }/static/js/angular.min.js"></script>
    <script src="${ctxPath }/static/js/jquery-form.js"></script>

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
    <script src="${ctxPath }/static/js/usrMgmt.js"></script>
	</body>
</html>
