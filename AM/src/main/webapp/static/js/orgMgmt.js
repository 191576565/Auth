$(function(){
	initTable()
})

function initTable() {
			$.get('orgMgmt/orgData', {r:Math.random()*10000000000000}, function(data){
				var rs=data;
				$('#table tbody').html('');
				var temp='';
				if(rs!=null){
					rs.forEach(function(e){
						if(e.level===1){
							temp='<tr class="treegrid-'+e.uuid+'" lvl="'+e.level+'"><td>'+e.org_unit_id+'</td><td>'+e.domain_name+'</td><td>'
									+e.org_unit_desc+'</td><td>'+e.up_org_unit_desc
									+'</td><td><button id="" type="button" class="btn btn-info btn-sm">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger btn-sm">删除</button></td></tr>';
							$('#table tbody').append(temp);
						}else {
							temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.org_up_uuid+'" lvl="'+e.level+'"><td>'+e.org_unit_id+'</td><td>'+e.domain_name+'</td><td>'
									+e.org_unit_desc+'</td><td>'+e.up_org_unit_desc
									+'</td><td><button id="" type="button" class="btn btn-info btn-sm">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger btn-sm">删除</button></td></tr>';
							$('#table tbody tr.treegrid-'+e.org_up_uuid).after(temp);
						}
					});
				}
				
				$('#table').treegrid({
					treeColumn:0,
	                expanderExpandedClass: 'glyphicon glyphicon-minus',
	                expanderCollapsedClass: 'glyphicon glyphicon-plus'
	            });
				//收起第二级及一下的机构
            		$('#table tbody tr[lvl=2]').treegrid('collapseRecursive');
            		
			});
		}

//表单验证
angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){};
})

//新增机构_弹出层
$('#org_add').on('click', function() {
	//初始化表单
	$("#org_add_div #form #up_org").html("");
	//获取域
	$.getJSON("orgMgmt/getId",function(data){
		$.each(data, function(i, item){
			$.each(item, function(key,value){
				if(key === "domain_name"){
					var $scop_n = $("#org_add_div #form #scop_n").val(value);
				}
				if(key === "uuid"){
					var $uuid = $("#org_add_div #form #uuid").val(value);
				}
			})
		})
	})
	//获取机构
	$.getJSON("orgMgmt/getOrg",function(data){
		console.log(data);
		$.each(data, function(i, item){
			var up_id = '';
			$.each(item, function(key,value){
				if(key === "uuid"){
					up_id = value;
				}
			})
			$.each(item, function(key,value){
				if(key === "org_unit_desc"){
					$('#up_org').append("<option value="+up_id+">" + value + "</option>");
				}
			})
		})
	})
	layer.open({
		type: 1,
		content: $('#org_add_div'),
		title: '机构信息',
		area: ['960px', '540px'],
	});
	return false;
});
//新增机构_保存
$('#sub').click(function(){
	$("#form").attr("action", "orgMgmt/save");
	$('#form').ajaxSubmit(function(resultJson){
		if(JSON.stringify(resultJson) == "false"){
			alert("机构编码不能重复!");
			return;
		}
		window.location.href='orgMgmt';
	});
	return false;//阻止表单默认提交
});