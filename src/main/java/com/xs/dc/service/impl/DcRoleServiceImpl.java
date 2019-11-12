package com.xs.dc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcRole;
import com.xs.dc.dao.DcRoleMapper;
import com.xs.dc.service.DcRoleService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月24日 下午3:13:33 
* 类说明 
*/
@Service
public class DcRoleServiceImpl implements DcRoleService {
	@Autowired
	private DcRoleMapper rolemapper;

	@Override
	public List<DcRole> RoleList(DcRole role) {
		// TODO Auto-generated method stub
		return rolemapper.selectList(role);
	}

	@Override
	public int insertRole(DcRole role) {
		// TODO Auto-generated method stub
		return rolemapper.insertRole(role);
	}

	@Override
	public int updateRole(DcRole role) {
		// TODO Auto-generated method stub
		return rolemapper.updateRole(role);
	}

	@Override
	public int deleteRole(Long id) {
		// TODO Auto-generated method stub
		return rolemapper.deleteRole(id);
	}
	
	
}
