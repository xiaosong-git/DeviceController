<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>用户管理列表</title>
</head>
<link rel="stylesheet" type="text/css" href="plugins/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/wu.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/icon.css" />
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body>
<script type="text/javascript" src="script/area.js"></script>
	<table id="dcuserlist"></table>
	<!-- 修改的div -->
	<div id="upUser" class="easyui-window" title="修改" data-options="iconCls:'icon-save',closed:true" 
				style="width:390px;height:370px;padding:5px;">
		<form id="upUserForm" method="post">
		<input type="hidden" name="id"/>
		<input type="hidden" name="areaCode" id="editAreaCode"/>
	    	<table cellpadding="5" style="text-align:center;padding:5px">
	    		<tr>
	    			<td>真实姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="trueName" data-options="required:true" style="width: 250px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登录名:</td>
	    			<td><input class="easyui-textbox" type="text" name="userName" data-options="required:true" style="width: 250px;"></input></td>
	    		</tr>
	    		<!-- <tr>
	    			<td>登录密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true" style="width: 250px;"></input></td>
	    		</tr> -->
	    		 <tr>
	    		 	<td>省级:</td>
					<td><select name="province" id="editprovince" style="width: 200px;">
							<option value="">请选择</option>
						</select>
		            </td>
				</tr>
				<tr>
					<td>市级:</td>
					<td><select name="city" id="editcity" style="width: 200px;">
							<option value="">请选择</option>
						</select>
		            </td>
				</tr>
				<tr>
	    			<td>电话:</td>
	    			<td><input class="easyui-textbox" type="text" name="tel" data-options="required:true" style="width: 250px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td><select name="roleId" id="eidtRoleId" style="width: 250px;">
						</select></td>
	    		</tr>
	    		<tr>
	    			<td>大楼标识:</td>
	    			<td><input class="easyui-textbox" type="text" name="orgCode" data-options="required:true" style="width: 250px;"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitUpdateUserForm()">修改</a>
	    </div>
	</div>
	
	<!-- 新增的div -->
	<div id="adduser" class="easyui-window" title="新增" data-options="iconCls:'icon-save',closed:true,modal:true" 
				style="width:350px;height:370px;padding:5px;">
		<form id="addUserForm" method="post">
		<input type="hidden" name="areaCode" id="addAreaCode"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>真实姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="trueName" data-options="required:true"  style="width: 200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登录名:</td>
	    			<td><input class="easyui-textbox" type="text" name="userName" data-options="required:true" style="width: 200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登录密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true" style="width: 200px;"></input></td>
	    		</tr>
	    		 <tr>
	    		 	<td>省级:</td>
					<td><select name="province" id="addprovince" style="width: 200px;">
							<option value="">请选择</option>
						</select>
		            </td>
				</tr>
				<tr>
					<td>市级:</td>
					<td><select name="city" id="addcity" style="width: 200px;">
							<option value="">请选择</option>
						</select>
		            </td>
				</tr>
				<tr>
	    			<td>电话:</td>
	    			<td><input class="easyui-textbox" type="text" name="tel" data-options="required:true" style="width: 200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td><select name="roleId" id="addRoleId" style="width: 200px;">
						</select></td>
	    		</tr>
	    		<tr>
	    			<td>大楼标识:</td>
	    			<td><input class="easyui-textbox" type="text" name="orgCode" data-options="required:true" style="width: 200px;"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitAddUserForm()">添加</a>
	    </div>
	</div>
	
<script>

			//初始化角色下拉框
			$(function() {
				$.ajax({
					url:'dcRole/userGetRole',
					method:'POST',
					dataType:'json',
					data:{},
					success:function(data){
						$('#eidtRoleId').empty();
						$('#eidtRoleId').append('<option value="">空</option>');
						$('#addRoleId').empty();
						$('#addRoleId').append('<option value="">空</option>');
						for(var i=0;i<data.length;i++){
							$('#eidtRoleId').append(new Option(data[i].roleName,data[i].id));
							$('#addRoleId').append(new Option(data[i].roleName,data[i].id));
						}
					}
				});
			});
		
			$('#dcuserlist').datagrid({    
			    url:'/dcUser/getUserlist',  
			    title:'用户列表',
			    border:false,
			    rownumbers:true,
			    toolbar: [{
					iconCls:'icon-add',
					text:'新增',
					//点击新增弹出一个新增的div窗口
					handler:function(){
						$('#addprovince').empty();
						$('#addprovince').append(new Option('请选择',''));
						for(var i=0;i<datas.length;i++){
							if(i<10){
								$('#addprovince').append(new Option(datas[i].name,'0'+i));
							}else{
								$('#addprovince').append(new Option(datas[i].name,i));
							}
						}
						$("#adduser").window('open');
					}
				},{
			    	iconCls:'icon-edit',
					text:'修改',
					handler:function(){
						var selectedRow=$("#dcuserlist").datagrid("getSelected");
						console.log(selectedRow);
							if(selectedRow==null){
								$.messager.alert('提示消息','请选择数据');
								return;
							}
						$('#editprovince').empty();
						$('#editcity').empty();
						$('#editprovince').append(new Option('请选择',''));
						$('#editcity').append(new Option('请选择',''));
						for(var i=0;i<datas.length;i++){
							if(i<10){
								$('#editprovince').append(new Option(datas[i].name,'0'+i));
							}else{
								$('#editprovince').append(new Option(datas[i].name,i));
							}
						}
						//if(selectedRow.areaCode.substr(2,4)!=''&&selectedRow.areaCode.substr(2,4)!=null){
							for(var i=0;i<datas[parseInt(selectedRow.areaCode.substr(0,2))].cityList.length;i++){
								if(i<10){
									$('#editcity').append(new Option(datas[parseInt(parseInt(selectedRow.areaCode.substr(0,2)))].cityList[i].name,'0'+i));
								}else{
									$('#editcity').append(new Option(datas[parseInt(parseInt(selectedRow.areaCode.substr(0,2)))].cityList[i].name,i));
								}
							}
						//}
						$("#editprovince").find("option[value='"+selectedRow.areaCode.substr(0,2)+"']").attr("selected",true); 
						$("#editcity").find("option[value='"+selectedRow.areaCode.substr(2,4)+"']").attr("selected",true); 
						$("#upUser").window('open');
						//带上原数据
						$("#upUserForm").form('load',selectedRow);
						$("#roleName").find("option[value='']").attr("selected",true); 
						$("#roleName").find("option[value='"+selectedRow.roleId+"']").attr("selected",true); 
					}
				    },{
						iconCls:'icon-remove',
						text:'删除',
						handler:function(){
							
							var selectedRow=$("#dcuserlist").datagrid("getSelected");
							if(selectedRow==null){
								$.messager.alert('提示消息','请选择数据');
								return;
							}
							$.ajax({
								url:'dcUser/deleteUser',
								method:'POST',
								dataType:'json',
								data:{"id":selectedRow.id},
								success:function(msg){
									$.messager.alert('提示消息',msg.result);
									$('#dcuserlist').datagrid('reload');//局部刷新列表
								}
							});
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
				    field:'trueName',
				    title:'真实姓名',
				    width:150
				},{
					field:'userName',
					title:'登录名',
					width:150
				},{
					field:'password',
					title:'登录密码',
					width:270
				},{
					field:'areaCode',
					title:'所属区域',
					width:250,
					formatter : function (value, rec) {
						if(rec.areaCode=='1'){
							return '无';
							}
						var province=rec.areaCode.substr(0,2);
						var city=rec.areaCode.substr(2,4);
						if(city==''||city==null){
							return datas[parseInt(province)].name;
						}
						return datas[parseInt(province)].name+datas[parseInt(province)].cityList[parseInt(city)].name;
					}
				},{
					field:'tel',
					title:'电话',
					width:250
				},{
					field:'roleName',
					title:'角色',
					width:120,
					formatter : function (value, rec) {
						console.log(rec);
						if(rec.role!=null&&rec.role!='null'&&rec.role!=''){
							return rec.role['roleName'];
						}else{
							return '角色分配';
						}
						}
				},{
					field:'orgCode',
					title:'大楼标识',
					width:120
				}]]    
			}); 

		//修改的方法
		function submitUpdateUserForm(){
			$('#editAreaCode').val($('#editprovince').val()+$('#editcity').val());
            //表单提交 会获取表单的所有信息
		 	$('#upUserForm').form('submit',{
				url:'/dcUser/updateUser',
				success: function(msg){
					msg=JSON.parse(msg);
							$.messager.alert('提示消息',msg.result);
							$('#dcuserlist').datagrid('reload');//局部刷新列表
							$("#upUser").window('close');
				}
			});
		}
		//新增的方法
		function submitAddUserForm(){
			$('#addAreaCode').val($('#addprovince').val()+$('#addcity').val());
            //表单提交 会获取表单的所有信息
			$('#addUserForm').form('submit',{
				url:'/dcUser/insertUser',
				success: function(msg){
					msg=JSON.parse(msg);
							$.messager.alert('提示消息',msg.result);
							$('#dcuserlist').datagrid('reload');//局部刷新列表
							$("#adduser").window('close');
				}
			});
		}
		//添加用户弹窗时候选择省触发的事件
		$("#addprovince").change(function(){
			$('#addcity').empty();
			$('#addcity').append(new Option('请选择',''));
			for(var i=0;i<datas[parseInt($('#addprovince option:selected').val())].cityList.length;i++){
				if(i<10){
					$('#addcity').append(new Option(datas[parseInt($('#addprovince option:selected').val())].cityList[i].name,'0'+i));
				}else{
					$('#addcity').append(new Option(datas[parseInt($('#addprovince option:selected').val())].cityList[i].name,i));
				}
			}
		});
		//修改用户弹窗时候选择省触发的事件
		$("#editprovince").change(function(){
			$('#editcity').empty();
			$('#editcity').append(new Option('请选择',''));
			for(var i=0;i<datas[parseInt($('#editprovince option:selected').val())].cityList.length;i++){
				if(i<10){
					$('#editcity').append(new Option(datas[parseInt($('#editprovince option:selected').val())].cityList[i].name,'0'+i));
				}else{
					$('#editcity').append(new Option(datas[parseInt($('#editprovince option:selected').val())].cityList[i].name,i));
				}
			}
		});
</script>
</body>
</html>