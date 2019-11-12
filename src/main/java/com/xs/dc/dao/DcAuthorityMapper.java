package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcAuthority;
@Mapper
@Repository
public interface DcAuthorityMapper {
	/*
	 * int deleteByPrimaryKey(Long id);
	 * 
	 * int insert(DcAuthority record);
	 * 
	 * int insertSelective(DcAuthority record);
	 * 
	 * DcAuthority selectByPrimaryKey(Long id);
	 * 
	 * int updateByPrimaryKeySelective(DcAuthority record);
	 * 
	 * int updateByPrimaryKey(DcAuthority record);
	 */
	List<DcAuthority> getMenuList(@Param(value="userId")Long userId);
	
	List<DcAuthority> getAuthorityList();
	
	List<DcAuthority> selectList(DcAuthority record);
    int insertAuthority(DcAuthority record);
    int updateAuthority(DcAuthority record);
    int deleteAuthority(Long id);
    
    List<DcAuthority> findByRoleId(@Param(value="roleId") Long roleId);
    List<DcAuthority> findAll();
	
}