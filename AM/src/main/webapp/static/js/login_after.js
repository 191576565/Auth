$.getJSON("getMenu",function(content){
	content.forEach(function(e,i){
		$('#1 #2 #3 #4').append('<div class="post-masonry col-md-4 col-sm-6"><div class="post-thumb"><a href="'+e.resUrl+'?userid=${userid }&sid=${sid }"><img src="'+e.resIcon+'"></a></div></div>');
	});
})

$.getJSON("getMenu",function(content) {
			var resIcon = '';
			var resUrl = '';
			$.each(content,function(index, data) {//遍历对象数组
				$.each(data, function(key,value) {
					if (key === "res_icon") {resIcon = value;}
					if (key === "res_url") {resUrl = value;}	
				})
				$('#1 #2 #3 #4').append('<div class="post-masonry col-md-4 col-sm-6"><div class="post-thumb effect"><a href="'+ resUrl+ '?userid=${userid }&sid=${sid }"><img src="'+resIcon+'"></a></div></div>');
			});
			$(".effect").mouseover(function() {
				$(this).css({"border" : "2px solid rgba(141,39,142,.75)",
							　	"overflow" : "hidden",
								"position" : "relative"
							})
			});
			$(".effect").mouseleave(function() {
				$(this).css({"border" : "1px solid #fff",
							"overflow" : "hidden",
							"position" : "relative"
							})
			});
		});