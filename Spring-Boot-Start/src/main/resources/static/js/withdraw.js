$(function(){
	$("#bankUrlBtn").click(function(){
		debugger;
		$.ajax({
	    	url: "http://localhost:9518/th/withdraw",
	    	type: "post",
	    	data:$("#withdrawSubmit").serialize(),
	    	dataType:"json",  //数据格式设置为json
	    	success: function(data) {
	    		debugger;
	    		if(data.resultCode === "000000" && data.resultMsg === "成功") {
	            	alert("success");
	            	var dataResult=data.data;
	            	$("#bankUrlForm").attr("action",dataResult.bankUrl)
	            	$("#merchantCode").val(dataResult.merchantCode);
	            	$("#requestKey").val(dataResult.requestKey);
	            	$("#data").val(dataResult.data);
	            	$("#sign").val(dataResult.sign);
	            	
	            	$("#bankUrlForm").submit();
	            } else {
	            	alert("发生异常：" + data.msg);
	            }
	    	},
	        error: function (response, ajaxOptions, thrownError) {
	        	debugger;
	        	alert("error");       
	        }
	 });
	});
});



