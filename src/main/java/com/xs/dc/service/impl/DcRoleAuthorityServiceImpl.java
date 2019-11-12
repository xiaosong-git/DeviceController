package com.xs.dc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.dao.DcRoleAuthorityMapper;
import com.xs.dc.service.DcRoleAuthorityService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月25日 下午5:22:36 
* 类说明 
*/
@Service
public class DcRoleAuthorityServiceImpl implements DcRoleAuthorityService{
	
	@Autowired
	private DcRoleAuthorityMapper roleauthoritmapper;

	@Override
	public int insertRoleAuthority(Long roleId, List<String> list) {
		// TODO Auto-generated method stub
		return roleauthoritmapper.insertRoleAuthority(roleId, list);
	}

	@Override
	public int deleteRoleAuthority(Long roleId) {
		// TODO Auto-generated method stub
		return roleauthoritmapper.deleteRoleAuthority(roleId);
	}
	
	
	
	
}
