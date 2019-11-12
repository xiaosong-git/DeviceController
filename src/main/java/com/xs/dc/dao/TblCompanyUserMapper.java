package com.xs.dc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TblCompanyUserMapper {
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(TblCompanyUser record);
	 * 
	 * int insertSelective(TblCompanyUser record);
	 * 
	 * TblCompanyUser selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(TblCompanyUser record);
	 * 
	 * int updateByPrimaryKey(TblCompanyUser record);
	 */
	String companyUser();//查询公司员工总数
	String entryCompany();//查询入驻公司总数
}