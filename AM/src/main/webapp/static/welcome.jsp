<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>

		<meta charset="UTF-8">

		<title>HTML5仿Apple Watch时钟动画DEMO演示</title>

		<link href="metrolab/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="metrolab/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link href="metrolab/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
		<link href="metrolab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href="metrolab/css/style.css" rel="stylesheet" />
		<link href="metrolab/css/style-responsive.css" rel="stylesheet" />
		<link href="metrolab/css/style-default.css" rel="stylesheet" id="style_color" />
		<link href="metrolab/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
		<link rel="stylesheet" href="clock/css/demo.css" />
		<style>
			body{background:#FFF} 
		</style>

	</head>

	<body>
<div  style="padding-left:18px;padding-right:18px;padding-top:18px;">


		<!--
Recreating Apple Watch's Utility face <http://www.apple.com/watch/design/> in HTML+CSS+JS
-->
		<div class="row-fluid">
			<div class="span7 responsive" data-tablet="span7 fix-margin" data-desktop="span7" style="width: 100%">
				<!-- BEGIN CALENDAR PORTLET-->
				<div class="widget yellow">
					<div class="widget-title">
						<h4>
						<i class="icon-calendar"></i> 日历
					</h4>
						
					</div>
					<div class="widget-body">
						<div id="calendar" class="has-toolbar"></div>
					</div>
				</div>
				<!-- END CALENDAR PORTLET-->
			</div>

			<!-- <div class="span5" style="min-width:350px;">
				<div class="widget purple">
					<div class="widget-title">
						<h4><i class="icon-time" ></i> 时钟 </h4>

					</div>
					<div class="widget-body">
						<div class="row-fluid">

							<div class="clock" style="margin: 0 auto;" id="clock"></div>

						</div>
					</div>

				</div>
			</div> -->

		</div>
		</div>
		<script src="clock/js/jquery-2.1.1.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="clock/js/clock-1.1.0.min.js"></script>
		<script>
			var clock = $("#clock").clock(),
				data = clock.data('clock');

			// data.pause();
			// data.start();
			// data.setTime(new Date());

			var d = new Date();
			d.setHours(9);
			d.setMinutes(51);
			d.setSeconds(20);
		</script>
		<script src="metrolab/js/jquery-1.8.3.min.js"></script>
		<script src="metrolab/js/jquery.nicescroll.js" type="text/javascript"></script>
		<script type="text/javascript" src="metrolab/assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="metrolab/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script src="metrolab/assets/fullcalendar/fullcalendar/fullcalendar.min.js"></script>
		<script src="metrolab/assets/bootstrap/js/bootstrap.min.js"></script>
		<script src="metrolab/js/jquery.scrollTo.min.js"></script>

		<!-- ie8 fixes -->
		<!--[if lt IE 9]>
   <script src="js/excanvas.js"></script>
   <script src="js/respond.js"></script>
   <![endif]-->

		<!--common script for all pages-->
		<script src="metrolab/js/common-scripts.js"></script>

		<!--script for this page only-->
		<script src="metrolab/js/external-dragging-calendar.js"></script>
	</body>

</html>