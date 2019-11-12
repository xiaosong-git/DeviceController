package com.xs.dc.websocket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.alibaba.fastjson.JSONArray;
import com.xs.dc.bean.DcDeviceDetail;
import com.xs.dc.service.DcDeviceDetailService;
@Component
@ServerEndpoint(value="/MyHandler")
public class MyHandler extends TextWebSocketHandler{
	
	private static DcDeviceDetailService deviceDetailSevice;
	
	@Autowired
    public void setDeviceDetailService(DcDeviceDetailService deviceDetailService) {
		MyHandler.deviceDetailSevice = deviceDetailService;
    }
	
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		/*
		 * String payload = message.getPayload(); Map<String, String> map = (Map<String,
		 * String>) JSONObject.parseObject(payload, HashMap.class);
		 */
        System.out.println("=====接受到的数据"+message.getPayload());
        String dealresult = dealResult(message.getPayload());//插入数据到库中
        session.sendMessage(new TextMessage("服务器返回收到的信息：" + dealresult));
    }
	/**
	 * 处理websocked连接接收到的数据
	 * @param result
	 * @return
	 * @throws ParseException 
	 */
	private String dealResult(String result) throws ParseException {
		DcDeviceDetail deviceDetail = new DcDeviceDetail();
		JSONArray jsonArray = JSONArray.parseArray(result);
		int total = 0;
		deldate(jsonArray.getJSONObject(0).getString("freshTime"));
		for(int i=0;i<jsonArray.size();i++) {
			deviceDetail.setDeviceIp(jsonArray.getJSONObject(i).getString("deviceIP"));
			deviceDetail.setDeviceName(jsonArray.getJSONObject(i).getString("deviceName"));
			deviceDetail.setFreshTime(dateformat(jsonArray.getJSONObject(i).getString("freshTime")));
			deviceDetail.setOrgCode(jsonArray.getJSONObject(i).getString("orgCode"));
			deviceDetail.setPingStatus(jsonArray.getJSONObject(i).getString("pingStatus"));
			deviceDetail.setPingAvg(jsonArray.getJSONObject(i).getString("pingavg"));
			deviceDetail.setPingLoss(jsonArray.getJSONObject(i).getString("pingloss"));
			deviceDetail.setTelnetStatus(jsonArray.getJSONObject(i).getString("telStatus"));
			deviceDetail.setCpu(jsonArray.getJSONObject(i).getString("cpu"));
			deviceDetail.setMemory(jsonArray.getJSONObject(i).getString("memory"));
			if(deviceDetail.getPingLoss()!=null&&!deviceDetail.getPingLoss().isEmpty()) {
				deviceDetail.setPingLoss(deviceDetail.getPingLoss().split("%")[0]);
			}
			if(deviceDetail.getCpu()!=null&&!deviceDetail.getCpu().isEmpty()) {
				deviceDetail.setCpu(deviceDetail.getCpu().split("%")[0]);
			}
			if(deviceDetail.getMemory()!=null&&!deviceDetail.getMemory().isEmpty()) {
				deviceDetail.setMemory(deviceDetail.getMemory().split("%")[0]);
			}
			int dataresult = MyHandler.deviceDetailSevice.insertDeviceDetail(deviceDetail);
			if(dataresult!=0) {
				total++;
			}
		}
		if(total==jsonArray.size()) {
			return "数据解析成功";
		}else {
			return "数据解析失败。请核对数据格式";
		}
	}
	/**
	 * 日期格式转换为年月日时分，不需要秒
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	private String dateformat(String date) throws ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" ); 
		 Date dates = sdf.parse(date);
		return sdf.format(dates);
	}
	
	/**
	 * 删除这个时间点减去一个月后之前的数据
	 * @param dates
	 * @throws ParseException 
	 */
	private void deldate(String dates) throws ParseException {
		MyHandler.deviceDetailSevice.delDeviceDetail(getLastMonth(dates));
	}
	
	/**
	 * 获取上个月的时间字符串
	 * @param dates
	 * @return
	 * @throws ParseException 
	 */
	private String getLastMonth(String dates) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(dates); 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		date = c.getTime();
		return sdf.format(date);
	}
}
