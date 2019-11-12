package com.xs.dc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcControl;
import com.xs.dc.dao.DcControlMapper;
import com.xs.dc.service.DcControlService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年10月8日 下午2:07:18 类说明
 */
@Service
public class DcControlServiceImpl implements DcControlService {

	@Autowired
	private DcControlMapper controlmapper;

	@Override
	public List<DcControl> getControlList() {
		// TODO Auto-generated method stub
		return controlmapper.dcControlList();
	}

	@Override
	public int updateConrol(DcControl dcControl) {
		// TODO Auto-generated method stub
		int list = controlmapper.updateConrol(dcControl);
		return list;
	}
	
	
	

}
