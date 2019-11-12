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
import com.xs.dc.bean.DcControl;
import com.xs.dc.bean.compose.PageModel;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcControlService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月8日 下午2:26:15 类说明
 */

@Controller
@RequestMapping("/dcControl")
public class DcControlController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(DcControlController.class);
	
	@Autowired
	private DcControlService controlservice;

	@RequestMapping("")
	public String dcControl() {
		return "dcControl";
	}

	/**
	 * 获取控制列表信息
	 */
	@RequestMapping("/controlList")
	@ResponseBody
	public PageModel<DcControl> dcControlList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows) {
		PageHelper.startPage(page, rows);
		List<DcControl> list = controlservice.getControlList();
		PageInfo<DcControl> pageInfo = new PageInfo<DcControl>(list);
		PageModel<DcControl> pagemodel = new PageModel<DcControl>();
		pagemodel.setRows(pageInfo.getList());
		pagemodel.setTotal(pageInfo.getTotal());
		return pagemodel;
	}

	/**
	 * 修改控制范围
	 */
	@RequestMapping("/updateControl")
	@ResponseBody
	public Map<String, String> updateControl(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcControl dcControl) {
		int result = controlservice.updateConrol(dcControl);
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
