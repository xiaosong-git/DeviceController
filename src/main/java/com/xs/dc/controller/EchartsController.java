package com.xs.dc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.dc.bean.Echarts;
import com.xs.dc.bean.UserCount;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.EchartsService;
import com.xs.dc.service.TblUserService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月8日 下午3:34:34 类说明
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(EchartsController.class);
	
	@Autowired
	private EchartsService echartsservice;
	@Autowired
	private TblUserService userservice;
	
	@RequestMapping("")
	public String echarts(HttpServletRequest request, HttpServletResponse response) {
		return "echarts";
	}
	/**
	 * 获取某一个设备类型的分布数量统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getEcharts")
	@ResponseBody
	public Echarts getEcharts(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "deviceName", required = false) String deviceName) {
		return echartsservice.getEcharts(deviceName,request.getSession().getAttribute("areaCode").toString());
	}
	
	/**
	 * tbl用户统计
	 * @param request
	 * @param response
	 * @param deviceName
	 * @return
	 */
	@RequestMapping("/countUser")
	@ResponseBody
	public UserCount countUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "deviceName", required = false) String deviceName) {
		return userservice.getTotalUser();
	}
}
