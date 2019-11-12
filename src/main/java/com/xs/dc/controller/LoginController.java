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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.dc.bean.DcUser;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcUserService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年9月24日 下午3:45:12 类说明
 */

@Controller
public class LoginController extends BaseController {
	 private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private DcUserService userservice;

	/**
	 * 登录校验
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		
		// 前端传回的密码实际为用户输入的：用户名（小写）+ 密码（小写）组合的字符串生成的md5值
		// 此处先通过后台保存的正确的用户名和密码计算出正确的md5值，然后和前端传回来的作比较
		if (username == null || password == null) {
			return "login";
		}
		String md5info = username.toLowerCase() + password.toLowerCase();
		String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
		List<DcUser> list = userservice.finduser(username, realPassword);
		if (list.size() == 0) {
			return "login";
		}
		request.getSession().setAttribute("areaCode", list.get(0).getAreaCode());
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("userId", list.get(0).getId());
		return "redirect:/menu";

		// 校验通过时，在session里放入一个标识
		// 后续通过session里是否存在该标识来判断用户是否登录
	}

	/**
	 * 注销登录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}
	/**
	 * 修改密码
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
	public Map<String, String> editPwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "oldPwd", required = false) String oldPwd,
			@RequestParam(value = "newPwd", required = false) String newPwd){
		DcUser user = new DcUser();
		user.setId((long)(request.getSession().getAttribute("userId")));
		List<DcUser> list = userservice.userList(user);
		String md5info = list.get(0).getUserName().toLowerCase() + newPwd.toLowerCase();
		Map<String, String> map = new HashMap<String, String>();
		if(list.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(md5info.getBytes()))) {
			int result = userservice.editPwd((long)(request.getSession().getAttribute("userId")), DigestUtils.md5DigestAsHex(md5info.getBytes()));
			if(result==1) {
				map.put("result", "密码修改成功！");
				return map;
			}else {
				map.put("result", "密码修改失败！");
				return map;
			}
		}else {
			map.put("result", "旧密码不正确！");
			return map;
		}
	}

}
