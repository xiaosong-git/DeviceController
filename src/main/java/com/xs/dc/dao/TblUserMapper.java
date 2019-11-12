package com.xs.dc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TblUserMapper {
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(TblUser record);
	 * 
	 * int insertSelective(TblUser record);
	 * 
	 * TblUser selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(TblUser record);
	 * 
	 * int updateByPrimaryKey(TblUser record);
	 */
	String totalUser();//查看实名用户总数
	String addUser();//查看每日新增用户总数
	String visitorUser();//查看访客记录总数
}