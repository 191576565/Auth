<%@ page language="java" contentType="text/html; charset=UTF-8"
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
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle J_menuItem" href="${ctxPath }/static/welcome.jsp">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-desktop"></i>
                                        <strong class="font-bold">Auth</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                    </li>
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
                        <a class="J_menuItem" href="dpgMgmt.html"><i class="fa fa-desktop"></i> 
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

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                    	<a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#">
                    		<i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#chevron-down">
                                admins
                                <i class="fa fa-chevron-down"></i>
                            </a>
                            <ul class="dropdown-menu yeqc-size">                               
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="uptPwd.html">
                                            <i class="fa fa-cog"></i> <strong> 修改密码</strong>
                                        </a>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="login_v2.html">
                                            <i class="fa fa-bell-slash"></i> <strong> 退出登录</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>                        
                    </ul>
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
    <script type="text/javascript" src="static/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="static/js/plugins/pace/pace.min.js"></script>

</body>

</html>
