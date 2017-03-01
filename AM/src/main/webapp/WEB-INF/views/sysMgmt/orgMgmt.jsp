<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">


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
		<style type="text/css">
			.input_result {
				position: relative;
				top: -27px;
				left: 880px;
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
						<a href="sysMgmt">系统管理</a>
					</li>
						<li>
						<a href="orgMgmt">机构管理</a>
					</li>
				</ol>
			</div>
		</div>
		<br/><br/><br/><br/>
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="org_add" type="button" class="btn btn-primary create">新增</button>
 			</div>
 		</div>
 		<table id="table"></table>
 		<div class="wrapper" id="org_add_div" style="display:none;" ng-app="myApp" ng-controller="SignUpController">
 			<form id="form" name="signUpForm" ng-submit="submitForm()" action="" method="post">
 				<div class="form-group" ng-class="{ 'has-success': signUpForm.scopeCode.$valid }">
 					<input id="uuid" 
 						name="UUID" 
 						type="hidden" 
 					/>
				</div>
				<div class="form-group">
					<label>域名称</label> 
					<input name="scopeName" 
						   type="text"
						   disabled="true"
						   class="form-control"
						   id="scop_n"
					/>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.orgCode.$valid }">
					<label>机构编码</label> 
					<input name="orgCode"
						   class="form-control"
						   id="org_code"
						   ng-model="userdata.orgCode"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.orgCode.$valid"></p>
					<p class="error" ng-if="(signUpForm.orgCode.$error.minlength ||
						signUpForm.orgCode.$error.maxlength) && 
						signUpForm.orgCode.$touched">
					长度应在1~30位之间</p>
					<p class="error" ng-if="signUpForm.orgCode.$error.pattern &&
						signUpForm.orgCode.$touched">
					只能是字母/数字组合</p>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.orgName.$valid }">
					<label>机构名称</label> 
					<input name="orgName"
						   class="form-control"
						   id="org_name"
						   ng-model="userdata.orgName"
						   ng-minlength="1"
						   ng-maxlength="30"
						   required
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.orgCode.$valid"></p>
					<p class="error" ng-if="(signUpForm.orgName.$error.minlength ||
						signUpForm.orgName.$error.maxlength) && 
						signUpForm.orgName.$touched">
					长度应在1~30位之间</p>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.upOrg.$valid }">
					<label>上级机构</label> 
					<select id="up_org" name="upOrg" class="form-control">
					</select>
				</div>
				<div class="form-group">
					<lable>备注</lable>
					<textarea id="ipt_memo" class="form-control" name="memo" rows="" cols=""></textarea>
				</div>
				<div class="form-group">
					<button id="sub" class="btn btn-primary">保存</button>
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
    <script src="${ctxPath }/static/js/orgMgmt.js"></script>
	</body>
</html>
