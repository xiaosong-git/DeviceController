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
	<table id="dcrolelist"></table>
	<!-- 修改的div -->
	<div id="upRole" class="easyui-window" title="修改" data-options="iconCls:'icon-save',closed:true" 
				style="width:350px;height:150px;padding:5px;">
		<form id="upRoleForm" method="post">
		<input type="hidden" name="id"/>
	    	<table cellpadding="5" style="text-align:center;padding:5px">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="roleName" data-options="required:true" style="width: 200px;" ></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitUpdateRoleForm()">修改</a>
	    </div>
	</div>
	
	<!-- 新增的div -->
	<div id="addRole" class="easyui-window" title="新增" data-options="iconCls:'icon-save',closed:true" 
				style="width:350px;height:150px;padding:5px;">
		<form id="addRoleForm" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="roleName" data-options="required:true" style="width: 200px;" ></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitAddRoleForm()">添加</a>
	    </div>
	</div>
	<div id="menuWindow" class="easyui-window" title="权限" closed="true" style="width:300px;height:250px;padding:5px;">
        <ul id="authorityTree" class="easyui-tree"></ul>
        <br />
        <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="getChecked()">修改</a>
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
		
			$('#dcrolelist').datagrid({    
			    url:'/dcRole/getRolelist',  
			    title:'用户列表',
			    border:false,
			    rownumbers:true,
			    toolbar: [{
					iconCls:'icon-add',
					text:'新增',
					//点击新增弹出一个新增的div窗口
					handler:function(){
						$("#addRole").window('open');
					}
				},{
			    	iconCls:'icon-edit',
					text:'修改',
					handler:function(){
						var selectedRow=$("#dcrolelist").datagrid("getSelected");
						console.log(selectedRow);
							if(selectedRow==null){
								$.messager.alert('提示消息','请选择数据');
								return;
							}
						$("#upRole").window('open');
						//带上原数据
						$("#upRoleForm").form('load',selectedRow);
					}
				    },{
						iconCls:'icon-remove',
						text:'删除',
						handler:function(){
							
							var selectedRow=$("#dcrolelist").datagrid("getSelected");
							if(selectedRow==null){
								$.messager.alert('提示消息','请选择数据');
								return;
							}
							$.ajax({
								url:'dcRole/deleteRole',
								method:'POST',
								dataType:'json',
								data:{"id":selectedRow.id},
								success:function(msg){
									$.messager.alert('提示消息',msg.result);
									$('#dcrolelist').datagrid('reload');//局部刷新列表
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
				    field:'roleName',
				    title:'角色名称',
				    width:150
				},{
					field:'authority',
					title:'编辑',
					width:120,
					formatter : function (value, rec) {
						return "<a onclick=\"addAuthority('"+rec.id+"')\"> 权限分配 </a>";
					}
				}]]    
			}); 

		//修改的方法
		function submitUpdateRoleForm(){
            //表单提交 会获取表单的所有信息
			$('#upRoleForm').form('submit',{
				url:'/dcRole/updateRole',
				success: function(msg){
					msg=JSON.parse(msg);
							$.messager.alert('提示消息',msg.result);
							$('#dcrolelist').datagrid('reload');//局部刷新列表
							$("#upRole").window('close');
				}
			});
		}
		//新增的方法
		function submitAddRoleForm(){
            //表单提交 会获取表单的所有信息
			$('#addRoleForm').form('submit',{
				url:'/dcRole/insertRole',
				success: function(msg){
					msg=JSON.parse(msg);
							$.messager.alert('提示消息',msg.result);
							$('#dcrolelist').datagrid('reload');//局部刷新列表
							$("#addRole").window('close');
				}
			});
		}

		function addAuthority(roleId){
			 $('#authorityTree').tree({  
	                url : '/authority/loadAuthority?roleId='+roleId,
	                lines : true,  
	                checkbox : true
	            });
			 $("#menuWindow").window('open');
		}

		function getChecked(){
            var nodes = $('#authorityTree').tree('getChecked');//获取:checked的结点.
            var s = '';
            for(var i=0; i<nodes.length; i++){
                    if (s != '') s += ',';
                    s += nodes[i].id;//例如:菜单的menuID
            }
            //alert(s);
            var row = $('#dcrolelist').datagrid('getSelected');
            $.post("/roleAuthority/insertRoleAuthority",{
                ids:s,
                roleId:row.id,
            },function(data){
                if(data){
                	$.messager.alert('提示消息','权限分配成功');
                    $("#menuWindow").window("close");
                };
            });
    }
</script>
</body>
</html>