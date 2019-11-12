package com.xs.dc.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xs.dc.bean.DcControl;
import com.xs.dc.bean.DcDeviceDetail;
import com.xs.dc.bean.DcUser;
import com.xs.dc.bean.TOrg;
import com.xs.dc.dao.DcControlMapper;
import com.xs.dc.dao.DcDeviceDetailMapper;
import com.xs.dc.dao.DcUserMapper;
import com.xs.dc.dao.TOrgMapper;
import com.xs.dc.service.DcDeviceDetailService;
import com.xs.dc.util.YunPainSmsUtil;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月9日 上午11:31:57 类说明
 */
@Service
public class DcDeviceDetailServiceImpl implements DcDeviceDetailService {

	@Autowired
	private DcDeviceDetailMapper deviceDetailMapper;
	@Autowired
	private DcControlMapper controlmapper;
	@Autowired
	private TOrgMapper orgmapper;
	@Autowired
	private DcUserMapper usermapper;

	@Override
	public List<DcDeviceDetail> deviceDetailList(String deviceName, String areaCode) {
		// TODO Auto-generated method stub
		if (areaCode.equals("1")) {
			return deviceDetailMapper.deviceDetailList(deviceName, "");
		} else {
			return deviceDetailMapper.deviceDetailLists(deviceName, "", areaCode);
		}
	}

	@Override
	public List<DcDeviceDetail> findDeviceDetail(String warn, String deviceName, String areaCode) {
		// TODO Auto-generated method stub
		if (areaCode.equals("1")) {
			return deviceDetailMapper.deviceDetailList(deviceName, warn);
		} else {
			return deviceDetailMapper.deviceDetailLists(deviceName, warn, areaCode);
		}
	}

	@Override
	public List<DcDeviceDetail> selectDeviceDetail(String orgCode, String deviceIp, String begTime, String endTime) {
		// TODO Auto-generated method stub
		return deviceDetailMapper.selectDetailList(orgCode, deviceIp, begTime, endTime);
	}

	@Override
	public int insertDeviceDetail(DcDeviceDetail deviceDetail) {
		// TODO Auto-generated method stub
		int s = deviceDetailMapper.insertDeviceDetail(dealData(deviceDetail));
		return s;
	}

	@Override
	public int delDeviceDetail(String dateTime) {
		// TODO Auto-generated method stub
		return deviceDetailMapper.delDeviceDetail(dateTime);
	}

	@Override
	public int updateDevice(DcDeviceDetail deviceDetail) {
		// TODO Auto-generated method stub
		int list = deviceDetailMapper.updateDeviceDetail(deviceDetail);
		return list;
	}

	/**
	 * 判断设备是正常还是预警还是报警状态
	 * 
	 * @param deviceDetail
	 * @return
	 */
	private DcDeviceDetail dealData(DcDeviceDetail deviceDetail) {
		Map<String, String> map = new HashMap<String, String>();
		DcControl control = controlmapper.getDcControl(deviceDetail.getDeviceName());
		if (control == null) {
			return deviceDetail;
		}
		if (deviceDetail.getCpu() != null && !deviceDetail.getCpu().isEmpty()) {
			if (Double.parseDouble(deviceDetail.getCpu()) < Double.parseDouble(control.getCpu().split(",")[0])) {
				deviceDetail.setWarn("normal");
			} else if (Double.parseDouble(deviceDetail.getCpu()) > Double.parseDouble(control.getCpu().split(",")[1])) {
				deviceDetail.setWarn("warn");
				map.put("errorName", "cpu使用率过高提醒");
				map.put("actValue", deviceDetail.getCpu() + "%");
			} else {
				deviceDetail.setWarn("ewarn");
			}
		}
		if (deviceDetail.getMemory() != null && !deviceDetail.getMemory().isEmpty()) {
			if (Double.parseDouble(deviceDetail.getMemory()) < Double.parseDouble(control.getMemory().split(",")[0])) {
				deviceDetail.setWarn("normal");
			} else if (Double.parseDouble(deviceDetail.getMemory()) > Double
					.parseDouble(control.getMemory().split(",")[1])) {
				deviceDetail.setWarn("warn");
				map.put("errorName", "内存使用率使用过高提醒");
				map.put("actValue", deviceDetail.getMemory() + "%");
			} else {
				deviceDetail.setWarn("ewarn");
			}
		}
		if (deviceDetail.getPingLoss() != null && !deviceDetail.getPingLoss().isEmpty()) {
			if (Double.parseDouble(deviceDetail.getPingLoss()) < Double
					.parseDouble(control.getPingloss().split(",")[0])) {
				deviceDetail.setWarn("normal");
			} else if (Double.parseDouble(deviceDetail.getPingLoss()) > Double
					.parseDouble(control.getPingloss().split(",")[1])) {
				deviceDetail.setWarn("warn");
				map.put("errorName", "丢包率过高提醒");
				map.put("actValue", deviceDetail.getPingLoss() + "%");
			} else {
				deviceDetail.setWarn("ewarn");
			}
		}
		if (deviceDetail.getPingStatus().equals("normal")) {
			if (Double.parseDouble(deviceDetail.getPingAvg()) < Double
					.parseDouble(control.getPingavg().split(",")[0])) {
				deviceDetail.setWarn("normal");
			} else if (Double.parseDouble(deviceDetail.getPingAvg()) > Double
					.parseDouble(control.getPingavg().split(",")[1])) {
				deviceDetail.setWarn("warn");
				map.put("errorName", "ping平均速度过慢提醒");
				map.put("actValue", deviceDetail.getPingAvg());
			} else {
				deviceDetail.setWarn("ewarn");
			}
		} else {
			deviceDetail.setPingAvg(null);
			deviceDetail.setWarn("warn");
			map.put("errorName", "ping状态错误提醒");
			map.put("actValue", "error");
		}
		if (deviceDetail.getTelnetStatus() != null && !deviceDetail.getTelnetStatus().isEmpty()) {
			if (deviceDetail.getTelnetStatus() == "error") {
				deviceDetail.setWarn("warn");
				map.put("errorName", "telnet状态错误提醒");
				map.put("actValue", "error");
			}
		}
		if (deviceDetail.getWarn().equals("warn")) {
			// 短信发送
			if (deviceDetail.getOrgCode() != null && deviceDetail.getOrgCode() != "") {
				List<TOrg> list = orgmapper.getList(deviceDetail.getOrgCode());
				map.put("InfoType", "严重警告");
				map.put("areaName", list.get(0).getProvince() + list.get(0).getCity() + list.get(0).getArea());
				map.put("orgName", list.get(0).getOrgName());
				switch (deviceDetail.getDeviceName()) {
				case "SWI":
					map.put("deviceType", "上位机");
					break;
				case "FACE":
					map.put("deviceType", "人脸识别仪");
					break;
				case "RELAY":
					map.put("deviceType", "继电器");
					break;
				case "QRCODE":
					map.put("deviceType", "二维码识别器");
					break;

				default:
					break;
				}
				map.put("ipAdress", deviceDetail.getDeviceIp());
				map.put("datetime", deviceDetail.getFreshTime());
				List<DcUser> userlist = usermapper.selectTel(deviceDetail.getOrgCode());
				map.put("mobile", userlist.get(0).getTel());
				if (!userlist.get(0).getTel().equals("") && userlist.get(0).getTel() != null) {
					try {
						YunPainSmsUtil.sendMsg(map);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return deviceDetail;
	}
}
