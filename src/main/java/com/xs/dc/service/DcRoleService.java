package com.xs.dc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcRole;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月24日 下午3:12:39 
* 类说明 
*/
@Service
public interface DcRoleService {
	public List<DcRole> RoleList(DcRole role);
	public int insertRole(DcRole role);
	public int updateRole(DcRole role);
	public int deleteRole(Long id);
	
}
