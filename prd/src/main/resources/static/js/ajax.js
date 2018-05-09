function ajax(url,value,id,key){
	if(value!=null){
		$.ajax({
			url:url+value,
			dataType:'json',
			success:function(val){
				$('#'+id).html(val[key]);
			}	
		});
	}
	return "<span id='"+id+"'></span>";
}