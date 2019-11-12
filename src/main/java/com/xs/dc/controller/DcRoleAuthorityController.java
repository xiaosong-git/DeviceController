package com.xs.dc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcRoleAuthorityService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月25日 下午5:25:09 
* 类说明 
*/
@Controller
@RequestMapping("roleAuthority")
public class DcRoleAuthorityController extends BaseController{

	@Autowired
	private DcRoleAuthorityService roleauthorityservice;
	
	@RequestMapping("insertRoleAuthority")
	@ResponseBody
	public Map<String, String> insertRoleAuthority(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = false)String ids,@RequestParam(value = "roleId", required = false) long roleId){
		List<String> authorityIdList =null;
		if(ids.contains(",")) {
			authorityIdList = Arrays.asList(ids.split(","));
		}
		roleauthorityservice.deleteRoleAuthority(roleId);
		int result = roleauthorityservice.insertRoleAuthority(roleId, authorityIdList);
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
