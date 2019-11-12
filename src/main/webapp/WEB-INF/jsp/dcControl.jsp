<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>监控指标管理列表</title>
</head>
<link rel="stylesheet" type="text/css" href="plugins/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/wu.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/icon.css" />
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body>
	<table id="dg"></table>
	<!-- 修改的div -->
	<div id="w" class="easyui-window" title="修改配置信息" data-options="iconCls:'icon-save',closed:true" 
				style="width:500px;height:300px;padding:5px;">
		<form id="upControl" method="post">
		<input type="hidden" name="id">
	    	<table cellpadding="5">
	    		<tr>
	    			<td><span style="color: red">图示范围为预警范围，超过范围最大值即报警,范围中间以逗号隔开，不可缺少</span></td>
	    		</tr>
	    		<tr>
	    			<td>设备名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="deviceName" data-options="required:true" readonly="readonly"></input></td>
	    		</tr>
	    		<tr>
	    			<td>ping平均速度:</td>
	    			<td><input class="easyui-textbox" type="text" name="pingavg" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>丢包率:</td>
	    			<td><input class="easyui-textbox" type="text" name="pingloss" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>cpu使用率:</td>
	    			<td><input class="easyui-textbox" type="text" name="cpu" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>内存使用率:</td>
	    			<td><input class="easyui-textbox" type="text" name="memory" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitUpdateForm()">保存</a>
	    </div>
	</div>
<script>
			$('#dg').datagrid({    
			    url:'/dcControl/controlList',  
			    title:'控制列表',
			    border:false,
			    rownumbers:true,
			    toolbar: [{
			    	iconCls:'icon-edit',
					text:'修改',
					handler:function(){
						var selectedRow=$("#dg").datagrid("getSelected");
							if(selectedRow==null){
								$.messager.alert('提示消息','请选择数据');
								return;
							}
						$("#w").window('open');
						//带上原数据
						$("#upControl").form('load',selectedRow)
					}
				    }],
			    fit: true,
			    editorHeight: "100",
			    striped: "true",
			    singleSelect: true,
			    pagination:true,//分页控件 
		        pageSize: 10,         //分页大小  
		        pageNumber:1,         //第几页显示（默认第一页，可以省略）  
		        pageList: [10, 20, 30], //设置每页记录条数的列表  
		        rownumbers:true,//行号 
			    frozenColumns : [ [ {
					title : 'id',
					field : 'id',
					width : 40,
					hidden : true
				} ] ],
			    columns:[[{
				    field:'deviceName',
				    title:'设备名称',
				    width:150,
				    formatter:function(value){
					    if(value =='FACE'){
						    return '人脸识别仪('+value+')';
						}else if(value =='RELAY'){
							return '继电器('+value+')';
						}else if(value =='QRCODE'){
							return '二维码识别器('+value+')';
						}else{
							return '上位机('+value+')';
						}
				    }
				},{
					field:'pingavg',
					title:'ping平均速度',
					width:280,
				    formatter:function(value){
					    var arr = value.split(',');
					    return "正常：小于"+arr[0]+";  预警："+arr[0]+'-'+arr[1]+'之间;  警告：大于'+arr[1];
				    }
				},{
					field:'pingloss',
					title:'丢包率',
					width:280,
				    formatter:function(value){
				    	 var arr = value.split(',');
						 return "正常：小于"+arr[0]+";  预警："+arr[0]+'-'+arr[1]+'之间;  警告：大于'+arr[1];
				    }
				},{
					field:'cpu',
					title:'cpu使用率',
					width:280,
				    formatter:function(value){
				    	var arr = value.split(',');
						return "正常：小于"+arr[0]+";  预警："+arr[0]+'-'+arr[1]+'之间;  警告：大于'+arr[1];
					}
				},{
					field:'memory',
					title:'内存使用率',
					width:280,
				    formatter:function(value){
				    	var arr = value.split(',');
						return "正常：小于"+arr[0]+";  预警："+arr[0]+'-'+arr[1]+'之间;  警告：大于'+arr[1];
					}
				}]]    
			}); 

		//修改的方法
		function submitUpdateForm(){
            //表单提交 会获取表单的所有信息
			$('#upControl').form('submit',{
				url:'/dcControl/updateControl',
				success: function(msg){
					msg=JSON.parse(msg);
							$.messager.alert('提示消息',msg.result);
							$('#dg').datagrid('reload');//局部刷新列表
							$("#w").window('close');
				}
			});
		}
</script>
</body>
</html>