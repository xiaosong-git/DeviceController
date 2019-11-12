package com.xs.dc.bean;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月9日 上午10:49:59 
* 类说明 
*/
public class Echarts {

	private String deviceName;
	//报警设备总数
	private int deviceWarn;
	//预警设备总数
	private int deviceEwarn;
	//正常设备总数
	private int deviceNormal;
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getDeviceWarn() {
		return deviceWarn;
	}
	public void setDeviceWarn(int deviceWarn) {
		this.deviceWarn = deviceWarn;
	}
	public int getDeviceEwarn() {
		return deviceEwarn;
	}
	public void setDeviceEwarn(int deviceEwarn) {
		this.deviceEwarn = deviceEwarn;
	}
	public int getDeviceNormal() {
		return deviceNormal;
	}
	public void setDeviceNormal(int deviceNormal) {
		this.deviceNormal = deviceNormal;
	}
	
	
	
	
}
