package com.xs.dc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.dc.bean.DcRole;
import com.xs.dc.bean.compose.PageModel;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcRoleService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月25日 上午11:29:44 
* 类说明 
*/
@Controller
@RequestMapping("dcRole")
public class DcRoleController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(DcRoleController.class);
	
	@Autowired
	private DcRoleService roleservice;
	
	@RequestMapping("")
    public String dcRole() {
        return "dcRole";
    }
	
	/**
	 * 用户列表获取角色列表
	 * @return
	 */
	@RequestMapping("userGetRole")
	@ResponseBody
	public List<DcRole> userGetRole(HttpServletRequest request, HttpServletResponse response){
		List<DcRole> list = roleservice.RoleList(new DcRole());
		return list;
	}
	
	@RequestMapping("getRolelist")
	@ResponseBody
	public PageModel<DcRole> getRolelist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows,
			@ModelAttribute DcRole role){
		PageHelper.startPage(page, rows);
		List<DcRole> list = roleservice.RoleList(role);
		PageInfo<DcRole> pageInfo = new PageInfo<DcRole>(list);
		PageModel<DcRole> pagemodel = new PageModel<DcRole>();
		pagemodel.setRows(pageInfo.getList());
		pagemodel.setTotal(pageInfo.getTotal());
		return pagemodel;
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public Map<String, String> updateRole(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcRole role){
		int result = roleservice.updateRole(role);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "修改成功");
			return map;
		} else {
			map.put("result", "修改失败");
			return map;
		}
		
	}
	
	@RequestMapping("insertRole")
	@ResponseBody
	public Map<String, String> insertRole(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcRole role){
		int result = roleservice.insertRole(role);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "修改成功");
			return map;
		} else {
			map.put("result", "修改失败");
			return map;
		}
		
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public Map<String, String> deleteRole(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) long id){
		int result = roleservice.deleteRole(id);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "删除成功");
			return map;
		} else {
			map.put("result", "删除失败");
			return map;
		}
		
	}
}
