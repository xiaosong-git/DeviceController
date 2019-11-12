<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">

<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<title>Insert title here</title>
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
</head>
<body>
	<div id="msg" style="display: none">
		<ul>
			<li>----------------------命令----------------------------</li>
			<li>关闭进程方式1：</li>
			<li>exit //退出 进程</li>
			<li>----------------------开始操作----------------------------</li>
		</ul>
			<span id="contexts"></span></br> 
			<span>请输入命令：</span> 
			<input type="text" id="inputext" value="" onkeydown="keyOnClick(event);"/>
	</div>
	<script type="text/javascript">
		if ('${result}' == 'success') {
			$('#msg').show();
		} else {
			$('#msg').hide();
			alert('远程连接失败，请联系管理员查看原因');
		}

		 function keyOnClick(e){
			    var theEvent = window.event || e;
			    var code = theEvent.keyCode || theEvent.which;
			    if (code==13) {  //回车键的键值为13
			        getdata();  //调用后端
			    }
			}
		function getdata(){
			var cmd=$('#inputext').val();
			var stringdata = '</br>';
			$.ajax({
	             type: "POST",
	             url:'/jsch/linux',
	             data: {"cmd": cmd},
	             dataType: 'json',
	             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	             success: function (data) {
	                 if(data){
	                	 console.log(data);
	                	 for(var i=0;i<data.length;i++){
		                	 stringdata += data[i]+'</br>';
		                	 }
	                	 $('#contexts').append(stringdata);
	                	 $('#inputext').val('');
	                 }
				 }
			});
		}
	</script>
</body>

</html>