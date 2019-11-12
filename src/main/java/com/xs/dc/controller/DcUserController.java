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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.dc.bean.DcUser;
import com.xs.dc.bean.compose.PageModel;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcUserService;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月24日 上午11:47:16 
* 类说明 
*/
@Controller
@RequestMapping("dcUser")
public class DcUserController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(DcUserController.class);
	
	@Autowired
	private DcUserService userservice;
	
	@RequestMapping("dcUser")
    public List<DcUser> list() {
        return null;
    }
	
	@RequestMapping("")
    public String dcUser() {
        return "dcUser";
    }
	
	@RequestMapping("getUserlist")
	@ResponseBody
	public PageModel<DcUser> getUserlist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows,
			@ModelAttribute DcUser user){
		PageHelper.startPage(page, rows);
		List<DcUser> list = userservice.userList(user);
		PageInfo<DcUser> pageInfo = new PageInfo<DcUser>(list);
		PageModel<DcUser> pagemodel = new PageModel<DcUser>();
		pagemodel.setRows(pageInfo.getList());
		pagemodel.setTotal(pageInfo.getTotal());
		return pagemodel;
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public Map<String, String> updateUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcUser user){
		int result = userservice.updateUser(user);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "修改成功");
			return map;
		} else {
			map.put("result", "修改失败");
			return map;
		}
		
	}
	
	@RequestMapping("insertUser")
	@ResponseBody
	public Map<String, String> insertUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcUser user){
		String md5info = user.getUserName().toLowerCase() + user.getPassword().toLowerCase();
		String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
		user.setPassword(realPassword);
		int result = userservice.insertUser(user);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "修改成功");
			return map;
		} else {
			map.put("result", "修改失败");
			return map;
		}
		
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public Map<String, String> deleteUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) long id){
		int result = userservice.deleteUser(id);
		Map<String, String> map = new HashMap<String, String>();
		if (result >= 1) {
			map.put("result", "修改成功");
			return map;
		} else {
			map.put("result", "修改失败");
			return map;
		}
		
	}

}
