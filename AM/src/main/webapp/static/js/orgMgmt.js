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
									+'</td><td><button id="" type="button" class="btn btn-info btn-sm tbl-upt" onclick="upt(\''+ e.uuid
									+'\',\''+ e.org_unit_id 
									+'\',\''+ e.domain_name
									+'\',\''+ e.domain_uuid
									+'\',\''+ e.org_unit_desc 
									+'\',\''+ e.up_org_unit_desc 
									+'\',\''+ e.org_up_uuid 
									+'\',\''+ e.memo 
									+'\')">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger btn-sm" onclick="del(\''+ e.uuid
									+'\')">删除</button></td></tr>';
							$('#table tbody').append(temp);
						}else {
							temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.org_up_uuid+'" lvl="'+e.level+'"><td>'+e.org_unit_id+'</td><td>'+e.domain_name+'</td><td>'
									+e.org_unit_desc+'</td><td>'+e.up_org_unit_desc
									+'</td><td><button id="" type="button" class="btn btn-info btn-sm tbl-upt" onclick="upt(\''+ e.uuid
									+'\',\''+ e.org_unit_id 
									+'\',\''+ e.domain_name
									+'\',\''+ e.domain_uuid
									+'\',\''+ e.org_unit_desc 
									+'\',\''+ e.up_org_unit_desc 
									+'\',\''+ e.org_up_uuid 
									+'\',\''+ e.memo
									+'\')">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger btn-sm" onclick="del(\''+ e.uuid
									+'\')">删除</button></td></tr>';
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
	$("#org_add_div #form")[0].reset();
	//获取域
	$.getJSON("orgMgmt/getId",function(data){
		$.each(data, function(i, item){
			$.each(item, function(key,value){
				if(key === "domain_name"){
					var $scop_n = $("#org_add_div #form #scop_n").val(value);
				}
				if(key === "uuid"){
					var $uuid = $("#org_add_div #form #domain_uuid").val(value);
				}
			})
		})
	})
	//获取机构
	$.getJSON("orgMgmt/getOrg",function(data){
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

//编辑机构_弹出层
function upt(uuid,orgCode,dName,dId,orgDesc,upOrgDesc,upOrgID,memo){
	//初始化表单
	$("#org_add_div #form #up_org").html("");
	$("#org_add_div #form #uuid").val(uuid);
	$("#org_add_div #form #scop_n").val(dName);
	$("#org_add_div #form #org_code").val(orgCode);
	$("#org_add_div #form #org_name").val(orgDesc);
	$('#org_add_div #form #up_org').append("<option value="+upOrgID+">" + upOrgDesc + "</option>");
	$("#org_add_div #form #ipt_memo").val(memo);
	$("#org_add_div #form #domain_uuid").val(dId);
	
	//获取机构
	$.getJSON("orgMgmt/getOrg",function(data){
		$.each(data, function(i, item){
			var up_id = '';
			$.each(item, function(key,value){
				if(key === "uuid"){
					up_id = value;
				}
			})
			$.each(item, function(key,value){
				if(key === "org_unit_desc" && value != upOrgDesc){
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
}
//新增||编辑机构_保存
$('#sub').click(function(){
	//非空验证
	var flag = true;
	$(".notNull").each(function(){
        if($(this).val()==""){
        	alert($(this).attr('nullName')+"不能为空");
        	flag = false;
        	return false;
        }
    });
	 if(!flag){
 		return;
 	}
	if($("#org_add_div #form #uuid").val() == ''){
		//新增操作
		$("#form").attr("action", "orgMgmt/save");
		$('#form').ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "false"){
				alert("机构编码不能重复!");
				return;
			}
			window.location.href='orgMgmt';
		});
		return false;//阻止表单默认提交
	}
	if($("#org_add_div #form #uuid").val() != ''){
		//编辑操作
		$("#form").attr("action", "orgMgmt/update");
		$('#form').ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "true"){
				window.location.href='orgMgmt';
				return;
			}
			$.each(resultJson, function(i, item){
				if(item === "repeat"){
					alert('机构编码重复，修改失败!');
				}
			})
		});
		return false;//阻止表单默认提交
	}
	
});


//删除
function del(id) {
	var $ipt_uuid = $("#sys_del_div #del_form #del_uuid").val(id);
	layer.open({
		type: 1,
		content: $('#sys_del_div'),
		title: '系统提示',
		area: ['300px', '100px'],
	});
};
$('#btn_beSure').click(function() {
	$('#del_form').attr("action", "orgMgmt/delete");
//	$('#del_form').submit(function(){
		$('#del_form').ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "false"){
				alert('删除失败');
				return;
			}else{
				window.location.href='orgMgmt';
			}
		});
		return false;
//	});
	
});