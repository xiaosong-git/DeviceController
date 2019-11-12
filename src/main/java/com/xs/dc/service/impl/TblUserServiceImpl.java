package com.xs.dc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.bean.UserCount;
import com.xs.dc.dao.TblCompanyUserMapper;
import com.xs.dc.dao.TblUserMapper;
import com.xs.dc.service.TblUserService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月23日 上午10:56:32 
* 类说明 
*/
@Service
public class TblUserServiceImpl implements TblUserService{
	private final Logger logger = LoggerFactory.getLogger(DcDeviceDetailServiceImpl.class);
	
	@Autowired
	private TblCompanyUserMapper companyusermapper;
	@Autowired
	private TblUserMapper usermappper;

	@Override
	public UserCount getTotalUser() {
		// TODO Auto-generated method stub
		UserCount usercount = new UserCount();
		usercount.setTotalAddUser(usermappper.addUser());
		usercount.setTotalCompanyUser(companyusermapper.companyUser());
		usercount.setTotalEntryCompany(companyusermapper.entryCompany());
		usercount.setTotalUser(usermappper.totalUser());
		usercount.setTotalVisitorUser(usermappper.visitorUser());
		return usercount;
	}
	
	

}
