package com.xs.dc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcDeviceDetail;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月9日 上午11:30:49 类说明
 */
@Service
public interface DcDeviceDetailService {
	
	List<DcDeviceDetail> deviceDetailList(String deviceName,String areaCode);
	
	List<DcDeviceDetail> findDeviceDetail(String warn,String deviceName,String areaCode);
	
	List<DcDeviceDetail> selectDeviceDetail(String orgCode,String deviceIp, String begTime, String endTime);
	
	int insertDeviceDetail(DcDeviceDetail deviceDetail);
	
	int delDeviceDetail(String dateTime);
	
	int updateDevice(DcDeviceDetail deviceDetail);
}
