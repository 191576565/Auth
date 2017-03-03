$(function(){
	initTable()
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
									+'</td><td>-</td></tr>';
							$('#table tbody').append(temp);
						}else {
							temp='<tr class="treegrid-'+e.uuid+' treegrid-parent-'+e.org_up_uuid+'" lvl="'+e.level+'"><td>'+e.org_unit_id+'</td><td>'+e.domain_name+'</td><td>'
									+e.org_unit_desc+'</td><td>'+e.up_org_unit_desc
									+'</td><td><button id="" type="button" class="btn btn-info btn-sm">编辑</button>&nbsp;<button id="" type="button" class="btn btn-danger btn-sm">删除</button></td></tr>';
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

//表单验证
angular.module('myApp', [])
.controller('SignUpController',function($scope){
	$scope.userdata = {};
	$scope.submitForm = function(){};
})