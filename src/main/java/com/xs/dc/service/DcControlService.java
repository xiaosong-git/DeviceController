package com.xs.dc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcControl;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月8日 下午2:05:14 
* 类说明 
*/
@Service
public interface DcControlService {
	
	List<DcControl> getControlList();
	
	int updateConrol(DcControl dccontrol);
}
