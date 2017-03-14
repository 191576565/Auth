$(function(){
	$.getJSON("auth/getMenu",function(content){
		content.forEach(function(e){
			if(e.lvl===1){
            	$('#side-menu').append('<li class="hidden-folded padder m-t m-b-sm text-muted text-xs res_'+e.res_uuid+'"><span class="ng-scope">'+e.res_name+'</span></li>');
			}else {
				$('.res_'+e.res_up_uuid).after('<li><a class="J_menuItem" href="'+e.res_url+'"><i class="fa '+e.res_icon+'"></i><span class="nav-label">'+e.res_name+'</span></a></li>');
			}
		});
//		var resName = '';
//		var resId = '';
//		var resUpId = '';
//		var resUrl = '';
//		$.each(content, function(index, data){//遍历出第一层
//			$.each(data, function(key, value){
//				if(key=="res_uuid"){
//					resId = value;
//				}
//			})
//			$.each(data, function(key, value){
//				if(key=="res_name"){
//					resName = value;
//				}
//			})
//			$.each(data, function(key, value){
//				if(key=="lvl" && value==1){
//					$('#wrapper #1 #side-menu').append('<li id="'+resId+'" class="hidden-folded padder m-t m-b-sm text-muted text-xs"><span class="ng-scope">'+resName+'</span></li>');
//				}
//			})
//		})
//		$.each(content, function(index, data){//遍历出第二层
//			$.each(data, function(key, value){
//				if(key=="res_up_uuid"){
//					resUpId = value;
//				}
//			})
//			$.each(data, function(key, value){
//				if(key=="res_name"){
//					resName = value;
//				}
//			})
//			$.each(data, function(key, value){
//				if(key=="res_url"){
//					resUrl = value;
//				}
//			})
//			$.each(data, function(key, value){
//				if(key=="lvl" && value==2){
//					$('#wrapper #1 #side-menu #'+resUpId).after('<li><a class="J_menuItem" href="'+resUrl+'"><i class="fa fa-desktop"></i><span class="nav-label">'+resName+'</span></a></li>');
//				}
//			})
//		})
		 //菜单点击
        $(".J_menuItem").on('click',function(){
            var url = $(this).attr('href');
            $("#J_iframe").attr('src',url);
            return false;
        });
	});
});
