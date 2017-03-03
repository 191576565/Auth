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
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
	<!--  
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
		
 		<br/><br/>-->
		<div id="sys_add_div" style="margin-left:1% ;">
 			<form id="form" method="post" action="funSave">
				
			<input type="hidden" id="fun_uuid" name="fun_uuid" value="${funuuid }"/>	
			<table class="table table-hover table-bordered">
  				<thead><th>功能名称</th></thead>
   				<tbody>
  				<c:forEach items="${funlist }" var="fun">
  				 
   					<tr>
   					<td>
   					<input name="fun_list" id="fun_list"  type="checkbox"  value="${fun.uuid }" style="margin-left: 9%;"/>${fun.res_name }
   					</td>

   					</tr>
  				 </c:forEach>
   				</tbody>
  			</table>
  			<div class="row">
 			<div class="col-xs-4">
 				<button id="sys_add" type="submit" class="btn btn-primary create">保存</button>
 				<button id="btn_add" type="button" class="btn btn-danger delete">刷新</button>
 			</div>
 		</div>
 			</form>
 		</div>
	</body>
</html>
