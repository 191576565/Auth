<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<link href="${ctxPath }/static/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">
		<style type="text/css">
			.input_result {
				position: relative;
				top: -27px;
				left: 700px;
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
						<a href="rolMgmt">角色管理</a>	
					</li>
				</ol>
			</div>
		</div>
		<br/><br/><br/><br/>
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="sys_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="btn_del" type="button" class="btn btn-danger delete">删除</button>
 			</div>
 		</div>
 		<table id="table"></table>
 		<div class="wrapper" id="sys_add_div" style="display:none;" ng-app="myApp" ng-controller="SignUpController">
 			<form id="form" name="signUpForm" ng-submit="submitForm()" action="" method="post">
 				<div class="form-group" ng-class="{ 'has-success': signUpForm.CustCode.$valid }">
 				<input id="uuid" 
 						name="UUID" 
 						type="hidden" 
 					/>
 					<label>角色编码</label> 
 					<input name="role_id" 
 						   id="role_id"
 						   type="text" 
 						   class="form-control"
 						   placeholder="1~30位字母/数字"
 						   ng-model="userdata.role_id"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
 					/>
 					<p id="" class="fa fa-check input_result success"
						ng-if="signUpForm.role_id.$valid && userdata.role_id!=''"></p>
					<p class="error" ng-if="(signUpForm.role_id.$error.minlength ||
						signUpForm.role_id.$error.maxlength) && 
						signUpForm.role_id.$touched">
					长度应在1~30位之间</p>
					<p class="error" ng-if="signUpForm.role_id.$error.pattern &&
						signUpForm.role_id.$touched">
					只能是字母/数字组合</p>
 				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.role_name.$valid }">
					<label>角色名称</label> 
					<input name="role_name" 
					       id="role_name"
						   type="text" 
						   class="form-control"
						   placeholder="请输入角色名"
						   ng-model="userdata.role_name"
						   required		   
					/>
					<p class="fa fa-check input_result success"
						ng-if="signUpForm.role_name.$valid"></p>
				</div>
				<div class="form-group">
					<label>所属域</label> 
					<select class="form-control" name="domain_uuid" id="domain_uuid">
						<c:forEach items="${domainlist }" var="dlist">
						<option value="${dlist.uuid }">${dlist.domain_name }</option>
						</c:forEach>
						
					</select>
				</div>
				<div class="form-group">
					<lable>备注</lable>
					<textarea id="ipt_memo" class="form-control" name="memo"></textarea>
				</div>
				<div class="form-group">
					<button class="btn btn-info ladda-button save" id="sub">保存</button>
				</div>
 			</form>
 		</div>
 		<div id="sys_del_div" class="form-group" style="display:none;">
 			<form id="del_form" action="" method="post">
 				<input type="hidden" name="UUID" id="role_uuid"/>
				确定要删除该系统信息吗？
				<button id="btn_beSure" class="btn btn-danger delete">删除</button>
			</form>
		</div>
		<div class="t" style="display: none;">
 			<ul id="res" class="ztree"></ul>
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
    <!-- Bootstrap-Treeview plugin javascript -->
    <script src="${ctxPath }/static/js/plugins/treeview/bootstrap-treeview.js"></script>
    
    <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
    
    <!-- ztree -->
    <script src="${ctxPath }/static/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/rolMgmt.js"></script>
	</body>
</html>
