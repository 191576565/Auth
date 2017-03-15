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
    	<link href="${ctxPath }/static/css/plugins/treegrid/jquery.treegrid.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/plugins/ztree/zTreeStyle.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/animate.css" rel="stylesheet">
    	<link href="${ctxPath }/static/css/style.css?v=4.1.0" rel="stylesheet">
    	<style type="text/css">
    		.table-striped>tbody>tr:nth-of-type(odd), .table-bordered > thead > tr > th, .table-bordered > thead > tr > td{
    			background-color: white;
    		}
    		.input-group-btn{
    			vertical-align: top;
    		}
    		select {
			     -webkit-appearance: none;
			     -webkit-border-radius: 0px;
			}
    	</style>
	</head>
	<body class="panel-body" style="padding-bottom:0px;">
 		<div class="row">
			<div class="col-lg-12">
					<ol class="breadcrumb">
					<li>
						<a href="${ctxPath }/static/welcome.jsp">首页</a>
					</li>
					<li>
						<a href="resMgmt">资源管理</a>
					</li>
				</ol>
			</div>
		</div>
		<br /><!-- <br /><br /><br /> -->
 		<div class="row">
 			<div class="col-xs-12">
 				<button id="res_add" type="button" class="btn btn-primary create">新增</button>
 				<!-- <button id="res_del" type="button" class="btn btn-danger delete">删除</button> -->
 			</div>
 			<!-- <div class="col-xs-8 text-right">
				<div class="form-inline">
					<div class="form-group">
						<input type="text" placeholder="请输入资源名称" style="width: 200px;" id="search" class="form-control">
					</div>
					<button class="btn btn-default search" type="button"><i class="fa fa-search"></i></button>
				</div>
			</div> -->
 		</div>
 		<br>
 		<table id="table"  class="table table-striped table-bordered table-hover">
 			<thead>
				<tr>
					<!-- <td style='width:40px;'></td> -->
					<th>资源编码</th>
					<th>资源名称</th>
					<th>上级资源编码</th>
					<th>资源URL</th>
					<th>资源类型</th>
					<th>资源样式</th>
					<th>资源背景色</th>
					<th>资源图标</th>
					<th>资源排序</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
                 
            </tbody>
 		</table>
 		<div class="t" style="display: none;">
 			<ul id="res" class="ztree"></ul>
 		</div>
 		<div id="sys_add_div"  class="row" style="display:none;width:100%;margin:0px;">
 			</br>
				<%-- <input id="uuid" 
 						name="res.uuid" 
 						type="hidden" 
 				/>
				<label>资源编码</label> <input id="res_id" name="res.res_id" value="${res.res_id }" type="text" placeholder="1~30位字母/数字/下划线" ></br></br><!--字母和数字(1~30位) -->
				<label>资源名称</label> <input id="res_name" name="res.res_name" value="${res.re_name }" type="text" placeholder="请输入角色名" ></br></br>
				<label>上级资源名称</label> <input id="res_up_uuid" name="res.res_up_uuid" value="" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源url</label> <input id="res_url" name="res.res_url" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源类型</label> 
				<select style="width: 120px;">
					<option value="1">叶子</option>
					<option value="0">节点</option>
				</select></br>
				<label>资源CSS</label> <input id="res_class" name="res.res_class" type="text" placeholder="请输入角色名" ></br></br>
				<label>资源颜色</label> 
				<select style="width: 120px;">
					<option value="red">red</option>
					<option value="yello">yello</option>
				</select></br>
				<label>资源图标</label> <input id="res_icon" name="res.res_icon" type="text" placeholder="请输入角色名" ></br></br>
				<label>排序编号</label> <input id="sort_id" name="res.sort_id" type="text" placeholder="请输入角色名" ></br></br>
				<button id="resave" type="submit" class="btn btn-info ladda-button save" >保存</button> --%>
                        
                        <div class="col-sm-12">
                        		<form id="form" >
                        		<input type="hidden" name="res.uuid" id="uuid">
                                <div class="col-sm-12">
                                	<div class="form-group col-sm-6">
	                                    <label>资源编码</label>
	                                    <input type="text" placeholder="" class="form-control chkResId" name="res.res_id" id="res_id" required>
	                                </div>
	                                <div class="form-group col-sm-6">
	                                    <label>资源名称</label>
	                                    <input type="text" placeholder="" class="form-control chkResName" name="res.res_name" id="res_name" required>
	                                </div>
                                </div>
                                <div class="col-sm-12">
                                	<div class="form-group col-sm-6">
	                                    <label>上级资源</label>
	                                    <div class="input-group">
	                                    	<input type="hidden" name="res.res_up_uuid" id="res_up_uuid">
		                                   <input type="text" class="form-control" name="res_up_name" id="up_res_name" required readonly="readonly"> 
		                                   <span class="input-group-btn"> 
		                                   		<button type="button" class="btn tree"><i class="fa fa-tree"></i></button> 
		                                   </span>
		                               </div>
	                                </div>
	                                <div class="form-group col-sm-6">
	                                    <label>资源类型</label>
	                                    <select class="form-control" name="res.res_type" style="border-radius: 2px;" id="res_type" required>
	                                    	<option value="">请选择资源类型</option>
	                                    	<option value="0">主菜单</option>
	                                        <option value="1">子菜单</option>
	                                        <option value="2">按钮</option>
	                                    </select>
	                                </div>
                                </div>
                                <div class="col-sm-12">
                                	<div class="form-group col-sm-6">
	                                    <label>资源URL</label>
	                                    <input type="text" placeholder="" class="form-control" name="res.res_url" id="res_url">
	                                </div>
	                                <div class="form-group col-sm-6">
	                                    <label>资源样式</label>
	                                    <input type="text" placeholder="" class="form-control" name="res.res_class" id="res_class">
	                                </div>
                                </div>
                                <div class="col-sm-12">
                                	<div class="form-group col-sm-6">
	                                    <label>资源背景色</label>
	                                    <input type="text" placeholder="" class="form-control" name="res.res_color" id="res_color">
	                                </div>
	                                <div class="form-group col-sm-6">
	                                    <label>资源图标</label>
	                                    <input type="text" placeholder="" class="form-control" name="res.res_icon" id="res_icon">
	                                </div>
                                </div>
                                <div class="col-sm-12">
                                	<div class="form-group col-sm-6">
	                                    <label>资源排序</label>
	                                    <input type="text" placeholder="" class="form-control digits" name="res.sort_id" id="sort_id" required maxlength="4">
	                                </div>
                                </div>
                                <div class="col-sm-12">
                                	<hr>
                                </div>
                                </form>
                                <button class="btn btn-primary pull-right m-t-n-xs save"  style="margin-right:30px;margin-bottom: 10px;">保存</button>
                        </div>
 			
 		</div>
 		<!-- <div id="sys_del_div" class="form-group" style="display:none;">
 			<form id="del_form" action="" method="post">
 				<input type="hidden" id="deluuid" name="uuid"/>
					确定要删除该系统信息吗？
				</br>
				<button id="resdelete" type="submit" class="btn btn-danger delete">确定删除</button>
			</form>
		</div> -->
	<!-- 全局js -->
    <script src="${ctxPath }/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath }/static/js/jquery-form.js"></script>
   
    <script src="${ctxPath }/static/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctxPath }/static/js/angular.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctxPath }/static/js/content.js?v=1.0.0"></script>


    <!-- Bootstrap table -->
    <%-- <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> --%>
     <!-- layer javascript -->
    <script src="${ctxPath }/static/js/plugins/layer/layer.min.js"></script>
    
    <!-- treegrid -->
    <script src="${ctxPath }/static/js/plugins/treegrid/jquery.treegrid.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
    
    <!-- ztree -->
    <script src="${ctxPath }/static/js/plugins/zTree/jquery.ztree.all.min.js"></script>
    
    <!-- validate -->
    <script src="${ctxPath }/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctxPath }/static/js/plugins/validate/messages_zh.min.js"></script>
    <!--
    	作者：ys
    	时间：2017-03-06
    	描述：页面js
    -->
    <script src="${ctxPath }/static/js/resMgmt.js"></script>
	</body>
</html>
