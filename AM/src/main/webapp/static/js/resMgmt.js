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
		onDblClick: zTreeOnDblClickSimple
	}
};
function zTreeOnDblClickSimple(event, treeId, treeNode) {
	$('#res_up_uuid').val(treeNode.id);
	$('#up_res_name').val(treeNode.name);
	
	var index = layer.index; //获取当前弹层的索引号
	layer.close(index); //关闭当前弹层
	
//	valid.element( '#up_res_name' );
};


var valid;
var rs;
$(function(){
	initTable();
	
	$('.tree').click(function(){
		
		$.get('resMgmt/restree', function(data){
			$.fn.zTree.init($("#res"), s, data); //树
			var treeObj = $.fn.zTree.getZTreeObj("res");
			treeObj.expandAll(true);
			
			layer.open({
				type : 1,
				content : $('.t'),
				title : '选择上级资源',
				area : [ '400px', '400px' ],
				maxmin: false,
				btn: ['确定', '取消'],
				yes: function(index, layero){
					var treeObj = $.fn.zTree.getZTreeObj("res");
					var nodes = treeObj.getSelectedNodes();
					
					$('#res_up_uuid').val(nodes[0].id);
					$('#up_res_name').val(nodes[0].name);
					
					var index = layer.index; //获取当前弹层的索引号
					layer.close(index); //关闭当前弹层
					
					valid.element( '#up_res_name' );
				},
				btn2: function(index, layero){
					
			    }
			});
		})
	})
})

function initTable() {
	$.get('resMgmt/get', {r:Math.random()*10000000000000}, function(data){
		rs=data;
		$('#table tbody').html('');
		var temp='';
		if(rs!=null){
			rs.forEach(function(e, index){
				if(e.lvl===1){
					temp='<tr class="treegrid-'+e.uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'">'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
							+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
							+'</td><td>-</td><td style="white-space: nowrap;">-</td></tr>';
					temp=temp.replace(/null/g, '-');
					$('#table tbody').append(temp);
				}else {
					temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.res_up_uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'">'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
							+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
							+'</td><td>'+e.sort_id+'</td><td style="white-space: nowrap;"><button type="button" class="btn btn-info" onclick="update(this)"  index="'+index+'">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="del(this)"  index="'+index+'">删除</button></td></tr>';
					temp=	temp.replace(/null/g, '-');
					$('#table tbody tr.treegrid-'+e.res_up_uuid).after(temp);
				}
			});
		}
		
		$('#table').treegrid({
			treeColumn:0,
			initialState:'collapsed',//将初始化状态设置为折叠
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });
		//收起第二级及以下的机构
//    		$('#table tbody tr[lvl=2]').treegrid('collapseRecursive');
		if($('#table tbody tr[lvl=1]').treegrid('isCollapsed')){
			  // 展开这一层节点
			$('#table tbody tr[lvl=1]').treegrid('expand');
		};
    		
	});
}

$('#res_add').on('click', function() {
	// $('#form')[0].reset();
	$('input').placeholder();
	$('.cl').val('');
	$('#uuid').val('');
	$('#res_id').removeAttr('readonly');
	$('input').removeClass('error');
	$('select').removeClass('error');
	
	layer.open({
		type : 1,
		content : $('#sys_add_div'),
		title : ['资源信息','color:rgb(99,102,104)'],
		area :  '640px',
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			var data=$('#form').serialize();
			$.post('resMgmt/resSave?'+data, function(d){
				if(JSON.stringify(d) == "false"){
					layer.msg('资源编码/资源名称不能重复');
				}else {
					layer.closeAll();
					initTable();
				}
			});
		},
		btn2: function(index, layero){
			
	    }
	});
});

function update(obj){
	$('input').placeholder();
	$('#res_id').attr('readonly', 'readonly');
	var info=rs[$(obj).attr('index')];
	
	for(var key in info){
		$('#'+key).val(info[key]);
	}
	
	layer.open({
		type : 1,
		content : $('#sys_add_div'),
		title : ['资源信息','color:rgb(99,102,104)'],
		area :  '640px',
		area :  '640px',
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			//上级资源不能选自己
			if($('#res_up_uuid').val() == $('#uuid').val()){
				layer.msg('上级资源不能选自己');
				return false;
			}
			var data=$('#form').serialize();
			$.post('resMgmt/resPut?'+data, function(d){
				if(d){
					layer.closeAll();
					initTable();
				}
			});
		},
		btn2: function(index, layero){
			
	    }
	});
}

function del(obj){
	var index=$(obj).attr('index');
	layer.confirm('是否删除该资源及其子资源？', {
	  btn: ['删除','取消'] //按钮
	}, function(){
		$.post('resMgmt/delete',{uuid:rs[index].uuid,res_id:rs[index].res_id,res_name:rs[index].res_name}, function(d){
			if(d){
				layer.msg('资源删除成功');
			}else {
				layer.msg('资源删除失败');
			}
			initTable();
		});
	}, function(){
		layer.close(layer.index);
	});
}

$('#res_del').click(function(){
	if($('td input:checked').length===0){
		layer.msg('请选择要删除的资源');
		return;
	}
	var index=$('td input:checked').parent().parent().attr('index');
	if(index=='0'){
		layer.msg('顶层资源不能删除');
		return ;
	}
	layer.confirm('是否删除该资源及其子资源？', {
	  btn: ['删除','取消'] //按钮
	}, function(){
		$.post('resMgmt/delete?uuid='+rs[index].uuid, function(d){
			if(d){
				layer.msg('资源删除成功');
			}else {
				layer.msg('资源删除失败');
			}
			initTable();
		});
	}, function(){
		layer.close(layer.index);
	});
});

function validate(){
	//非空验证
	var flag = true;
	if("" == $('#res_type').val()){
		layer.msg($('#res_type').attr('nullName')+"不能为空");
		flag = false;
		return false;
	}
	if("" == $('#res_up_uuid').val()){
		layer.msg($('#res_up_uuid').attr('nullName')+"不能为空");
		flag = false;
		return false;
	}
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


