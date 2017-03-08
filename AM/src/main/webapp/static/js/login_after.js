$.getJSON("getMenu",function(content){
	var resIcon = '';
	var resUrl = '';
	$.each(content, function(index, data){//遍历对象数组
		$.each(data, function(key, value){
			if(key === "res_icon"){
				resIcon = value;
			}
			if(key === "res_url"){
				resUrl = value;
			}
		})
		$('#1 #2 #3 #4').append('<div class="post-masonry col-md-4 col-sm-6"><div class="post-thumb"><a href="'+resUrl+'?userid=${userid }&sid=${sid }"><img src="'+resIcon+'"></a></div></div>');
	})
})