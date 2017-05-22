function initsystable() {
	//先销毁表格
	$('#table').bootstrapTable('destroy');
	$('#table').bootstrapTable({
		url: 'sysMgmt/sysData', //请求后台的URL（*）
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		queryParams: function queryParams() { //设置查询参数  
			var param = {
				sendParam: $("#iptSearch").val(),
				send: ''
			};　　
			return param;
		},
		queryParamsType: '',
		sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 1, //初始化加载第一页，默认第一页
		pageSize: 10, //每页的记录行数（*）
		pageList: [10], //可供选择的每页的行数（*）

		columns: [{
			checkbox: true
		}, {
			field: 'domain_id',
			title: '域编码',
			align: 'center',
			width: '30%'
		}, {
			field: 'domain_name',
			title: '域名称',
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
function onOrg(id) {
	$.ajax({
		type: "POST",
		url: "orgMgmt/accept",
		cache: false,
		data: {
			"uuid": id
		},
		dataType: 'json',
		success: function(message) {
			if (message > 0) {
				window.location.href = "orgMgmt";
			}
		},
		error: function(message) {
			alert("提交数据失败！");
		}
	});
}

//批量删除
$('#btn_del').on('click', function() {
	var selectContent = $('#table').bootstrapTable('getSelections');
	var uuids = '',
		domainCodes = '',
		domainNames = '';
	selectContent.forEach(function(e, i) {
		//逗号分隔拼接字符串,末尾不加逗号
		if (i == (selectContent.length - 1)) {
			uuids += (e.uuid);
			domainCodes += (e.domain_id);
			domainNames += (e.domain_name);
		} else {
			uuids += (e.uuid + ',');
			domainCodes += (e.domain_id + ',');
			domainNames += (e.domain_name + ',');
		}
	});
	if ('' == uuids) {
		layer.msg('请选择要删除的系统信息');
	} else {
		layer.confirm('是否删除选定的系统信息？', {
				title: ['提示信息','color:rgb(99,102,104)'],
				btn: ['删除', '取消']
			},
			function() {
				$.post('sysMgmt/deleteMore', {
					uuids: uuids,
					domainCodes: domainCodes,
					domainNames: domainNames
				}, function(d) {
					if (d) {
						layer.msg('删除成功');
					} else {
						layer.msg('删除失败，检查是否已关联机构/用户/角色/权限组');
					}
					$('#table').bootstrapTable('refresh', {
						silent: true
					});
				});
			},
			function() {
				layer.closeAll();
			}
		);
	}
})

//layer弹出自定义div__新增
$('#sys_add').on('click', function() {
	// $("#sys_add_div #form")[0].reset();
	$('input').placeholder();
	$('.cl').val('');
	$("#sys_add_div #form #ipt_memo").html('');
	$('.error').empty();
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: ['系统信息','color:rgb(99,102,104)'],
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero) {
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "sysMgmt/save");
			$('#form').ajaxSubmit(function(resultJson) {
				if (JSON.stringify(resultJson) == "false") {
					layer.msg('域编码/域名不能重复');
				} else {
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {
						silent: true
					});
				}
			});
		},
		btn2: function(index, layero) {},
		end: function(){
			layer.closeAll();
			$('#table').bootstrapTable('refresh', {
				silent: true
			});
		}
	});
	return false;
});

//layer弹出自定义div__修改
function onEdit(index) {
	var info = $('#table').bootstrapTable('getData')[index];
	$('input').placeholder();
	var memo = info.memo;
	//字符串替换(备注)
	if (null == memo) {
		memo = '';
	}
	var reg = /<br ><\/a>/g;
	var reg2 = /<br \/>/g;
	memo = memo.replace(reg, '&#13;&#10;');
	memo = memo.replace(reg2, '&#13;&#10;');
	//重置表单
	$("#sys_add_div #form")[0].reset();
	$('.error').empty();
	//填入数据

	$("#sys_add_div #form #uuid").val(info.uuid);
	$("#sys_add_div #form #ipt_code").val(info.domain_id);
	$("#sys_add_div #form #ipt_name").val(info.domain_name);
	$("#sys_add_div #form #ipt_sort").val(info.sort_id);
	$("#sys_add_div #form #ipt_memo").html(memo);
	layer.open({
		type: 1,
		content: $('#sys_add_div'),
		title: ['系统信息','color:rgb(99,102,104)'],
		area: ['400px', '400px'],
		btn: ['确定', '取消'],
		yes: function(index, layero) {
			if (!validate()) {
				return false;
			}
			$("#form").attr("action", "sysMgmt/update");
			$('#form').ajaxSubmit(function(resultJson) {
				if (JSON.stringify(resultJson) === "true") {
					layer.closeAll();
					$('#table').bootstrapTable('refresh', {
						silent: true
					});
					return;
				}
				$.each(resultJson, function(i, item) {
					if (item === "repeat") {
						layer.msg('域编码/域名不能重复');
					}
				})
			});
		},
		btn2: function(index, layero) {}
	});
};

//删除
function onDel(index) {
	var info = $('#table').bootstrapTable('getData')[index];
	layer.confirm(
		'是否删除该系统信息？', {　　
			title: ['提示信息','color:rgb(99,102,104)'],
			btn: ['删除', '取消'] //按钮
		},
		function() {
			$.post('sysMgmt/delete', {
				UUID: info.uuid,
				scopeCode: info.domain_id,
				scopeName: info.domain_name
			}, function(d) {
				if (d) {
					layer.msg('删除成功');
				} else {
					layer.msg('删除失败，检查是否已关联机构/用户/角色/权限组');
				}
				$('#table').bootstrapTable('refresh', {
					silent: true
				});
			});
		},
		function() {
			layer.closeAll();
		}
	);
};

function validate() {
	//非空验证
	var flag = true;
	$(".notNull").each(function() {
		if ("" == $(this).val()) {
			layer.msg($(this).attr('nullName') + "不能为空");
			flag = false;
			return false;
		}
	});
	//合法验证
	$("p.error").each(function() {
		if ("" != $(this).text()) {
			flag = false;
		}
	});
	return flag;
}