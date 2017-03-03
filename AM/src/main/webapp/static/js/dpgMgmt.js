function initdpgMgmtlist(){
   $('#table').bootstrapTable({
	url: 'dpgMgmt/list',
// 	toolbar: '#toolbar', //工具按钮用哪个容器
 	striped: true, //是否显示行间隔色
 	pagination: true, //是否显示分页（*）
 	sortable: true, //是否启用排序
 	sortOrder: "asc", //排序方式
 	pageNumber:1, //初始化加载第一页，默认第一页
 	pageSize: 10, //每页的记录行数（*）
 	pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
 	search:  false,
 	showColumns: false, //是否显示所有的列
 	showRefresh: false, //是否显示刷新按钮
 	clickToSelect: true, //是否启用点击选中行
 	uniqueId: "group_id", //每一行的唯一标识，一般为主键列
 	showToggle:false, //是否显示详细视图和列表视图的切换按钮
 	cardView: false, //是否显示详细视图
 	queryParamsType: '',
 	sidePagination: "server",
 	queryParams: function queryParams(params) {   //设置查询参数  
        var param = {    
            pageNumber: params.pageNumber,    
            pageSize: params.pageSize,  
            user_id : $("#userid").val()
        };    
        return param;                   
      },  

    columns: [{
        field: 'domain_id',
        title: '域 ID',
        width:'20',
        checkbox: true
    }, {
        field: 'domain_name',
        title: '域名称',
        width:'100'
    },{
        field: 'group_id',
        title: '组编码',
        width:'40'
    }, {
        field: 'group_desc',
        title: '组名称',
        width:'100'
    }, {
        field: 'users',
        title: '所属用户',
        width:'200'
    }, {
    	field: 'opt',
    	title: '操 作',
        width:'400',
    	formatter:function(value,row,index){
    		var e = '<a href="#" class="btn btn-info update edit">编辑</a> ';
    		var d = '<a href="#" class="btn btn-danger delete" onclick="del(\''+ row.id +'\')">删除</a> ';
    		var f = '<a href="#" class="btn btn-primary create">数据权限</a> ';
    		return e+d+f;
    	}
    },]
});

};

//yeqc
//layer弹出自定义div

//$('#sys_add').on('click', function() {
//	layer.open({
//		type: 1,
//		content: $('#sys_add_div'),
//		skin: 'layui-layer-molv',
//		title: ' 权限组信息',
//		area: ['400px', '300px'],	
//	});
//});

function sys_add(){
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: ' 权限组信息',
		area: ['400px', '300px'],	
	});
};

$('#table').on('click', '.edit', function() {
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: '权限组信息',
		area: ['500px', '300px']
	});
	return false;
});

$('#sys_add_div #form').on('click', '#tree', function() {
	$.getJSON("js/demo/8_tree.json",function(result){
		$('#treeview12').treeview({
        	data: result
    	});
	});
	layer.open({
		type: 1,
		content: $('#opn_tree'),
		title: '权限组信息',
		area: ['300px', '600px']
	});
	return false;
});

$('#sys_add').on('click', function() {
	removeAll();
	$("#domaininfo").append("<option value='Value'>==请选择域==</option>");
    $.ajax({  
        url: "dpgMgmt/domaininfo",
        dataType: "json",  
        success: function (data) {  
            $.each(data, function (index, domaininfo) {
            	$("#domaininfo").append("<option value='"+ domaininfo.domain_id +"'>" + domaininfo.domain_id + "</option>");
            });  
        },  
         
        error: function (XMLHttpRequest, textStatus, errorThrown) {  
            alert("error");  
        }  
    }); 
    sys_add();
});  

function removeAll(){ 
	var obj=document.getElementById('domaininfo'); 
	obj.options.length=0; 
	} 

function formValidate(){ 
	$("#form").validate({
	  });
	}; 

$('#groupid').blur(function() {
	  $.ajax({  
	        url: "dpgMgmt/verifygroupid",
	        dataType: "json",  
	        success: function (data) {  
	            $.each(data, function (index, domaininfo) {
	            	 alert("start--->>>"+txt+"--->>>"+txt1);
	            });  
	        },  
	        error: function (XMLHttpRequest, textStatus, errorThrown) {  
	            alert("error");  
	        }  
	    }); 
	  
	});

$('#guserid').on('click', function() {
	getTreeData();
	layer.open({
		type: 1,
		content: $('#sys_user_div'),
		skin: 'layui-layer-molv',
		title: '选择用户',
		area: ['500px', '700px']
	});
	return false;
}); 

function getTreeData() {
	// var domainid=$('#domaininfo').val();
     $.ajax({ 
		    url: "dpgMgmt/getTreeData",
	        dataType: "json",
	        data:{domainid:$('#domaininfo').val()},
	        success: function (data) {
                var defaultData = eval(data.data);
                $('#user_in_div').treeview({
                	data: defaultData,  // 数据源
                	multiSelect: true,
                    color: "#428bca",
                    levels:30,
                    multiSelect: true,
                    showBorder: false,
                    showCheckbox: true
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown,data) {
                alert('啊哦，数据掉坑里了。。。');
            },
	    }); 
	}




    





	
