package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.TOrg;
@Mapper
@Repository
public interface TOrgMapper {
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(TOrg record);
	 * 
	 * int insertSelective(TOrg record);
	 * 
	 * TOrg selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(TOrg record);
	 * 
	 * int updateByPrimaryKey(TOrg record);
	 */
	List<TOrg> getList(@Param(value="orgCode")String orgCode);
}