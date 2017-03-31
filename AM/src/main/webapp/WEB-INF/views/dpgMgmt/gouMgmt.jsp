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
		<link href="${ctxPath }/static/css/plugins/ztree/metroStyle/metroStyle.css" rel="stylesheet">
		<link href="${ctxPath }/static/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
 		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="${ctxPath }/static/welcome.jsp">首页</a>
					</li>
					<li>
						<a href="${ctxPath }/dpgMgmt"/>数据权限组管理</a>
					</li>
					<li>
						<a href="gouMgmt?groupuuid=${groupuuid}">权限组管理</a>
					</li>
				</ol>
			</div>
		</div>
		<br/>
 		<div class="row">
 			<div class="col-xs-4">
 				<button id="sys_add" type="button" class="btn btn-primary create">新增</button>
 				<button id="btn_add" type="button" class="btn btn-danger delete">删除</button>
 			</div>
 			  <div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input type="text" placeholder="请输入系统名称" style="width: 200px;" id="search" class="form-control">
					</div>
					<button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
				</div><br/>
			</div>
 		</div>
 		<div class="wrapper" id="sys_add_div" style="display:none;">
 			<form id="form">
 				<div class="form-group col-sm-12">
	 			    <label>组编码</label> 
	 			    <input id="groupid"  name="groupid" readonly="true" class="form-control" type="text">
	 			    <input id="groupUuid" name="groupUuid" type="hidden">
	 			</div>
	 			<div class="form-group col-sm-12">
 		     		<label>条件类型</label> 
 		     		<select id="urlid" name="urlid" class="form-control">
 		     			<c:forEach items="${condition }" var="dlist">
						<option value="${dlist.dict_id }">${dlist.dict_name }</option>
						</c:forEach>
 		     		</select>
				</div>
				<div class="form-group col-sm-12">
	 		     	<label>URL描述</label> 
					<select id="dictcode" name="dictcode" nValidate="{required:true}" class="form-control">
		 			</select>
	 			</div>
				<div class="form-group col-sm-12">
					<input id="urlname"  name="urlname"  class="form-control" readonly="true" type="hidden">
				</div>
 			</form>
 				<div class="form-group col-sm-12">
					<label>条件值</label> 
					<div>
						<button　type="button" class="btn btn-block btn-default tree">选择条件值</button>
					</div>
				</div>
 		</div>
 		<table id="table"></table>
 		<div id="opn_tree" class="ibox float-e-margins" style="display:none;">
                <div class="ibox-content">
                    <div id="treeview12" class="test"></div>
                </div>
            </div>
 		<div id="sys_user_div" style="display:none;">
 			<form id="userform"></br>
 		     	<div id="user_in_div" style="margin-left: 2%;"></div>
 			</form>
 		</div>
 		<div class="t" style="display:none;">
 			<ul id="org" class="ztree"></ul>
 		</div>
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
    
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
    <script src="${ctxPath }/static/js/gouMgmt.js"></script>
	</body>
</html>
