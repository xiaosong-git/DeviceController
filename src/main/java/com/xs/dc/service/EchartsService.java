package com.xs.dc.service;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月9日 上午11:05:16 
* 类说明 
*/

import org.springframework.stereotype.Service;

import com.xs.dc.bean.Echarts;

@Service
public interface EchartsService {
	Echarts getEcharts(String deviceName,String areaCode);
}
