$('#table').bootstrapTable({                                                                                                  
    url: 'js/demo/5-res.json',                                                                                                
 	toolbar: '#toolbar', //工具按钮用哪个容器                                                                                          
 	striped: true, //是否显示行间隔色                                                                                                 
 	pagination: true, //是否显示分页（*）                                                                                             
 	sortable: false, //是否启用排序                                                                                                 
 	sortOrder: "asc", //排序方式                                                                                                  
 	pageNumber:1, //初始化加载第一页，默认第一页                                                                                            
 	pageSize: 5, //每页的记录行数（*）                                                                                                 
 	pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）                                                                              
 	strictSearch: true,                                                                                                       
 	showColumns: false, //是否显示所有的列                                                                                             
 	showRefresh: false, //是否显示刷新按钮                                                                                             
 	clickToSelect: false, //是否启用点击选中行                                                                                          
 	uniqueId: "ID", //每一行的唯一标识，一般为主键列                                                                                         
 	showToggle:false, //是否显示详细视图和列表视图的切换按钮                                                                                     
 	cardView: false, //是否显示详细视图                                                                                               
                                                                                                                              
    
      columns: [{                                                                                                               
        field: 'RES_ID',                                                                                                      
        title: '资源编码'                                                                                                         
    }, {                                                                                                                      
        field: 'RES_NAME',                                                                                                    
        title: '资源名称'                                                                                                         
    }, {                                                                                                                      
        field: 'RES_UP_UUID',                                                                                                        
        title: '上级资源'                                                                                                        
    }, {                                                                                                                      
        field: 'RES_URL',                                                                                                     
        title: '资源URL'                                                                                                        
    }, {                                                                                                                      
        field: 'RES_TYPE',                                                                                                    
        title: '资源类型'                                                                                                         
    }, {                                                                                                                      
        field: 'RES_CLASS',                                                                                                   
        title: '资源CSS'                                                                                                        
    }, {                                                                                                                      
        field: 'RES_COLOR',                                                                                                   
        title: '资源颜色'                                                                                                         
    }, {                                                                                                                      
        field: 'RES_ICON',                                                                                                    
        title: '资源ICON'                                                                                                       
    }, {                                                                                                                      
        field: 'SORT_ID',                                                                                                     
        title: '排序编号'                                                                                                         
    } 
    ,                                                                                                                         
    {                                                                                                                         
    	field: 'opt',                                                                                                         
    	title: '操 作',                                                                                                         
    	formatter:function(value,row,index){                                                                                  
    	var e = '<a href="#" onclick="edit(\''+ row.id + '\')" class="btn btn-info update edit">编辑</a> ';                                                      
    	var d = '<a href="#" onclick="del(\''+ row.id +'\')" class="btn btn-danger delete">删除</a> ';                                                        
    	var f = '<a href="2.html" onclick="del(\''+ row.id +'\')">角色列表</a> ';                                                   
    	return e+d;                                                                                                           
    	}                                                                                                                     
    },]                                                                                                                       
});                                                                                                                           
