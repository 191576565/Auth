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

//解决IE浏览器remove()不能使用的问题
var navigatorName = "Microsoft Internet Explorer";
var navigatorName_ = "Netscape";


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
	$.get('resMgmt/getResData', {r:Math.random()*10000000000000}, function(data){
		rs=data;
		$('#table tbody').html('');
		var temp='';
		if(rs!=null){
			rs.forEach(function(e, index){
				if(e.count_ >0){
					temp='<tr class="treegrid-'+e.uuid+' treegrid-collapsed" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'"><span onclick=chg_exp(this,"'+e.uuid+'") class="treegrid-expander glyphicon glyphicon-plus"></span>'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
					+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
					+'</td><td>'+e.sort_id+'</td><td style="white-space: nowrap;"><button type="button" class="btn btn-info" onclick="update(\''+e.uuid+'\',\''+e.lvl+'\',\''+e.res_id+'\',\''+e.res_name+'\',\''+e.res_type+'\',\''+e.res_up_uuid+'\',\''+e.up_res_name+'\',\''+e.res_url+'\',\''+e.res_bg_url+'\',\''+e.res_type_name+'\',\''+e.res_class+'\',\''+e.res_color+'\',\''+e.res_icon+'\',\''+e.sort_id+'\')"  index="'+index+'">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="del(\''+e.uuid+'\',\''+e.res_id+'\',\''+e.res_name+'\')"  index="'+index+'">删除</button></td></tr>';
					temp=temp.replace(/null/g, '-');
					$('#table tbody').append(temp);
				}else{
					temp='<tr class="treegrid-'+e.uuid+' treegrid-collapsed" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'">'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
					+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
					+'</td><td>'+e.sort_id+'</td><td style="white-space: nowrap;"><button type="button" class="btn btn-info" onclick="update(\''+e.uuid+'\',\''+e.lvl+'\',\''+e.res_id+'\',\''+e.res_name+'\',\''+e.res_type+'\',\''+e.res_up_uuid+'\',\''+e.up_res_name+'\',\''+e.res_url+'\',\''+e.res_bg_url+'\',\''+e.res_type_name+'\',\''+e.res_class+'\',\''+e.res_color+'\',\''+e.res_icon+'\',\''+e.sort_id+'\')"  index="'+index+'">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="del(\''+e.uuid+'\',\''+e.res_id+'\',\''+e.res_name+'\')"  index="'+index+'">删除</button></td></tr>';
					temp=temp.replace(/null/g, '-');
					$('#table tbody').append(temp);
				}
					
			});
		}
    		
	});
}

function chg_exp(th,uuid){
	var sp_class = $(th).attr('class');
	if(sp_class == 'treegrid-expander glyphicon glyphicon-plus'){
		$(th).attr('class','treegrid-expander glyphicon glyphicon-minus');
		$.get("resMgmt/getResData?res_up_uuid="+uuid, function(rs){
			var temp = '';
			var str_space = '';
			//缩进
			for(var i=2; i<rs[0].lvl; i++){
				str_space += '<span class="treegrid-indent"></span>';
			}
			if(rs!=null){
				rs.forEach(function(e, index){
					
					if(e.count_ >0){
						temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.res_up_uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'">'+str_space+'<span onclick=chg_exp(this,"'+e.uuid+'") class="treegrid-expander glyphicon glyphicon-plus"></span>'+e.res_id+'</td><td>'+e.res_name+'</td><td>'
						+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
						+'</td><td>'+e.sort_id+'</td><td style="white-space: nowrap;"><button type="button" class="btn btn-info" onclick="update(\''+e.uuid+'\',\''+e.lvl+'\',\''+e.res_id+'\',\''+e.res_name+'\',\''+e.res_type+'\',\''+e.res_up_uuid+'\',\''+e.up_res_name+'\',\''+e.res_url+'\',\''+e.res_bg_url+'\',\''+e.res_type_name+'\',\''+e.res_class+'\',\''+e.res_color+'\',\''+e.res_icon+'\',\''+e.sort_id+'\')"  index="'+index+'">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="del(\''+e.uuid+'\',\''+e.res_id+'\',\''+e.res_name+'\')"  index="'+index+'">删除</button></td></tr>';
						temp=temp.replace(/null/g, '-');
					}else{
						temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.res_up_uuid+'" lvl="'+e.lvl+'" index="'+index+'"><td style="white-space: nowrap;" id="'+e.res_id+'">'+str_space+e.res_id+'</td><td>'+e.res_name+'</td><td>'
						+e.up_res_name+'</td><td>'+e.res_url+'</td><td>'+e.res_type_name+'</td><td>'+e.res_class+'</td><td>'+e.res_color+'</td><td>'+e.res_icon
						+'</td><td>'+e.sort_id+'</td><td style="white-space: nowrap;"><button type="button" class="btn btn-info" onclick="update(\''+e.uuid+'\',\''+e.lvl+'\',\''+e.res_id+'\',\''+e.res_name+'\',\''+e.res_type+'\',\''+e.res_up_uuid+'\',\''+e.up_res_name+'\',\''+e.res_url+'\',\''+e.res_bg_url+'\',\''+e.res_type_name+'\',\''+e.res_class+'\',\''+e.res_color+'\',\''+e.res_icon+'\',\''+e.sort_id+'\')"  index="'+index+'">编辑</button>&nbsp;<button type="button" class="btn btn-danger" onclick="del(\''+e.uuid+'\',\''+e.res_id+'\',\''+e.res_name+'\')"  index="'+index+'">删除</button></td></tr>';
						temp=temp.replace(/null/g, '-');
					}
					$(th).parent().parent().after(temp);	
				});
			}
		});
	}else if(sp_class == 'treegrid-expander glyphicon glyphicon-minus'){
		$(th).attr('class','treegrid-expander glyphicon glyphicon-plus');
		var trs = $('#table tbody tr');//获取每一行
		var trc = '';//存放每一行的class值
		var uuidArr=[];//记录被移除元素的class值,因为这个元素可能拥有子元素，需要在后续判断如果该元素有子元素,则把子元素一并移除
		for(var i=0;i<trs.length;i++){//遍历行
//			console.log(trs[i]);
			trc = $(trs[i]).attr('class');//获取行的class值
			var trc_v = trc.split(' ');//把class值用空格切割
//			console.log(trc_v);
			for(var j=0; j<trc_v.length; j++){//遍历用空格切割后的值
//				console.log(trc_v[j]);
				var str_arr = trc_v[j].split('-');//对上述值再次用'-'切割
				for(var k=0; k<str_arr.length; k++){//遍历用'-'切割后的值
//					console.log(str_arr[k]);
					//如果这个值是事件中传入的uuid并且上一个值是'parent'则把此元素移除(移除行,因为到这里仍然处于此行中)
					if(str_arr[k]==uuid && str_arr[k-1]=='parent'){
//						console.log("----------------"+(trc_v[j-1].split('-'))[1]);
						uuidArr.push((trc_v[j-1].split('-'))[1]);//把将要移除的该行的uuid存入数组,用作后续判断剩下的行中是否有元素以该元素作为父元素,若有,则一并移除
//						console.log(uuid+"  "+str_arr[k]);
						
						
						
						if(navigator.appName == navigatorName){//IE不支持remove问题
							console.log(navigator.appName);
							trs[i].removeNode(true);
						}else if(navigator.appName == navigatorName_){//NetScape不支持removeNode和remove问题
							var _parentElement = trs[i].parentNode;
							if(_parentElement){
				                _parentElement.removeChild(trs[i]);  
							}
						}else{
							console.log(navigator.appName);
							trs[i].remove();
						}
						
//						trs[i].remove();//移除
					}
				}
			}
		}
//		console.log(uuidArr);
		var trsn = $('#table tbody tr');//重新获取
		var trcn = '';
		//重复以上循环的步骤,把子元素全部移除
		for(var i=0;i<trsn.length;i++){
//			console.log(trsn[i]);
			trcn = $(trsn[i]).attr('class');
			var trc_vn = trcn.split(' ');
			for(var j=0; j<trc_vn.length; j++){
				var str_arrn = trc_vn[j].split('-');
				for(var k=0; k<str_arrn.length; k++){
//					console.log(str_arrn[k]);
					for(var l=0; l<uuidArr.length; l++){
						if(uuidArr[l]==str_arrn[k] && str_arrn[k-1]=='parent'){
							uuidArr.push((trc_vn[j-1].split('-'))[1]);
							if(navigator.appName == navigatorName){//IE不支持remove问题
								console.log(navigator.appName);
								trsn[i].removeNode(true);
							}else if(navigator.appName == navigatorName_){//NetScape不支持removeNode和remove问题
								var _parentElement = trsn[i].parentNode;
								if(_parentElement){
					                _parentElement.removeChild(trsn[i]);  
								}
							}else{
								console.log(navigator.appName);
								trsn[i].remove();
							}
						}
					}
				}
			}
		}
		
//	console.log();
//	console.log($(th).parent().parent().attr('class'));
	
	}
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

function uptClear(uuid,lvl,res_id,res_name,res_type,res_up_uuid,up_res_name,res_url,res_bg_url,res_type_name,res_class,res_color,res_icon,sort_id){
	var pars = [];
	pars.push(uuid);
	pars.push(lvl);
	pars.push(res_id);
	pars.push(res_name);
	pars.push(res_type);
	pars.push(res_up_uuid);
	pars.push(up_res_name);
	pars.push(res_url);
	pars.push(res_bg_url);
	pars.push(res_type_name);
	pars.push(res_class);
	pars.push(res_color);
	pars.push(res_icon);
	pars.push(sort_id);
	for(var i=0; i<pars.length; i++){
		if(pars[i] == '-'){
			pars[i] = '';
		}
	}
	return pars;
}

function update(uuid,lvl,res_id,res_name,res_type,res_up_uuid,up_res_name,res_url,res_bg_url,res_type_name,res_class,res_color,res_icon,sort_id){
	$('input').placeholder();
	$('#res_id').attr('readonly', 'readonly');
//	var info=rs[$(obj).attr('index')];
	
//	for(var key in info){
//		$('#'+key).val(info[key]);
//	}
//	console.log(uptClear(uuid,lvl,res_id,res_name,res_type,res_up_uuid,up_res_name,res_url,res_bg_url,res_type_name,res_class,res_color,res_icon,sort_id));
	var pars = uptClear(uuid,lvl,res_id,res_name,res_type,res_up_uuid,up_res_name,res_url,res_bg_url,res_type_name,res_class,res_color,res_icon,sort_id);
//	console.log(uuid);
	
	$('#uuid').val(pars[0]);
	$('#lvl').val(pars[1]);
	$('#res_id').val(pars[2]);
	$('#res_name').val(pars[3]);
	$('#res_type').val(pars[4]);
	$('#res_up_uuid').val(pars[5]);
	$('#up_res_name').val(pars[6]);	
	$('#res_url').val(pars[7]);
	$('#res_bg_url').val(pars[8]);
	$('#res_type_name').val(pars[9]);
	$('#res_class').val(pars[10]);
	$('#res_color').val(pars[11]);
	$('#res_icon').val(pars[12]);
	$('#sort_id').val(pars[13]);
	
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

function del(uuid,res_id,res_name){
//	var index=$(obj).attr('index');
	layer.confirm('是否删除该资源及其子资源？', {
	  btn: ['删除','取消'] //按钮
	}, function(){
		$.post('resMgmt/delete',{uuid:uuid,res_id:res_id,res_name:res_name}, function(d){
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
	//引号验证
	if(!commaValidate()){
		layer.msg("输入框中不能带有引号(\"or\')");
		flag = false;
	}
	return flag;
}

//输入框中带有引号("or')的校验
function commaValidate(){
	var values = [];
	values.push($('#res_name').val());
	values.push($('#res_url').val());
	values.push($('#res_bg_url').val());
	values.push($('#res_color').val());
	values.push($('#res_icon').val());
	values.push($('#res_class').val());
	values.push($('#sort_id').val());
//	console.log(values);
	for(var i=0; i<values.length; i++){
		var value = values[i];
//		console.log(value);
		var charInValues = value.split('');
//		console.log(charInValues);
		for(j=0; j<charInValues.length; j++){
			if(charInValues[j]=='\"' || charInValues[j]=='\''){
				return false;
			}
		}
	}
	return true;
}


