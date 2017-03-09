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
 	clickToSelect: false, //是否启用点击选中行
 	uniqueId: "uuid", //每一行的唯一标识，一般为主键列
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
        field: 'uuid',
        title: '主键'
    },{
        field: 'domain_id',
        title: '域 ID'
    },{
        field: 'check_ed',
        title: '多选框',
        width: '20',
        checkbox: true
    },{
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
        events: operateEvents,
    	formatter:function(value,row,index){
    		var e = '<button type="button" class="edit  btn-info update edit">编辑</button> ';
    		var d = '<button type="button" class="delete  btn-danger delete">删除</button> ';
    		var f = '<button type="button" class="access  btn-primary access">数据权限</button> ';
    		return e+d+f;
    	}
    },]
});
   $('#table').bootstrapTable('hideColumn', 'uuid');
   $('#table').bootstrapTable('hideColumn', 'domain_id');
};

window.operateEvents = {
      'click .edit': function (e, value, row, index) {
    	  var selRow = $("#table").bootstrapTable('getData');
          $('#groupid').val(selRow[index].group_id);
          $('#groupname').val(selRow[index].group_desc);
          $('#guserid').val(selRow[index].users);
          removeAll();
          $.ajax({  
                   url: "dpgMgmt/domaininfo",
                   dataType: "json",  
                   success: function (data) {  
                        $.each(data, function (index, domaininfo) {
                            $("#domaininfo").append("<option value='"+ domaininfo.domain_id +"'>" + domaininfo.domain_id + "</option>");
                            });  
                          },  
                   error: function (XMLHttpRequest, textStatus, errorThrown) {  
            	        layer.tips("抱歉，数据掉沟里了！", '#domaininfo');
                    }  
             });
            $("#domaininfo").val(selRow[index].domain_id); 
    	    sys_edit(selRow[index].uuid);
      },
      'click .delete': function (e, value, row, index) {
    	  var selRow = $("#table").bootstrapTable('getData');
          del(selRow[index].uuid);
      },
      'click .access': function (e, value, row, index) {
    	  var selRow = $("#table").bootstrapTable('getData');
    	  window.location="dpgMgmt/gouMgmt?groupuuid="+selRow[index].uuid;
      }
      };


function sys_add(){
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: ' 权限组信息',
		area: ['400px', '310px'],
		btn: ['保存']
         ,yes: function(index){
        	  saveform();
              location.reload();
              layer.closeAll(index);
         }
	});
};

function sys_edit(uuid){
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		skin: 'layui-layer-molv',
		title: '权限组信息',
		area: ['400px', '310px'],
		btn: ['保存']
        ,yes: function(index){
        	updateform(uuid);
        	location.reload();
            layer.closeAll(index);
        }
	});
	return false;
}

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
		area: ['300px', '500px']
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
        	layer.tips("抱歉，数据掉沟里了！", '#domaininfo');
        }  
    }); 
    sys_add();
});  

function removeAll(){ 
	var obj=document.getElementById('domaininfo'); 
	obj.options.length=0; 
	} 

$('#groupid').blur(function() {
		var domainid=$('#domaininfo').val();
		var groupid=$('#groupid').val();
		if(domainid==null || domainid=='Value'){
			layer.tips("请先选择所属域!", '#domaininfo');
			$('#groupid').val("");
		    return false;
		 }
		if(groupid==null || groupid==''){
			layer.tips("组 id 不能为空!", '#groupid');
			 return false;
		}else{
	        $.ajax({  
	        url: "dpgMgmt/verifygroupid",
	        data:{domaininfo:domainid,groupid:groupid},
	        dataType: "json",  
	        success: function (data) {
	        	if(data.status!='success'){
	        		layer.tips("啊哦,组id已被占用,请重新输入！", '#groupid');
	        		$('#groupid').val("");
	        	}  	
	        },  
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        	    layer.tips("抱歉，数据掉沟里了！", '#groupid');
	        } 
	    });
	}
});

$('#guserid').on('click', function() {
	var domainid=$('#domaininfo').val();
	if(domainid==null || domainid=='Value'){
		layer.tips("请先选择所属域!", '#domaininfo');
		 return false;
	 }else{
	    getTreeData();
	    layer.open({
	    	  type: 1,
	    	  title: '选择用户',
	    	  skin: 'layui-layer-molv',
	    	  area: ['500px', '550px'],
	    	  content: $('#sys_user_div'),
	    	  btn: ['重置', '保存']
	          , yes: function(){
	            dosome(2);
	          },btn2: function(index){
	            getDisabled();
                layer.closeAll(index);
              }
	    	});
	return false;
	}
}); 

function getTreeData() {
     $.ajax({ 
		    url: "dpgMgmt/getTreeOrguserData",
	        dataType: "json",
	        data:{domainid:$('#domaininfo').val()},
	        success: function (data) {
                var defaultData = eval(data.data);
                $('#user_in_div').treeview({
                	data: defaultData,  // 数据源
                	showCheckbox : true,
                    levels:10,
                    color: "#000000",
                    showBorder: false,
                //  backColor:"#0000FF",
                //  onhoverColor:"7EC0EE",
                //  multiSelect:true,
                //  checkedIcon:"glyphicon glyphicon-check",
                //  selectable: true,
                //  showBorder: false,
                    onNodeChecked: function(event, data){
                   	if(data.nodes != null)
                   	{
                   		var arrayInfo = data.nodes;
                   		for (var i = 0; i < arrayInfo.length; i++) {
                   			$('#user_in_div').treeview('checkNode', [ arrayInfo[i].nodeId, { silent: true } ]);
                   			//$('#user_in_div').treeview('toggleNodeChecked', [ arrayInfo[i].nodeId, { silent: true } ]);
               			}                                 
                   	}
               	  },
               	  onNodeUnchecked: function(event, data){
                       	if(data.nodes != null)
                       	{
                       		var arrayInfo = data.nodes;
                       		for (var i = 0; i < arrayInfo.length; i++) {
                       			//$('#user_in_div').treeview('checkNode', [ arrayInfo[i].nodeId, { silent: true } ]);
                       			$('#user_in_div').treeview('toggleNodeChecked', [ arrayInfo[i].nodeId, { silent: true } ]);
               				}
                       	}
               		  }
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown,data) {
            	layer.tips("抱歉，数据掉沟里了！", '#domaininfo');
            },
	    }); 
	}


function dosome( num){
	if(num == 1)
	{
		$('#user_in_div').treeview('checkAll', { silent: true });//全选
	}else if(num == 2){
		$('#user_in_div').treeview('uncheckAll', { silent: true });//取消全选
	}else if(num == 3){
		$('#user_in_div').treeview('collapseAll', { silent: true });//折叠
	}else if(num == 4){
		$('#user_in_div').treeview('expandAll', { levels: 2, silent: true });//展开所有二级节点
	}
}

function getDisabled(){
	var checkedArr = $('#user_in_div').treeview('getChecked','');
	var userid="";
	for(var i = 0; i < checkedArr.length; i++){
          if(i == 0){
        	  userid=checkedArr[i].id;
          }else{
        	  userid=userid+","+checkedArr[i].id;
           }
	    }
	$('#guserid').val(userid);
}

function saveform(){
	$.ajax({  
        url: "dpgMgmt/saveform",
        dataType: "json",
        type:'post',
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data:{domaininfo:$('#domaininfo').val(),groupid:$('#groupid').val(),groupname:$('#groupname').val(),guserid:$('#guserid').val()},
        success: function (data) {
        	if(data.status=='success'){
        		layer.msg("数据保存成功");
        	}else{
        		layer.msg("数据保存失败");
        	}  	
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { 
             	layer.msg("数据被城管抓走了！");
        }  
    }); 
}

function updateform(uuid){
	$.ajax({  
        url: "dpgMgmt/updateform",
        dataType: "json",
        type:'post',
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data:{uuid:uuid,domaininfo:$('#domaininfo').val(),groupid:$('#groupid').val(),groupname:$('#groupname').val(),guserid:$('#guserid').val()},
        success: function (data) {
        	if(data.status=='success'){
        		layer.msg("数据保存成功");
        	}else{
        		layer.msg("数据保存失败");
        	}  	
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { 
             	layer.msg("数据被城管抓走了！");
        }  
    }); 
}

$('#btn_add').on('click', function() {
    var selRow = $("#table").bootstrapTable('getSelections');
    var uuid = new Array(); 
    if(selRow.length>0){
    	$.each(selRow, function() {
    		uuid.push(this.uuid);
    		});
        $.ajax({  
            url: "dpgMgmt/delform",
            dataType: "json", 
            data:{'uuid':uuid.toString()},
            success: function (data) {
                	if(data.status=='success'){
                	    	layer.msg("数据删除成功,本次共删除"+data.delcount+"行数据！");
                	   }else{
                	    	layer.msg("数据删除失败咯！");}  	
                       },
            error: function (XMLHttpRequest, textStatus, errorThrown) { 
             	layer.msg("对象被城管抓走了！");}  
               }); 
        location.reload();
    }else{
     layer.tips("要先选择删除对象哦！", '#btn_add');
    }
});

function del(uuid){
	$.ajax({  
        url: "dpgMgmt/delform",
        dataType: "json", 
        data:{'uuid':uuid.toString()},
        success: function (data) {
            	if(data.status=='success'){
            	    	layer.msg("数据删除成功,本次共删除"+data.delcount+"行数据！");
            	   }else{
            	    	layer.msg("数据删除失败咯！");}  	
                   },
        error: function (XMLHttpRequest, textStatus, errorThrown) { 
         	layer.msg("对象被城管抓走了！");}  
           }); 
    location.reload();
}










    





	
