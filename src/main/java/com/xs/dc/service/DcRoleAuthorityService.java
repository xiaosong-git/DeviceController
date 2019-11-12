package com.xs.dc.service;

import java.util.List;

import org.springframework.stereotype.Service;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月25日 下午5:20:06 
* 类说明 
*/
@Service
public interface DcRoleAuthorityService {
	int insertRoleAuthority(Long roleId,List<String> list);
	int deleteRoleAuthority(Long roleId);
}
