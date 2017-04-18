<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.jfinal.plugin.activerecord.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>TianJian</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${ctxPath }/static/css/templatemo-style.css">
<link href="${ctxPath }/static/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctxPath }/static/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link
	href="${ctxPath }/static/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="${ctxPath }/static/css/style.css?v=4.1.0" rel="stylesheet">

<link rel="stylesheet" href="${ctxPath }/static/css/normalize.css">
<link rel="stylesheet" href="${ctxPath }/static/css/font-awesome.css">

</head>
<body>
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

	<div id="loader-wrapper">
		<div id="loader"></div>
	</div>

	<div class="content-bg"></div>
	<div class="bg-overlay"></div>

	<!-- SITE TOP -->
	<div class="site-top">
		<div class="site-header clearfix">
			<div class="container">
				<div class="row">
					<div class="col-sm-10">
						<a href="#" class="site-brand pull-left"><strong>TianJian</strong>
							重庆天健金管科技服务有限公司</a>
					</div>
					<div class="col-sm-4 pull-right">
						<i class="fa fa-user" style="color: white;">&nbsp; <%=((Record) session.getAttribute("userinfo")).getStr("user_name")%></i>&nbsp;
						<i class="fa fa-cog" style="color: white; cursor: pointer"
							onclick="modifyuserinfo()">&nbsp;修改用户信息</i>&nbsp; <i
							class="fa fa-cog" style="color: white; cursor: pointer"
							onclick="modifyuserpass()">&nbsp;修改密码</i>&nbsp; <a class=""
							href="ulogin/userexit" style="color: white;"> <i
							class="fa fa-sign-out">&nbsp; 注销</i>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- .site-header -->
	</div>
	<!-- .site-top -->
	<!-- MAIN POSTS -->
	<div id="1" class="main-posts">
		<div id="2" class="container">
			<div id="3" class="row">
				<div id="4" class="blog-masonry masonry-true">
					<!-- 
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb effect">
                                <a href="auth"><img src="${ctxPath }/static/img/Auth_03.png" alt=""></a>
                            </div>
                        </div>
                    
                     
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="http://172.168.171.241:8080/app-rpm/index.html?userid=${userid }&sid=${sid }"><img src="${ctxPath }/static/img/rpm.png" alt=""></a>
                            </div>
                        </div>
                           <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="http://172.168.173.3:9090/index.html?userid=${userid }&sid=${sid }"><img src="${ctxPath }/static/img/static/img/rpm_test.png" alt=""></a>
                            </div>
                        </div>
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="#"><img src="${ctxPath }/static/img/ftp.png" alt=""></a>
                            </div>
                        </div> 
                    -->
				</div>
			</div>
		</div>
	</div>
	<div id="password_mod" class="row"
		 style="display: none; width: 100%; margin: 0px;">
		<div class="col-sm-12">
			<form id="form_modfiypass" method="post" action="uptPwd/modifpwd" role="form"
				>
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
					<label>确认密码</label> <input id="renewpassword" name="renewpassword"
						type="password" placeholder="请确认新密码" class="form-control"
						onblur="newpcheck()">
					<p id="newpmessage" class="error"></p>
				</div>
				<div>
					<button class="btn btn-sm btn-primary pull-right m-t-n-xs" onclick="psubmitFunc();"
						style="margin-right: 2px; margin-bottom: 10px;" type="button">
						<strong>保 存</strong>
					</button>
				</div>
				</br>
			</form>

		</div>

	</div>
	<div id="userinfo_mod" class="row"
		 style="display: none; width: 100%; margin: 0px;">
		<div class="col-sm-12">
			<form id="form_modfiyuser" method="post" role="form"
				>
				<div class="form-group">
					<input id="uuid" 
 						name="uuid" 
 						type="hidden" 
 					/>
					<label>用户编码</label> <input id="userid" name="userid"
						  class="form-control" readonly
					>
					
				</div>
				<div class="form-group">
					<label>用户名称</label> <input id="username" name="username"
						  class="form-control" 
					>
					
				</div>
				<div class="form-group">
					<label>所属域</label> <input id="userdomain" name="userdomain"
						  class="form-control" readonly
					>
					
				</div>
				<div class="form-group">
					<label>所属机构</label> <input id="userorg" name="userorg"
						  class="form-control" readonly
					>
					
				</div>
				<div class="form-group">
					<label>角色</label> <input id="userole" name="userole"
						 class="form-control" readonly
						>
					<p id="newpmessage" class="error"></p>
				</div>
				<div class="form-group">
					<label>手机号</label> <input id="userphone" name="userphone"
						  class="form-control"
					>
					<p id="phonecheck" class="error"></p>
				</div>
				<div class="form-group">
					<label>邮箱</label> <input id="useremail" name="useremail"
						  class="form-control"
					>
					<p id="emailcheck" class="error"></p>
				</div>
				<div>
					<button class="btn btn-sm btn-primary pull-right m-t-n-xs"
						style="margin-right: 2px; margin-bottom: 10px;" onclick="submitFunc();" type="button">
						<strong>保 存</strong>
					</button>
				</div>
				</br>
			</form>

		</div>
		</div>
	<script src="${ctxPath }/static/js/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="${ctxPath }/static/js/vendor/jquery-1.10.2.min.js"><\/script>')
	</script>
	<script src="${ctxPath }/static/js/plugins/min/plugins.min.js"></script>
	<script type="text/javascript" src="${ctxPath}/static/js/index.js"></script>
	<script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
	<!-- Preloader -->
	<script type="text/javascript">
		//<![CDATA[
		$(window).load(function() { // makes sure the whole site is loaded
			$('#loader').fadeOut(); // will first fade out the loading animation
			$('#loader-wrapper').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
			$('body').delay(350).css({
				'overflow-y' : 'visible'
			});
		})
		//]]>

		$.getJSON("getMenu",function(content) {
			var resIcon = '';
			var resUrl = '';
			$.each(content,function(index, data) {//遍历对象数组
				$.each(data, function(key,value) {
					if (key === "res_icon") {resIcon = value;}
					if (key === "res_url") {resUrl = value;}	
				})
				$('#1 #2 #3 #4').append('<div class="post-masonry col-md-4 col-sm-6"><div class="post-thumb effect"><a href="'+ resUrl+ '?userid=${userid }&sid=${sid }"><img src="'+resIcon+'"></a></div></div>');
			});
			$(".effect").mouseover(function() {
				$(this).css({"border" : "2px solid rgba(141,39,142,.75)",
							　	"overflow" : "hidden",
								"position" : "relative"
							})
			});
			$(".effect").mouseleave(function() {
				$(this).css({"border" : "1px solid #fff",
							"overflow" : "hidden",
							"position" : "relative"
							})
			});
		});
		function modifyuserpass() {
			$('#form_modfiypass')[0].reset();   
			$("#oldpmessage").html("");
			$("#newplenmessage").html("");
			$("#newpmessage").html("");
			layer.open({
				type : 1,
				content : $('#password_mod'),
				// skin: 'layui-layer-molv',
				title : '密码修改',
				area : '600px',
				maxmin : false
			});
		}
		function modifyuserinfo(){
		
			$("#phonecheck").html("");
			$("#emailcheck").html("");	
			$.ajax({
				type : "post",
				dataType : "json",
				url : "uptPwd/userinfoMod",
				async : false,
				cache : false,
				success : function(data) {
					$('#uuid').val(data.uuid);
					$('#userid').val(data.user_id);
					$('#username').val(data.user_name);
					$('#userdomain').val(data.domain_name);
					$('#userorg').val(data.org_unit_desc);
					$('#userole').val(data.roles);
					$('#userphone').val(data.user_phone);
					$('#useremail').val(data.user_email);
				}
			});
			
			layer.open({
				type : 1,
				content : $('#userinfo_mod'),
				// skin: 'layui-layer-molv',
				title : '用户信息修改',
				area : '600px',
				maxmin : false
			});
		}
		function mytest1() {
			alert("修改用户信息");
		}
	</script>
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
		function phonecheck() {
			var s = $("#userphone").val();
			var patrn = /^1[0-9]{10}$/;
			if (!patrn.exec(s)) {
				$("#phonecheck").html("手机号码不符合规则");
				return false;
			} else {
				$("#phonecheck").html("");
				return true;
			}
		}
		function emailcheck() {
			var s = $("#useremail").val();
			var patrn = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			if (!patrn.exec(s)) {
				$("#emailcheck").html("邮箱地址不符合规则");
				return false;
			} else {
				$("#emailcheck").html("");
				return true;
			}
		}
		function newpcheck() {

			var s1 = $("#newpassword").val();
			var s2 = $("#renewpassword").val();
			if (s1 == s2) {
				$("#newpmessage").html("");
				return true;
			} else {
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
		function psubmitFunc(){
			var m1 = oldpcheck();
			var m2 = newplencheck();
			var m3 = newpcheck();
			//if (m1=="" && m2 == ""){
			
			if (m1 && m2 && m3) {
				$.ajax({
					type : "post",
					dataType : "html",
					data:$('#form_modfiypass').serialize(),
					url : "uptPwd/modifpwd",
					async : false,
					cache : false,
					success : function(data) {
						layer.closeAll();
						layer.msg('用户密码更新成功,将跳转到登录页面', {
							  icon: 1,
							  time: 1500 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
							 // alert("回调函数");
								window.location.href = 'init_login';
							});  
					}
				});
			}	
			//layer.closeAll();
		}
		function submitFunc(){
			var m1=phonecheck();
			var m2=emailcheck();
			console.log(m1);
			console.log(m2);
			if (m1 && m2){
				$.ajax({
					type : "post",
					dataType : "html",
					data:$('#form_modfiyuser').serialize(),
					url : "uptPwd/userinfoupt",
					async : false,
					cache : false,
					success : function(data) {
						layer.closeAll();
						layer.msg('用户信息更新成功');		
					}
				});
			}	
			//layer.closeAll();
		}
	</script>
	<!-- templatemo 434 masonry -->
	<!--
    	作者：yeqc
    	时间：2017-02-09
    	描述：页面js
    -->
</body>
</html>