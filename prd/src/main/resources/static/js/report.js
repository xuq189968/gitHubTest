$(function(){
	$('#grid').datagrid({
		url:'emp_listByPage.action',
		columns:[[
		  		    {field:'uuid',title:'编号',width:100},
		  		    {field:'username',title:'登陆名',width:100},
		  		    {field:'name',title:'真实姓名',width:100},
		  		    {field:'gender',title:'性别',width:100,formatter:function(value,row,index){
		  		    		if(value == '1'){
		  		    			return '男';
		  		    		}
		  		    	 	if(value == '0'){
			  		    		return '女';
			  		    	}
		  		    }},
		  		    {field:'email',title:'EMAIL',width:100},
		  		    {field:'tele',title:'电话',width:100},
		  		    {field:'address',title:'地址',width:100},
		  		    {field:'birthday',title:'出生年月日',width:100,formatter:function(value){
		  		    	return new Date(value).Format('yyyy年MM月dd日');
		  		    }},
		  		    {field:'dep',title:'部门编号',width:100,formatter:function(value){
		  		    		return value.name;
		  		    }},

				    {field:'-',title:'操作',width:200,formatter:function(value,row,index)
				    	{
				    		return "<a href='#' onclick='updatePwd("+row.uuid+")'>重置密码</a>";
				    	}}		    
				          ]]
	
	})
	
	//修改密码的保存按钮绑定事件
	$('#btnSave').bind('click',function(){
		var formdata = $('#updateForm').serializeJSON();
		//alert(JSON.stringify(formdata));
		
		$.ajax({
			url:'emp_updatePwd_reset.action',
			dataType:'json',
			type:'post',
			data:formdata,
			success:function(value){
				$.messager.alert('提示',value.message);
				if(value.success){
					$('#updatePwdWindow').window('close'); //关闭掉重置密码窗口
				}
			}
		})
	})
})

function updatePwd(uuid){
	//alert(uuid);
	//打开重置密码div层
	$('#updatePwdWindow').window('open');
	//获取updateForm
	$('#updateForm').form('load',{'id':uuid,'newPwd':''});
}