var s = {
	view: {
		dblClickExpand: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid"
		}
	},
	callback: {
		onClick: zTreeOnClickSimple
	},
	check : {
		enable : true,
		chkboxType : {
			"Y" : "",
			"N" : ""
		}
	}
};

function zTreeOnClickSimple(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandNode(treeNode, null, null, null);

	return true;
};

$(function(){
	initTable();
	$('.tree').click(function(){
		$.get('gouMgmt/orgTree', function(data){
			$.fn.zTree.init($("#org"), s, data); //树
			var treeObj = $.fn.zTree.getZTreeObj("org");
			treeObj.expandAll(true);
			
			var u=orgs.split(',');
			if(orgs!="" && orgs!=null){
				u.forEach(function(e){
					var node = treeObj.getNodeByParam("id", e);
					treeObj.checkNode(node, true, true);
				});
			}
			
			layer.open({
					type : 1,
					content : $('.t'),
					title : '选择机构',
					area : [ '400px', '400px' ],
					maxmin: true,
					btn: ['确定', '取消'],
					yes: function(index, layero){
						var nodes = treeObj.getCheckedNodes(true);
						var us=[];
						nodes.forEach(function(e){
							us.push(e.id);
						});
						orgs=us.join();
						if(orgs!=null && orgs!=''){
							$('.tree').text('编辑条件值');
						}
						layer.close(index);
					},
					btn2: function(index, layero){	
					}
			});
		})
	})
})

function initTable(){
   $('#table').bootstrapTable({
	url: 'gouMgmt/goulist',
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
        field: 'check_ed',
        title: '多选框',
        width: '20',
        checkbox: true
    },{
        field: 'domain_id',
        title: '域 ID'
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
        field: 'req_url',
        title: 'URL',
        width:'200'
    }, {
    	field: 'req_url_desc',
    	title: 'URL描述',
        width:'400'
    }, {
        field: 'condition_type',
        title: '条件类型',
        width:'60',
    }, {
    	field: 'opt',
    	title: '操 作',
        width:'170',
    	formatter:function(value,row,index){
    		var e = '<button type="button" class="btn btn-info update edit" onclick="onEdit(\''+ row.uuid +'\',\''+ row.condition_content_uuid +'\')">编辑</button> ';
    		var d = '<button type="button" class="btn btn-danger delete">删除</button> ';
    		return e+d;
    	}
    },]
});
   $('#table').bootstrapTable('hideColumn', 'uuid');
   $('#table').bootstrapTable('hideColumn', 'domain_id');
};

//layer弹出自定义div__修改
function onEdit(uuid,content) {
	orgs = content=="null"?"":content;
	console.log(orgs);
	$("#sys_add_div #form")[0].reset();
	$("#sys_add_div #form #urlid").html("");
	$("#sys_add_div #form #dictcode").html("");
	$.getJSON("gouMgmt/loadEditPara?uuid="+uuid,function(content){
		var start = "";//保存初始默认值
		content.forEach(function(e){
			$('#groupid').val(e.group_desc);
			$("#urlid").append("<option value='"+ e.condition_type +"'>" + e.condition_type_name + "</option>");
			$("#dictcode").append("<option value='"+ e.url_uuid +"'>" + e.req_url_desc + "</option>");
			start = e.condition_type;
		});
		$.getJSON("gouMgmt/getCondition",function(content){
			content.forEach(function(e1){
				if(start != e1.dict_id){
					$("#urlid").append("<option value='"+ e1.dict_id +"'>" + e1.dict_name + "</option>");
				}
			});
		});
	});
	
	$('#urlid').change(function(){
		orgs = "";
    	$("#dictcode").html("");
    	$("#dictcode").append("<option value=''>请选择URL描述</option>");
    	var conType = $(this).children('option:selected').val();
    	$.getJSON("dpgMgmt/typeUrl?type="+conType,function(data){    	    		
    		$.each(data, function (index, groupuuid) {
            	$("#dictcode").append("<option value='"+ groupuuid.uuid +"'>" + groupuuid.req_url_desc + "</option>");
            }); 
    	});
    });
	
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: '系统信息',
		area: ['768px', '432px'],
		btn: ['保存','取消'],
		yes: function(index){
        	$.ajax({
				url : "gouMgmt/update?"+$('#form').serialize()+"&orgs="+orgs+"&uuid="+uuid,
				dataType : "json",
				type : 'post',
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
				},
				error : function(
						XMLHttpRequest,
						textStatus, errorThrown) {
					layer.msg("数据被城管抓走了！");
				}
			});
        },
		btn2: function(index, layero){}
	});
};

//window.operateEvents = {
//      'click .edit': function (e, value, row, index) {
//    	  var selRow = $("#table").bootstrapTable('getData');
//          $('#groupid').val(selRow[index].group_id);
//          $('#urlid').val(selRow[index].req_url);
//          $('#urlname').val(selRow[index].req_url_desc);
//          $('#dictinfo').val(selRow[index].condition_content);
//          var condition_type=selRow[index].condition_type;
//          removeAll();
//          $.ajax({  
//                   url: "getdictcode",
//                   dataType: "json",
//                   data:{groupuuid:groupuuid},
//                   success: function (data) {  
//                	    $.each(data, function (index, groupuuid) {
//                	    	dumainid=groupuuid.domain_id;
//                        	$("#dictcode").append("<option value='"+ groupuuid.dict_id +"'>" + groupuuid.dict_name + "</option>");
//                        	if(groupuuid.dict_id==selRow[index].condition_type){
//                        	  $("#dictcode").val(condition_type); }
//                	    });  
//                    },  
//                    error: function (XMLHttpRequest, textStatus, errorThrown , data) {  
//                    	layer.tips("请先配置组所属域的条件类型字典！", '#dictcode');
//                    } 
//             });
//    	    sys_edit(selRow[index].uuid);
//      },
//      'click .delete': function (e, value, row, index) {
//    	  var selRow = $("#table").bootstrapTable('getData');
//          del(selRow[index].uuid);
//      }
//      };

var orgs = '';
function sys_add(){
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: ' URL资源配置',
		area: ['600px', '450px'],
		btn: ['保存','取消'],
        yes: function(index){
        	$.ajax({
				url : "gouMgmt/save?"+$('#form').serialize()+"&orgs="+orgs,
				dataType : "json",
				type : 'post',
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {silent: true});
				},
				error : function(
						XMLHttpRequest,
						textStatus, errorThrown) {
					layer.msg("数据被城管抓走了！");
				}
			});
        },
		btn2: function(index, layero){}
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
          if(fromcheck()){
        	updateurlform(uuid);
        	location.reload();
            layer.closeAll(index);
           };
        }
	});
	return false;
}

function fromcheck(){
	var urlid=$('#urlid').val();
    var dictcode=$('#dictcode').val();
    var dictinfo=$('#dictinfo').val();
    if(isnull(urlid)){
		layer.tips("请输入URL！", '#urlid');
		return false;
	}
	if(dictcode==null || dictcode=='Value'){
		layer.tips("请选择条件类型!", '#dictcode');
		 return false;
	 }
	if(isnull(dictinfo)){
		layer.tips("请输入条件参数！", '#dictinfo');
		 return false;
	}else{return true;};
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
     $('#urlid').val('');
     $('#urlname').val('');
     $('#dictinfo').val('');
     orgs = '';
     $('.tree').text('选择条件值');
	removeAll();
    $.ajax({  
        url: "gouMgmt/getGroupCode",
        dataType: "json",
        success: function (data) {  
            $.each(data, function (index, groupuuid) {
            	$("#groupid").val(groupuuid.group_desc);
            	$("#groupUuid").val(groupuuid.group_uuid);
            });  
        },  
        error: function (XMLHttpRequest, textStatus, errorThrown , data) {  
        	layer.tips("请先配置组所属域的条件类型字典！", '#dictcode');
        }  
    });
    var urlUuid = "";
	$('#urlid').change(function(){
    	$("#dictcode").html("");
    	$("#dictcode").append("<option value=''>请选择URL描述</option>");
    	var conType = $(this).children('option:selected').val();
    	$.getJSON("dpgMgmt/typeUrl?type="+conType,function(data){    	    		
    		$.each(data, function (index, groupuuid) {
            	$("#dictcode").append("<option value='"+ groupuuid.uuid +"'>" + groupuuid.req_url_desc + "</option>");
            }); 
    	});
    });
	$('#dictcode').change(function(){
		var url = $(this).children('option:selected').val();
		$('#urlname').val(url);
	});
    sys_add();
});  

function removeAll(){ 
	var obj=document.getElementById('dictcode'); 
	obj.options.length=0; 
	} 

$('#urlid').blur(function() {
		var urlid=$('#urlid').val();
		if(urlid==null || urlid==''){
			layer.tips("URL 不能为空!", '#urlid');
			 return false;
		}else{
	        $.ajax({  
	        url: "gouMgmt/verifyurlid",
	        data:{urlid:urlid},
	        dataType: "json",  
	        success: function (data) {
	        	if(data.status!='success'){
	        		layer.tips("啊哦,组id已被占用,请重新输入！", '#urlid');
	        	}  	
	        },  
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        	    layer.tips("抱歉，数据掉沟里了！", '#groupid');
	        } 
	    });
	}
});

$('#dictinfo').on('click', function() {
	var dictcode=$('#dictcode').val();
	if(dictcode==null || dictcode=='Value'){
		layer.tips("条件类型!", '#dictcode');
		 return false;
	 }else{
	    getTreeData(dictcode);
	    layer.open({
	    	  type: 1,
	    	  title: '选择'+dictcode,
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

function getTreeData(dictcode) {
     $.ajax({ 
		    url: "getTreeData",
	        dataType: "json",
	        data:{dictcode:dictcode,domainid:dumainid},
	        success: function (data) {
                var defaultData = eval(data.data);
                $('#user_in_div').treeview({
                	data: defaultData,  // 数据源
                	showCheckbox : true,
                    levels:10,
                    color: "#000000",
                    showBorder: false,
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
	var dictinfo="";
	for(var i = 0; i < checkedArr.length; i++){
          if(i == 0){
        	  dictinfo=checkedArr[i].id;
          }else{
        	  dictinfo=dictinfo+","+checkedArr[i].id;
           }
	    }
	$('#dictinfo').val(dictinfo);
}

function updateurlform(uuid){
	$.ajax({  
        url: "updateurlform",
        dataType: "json",
        type:'post',
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        data:{uuid:uuid,urlid:$('#urlid').val(),urlname:$('#urlname').val(),dictcode:$('#dictcode').val(),dictinfo:$('#dictinfo').val()},
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
            url: "delurlform",
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
        url: "delurlform",
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

function ismail(str){
    var rg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return rg.test(str);
}


function isgroupid(str){
	/**正则表达式:只能是1~30位字母数字组合**/
    var rg = /^[a-zA-Z0-9]{1,30}$/;
    return rg.test(str);
}

function isnull(str){
	/**正则表达式:整个字符串为空或者都是空白字符 **/
	var rg = /^[\s]{0,}$/;
    return rg.test(str);
}











    





	
