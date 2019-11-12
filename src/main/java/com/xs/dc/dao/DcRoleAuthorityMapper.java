package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcRoleAuthority;
@Mapper
@Repository
public interface DcRoleAuthorityMapper {
    int insert(DcRoleAuthority record);

    int insertSelective(DcRoleAuthority record);
    
    int insertRoleAuthority(@Param(value="roleId")Long roleId,  @Param(value="authorityIdlList")List<String> authorityIdlList);
    int deleteRoleAuthority(@Param(value="roleId")Long roleId);
}