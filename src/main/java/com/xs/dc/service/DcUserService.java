package com.xs.dc.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.xs.dc.bean.DcUser;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年9月24日 上午11:29:22 
* 类说明 
*/
@Service
public interface DcUserService {
	
	public List<DcUser> finduser(String username, String password);//登录获取用户信息 
	public List<DcUser> userList(DcUser user);
	public int insertUser(DcUser user);
	public int updateUser(DcUser user);
	public int deleteUser(Long id);
	public int editPwd(Long id,String password);
	
}
