package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcRole;
@Mapper
@Repository
public interface DcRoleMapper {
	List<DcRole> selectList(DcRole record);
    int insertRole(DcRole record);
    int updateRole(DcRole record);
    int deleteRole(Long id);
}