package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcUser;

import tk.mybatis.mapper.common.BaseMapper;
@Mapper
@Repository
public interface DcUserMapper extends BaseMapper<DcUser>{
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(DcUser record);
	 * 
	 * int insertSelective(DcUser record);
	 * 
	 * DcUser selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(DcUser record);
	 * 
	 * int updateByPrimaryKey(DcUser record);
	 */
    
    List<DcUser> finduser(@Param(value="username")String username, @Param(value="password")String password);
    
    List<DcUser> selectList(DcUser record);
    int insertUser(DcUser record);
    int updateUser(DcUser record);
    int deleteUser(Long id);
    int updatePwd(@Param(value="id")Long id, @Param(value="password")String password);
    
    List<DcUser> selectTel(@Param(value="orgCode")String orgCode);
}