<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>- Bootstrap Table</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="${ctxPath }/static/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctxPath }/static/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${ctxPath }/static/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${ctxPath }/static/css/animate.css" rel="stylesheet">
<link href="${ctxPath }/static/css/style.css?v=4.1.0" rel="stylesheet">
<!-- Toastr style -->
<link href="${ctxPath }/static/css/plugins/toastr/toastr.min.css"
	rel="stylesheet">
<link href="${ctxPath }/static/css/plugins/select2/select2.min.css"
	rel="stylesheet">
<!-- Ladda style -->
<link
	href="${ctxPath }/static/css/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet">
<link
	href="${ctxPath }/static/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<!-- Sweet Alert -->
<link href="${ctxPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link
	href="${ctxPath }/static/css/plugins/ztree/metroStyle/metroStyle.css"
	rel="stylesheet">

<script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
</head>
<body class="panel-body" style="padding-bottom: 0px;">
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="${ctxPath }/static/welcome.jsp">首页</a></li>
				<li><a href="uptPwd">密码更改</a></li>
			</ol>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<div  class="col-sm-4"  style="margin:0 auto;float:none">
		<div  class="ibox float-e-margins">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>修改密码</h5>
				</div>
			</div>
			<div class="ibox-content">
				<div class="row">
					<div class="col-sm-12">
						<form method="post" action="uptPwd/modifpwd" role="form"
							onsubmit="return checkForm()">
							<div class="form-group">
								<label>旧密码</label> <input id="oldpassword" name="oldpassword"
									type="password" placeholder="请输入旧密码" class="form-control"
									onblur="oldpcheck()">
								<p id="oldpmessage" class="error"></p>
							</div>
							<div class="form-group">
								<label>新密码</label> <input id="newpassword" name="newpassword"
									type="password" placeholder="请输入新密码" class="form-control"
									onblur="newplencheck()">
								<p id="newplenmessage" class="error"></p>
							</div>
							<div class="form-group">
								<label>确认密码</label> <input id="renewpassword"
									name="renewpassword" type="password" placeholder="请确认新密码"
									class="form-control" onblur="newpcheck()">
								<p id="newpmessage" class="error"></p>
							</div>
							<div>
								<button class="btn btn-sm btn-primary pull-right m-t-n-xs"
									type="submit">
									<strong>保 存</strong>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function oldpcheck() {
		var result = false;
		$.ajax({
			type : "post",
			dataType : "html",
			url : "uptPwd/oldpwdCheck",
			data : {
				"oldpassword" : $("#oldpassword").val()
			},
			async : false,
			cache : false,
			success : function(data) {

				if (data == "false") {
					$("#oldpmessage").html("密码错误");
					result = false;
				} else {
					$("#oldpmessage").html("");
					result = true;
				}

			}
		});
		return result;
	}

	function newplencheck() {
		var s = $("#newpassword").val();
		var patrn = /^[A-Za-z0-9]{6,20}$/;
		if (!patrn.exec(s)) {
			$("#newplenmessage").html("密码长度不符合，至少为6位");
			return false;
		} else {
			$("#newplenmessage").html("");
			return true;
		}
	}
	function newpcheck() {

		var s1 = $("#newpassword").val();
		var s2 = $("#renewpassword").val();
		if (s1==s2){
			$("#newpmessage").html("");
			return true;
		}else{
			$("#newpmessage").html("两次输入密码不一致");
			return false;
		}

	}
	function checkForm() {
		//var m1=$("#oldpmessage").html();
		//var m2=$("#newpmessage").html();
		var m1 = oldpcheck();
		var m2 = newplencheck();
		var m3 = newpcheck();
		//if (m1=="" && m2 == ""){
		if (m1 && m2 && m3) {		
			return true;
		} else {
			return false;
		}
	}
	function newpcheckwww() {
		var result = false;
		$.ajax({
			type : "post",
			url : "uptPwd/newpCheck",
			dataType : "html",
			data : {
				"newpassword" : $("#newpassword").val(),
				"renewpassword" : $("#renewpassword").val()
			},
			async : false,
			cache : false,
			success : function(data) {

				if (data == "false") {
					$("#newpmessage").html("");
					result = false;
				} else {
					$("#newpmessage").html("");
					result = true;
					console.log("resulat1:" + result)
				}

			}
		});
		console.log("resulat2:" + result)
		return result;
	}
	function oldplencheckwww() {
		var result = false;
		$.ajax({
			type : "post",
			url : "uptPwd/newplenCheck",
			dataType : "html",
			data : {
				"newpassword" : $("#newpassword").val(),
				"renewpassword" : $("#renewpassword").val()
			},
			async : false,
			cache : false,
			success : function(data) {

				if (data == "false") {
					$("#newplenmessage").html("密码长度不符合");
					result = false;
				} else {
					$("#newplenmessage").html("");
					result = true;
					console.log("resulat1:" + result)
				}

			}
		});
		console.log("resulat2:" + result)
		return result;
	}
</script>
</html>
