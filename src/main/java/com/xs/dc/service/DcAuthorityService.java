package com.xs.dc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcAuthority;
import com.xs.dc.bean.DcMenu;
import com.xs.dc.bean.DcRole;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月26日 下午4:04:09 
* 类说明 
*/
@Service
public interface DcAuthorityService {
	
	public List<DcMenu> getMenus(String userId);//查找该用户所有权限菜单
	
	public List<DcAuthority> getAuthorityList();//获取权限列表
	
	public List<DcAuthority> AuthorityList(DcAuthority authority);
	public int insertAuthority(DcAuthority authority);
	public int updateAuthority(DcAuthority authority);
	public int deleteAuthority(Long id);
	
	public List<DcAuthority> findByRoleId(Long roleId);
	 public List<DcAuthority> findAll();
}
