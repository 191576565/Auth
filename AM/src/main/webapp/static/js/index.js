$(function(){
	$.getJSON("auth/getMenu",function(content){
		content.forEach(function(e){
			if(e.lvl===1){
            	$('#side-menu').append('<li class="hidden-folded padder m-t m-b-sm text-muted text-xs res_'+e.res_uuid+'"><span class="ng-scope">'+e.res_name+'</span></li>');
			}else {
				$('.res_'+e.res_up_uuid).after('<li><a class="J_menuItem" href="'+e.res_url+'"><i class="fa '+e.res_icon+'"></i><span class="nav-label">'+e.res_name+'</span></a></li>');
			}
		});
		 //菜单点击
        $(".J_menuItem").on('click',function(){
            var url = $(this).attr('href');
            $("#J_iframe").attr('src',url);
            return false;
        });
	});
});
