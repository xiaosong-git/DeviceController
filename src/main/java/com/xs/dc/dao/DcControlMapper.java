package com.xs.dc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xs.dc.bean.DcControl;

@Mapper
@Repository
public interface DcControlMapper {
	
    List<DcControl> dcControlList();
    
    int updateConrol(DcControl dcControl);
    
    DcControl getDcControl(String deviceName);
}