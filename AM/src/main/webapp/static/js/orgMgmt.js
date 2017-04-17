var s = {
	view: {
		dblClickExpand: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: '#'
		}
	},
	callback: {
		onDblClick: zTreeOnDblClickSimple,
		onClick: zTreeOnClickSimple
	}
};
function zTreeOnDblClickSimple(event, treeId, treeNode) {
	$('#org_up_uuid').val(treeNode.id);
	$('#up_org_name').val(treeNode.name);
	
	var index = layer.index; //获取当前弹层的索引号
	layer.close(index); //关闭当前弹层
};

function zTreeOnClickSimple(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandNode(treeNode, null, null, null);

	return true;
};
$(function(){
	initTable();
	$('.tree').click(function(){
		$.get('orgMgmt/getOrg', function(data){
			$.fn.zTree.init($("#org"), s, data); //树
			var treeObj = $.fn.zTree.getZTreeObj("org");
			treeObj.expandAll(true);
			layer.open({
					type : 1,
					content : $('.t'),
					title : '选择上级机构',
					area : [ '400px', '400px' ],
					maxmin: false,
					btn: ['确定', '取消'],
					yes: function(index, layero){
						var treeObj = $.fn.zTree.getZTreeObj("org");
						var nodes = treeObj.getSelectedNodes();
						$('#org_up_uuid').val(nodes[0].id);
						$('#up_org_name').val(nodes[0].name);
						var index = layer.index; //获取当前弹层的索引号
						layer.close(index); //关闭当前弹层
					},
					btn2: function(index, layero){	
					}
			});
		})
	})
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
									+'</td><td><button id="" type="button" class="btn btn-info update" onclick="upt(\''+ e.uuid
									+'\',\''+ e.org_unit_id 
									+'\',\''+ e.domain_name
									+'\',\''+ e.domain_uuid
									+'\',\''+ e.org_unit_desc 
									+'\',\''+ e.up_org_unit_desc 
									+'\',\''+ e.org_up_uuid 
									+'\',\''+ e.memo 
									+'\')">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger delete" onclick="del(\''+ e.uuid
									+'\')">删除</button></td></tr>';
							$('#table tbody').append(temp);
						}else {
							temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.org_up_uuid+'" lvl="'+e.level+'"><td>'+e.org_unit_id+'</td><td>'+e.domain_name+'</td><td>'
									+e.org_unit_desc+'</td><td>'+e.up_org_unit_desc
									+'</td><td><button id="" type="button" class="btn btn-info update" onclick="upt(\''+ e.uuid
									+'\',\''+ e.org_unit_id 
									+'\',\''+ e.domain_name
									+'\',\''+ e.domain_uuid
									+'\',\''+ e.org_unit_desc 
									+'\',\''+ e.up_org_unit_desc 
									+'\',\''+ e.org_up_uuid 
									+'\',\''+ e.memo
									+'\')">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger delete" onclick="del(\''+ e.uuid
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

//新增机构_弹出层
$('#org_add').on('click', function() {
	$("#org_add_div #form")[0].reset();
	$("#sys_add_div #form #ipt_memo").html('');
	$('.error').empty();
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
	layer.open({
		type: 1,
		content: $('#org_add_div'),
		title: '机构信息',
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (validate()) {
				$("#form").attr("action", "orgMgmt/save");
				$('#form').ajaxSubmit(function(resultJson){
					if(JSON.stringify(resultJson) == "false"){
						layer.msg('机构编码不能重复!');
						return;
					}
					initTable();
					layer.closeAll();
				});
			}
		},
		btn2: function(index, layero){	
		}
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
	$("#org_add_div #form #org_up_uuid").val(upOrgID);
	$("#org_add_div #form #up_org_name").val(upOrgDesc);
	$("#org_add_div #form #ipt_memo").val(memo);
	$("#org_add_div #form #domain_uuid").val(dId);
	$('.error').empty();
	
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
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "orgMgmt/update");
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "true"){
					initTable();
					layer.closeAll();
					return;
				}
				$.each(resultJson, function(i, item){
					if(item === "repeat"){
						layer.msg('机构编码重复，修改失败!');
					}
				})
			});
		},
		btn2: function(index, layero){	
		}
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
				layer.msg('机构编码不能重复!');
				return;
			}
			initTable();
			layer.closeAll();
		});
		return false;//阻止表单默认提交
	}
	if($("#org_add_div #form #uuid").val() != ''){
		//编辑操作
		$("#form").attr("action", "orgMgmt/update");
		$('#form').ajaxSubmit(function(resultJson){
			if(JSON.stringify(resultJson) == "true"){
				initTable();
				layer.closeAll();
				return;
			}
			$.each(resultJson, function(i, item){
				if(item === "repeat"){
					layer.msg('机构编码重复，修改失败!');
				}
			})
		});
		return false;//阻止表单默认提交
	}
	
});


//删除
function del(id) {
	layer.confirm('是否删除该机构信息？', 
			{
			  btn: ['删除','取消'] //按钮
			}, 
			function(){
				$.post('orgMgmt/delete?UUID='+id, function(d){
					if(d){
						layer.msg('删除成功');
					}else {
						layer.msg('删除失败');
					}
					initTable();
				});
			}, 
			function(){
				layer.closeAll();
			}
	);
};

function validate(){
	//非空验证
	var flag = true;
	$(".notNull").each(function(){
        if(""==$(this).val()){
        	layer.msg($(this).attr('nullName')+"不能为空");
        	flag = false;
        	return false;
        }
    });
	//合法验证
	$("p.error").each(function(){
		if(""!=$(this).text()){
			flag = false;
		}
	});
	return flag;
}