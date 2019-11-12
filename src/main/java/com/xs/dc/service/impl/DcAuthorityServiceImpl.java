package com.xs.dc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcAuthority;
import com.xs.dc.bean.DcMenu;
import com.xs.dc.bean.compose.MenuUtil;
import com.xs.dc.dao.DcAuthorityMapper;
import com.xs.dc.service.DcAuthorityService;

/**
 * @author 作者 : xiaojf
 * @Date 创建时间：2019年9月26日 下午4:05:26 类说明
 */
@Service
public class DcAuthorityServiceImpl implements DcAuthorityService {

	@Autowired
	private DcAuthorityMapper authoritymapper;

	@Override
	public List<DcMenu> getMenus(String userId) {
		// TODO Auto-generated method stub
		List<DcAuthority> authoritylist = authoritymapper.getMenuList(Long.parseLong(userId));
		List<DcMenu> menuList = iterateAuthority(authoritylist);
		return menuList;
	}

	/**
	 * @param DcMenuList
	 * @param pid
	 * @return
	 */
	public List<DcMenu> iterateAuthority(List<DcAuthority> DcAuthorityList) {
		List<DcMenu> menuList = new ArrayList<DcMenu>();
		for (DcAuthority parentAuthority : DcAuthorityList) {
			DcMenu menu = new DcMenu();
			List<DcAuthority> childList = new ArrayList<DcAuthority>();
			String authorityid = parentAuthority.getId().toString();// 获取菜单的id
			String parentid = parentAuthority.getSuperId().toString();// 获取菜单的父id
			if (parentid.equals("0")) {
				for (DcAuthority authority : DcAuthorityList) {
					if (authorityid.equals(authority.getSuperId().toString())) {
						childList.add(authority);
					}

				}
				menu.setId(parentAuthority.getId());
				menu.setAuthorityGrade(parentAuthority.getAuthorityGrade());
				menu.setAuthorityName(parentAuthority.getAuthorityName());
				menu.setSuperId(parentAuthority.getSuperId());
				menu.setListAuthority(childList);
				menuList.add(menu);
			}
		}
		return menuList;
	}

	@Override
	public List<DcAuthority> getAuthorityList() {
		// TODO Auto-generated method stub
		return authoritymapper.getAuthorityList();
	}

	@Override
	public List<DcAuthority> AuthorityList(DcAuthority authority) {
		// TODO Auto-generated method stub
		return authoritymapper.selectList(authority);
	}

	@Override
	public int insertAuthority(DcAuthority authority) {
		// TODO Auto-generated method stub
		return authoritymapper.insertAuthority(authority);
	}

	@Override
	public int updateAuthority(DcAuthority authority) {
		// TODO Auto-generated method stub
		return authoritymapper.updateAuthority(authority);
	}

	@Override
	public int deleteAuthority(Long id) {
		// TODO Auto-generated method stub
		return authoritymapper.deleteAuthority(id);
	}

	@Override
	public List<DcAuthority> findByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		List<DcAuthority> list = authoritymapper.findByRoleId(roleId);
		return MenuUtil.getMenu(list);
	}

	@Override
	public List<DcAuthority> findAll() {
		// TODO Auto-generated method stub
		return MenuUtil.getMenu(authoritymapper.findAll());
	}
	
	

}
