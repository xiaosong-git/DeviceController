<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>设备监控系统</title>
<link rel="stylesheet" type="text/css" href="plugins/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/wu.css" />
<link rel="stylesheet" type="text/css" href="plugins/css/icon.css" />
<script type="text/javascript" src="plugins/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/easyui/1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1>设备监控系统</h1>
        </div>
        <div class="wu-header-right">
        	<p>登录用户：<strong class="easyui-tooltip">${username}</strong></p>
            <p><a href="javascript:void(0);" onclick="openwin()">修改密码</a>|<a href="/loginout">安全退出</a></p>
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'功能菜单'"> 
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
    		<c:forEach items="${MenuList}" var="authority" varStatus="vs">  
    			<div title="${authority.authorityName}" data-options="iconCls:'icon-application-cascade'" style="padding:5px;"> 
    			<c:forEach items="${authority.listAuthority}" var="permission" varStatus="vs">
    				<ul class="easyui-tree wu-side-tree">
                		<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="${permission.resourceUrl}" iframe="0">${permission.authorityName}</a></li>
                	</ul>
    			</c:forEach> 	
    			</div>
    		</c:forEach>
        </div>
    </div>	
    <!-- end of sidebar -->    
    <!-- begin of main -->
    <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">  
            <div title="首页" data-options="href:'/echarts',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
        </div>
    </div>
    
    <div id="editPwd" class="easyui-window" title="修改密码" data-options="iconCls:'icon-save',closed:true" 
				style="width:300px;height:200px;padding:5px;">
		<form id="editPwdForm" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>旧密码：</td>
	    			<td><input class="easyui-textbox" type="text" name="oldPwd" id="oldPwd" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>新密码：</td>
	    			<td><input class="easyui-textbox" type="text" name="newPwd" id="newPwd" data-options="required:true" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>确认密码：</td>
	    			<td><input class="easyui-textbox" type="text" name="checkPwd" id="checkPwd" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="UpdatePwdForm()">保存</a>
	    </div>
	</div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy;
    </div>
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		function openwin(){
			$("#editPwd").window('open');
		}

		function UpdatePwdForm(){
			
			if($('#oldPwd').val()==$('#newPwd').val()){
				$.messager.alert('提示消息','新密码不能与原来密码一样');
				return;
			}else if($('#checkPwd').val()!=$('#newPwd').val()){
				$.messager.alert('提示消息','新密码与确认密码不一致');
				return;
			}else{
				$('#editPwdForm').form('submit',{
					url:'/editPwd',
					success: function(msg){
						msg=JSON.parse(msg);
								$.messager.alert('提示消息',msg.result);
								$("#editPwd").window('close');
					}
				});
			}
		}
		
		/**
		* Name 载入树形菜单 
		*/
		$('#wu-side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#wu-datagrid').datagrid('reload');
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
	</script>
</body>
</html>
