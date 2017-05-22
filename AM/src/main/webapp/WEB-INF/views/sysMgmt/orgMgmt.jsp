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
    	<link href="${ctxPath }/static/css/plugins/treegrid/jquery.treegrid.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/animate.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/style.css?v=4.1.0" rel="stylesheet">

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
						<a href="sysMgmt">系统管理</a>
					</li>
						<li>
						<a href="orgMgmt">机构管理</a>
					</li>
				</ol>
			</div>
		</div>
		<hr/>
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="org_add" type="button" class="btn btn-primary create">新增</button>
 			</div>
 		</div>
 		<br/>
 		<table id="table"  class="table table-striped table-bordered table-hover">
 			<thead>
				<tr>
					<th>机构编码</th>
					<th>域名称</th>
					<th>机构名称</th>
					<th>上级机构</th>
					<th style="width:130px">操作</th>
				</tr>
			</thead>
			<tbody>
                 
            </tbody>
 		</table>
 		<div class="t" style="display: none;">
 			<ul id="org" class="ztree"></ul>
 		</div>
 		<div class="wrapper" id="org_add_div" style="display:none;">
 			<form ng-app="myApp"
 				ng-controller="validateCtrl"
 				id="form" 
 				name="myForm" 
 				action="" 
 				method="post"
 				novalidate>
 				<div class="form-group">
 					<input id="uuid" 
 						name="UUID" 
 						type="hidden" 
 					/>
				</div>
				<div class="form-group">
					<label>域名称</label> 
					<input id="domain_uuid" 
 						name="domainUUID" 
 						type="hidden" 
 					/>
					<input name="scopeName" 
						   type="text"
						   class="form-control"
						   id="scop_n"
						   disabled="true"
					/>
				</div>
				<div class="form-group">
					<label>机构编码</label> 
					<input name="orgCode"
						   class="form-control notNull"
						   nullName="机构编码"
						   id="org_code"
						   ng-model="orgCode"
						   ng-minlength="1"
						   ng-maxlength="30"
						   ng-pattern="/^[A-Za-z0-9]+$/"
						   required
					/>
					<p class="error" ng-if="(myForm.orgCode.$error.minlength ||
						myForm.orgCode.$error.maxlength) && 
						myForm.orgCode.$touched">
					长度应在1~30位之间</p>
					<p class="error" ng-if="myForm.orgCode.$error.pattern &&
						myForm.orgCode.$touched">
					只能是字母/数字组合</p>
				</div>
				<div class="form-group" ng-class="{ 'has-success': signUpForm.orgName.$valid }">
					<label>机构名称</label> 
					<input name="orgName"
						   class="form-control notNull"
						   nullName="机构名称"
						   id="org_name"
						   ng-model="orgName"
						   ng-minlength="1"
						   ng-maxlength="30"
						   required
					/>
					<p class="error" ng-if="(myForm.orgName.$error.minlength ||
						myForm.orgName.$error.maxlength) && 
						myForm.orgName.$touched">
					长度应在1~30位之间</p>
				</div>
				<div class="form-group">
					<label>上级机构</label> 
					<div class="input-group">
						<input type="hidden" name="org_up_uuid" id="org_up_uuid">
			            <input type="text" class="form-control notNull" nullName="上级机构" name="org_up_name" id="up_org_name" required readonly="readonly"> 
			            <span class="input-group-btn"> 
			            	<button type="button" class="btn tree" style="height: 34px"><i class="fa fa-tree"></i></button> 
			            </span>
		            </div>
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
    
    <!-- treegrid -->
    <script src="${ctxPath }/static/js/plugins/treegrid/jquery.treegrid.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
    
    <!-- ztree -->
    <script src="${ctxPath }/static/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    <!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/orgMgmt.js"></script>
    <script>
	//表单验证
	var app = angular.module('myApp', []);
	app.controller('validateCtrl',function($scope){
	});
    </script>
	</body>
</html>
