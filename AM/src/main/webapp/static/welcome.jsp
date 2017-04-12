<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>HTML5仿Apple Watch时钟动画DEMO演示</title>

<link rel="stylesheet" href="watch/css/style.css" media="screen"
	type="text/css" />
<style>
.first {
	position: relative;
	width: 300px;
	height: 300px;
	border: 1px #333 solid;
	background: #160805;
}
</style>

</head>

<body>

	<!--
Recreating Apple Watch's Utility face <http://www.apple.com/watch/design/> in HTML+CSS+JS
-->
	<div class="first">
		<div class="fill">

			<div class="clock" id="utility-clock">
				<div class="centre">
					<div class="dynamic"></div>
					<div class="expand round circle-1"></div>
					<div class="anchor hour">
						<div class="element thin-hand"></div>
						<div class="element fat-hand"></div>
					</div>
					<div class="anchor minute">
						<div class="element thin-hand"></div>
						<div class="element fat-hand minute-hand"></div>
					</div>
					<div class="anchor second">
						<div class="element second-hand"></div>
					</div>
					<div class="expand round circle-2"></div>
					<div class="expand round circle-3"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="watch/js/index.js"></script>

</body>

</html>