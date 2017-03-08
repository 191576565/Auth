$(function(){
	$.getJSON("auth/getMenu",function(content){
		var resName = '';
		var resId = '';
		var resUpId = ''
		$.each(content, function(index, data){//遍历出第一层
			$.each(data, function(key, value){
				if(key=="res_uuid"){
					resId = value;
				}
			})
			$.each(data, function(key, value){
				if(key=="res_name"){
					resName = value;
				}
			})
			$.each(data, function(key, value){
				if(key=="lvl" && value==1){
					$('#wrapper #1 #side-menu').append('<li id="'+resId+'" class="hidden-folded padder m-t m-b-sm text-muted text-xs"><span class="ng-scope">'+resName+'</span></li>');
				}
			})
		})
		$.each(content, function(index, data){//遍历出第二层
			$.each(data, function(key, value){
				if(key=="res_up_uuid"){
					resUpId = value;
				}
			})
			$.each(data, function(key, value){
				if(key=="res_name"){
					resName = value;
				}
			})
			$.each(data, function(key, value){
				if(key=="lvl" && value==2){
					$('#wrapper #1 #side-menu #'+resUpId).after('<li><a class="J_menuItem" href="sysMgmt"><i class="fa fa-desktop"></i><span class="nav-label">'+resName+'</span></a></li>');
				}
			})
		})
		 //菜单点击
        $(".J_menuItem").on('click',function(){
            var url = $(this).attr('href');
            $("#J_iframe").attr('src',url);
            return false;
        });
	});
});
