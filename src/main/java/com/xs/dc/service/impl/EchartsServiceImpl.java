package com.xs.dc.service.impl;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xs.dc.bean.DcDeviceDetail;
import com.xs.dc.bean.Echarts;
import com.xs.dc.dao.DcDeviceDetailMapper;
import com.xs.dc.service.EchartsService;

/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月9日 上午11:07:25 
* 类说明 
*/
@Service
public class EchartsServiceImpl implements EchartsService{
	@Autowired
	private DcDeviceDetailMapper deviceDetailMapper;

	@Override
	public Echarts getEcharts(String deviceName,String areaCode) {
		// TODO Auto-generated method stub
		List<DcDeviceDetail> list =null;
		if(areaCode.equals("1")) {
			list = deviceDetailMapper.deviceDetailList(deviceName, "");
		}else {
			list = deviceDetailMapper.deviceDetailLists(deviceName,"", areaCode);
		}
		return getEcahrts(list);
	}
	
	/**
	 * 处理list数据，统计各自数据生成echarts返回
	 * @param list
	 * @return
	 */
	public Echarts getEcahrts(List<DcDeviceDetail> list){
		Iterator<DcDeviceDetail> it = list.iterator();
		Echarts echarts = new Echarts();
		echarts.setDeviceEwarn(0);
		echarts.setDeviceNormal(0);
		echarts.setDeviceWarn(0);
		while(it.hasNext()) {
			switch(it.next().getWarn()){
		    case "warn" :
		       echarts.setDeviceWarn(echarts.getDeviceWarn()+1);
		       break;
		    case "normal" :
		       echarts.setDeviceNormal(echarts.getDeviceNormal()+1);
		       break; 
		    default : 
		    	echarts.setDeviceEwarn(echarts.getDeviceEwarn()+1);
		       //语句
			}
		}
		return echarts;
	}
	
}
