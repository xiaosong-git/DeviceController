<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="plugins/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/wu.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/icon.css" />
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="plugins/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="plugins/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body>
	<div class="easyui-panel" title="设备详情列表">
		<div style="padding: 10px 0 10px 60px">
			<form id="ff" method="post">
				<table cellpadding="5px" cellspacing="5px">
					<tr>
						<td>设备名称：</td>
						<td><select name="deviceName" id="deviceName">
								<option value="">空</option>
								<option value="RELAY">继电器</option>
								<option value="FACE">人脸识别器</option>
								<option value="QRCODE">二维码识别仪</option>
								<option value="SWI">上位机</option>
						</select></td>
						<td>故障类型：</td>
						<td><select name="warn" id="warn">
								<option value="">空</option>
								<option value="normal">正常设备</option>
								<option value="ewarn">预警设备</option>
								<option value="warn">报警设备</option>
						</select></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitForm()">查询</a></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearForm()">清空</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<table id="tableShowData" class="querytable"></table>
	<div id="edits" class="easyui-window" title="修改配置信息" data-options="iconCls:'icon-save',closed:true" 
				style="width:500px;height:300px;padding:5px;">
		<form id="updevicedetail" method="post">
		<input type="hidden" name="id">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>设备名称：</td>
	    			<td><input class="easyui-textbox" type="text" name="deviceName" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>远程ip地址：</td>
	    			<td><input class="easyui-textbox" type="text" name="remoteIp" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>远程账户：</td>
	    			<td><input class="easyui-textbox" type="text" name="loginName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>远程密码：</td>
	    			<td><input class="easyui-textbox" type="text" name="loginPwd" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>远程端口号：</td>
	    			<td><input class="easyui-textbox" type="text" name="remotePort" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="UpdateForm()">保存</a>
	    </div>
	</div>
	
<script type="text/javascript">
	$(function() {
		var warn = '${warn}';
		var deviceName = '${deviceName}';
		$("#deviceName").find("option[value='"+deviceName+"']").attr("selected",true); 
		$("#warn").find("option[value='"+warn+"']").attr("selected",true); 
		submitForm();
	});

	function submitForm(){//查詢按鈕触发的事件
		var pars = {
				deviceName : $("#deviceName").val(),
				warn:$("#warn").val()
			};
		initTable(pars);
		$('#tableShowData').datagrid('reload')
		}

	function clearForm(){
		$("#deviceName").find("option[value='']").attr("selected",true); 
		$("#warn").find("option[value='']").attr("selected",true); 
		submitForm();
		}
	//实现DataGird控件的绑定操作
	function initTable(pars) {
		$('#tableShowData').datagrid({ //定位到Table标签，Table标签的ID是tableShowData
			fitColumns : true,
			url : '/deviceDetail/deviceDetailList', //指向后台的Action来获取当前用户的信息的Json格式的数据
			title : '设备详情列表',
			iconCls: 'icon-save',
			toolbar: [{
		    	iconCls:'icon-edit',
				text:'修改远程配置',
				handler:function(){
					var selectedRow=$("#tableShowData").datagrid("getSelected");
						if(selectedRow==null){
							$.messager.alert('提示消息','请选择数据');
							return;
						}
					$("#edits").window('open');
					//带上原数据
					$("#updevicedetail").form('load',selectedRow)
				}
			    }],
            height: 723,
            nowrap: true,
            loadMsg: '正在加载用户的信息...',
            autoRowHeight: false,
            striped: true,
            collapsible: true,
            pagination: true,
            rownumbers: true,//添加列数字
            //sortName: 'ID',    //根据某个字段给easyUI排序
            //sortOrder: 'asc',
            remoteSort: false,
            queryParams: pars,  //异步查询的参数
            pageList: [1, 10, 15, 20, 25,30],//分页的分组设置
            pageSize: 10,//每页的默认值大小
		    frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 40,
				hidden : true
			} ] ],
		    columns:[[{
			    field:'deviceName',
			    title:'设备名称',
			    width:120,
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
				field:'deviceIp',
				title:'ip地址',
				width:70
			},{
				field:'pingAvg',
				title:'ping平均速度',
				width:60
			},{
				field:'pingLoss',
				title:'丢包率',
				width:60,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'cpu',
				title:'cpu使用率',
				width:60,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'memory',
				title:'内存使用率',
				width:60,
				formatter:function(value){
					return value+'%'
				}
			},{
				field:'pingStatus',
				title:'网络状态',
				width:50
			},{
				field:'telnetStatus',
				title:'telnet状态',
				width:50
			},{
				field:'remoteIp',
				title:'远程ip地址',
				width:50
			},{
				field:'loginName',
				title:'远程登录账号',
				width:50
			},{
				field:'loginPwd',
				title:'远程登录密码',
				width:100
			},{
				field:'remotePort',
				title:'远程端口号',
				width:50
			},{
				field:'detail',
				title:'编辑',
				align:'center',
				width:90,
				formatter:function(value,row){
				       if(row.deviceName=='SWI'){
				    	   return "<a onclick=\"edits('"+row.orgCode+"','"+row.deviceIp+"')\"> 查看 </a>"+
				    	   		  "<a onclick=\"jsch('"+row.remoteIp+"','"+row.loginName+"','"+row.loginPwd+"','"+row.remotePort+"')\"> 远程链接 </a>"
					       }
				       else{
				    	   	return "<a onclick=\"edits('"+row.orgCode+"','"+row.deviceIp+"')\"> 查看 </a>";
					       }
				    }
			}]]    
		});
	}
	function edits(orgCode,deviceIp){
		 self.parent.addTab('设备详情','/deviceDetail/oneDevice?orgCode='+orgCode+'&deviceIp='+deviceIp);
		}
	function jsch(remoteIp,loginName,loginPwd,remotePort){
		if(remoteIp=='null'||remoteIp==""||loginName=='null'||loginName==""||loginPwd=='null'||loginPwd==""||remotePort=='null'||remotePort==""){
			$.messager.alert('提醒','请先配置远程链接信息');
			}
		else{
			$.ajax({
	            type: "POST",
	            url:'/deviceDetail/xshell',
	            data: {"remoteIp":remoteIp,"loginName":loginName,"loginPwd":loginPwd,"remotePort":remotePort},
	            dataType: 'json',
	            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	            success: function (data) {
	                if(data){
	               		console.log(data.result);
	               		if(data.result=='fail'){
	               			$.messager.alert('提醒','请下载安装Xshell到windows系统或者远程地址账户密码出错');
	                   		}
	                }
				 }
			});
			}
		
		}

	//修改的方法
	function UpdateForm(){
        //表单提交 会获取表单的所有信息
		$('#updevicedetail').form('submit',{
			url:'/deviceDetail/updateDevice',
			success: function(msg){
				msg=JSON.parse(msg);
						$.messager.alert('提示消息',msg.result);
						$('#tableShowData').datagrid('reload');//局部刷新列表
						$("#edits").window('close');
			}
		});
	}
</script>
</body>
</html>