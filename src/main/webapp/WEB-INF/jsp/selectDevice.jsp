<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<script type="text/javascript" src="script/network.js"></script><!-- 网络状况曲线图所在的js -->
	<script type="text/javascript" src="script/performance.js"></script><!-- 设备性能曲线图所在的js -->
	<script type="text/javascript" src="script/selectDevice.js"></script><!-- 列表所在的js -->
	<div id="network" style="height: 50%;width: 48%;float: left"></div><!-- 网络状况曲线图 -->
	<div id="performance" style="height: 50%;width: 48%;float: right"></div><!-- 设备性能曲线图 -->
	<div id="selectDeviceList" style="height: 50%;width: 100%;float: left"><!-- 设备列表 -->
		<div style="padding: 10px 0 10px 60px">
			<form id="ff" method="post">
				<table cellpadding="5px" cellspacing="5px">
					<tr>
						<td>开始时间：</td>
						<td><input id="begtime" name="begtime" class="easyui-datetimebox"></td>
						<td>~</td>
						<td>结束时间：</td>
						<td><input id="endtime" name="endtime" class="easyui-datetimebox"></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="findDevice()">查询</a></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearform()">清除</a></td>
					</tr>
				</table>
			</form>
		</div>
		<table id="selectDeviceTable" class="querytable"></table>
	</div>
</body>
</html>