package com.xs.dc.controller;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月26日 下午2:33:56 
* 类说明 
*/
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xs.dc.bean.DcUser;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcAuthorityService;

@Controller
public class MenuController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private DcAuthorityService authorityservice;
	/**
	 * 菜单获取
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/menu")
	public String menu(HttpServletRequest request) {
		request.setAttribute("username",request.getSession().getAttribute("username"));
		request.setAttribute("MenuList", authorityservice.getMenus(request.getSession().getAttribute("userId").toString()));
		return "layout";
	}
}
