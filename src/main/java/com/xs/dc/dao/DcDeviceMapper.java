package com.xs.dc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcDevice;
@Mapper
@Repository
public interface DcDeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DcDevice record);

    int insertSelective(DcDevice record);

    DcDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DcDevice record);

    int updateByPrimaryKey(DcDevice record);
}