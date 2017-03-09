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
//	if(treeNode.id===$('#res_id').val()){
//		toastr.warning('资源编码与上级资源编码冲突');
//		return;
//	}
	$('#res_up_uuid').val(treeNode.id);
	$('#up_res_name').val(treeNode.name);
	
	var index = layer.index; //获取当前弹层的索引号
	layer.close(index); //关闭当前弹层
	
	//$('#form').valid();
	valid.element( '#up_res_name' );
};

function zTreeOnClickSimple(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandNode(treeNode, null, null, null);

	return true;
};


var valid;
var rs;
$(function(){
	valid=$('#form').validate();
	
	initTable();
	
	$('.tree').click(function(){
		
		$.get('resMgmt/restree', function(data){
			$.fn.zTree.init($("#res"), s, data); //树
			
			layer.open({
				type : 1,
				content : $('.t'),
				title : '选择上级资源',
				area : [ '400px', '400px' ],
				maxmin: true
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
					temp='<tr class="treegrid-'+e.uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="width:40px;text-align:center;"><input type="radio" name="radio"/></td><td id="'+e.res_id+'">'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
							+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
							+'</td><td>-</td><td>-</td></tr>';
					temp=temp.replace(/null/g, '-');
					$('#table tbody').append(temp);
				}else {
					temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.res_up_uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="width:40px;text-align:center;"><input type="radio" name="radio"/></td><td id="'+e.res_id+'">'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
							+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
							+'</td><td>'+e.sort_id+'</td><td><button type="button" class="btn btn-info btn-sm" onclick="update(this)"  index="'+index+'">编辑</button></td></tr>';
					temp=	temp.replace(/null/g, '-');
					$('#table tbody tr.treegrid-'+e.res_up_uuid).after(temp);
				}
			});
		}
		
		$('#table').treegrid({
			treeColumn:1,
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });
		//收起第二级及一下的机构
    		$('#table tbody tr[lvl=3]').treegrid('collapseRecursive');
    		
    		//tr点击单选钮选中
        $('#table tbody tr').click(function(){
        		$(this).find('input:radio')[0].checked=true;
        });
	});
}

$('#res_add').on('click', function() {
	$('#form')[0].reset();
	$('#uuid').val('');
	
	//去除上次表单验证的样式
	valid.resetForm();
	$('input').removeClass('error');
	
	var index=$('td input:checked').parent().parent().attr('index');
	if(index){
		var info=rs[index];
		
		$('#res_up_uuid').val(info.uuid);
		$('#up_res_name').val(info.res_name);
	}
	
	layer.open({
		type : 1,
		content : $('#sys_add_div'),
		// skin: 'layui-layer-molv',
		title : '资源信息-新增',
		area : [ '640px', '640px' ], 
		maxmin: true
	});
});

function update(obj){
	var info=rs[$(obj).attr('index')];
	
	for(var key in info){
		$('#'+key).val(info[key]);
	}
	
	layer.open({
		type : 1,
		content : $('#sys_add_div'),
		// skin: 'layui-layer-molv',
		title : '资源信息-编辑',
		area : [ '640px', '640px' ], 
		maxmin: true
	});
}

$('.save').click(function(){
	if($('#form').valid()){
		var data=$('#form').serialize();
		if($('#uuid').val()==''){ //add
			$.post('resMgmt/save?'+data, function(d){
				if(d){
					layer.msg('资源新增成功');
					initTable();
				}else {
					layer.msg('资源新增失败');
				}
			});
		}else { //update
			$.post('resMgmt/put?'+data, function(d){
				if(d){
					layer.msg('资源更新成功');
					initTable();
				}else {
					layer.msg('资源更新失败');
				}
			});
		}
		layer.closeAll();
	}
});

$('#res_del').click(function(){
	if($('td input:checked').length===0){
		layer.msg('请选择要删除的资源');
		return;
	}
	var index=$('td input:checked').parent().parent().attr('index');
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

$("input").blur(function(){
//	$(this).valid();
	valid.element( this );
});