function initsyslogtable(){
	//先销毁表格
	$('#syslogtable').bootstrapTable('destroy');  
	//初始化表格
	$('#syslogtable').bootstrapTable({
		//method: 'post',
		url: 'syslog/logdata',
//	 	toolbar: '#toolbar', //工具按钮用哪个容器
	 	striped: true, //是否显示行间隔色
	 	pagination: true, //是否显示分页（*）
	 	sortable: false, //是否启用排序
	 	sortOrder: "asc", //排序方式
	 	pageNumber:1, //初始化加载第一页，默认第一页
	 	pageSize: 15, //每页的记录行数（*）
	 	pageList: [10, 25, 50], //可供选择的每页的行数（*）
	 	queryParamsType: '',
	 	sidePagination: "server",
	 	queryParams: function queryParams(params) {   //设置查询参数  
            var param = {    
                pageNumber: params.pageNumber,    
                pageSize: params.pageSize,  
                user_uuid : $("#searchid").val(),  
                op_type:$("#searchoptype").val(),
                startdate_start:$("#startdate").val(),
                startdate_end:$("#endate").val()
            };    
            return param;                   
          },  

//	 	strictSearch: true,
//	 	showColumns: true, //是否显示所有的列
//	 	showRefresh: true, //是否显示刷新按钮
//	 	clickToSelect: true, //是否启用点击选中行
	   	uniqueId: "ID", //每一行的唯一标识，一般为主键列
//	 	showToggle:true, //是否显示详细视图和列表视图的切换按钮
//	 	cardView: false, //是否显示详细视图

	    columns: [{
	        field: 'domain_name',
	        title: '域名称'
	    }, {
	        field: 'org_unit_desc',
	        title: '机构名称'
	    },{
	        field: 'role_name',
	        title: '角色名称'
	    }, {
	        field: 'user_name',
	        title: '用户名'
	    }, {
	        field: 'op_type',
	        title: '操作类型'
	    }, {
	        field: 'op_date',
	        title: '操作时间'
	    }, {
	    	field: 'op_content',
	    	title: '操 作',
	    	formatter:function(value,row,index){
	    		
	    		var e = '<a href="#" class="btn btn-info update edit" onclick="onMore(\''+ row.op_content +'\')">操作明细</a> ';
	    		return e;
	    	}
	    },],
	    pagination: true
	});
};

function onMore(name) {
	layer.open({
		type: 1,
		content: name,
		skin: 'layui-layer-molv',
		title: '操作信息',
		area: ['500px', '300px']
	});
};


//------------
$("#logsearch_old").click(function() {
    $.ajax({
        url: "syslog/search",//要请求的服务器url         
        data: $("#searchform").serialize(),
        async: true,   //是否为异步请求
        cache: false,  //是否缓存结果
        type: "POST", //请求方式为POST
        dataType: "json",   //服务器返回的数据是什么类型 
        success: function(result){  //这个方法会在服务器执行成功是被调用 ，参数result就是服务器返回的值(现在是json类型) 
            if(result){
            	
            	$('#syslogtable').bootstrapTable('refresh');
            	//$('#syslogtable').bootstrapTable('load', result);
            }else{
            	alert("error");
            	//$('#syslogtable').bootstrapTable('load', result);
            }
        }
      });
});

$('#startdate').datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    autoclose:true,
    language:'zh-CN'
});

$('#endate').datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    autoclose:true,
    language:'zh-CN'
});	
