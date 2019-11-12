package com.xs.dc.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcArea;
@Mapper
@Repository
public interface DcAreaMapper{
    int deleteByPrimaryKey(Long id);

    int insert(DcArea record);

    int insertSelective(DcArea record);

    DcArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DcArea record);

    int updateByPrimaryKey(DcArea record);
}