<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="plugins/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/wu.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/icon.css" />
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body>
<script type="text/javascript" src="plugins/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="plugins/echarts/echarts.js"></script>
	<script type="text/javascript" src="plugins/echarts/echarts.common.min.js"></script>
	<script type="text/javascript" src="script/hostComputerChart.js"></script><!-- 上位机所在的js -->
	<script type="text/javascript" src="script/portrait.js"></script><!-- 人像识别仪所在的js -->
	<script type="text/javascript" src="script/QRcode.js"></script><!-- 二维码识别器所在的js -->
	<script type="text/javascript" src="script/relay.js"></script><!-- 继电器所在的js -->
	<div id="countuser" style="height: 12%;width: 100%;"><!-- 统计展示 -->
	<div class="easyui-panel">
		<div style="padding: 10px 0 10px 60px;background:#87CEEB;" align="center">
		<table cellpadding="5px" cellspacing="5px">
					<tr style="font-size:20px;color: #E1FFFF;" id='tr'>
						<%-- <td>总用户：</td>
						<td>${countUser.totalUser}</td>
						<td>公司员工总数：</td>
						<td>${countUser.totalCompanyUser}</td>
						<td>访客总量：</td>
						<td>${countUser.totalVisitorUser}</td>
						<td>当日新增用户：</td>
						<td>${countUser.totalAddUser}</td>
						<td>入驻公司：</td>
						<td>${countUser.totalEntryCompany}</td> --%>
					</tr>
				</table>
		</div>
	</div>
	</div>
	<div id="host_computer" style="height: 47%;width: 50%;float: left"></div><!-- 上位机 -->
	<div id="portrait" style="height: 47%;width: 50%;float: right"></div><!-- 人像识别仪 -->
	<div id="QRcode" style="height: 47%;width: 50%;float: left"></div><!-- 二维码识别器 -->
	<div id="relay" style="height: 47%;width: 50%;float: right"></div><!-- 继电器 -->
	<script type="text/javascript">
	 $(function(){//加载options数据
		 countUser();
	   });
	 setInterval(countUser, 5*60*1000);//定时器
	 function countUser(){
         $.ajax({
             type: "POST",
             url:'/echarts/countUser',
             data: {},
             dataType: 'json',
             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
             success: function (data) {
                 if(data){
                     var dataString='<td>总用户：</td>'+
                     				'<td>'+data.totalUser+'</td>'+
                     				'<td>公司员工总数：</td>'+
                     				'<td>'+data.totalCompanyUser+'</td>'+
                     				'<td>访客总量：</td>'+
                     				'<td>'+data.totalVisitorUser+'</td>'+
                     				'<td>当日新增用户：</td>'+
                     				'<td>'+data.totalAddUser+'</td>'+
                     				'<td>入驻公司：</td>'+
                     				'<td>'+data.totalEntryCompany+'</td>';
                	 $('#tr').html(dataString);
                 }
             }
         })
	 }
	</script>
</body>
</html>