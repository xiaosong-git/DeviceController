<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>用户管理列表</title>
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
	<div data-options="region:'north',title:''" style="height: 25px; padding: 5px;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="append()">新增目录</a> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="removeIt()">删除</a>
	</div>
	<ul id="authorityTreeList" class="easyui-tree"></ul>
	
	<div id="addAuthority" class="easyui-window" title="新增" data-options="iconCls:'icon-save',closed:true" 
				style="width:500px;height:300px;padding:5px;">
		<form id="addAuthorityForm" method="post">
		<input type="hidden" name="authorityGrade" value="0" id="authorityGrade"/>
		<input type="hidden" name="superId" value="0" id="superId"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>权限名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="authorityName" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>跳转路径:</td>
	    			<td><input class="easyui-textbox" type="text" name="resourceUrl" data-options="required:true" ></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitAddAuthorityForm()">添加</a>
	    </div>
	</div>
	<script>
		//初始化角色下拉框
		$(function() {
			$('#authorityTreeList').tree({
				url : '/authority/loadAuthority',
				lines : true
			});
		});

		function append() {
	        var node = $('#authorityTreeList').tree('getSelected');
	        console.log(node);
	        if(node=='null'||node==null){
	        	 $("#addAuthority").window('open');
		    }else{
			  $('#authorityGrade').val("1");
			  $('#superId').val(node.id); 
			  $("#addAuthority").window('open');
			}
	       
	    }
	//移除节点
	    function removeIt() {
	        var node = $('#authorityTreeList').tree('getSelected');
	        if (node==null||node=='null'){
	        	 $.messager.alert("提示信息", "请选择一个节点");
		    }
	        if (node) {
	        	 $.ajax({
		             type: "get",
		             url:'/authority/deleteAuthority',
		             data: {"id": node.id},
		             dataType: 'json',
		             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		             success: function (msg) {
						 $.messager.alert('提示消息',msg.result);
						 $('#authorityTreeList').tree('reload');//局部刷新列表
			          }
	        	});
	        }
	    }
	    function submitAddAuthorityForm(){
            //表单提交 会获取表单的所有信息
			$('#addAuthorityForm').form('submit',{
				url:'/authority/insertAuthority',
				success: function(msg){
					msg=JSON.parse(msg);
					$.messager.alert('提示消息',msg.result);
					$('#authorityTreeList').tree('reload');//局部刷新列表
					$("#addAuthority").window('close');
				}
			});
		}
	</script>
</body>
</html>