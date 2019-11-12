package com.xs.dc.controller;

import java.io.IOException;
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
import com.xs.dc.bean.DcDeviceDetail;
import com.xs.dc.bean.compose.PageModel;
import com.xs.dc.controller.base.BaseController;
import com.xs.dc.service.DcDeviceDetailService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月10日 上午10:19:01 类说明
 */

@Controller
@RequestMapping("/deviceDetail")
class DcDeviceDetailController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(DcDeviceDetailController.class);

	@Autowired
	private DcDeviceDetailService devicedetailservice;

	/**
	 * 跳转至设备详情页面
	 * 
	 * @param request
	 * @param response
	 * @param orgCode
	 * @param deviceName
	 * @return
	 */
	@RequestMapping("")
	public String dcDeviceDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "warn", required = false) String warn,
			@RequestParam(value = "deviceName", required = false) String deviceName) {
		request.setAttribute("warn", warn);
		request.setAttribute("deviceName", deviceName);
		return "deviceDetail";
	}
	
	@RequestMapping("xshell")
	@ResponseBody
	public Map<String, String> openXsehll(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "remoteIp", required = false) String remoteIp,
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "loginPwd", required = false) String loginPwd,
			@RequestParam(value = "remotePort", required = false) String remotePort) throws IOException, InterruptedException {
		String remoteUrl = "cmd /c start xshell -url ssh://"+loginName+":"+loginPwd+"@"+remoteIp+":"+remotePort;
		Process prs = Runtime.getRuntime().exec(remoteUrl);
		Map<String, String> map = new HashMap<String, String>();
		int exitValue = prs.waitFor();
		if(exitValue==0) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}

	/**
	 * 跳转至获取某一台设备的历史设备状态页面
	 * 
	 * @param request
	 * @param response
	 * @param orgCode
	 * @param deviceName
	 * @return
	 */
	@RequestMapping("/oneDevice")
	public String oneDevice(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "deviceIp", required = false) String deviceIp) {
		request.getSession().setAttribute("orgCode", orgCode);
		request.getSession().setAttribute("deviceIp", deviceIp);
		return "selectDevice";
	}

	/**
	 * 获取控制列表信息
	 */
	@RequestMapping("/deviceDetailList")
	@ResponseBody
	public PageModel<DcDeviceDetail> dcControlList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "warn", required = false) String warn,
			@RequestParam(value = "deviceName", required = false) String deviceName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows) {
		PageHelper.startPage(page, rows);
		List<DcDeviceDetail> list = devicedetailservice.findDeviceDetail(warn, deviceName,request.getSession().getAttribute("areaCode").toString());
		PageInfo<DcDeviceDetail> pageInfo = new PageInfo<DcDeviceDetail>(list);
		PageModel<DcDeviceDetail> pagemodel = new PageModel<DcDeviceDetail>();
		pagemodel.setRows(pageInfo.getList());
		pagemodel.setTotal(pageInfo.getTotal());
		return pagemodel;
	}

	/**
	 * 获取某台设备列表信息
	 */
	@RequestMapping("/oneDeviceList")
	@ResponseBody
	public PageModel<DcDeviceDetail> oneDeviceList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "begTime", required = false) String begTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows) {
		PageHelper.startPage(page, rows);
		List<DcDeviceDetail> list = devicedetailservice.selectDeviceDetail(
				request.getSession().getAttribute("orgCode").toString(),
				request.getSession().getAttribute("deviceIp").toString(), begTime, endTime);
		PageInfo<DcDeviceDetail> pageInfo = new PageInfo<DcDeviceDetail>(list);
		PageModel<DcDeviceDetail> pagemodel = new PageModel<DcDeviceDetail>();
		pagemodel.setRows(pageInfo.getList());
		pagemodel.setTotal(pageInfo.getTotal());
		return pagemodel;
	}
	/**
	 * 获取某台设备列表信息导入折线图中
	 */
	@RequestMapping("/oneDeviceListToPIC")
	@ResponseBody
	public List<DcDeviceDetail> oneDeviceListToPIC(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "begTime", required = false) String begTime,
			@RequestParam(value = "endTime", required = false) String endTime) {
		return devicedetailservice.selectDeviceDetail(
				request.getSession().getAttribute("orgCode").toString(),
				request.getSession().getAttribute("deviceIp").toString(), begTime, endTime);
	}
	
	/**
	 * 修改远程配置
	 */
	@RequestMapping("/updateDevice")
	@ResponseBody
	public Map<String, String> updateDevice(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute DcDeviceDetail deviceDetail) {
		int result = devicedetailservice.updateDevice(deviceDetail);
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
