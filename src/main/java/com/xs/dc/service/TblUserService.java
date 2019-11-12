package com.xs.dc.service;

import org.springframework.stereotype.Service;
import com.xs.dc.bean.UserCount;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月23日 上午10:41:10 
* 类说明 
*/
@Service
public interface TblUserService {
	
	UserCount getTotalUser();
}
