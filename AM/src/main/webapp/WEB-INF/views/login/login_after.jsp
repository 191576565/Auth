<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Masonry Responsive Template</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="${ctxPath }/static/css/normalize.css">
        <link rel="stylesheet" href="${ctxPath }/static/css/font-awesome.css">
        <link rel="stylesheet" href="${ctxPath }/static/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctxPath }/static/css/templatemo-style.css">
        <script src="${ctxPath }/static/js/plugins/vendor/modernizr-2.6.2.min.js"></script>
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
                    <a href="#" class="site-brand pull-left"><strong>TianJian</strong> 重庆天健金管科技服务有限公司</a>
                </div>
            </div> <!-- .site-header -->
        </div> <!-- .site-top -->
        <!-- MAIN POSTS -->
        <div class="main-posts">
            <div class="container">
                <div class="row">
                    <div class="blog-masonry masonry-true">
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="auth"><img src="${ctxPath }/static/img/auth.png" alt=""></a>
                            </div>
                            <div class="post-thumb">
                                <a href="http://172.168.171.241:8080/app-rpm/index.html?sid=1c2facbcff07293ef26c3834a3444e94"><img src="${ctxPath }/static/img/auth.png" alt=""></a>
                            </div>
                        </div> <!-- /.post-masonry -->
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="#"><img src="${ctxPath }/static/img/rpm.png" alt=""></a>
                            </div>
                        </div> <!-- /.post-masonry -->
                        <div class="post-masonry col-md-4 col-sm-6">
                            <div class="post-thumb">
                                <a href="#"><img src="${ctxPath }/static/img/ftp.png" alt=""></a>
                            </div>
                        </div> <!-- /.post-masonry -->
                    </div>
                </div>
            </div>
        </div>

        <script src="${ctxPath }/static/js/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="${ctxPath }/static/js/vendor/jquery-1.10.2.min.js"><\/script>')</script>
        <script src="${ctxPath }/static/js/plugins/min/plugins.min.js"></script>
        <script src="${ctxPath }/static/js/plugins/min/main.min.js"></script>

        <!-- Preloader -->
        <script type="text/javascript">
            //<![CDATA[
            $(window).load(function() { // makes sure the whole site is loaded
                $('#loader').fadeOut(); // will first fade out the loading animation
                    $('#loader-wrapper').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
                $('body').delay(350).css({'overflow-y':'visible'});
            })
            //]]>
        </script>
	<!-- templatemo 434 masonry -->
    </body>
</html>