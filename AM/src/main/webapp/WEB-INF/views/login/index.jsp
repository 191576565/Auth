<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.jfinal.plugin.activerecord.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> Auth- 主页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="static/favicon.ico"> <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css?v=4.1.0" rel="stylesheet">
<style type="text/css">
.pull-right-1{
	width:316px;
	margin-top: 5px;
}
.fa-pa{
	transition: color 0.2s linear;
}
.fa-pa:hover{
	color:#f59b60;
 	text-shadow: 1px 2px 3px rgba(1,11,20,0.9);	
}
.btn-info1{
	border: 1px solid #8de5ff;
	box-shadow: 0px 0px 3px 2px rgba(0,0,0,0.4);
	transition: all 0.2s linear;
}
.btn-info1:hover{
	border: 1px solid #52b9d8;
	transform: scale(1.1);
	box-shadow: 0px 0px 3px 2px rgba(255,255,255,0.4);
}
</style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation" style="background-color:rgb(48,57,72);">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse" id="1">
                <ul class="nav" id="side-menu">
                    <li class="nav-header" style="background-color:rgb(48,57,72)";>
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle J_menuItem" href="${ctxPath }/static/welcome.jsp">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <img width="100px" src="${ctxPath }/static/img/leftLogo.png">
                                    </span>
                                </span>
                            </a>
                        </div>
                    </li>
                    <!--  
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">基础配置</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="sysMgmt"><i class="fa fa-desktop"></i>
                            <span class="nav-label">系统管理</span>
                        </a>
                    </li>
                    <li class="line dk"></li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">用户权限</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="usrMgmt">
                        	<i class="fa fa-desktop"></i> 
                        	<span class="nav-label">用户管理 </span>
                        	<span class="nav-label"></span></a>                       
                    </li>
                    <li>
                        <a class="J_menuItem" href="rolMgmt"><i class="fa fa-desktop"></i> 
                        	<span class="nav-label">角色管理</span>
                        	<span class="nav-label"></span></a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="resMgmt"><i class="fa fa-desktop"></i> 
                        	<span class="nav-label">资源管理</span>
                        	<span class="nav-label"></span></a>
                    </li>   
                    <li>
                        <a class="J_menuItem" href="dpgMgmt"><i class="fa fa-desktop"></i> 
                        	<span class="nav-label">数据权限组管理</span>
                        	<span class="nav-label"></span></a>
                    </li>
                    <li class="line dk"></li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">系统维护</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="syslog"><i class="fa fa-desktop"></i> 
                        	<span class="nav-label">系统日志</span>
                        	<span class="nav-label"></span></a>
                    </li>
				-->
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;background-color:#2e374a;border-bottom: 1px solid #3a4d5e;
	box-shadow: 0px 2px 4px 2px rgba(0,0,0,0.1);">
                    <div class="navbar-header">
                    		<a class="navbar-minimalize minimalize-styl-2 btn btn-info-init btn-info1" href="#">
                    			<i class="fa fa-bars"></i> 
                   	 	</a>
                    		<!--<img alt="" src="${ctxPath }/static/img/tianjian.png" style="margin-top: 15px">-->
                    </div>
                    <div class="col-sm-4 pull-right pull-right-1" style="margin-top:15px; width:400px">
                        <a class="" style="color: #7ea4c6;text-shadow: 1px 2px 3px rgba(1,11,20,0.9);">
                        		<i class="fa fa-user fa-pa" >&nbsp; <%=((Record) session.getAttribute("userinfo")).getStr("user_name")%>                        		
                        		</i>&nbsp;
                        </a>
                        <a href="loginafter"  class="" style="color:#7ea4c6; cursor: pointer;text-shadow: 1px 2px 3px rgba(1,11,20,0.9);">
                        		<i class="fa fa-home fa-pa" >&nbsp;回主界面
                        		</i>
                        </a>&nbsp;
                        <a class="" style="color: #7ea4c6; cursor: pointer;text-shadow: 1px 2px 3px rgba(1,11,20,0.9);">
                       		<i class="fa fa-cog fa-pa"  onclick="modclick()">&nbsp;修改密码
                       	 	</i>&nbsp; 
                        </a>
                        <a class="" href="ulogin/userexit" style="color: #7ea4c6; cursor: pointer;text-shadow: 1px 2px 3px rgba(1,11,20,0.9);"> 
                        		<i class="fa fa-sign-out fa-pa">&nbsp; 退出登录
                        		</i>
                        </a>
                    </div>
                    <!-- <ul class="nav navbar-top-links navbar-right">
                        <li id="idropdown" class="dropdown">
                            <a herf="#" class="dropdown-toggle count-info" data-toggle="dropdown" >
                               	欢迎您，<%=((Record)session.getAttribute("userinfo")).getStr("user_name") %>
                                <i class="fa fa-chevron-down"></i>
                            </a>
                            <ul class="dropdown-menu yeqc-size"> 
                            	<li>
                                    <div id="div1" class="text-center link-block" >
                                       <a id="div2" class="" href="loginafter">
                                            <i class=" fa fa-home" ></i> <strong > 回主界面</strong>
                                        </a>
                                    </div>
                                </li>
                                <li class="divider"></li>                              
                                <li>
                                    <div id="div1" class="text-center link-block" >
                                       <a id="div2" class="J_menuItem" onclick="modclick()">
                                            <i class=" fa fa-cog" ></i> <strong > 修改密码</strong>
                                        </a>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="ulogin/userexit">
                                            <i class="fa fa-sign-out"></i> <strong> 退出登录</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>                        
                    </ul> -->
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="static/welcome.jsp" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="static/js/jquery.min.js?v=2.1.4"></script>
    <script src="static/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="static/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="static/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="static/js/plugins/pace/pace.min.js"></script>
    <script type="text/javascript">
    function dosubmit(){
    	document.forms[0].action="userexit";
    	document.forms[0].submit();
    	}
    function modclick(){
    	//window.location.href='uptPwd';
    	 $("#idropdown").removeClass('open'); 
    	// $('.dropdown-toggle').dropdown();
    	var url='uptPwd';
    	 $("#J_iframe").attr('src',url);
    }
    $('#idropdown').mouseout(function(){
    	$(this).removeClass('open');
    });
    $('#idropdown').mouseover(function(){
    	$(this).addClass('open');
    });
    </script>

</body>

</html>
