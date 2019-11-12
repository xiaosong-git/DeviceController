package com.xs.dc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcUser;
import com.xs.dc.dao.DcUserMapper;
import com.xs.dc.service.DcUserService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月24日 上午11:29:53 
* 类说明 
*/
@Service
public class DcUserServiceImpl implements DcUserService{
	
	@Autowired
	private DcUserMapper usermapper;
	
	@Override
	public List<DcUser> finduser(String username, String password) {
		// TODO Auto-generated method stub
		return usermapper.finduser(username,password);
	}

	@Override
	public List<DcUser> userList(DcUser user) {
		// TODO Auto-generated method stub
		return usermapper.selectList(user);
	}

	@Override
	public int insertUser(DcUser user) {
		// TODO Auto-generated method stub
		if(user.getRoleId()==1) {
			user.setAreaCode("1");
		}
		return usermapper.insertUser(user);
	}

	@Override
	public int updateUser(DcUser user) {
		// TODO Auto-generated method stub
		if(user.getRoleId()==1) {
			user.setAreaCode("1");
		}
		return usermapper.updateUser(user);
	}

	@Override
	public int deleteUser(Long id) {
		// TODO Auto-generated method stub
		return usermapper.deleteUser(id);
	}

	@Override
	public int editPwd(Long id,String password) {
		// TODO Auto-generated method stub
		return usermapper.updatePwd(id, password);
	}
	
	
	
}
