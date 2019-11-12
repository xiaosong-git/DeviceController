package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcDeviceDetail;
@Mapper
@Repository
public interface DcDeviceDetailMapper {
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(DcDeviceDetail record);
	 * 
	 * int insertSelective(DcDeviceDetail record);
	 * 
	 * DcDeviceDetail selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(DcDeviceDetail record);
	 * 
	 * int updateByPrimaryKey(DcDeviceDetail record);
	 */
	List<DcDeviceDetail> deviceDetailList(@Param(value="deviceName")String deviceName, @Param(value="warn")String warn);
	
	List<DcDeviceDetail> deviceDetailLists(@Param(value="deviceName")String deviceName, @Param(value="warn")String warn,@Param(value="areaCode")String areaCode);
	
	List<DcDeviceDetail> selectDetailList(@Param(value="orgCode")String orgCode,  @Param(value="deviceIp")String deviceIp,
			@Param(value="begTime")String begTime,  @Param(value="endTime")String endTime);
	
	int insertDeviceDetail(DcDeviceDetail record);
	
	int delDeviceDetail(@Param(value="dateTime")String dateTime);
	
	int updateDeviceDetail(DcDeviceDetail dcDeviceDetail);
}