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
		<!-- Sweet Alert -->
		<link href="${ctxPath }/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
		<link href="${ctxPath }/static/css/plugins/ztree/metroStyle/metroStyle.css" rel="stylesheet">
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="welcome.html">首页</a>
					</li>
					<li>
						<a href="7.html">修改密码</a>
					</li>
				</ol>
			</div>
		</div>
		<br /><br /><br /><br />
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <h5>修改密码</h5>
		            </div>
		        </div>
		        <div class="ibox-content">
		            <div class="row">
		                <div class="col-sm-12">
		                    <form role="form">
		                        <div class="form-group">
		                        	<label>旧密码</label>
		                            <input type="password" placeholder="请输入旧密码" class="form-control">
		                        </div>
		                        <div class="form-group">
		                            <label>新密码</label>
		                            <input type="password" placeholder="请输入新密码" class="form-control">
		                        </div>
		                        <div>
		                            <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>保 存</strong>
		                            </button>
		                        </div>
		                    </form>
		                </div>
		            </div>
		        </div>
	        </div>
        </div>
	</body>
</html>
