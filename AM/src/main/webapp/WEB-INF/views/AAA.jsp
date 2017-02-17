<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - ç»å½</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="auth/css/animate.css" rel="stylesheet">
    <link href="auth/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-12">
                <form method="post" action="validLogin">
                    <h4 class="no-margins">ç»å½ï¼</h4>
                    <input type="text" name="username" class="form-control uname" placeholder="ç¨æ·å" />
                    <input type="password" name="password" class="form-control pword m-b" placeholder="å¯ç " />
                <button type="submit" class="btn btn-primary block full-width m-b">ç» å½</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-right">
                &copy; Auth
            </div>
        </div>
    </div>
</body>

</html>