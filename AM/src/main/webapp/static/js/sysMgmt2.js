function initsystable(){
	//先销毁表格
	$('#table').bootstrapTable('destroy'); 
	$('#table').bootstrapTable({
		url: 					'sysMgmt/sysData', 		//请求后台的URL（*）
		toolbar: 				'', 					//工具按钮用哪个容器
		striped: 				false, 					//是否显示行间隔色
		cache: 					false, 					//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: 			true, 					//是否显示分页（*）
		sortable: 				false, 					//是否启用排序
		queryParams: function queryParams() {     //设置查询参数  
	        var param = {    
		        sendParam : $("#iptSearch").val(),
		        send : ''
	        };    
       　　	return param;                   
         },	
        queryParamsType: '',
		sidePagination: 		"client",   			//分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 			1, 						//初始化加载第一页，默认第一页
		pageSize: 				10, 					//每页的记录行数（*）
		pageList: 				[10, 25, 50, 100], 		//可供选择的每页的行数（*）
		search: 				false, 					//是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		showColumns: 			false, 					//是否显示所有的列
		showRefresh: 			false, 					//是否显示刷新按钮
		minimumCountColumns: 	2, 						//最少允许的列数
		clickToSelect: 			false, 					//是否启用点击选中行
	//	height: 				400,      				//行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
		showToggle: 			false, 					//是否显示详细视图和列表视图的切换按钮
		cardView: 				false, 					//是否显示详细视图
		detailView: 			false,     				//是否显示父子表
	
		columns: [{
			field: 'domain_id',
			title: '域编码',
			align: 'center',
			width: '30%'
		}, {
			field: 'domain_name',
			title: '域 名',
			align: 'center'
		}, {
			field: 'uuid',
			title: '操 作',
			width: '188px',
			align: 'center',
			formatter: function(value, row, index) {
				var e = '<a href="#" class="btn btn-info update" onclick="onEdit(\'' + index + '\')">编辑</a> ';
				var d = '<a href="#" class="btn btn-danger delete" onclick="onDel(\'' + index + '\')">删除</a> ';
				var f = '<a href="#" class="btn btn-org" onclick="onOrg(\'' + row.uuid + '\')">机构</a> ';
				return e + d + f;
			}
		}, ]
		
	});
};

//跳转机构页面
function onOrg(id){
	$.ajax({
        type: "POST",
        url: "orgMgmt/accept",
        cache : false,
        data: {"uuid":id},
        dataType: 'json',
        success: function (message) {
            if (message > 0) {
                window.location.href = "orgMgmt";
            }
        },
        error: function (message) {
           alert("提交数据失败！");
        }
    });
}

//批量删除
$('#btn_del').on('click', function(){
	var selectContent = $('#table').bootstrapTable('getSelections');
	var len = selectContent.length;//获取对象数组的长度
	var arr = new Array();//初始化数组
	var i = 0;//初始化数组下标
	var sendData = '';//初始化发送数据
	var sep = ",";
	$.each(selectContent, function(index, data){//遍历对象数组
		//遍历对象,拼接数据
		$.each(data, function(key, value){
			if(key === "uuid"){
				sendData += value;
			}
		})
		if(index < len-1){
			sendData += sep;
		}else{
			sendData = sendData;
		}
		i++;
	})
	if(sendData == ''){
		layer.msg('请选择要删除的系统信息');
	}else{
		layer.confirm('是否删除选定的系统信息？', 
				{
				  btn: ['删除','取消'] //按钮
				}, 
				function(){
					$.post('sysMgmt/deleteMore?uuid='+sendData, function(d){
						if(d){
							layer.msg('删除成功');
						}else {
							layer.msg('删除失败，检查是否已关联机构');
						}
						$('#table').bootstrapTable('refresh', {silent: true});
					});
				}, 
				function(){
					layer.closeAll();
				}
		);
	}	
})

//layer弹出自定义div__新增
$('#sys_add').on('click', function() {
	$("#sys_add_div #form")[0].reset();
	$("#sys_add_div #form #ipt_memo").html('');
	$('.error').empty();
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: ['系统信息','color:rgb(99,102,104)'],
		area: ['400px', '400px'],
		maxmin: true,
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "sysMgmt/save");
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) == "false"){
					layer.msg('域编码/域名不能重复');
				}else{
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
				}
			});
		},
		btn2: function(index, layero){	
		}
	});
	return false;
});

//layer弹出自定义div__修改
function onEdit(id,code,name,sort,memo) {
	//字符串替换
	var reg = /<br ><\/a>/g;
	var reg2 = /<br \/>/g;
	memo = memo.replace(reg,'&#13;&#10;');
	memo = memo.replace(reg2,'&#13;&#10;');
	$("#sys_add_div #form")[0].reset();
	$('.error').empty();
	$("#sys_add_div #form #uuid").val(id);
	$("#sys_add_div #form #ipt_code").val(code);
	$("#sys_add_div #form #ipt_name").val(name);
	$("#sys_add_div #form #ipt_sort").val(sort);
	if("null" == memo){
		memo = ' ';
	}
	$("#sys_add_div #form #ipt_memo").html(memo);
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: ['系统信息','color:rgb(99,102,104)'],
		area: ['400px', '400px'],
		maxmin: true,
		btn: ['确定', '取消'],
		yes: function(index, layero){
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "sysMgmt/update");
			$('#form').ajaxSubmit(function(resultJson){
				if(JSON.stringify(resultJson) === "true"){
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
					return;
				}
				$.each(resultJson, function(i, item){
					if(item === "repeat"){
						layer.msg('域编码/域名不能重复');
					}
				})
			});
		},
		btn2: function(index, layero){	
		}
	});
};

//删除
function onDel(id) {
	var $ipt_uuid = $("#sys_del_div #del_form #del_uuid").val(id);
	layer.confirm('是否删除该系统信息？', 
		{
		  btn: ['删除','取消'] //按钮
		}, 
		function(){
			$.post('sysMgmt/delete?UUID='+id, function(d){
				if(d){
					layer.msg('资源删除成功');
				}else {
					layer.msg('删除失败，检查是否已关联机构');
				}
				$('#table').bootstrapTable('refresh', {silent: true});
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

