package com.xs.dc.bean;

public class DcDeviceDetail {
    private Long id;

    private String orgCode;

    private String deviceName;

    private String deviceIp;

    private String pingStatus;

    private String pingAvg;

    private String pingLoss;

    private String telnetStatus;

    private String freshTime;

    private String memory;

    private String cpu;

    private String disk;

    private String process;

    private String prtInfo;
    
    private String warn;
    
    private String remoteIp;
    
    private String loginName;
    
    private String loginPwd;
    
    private String remotePort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp == null ? null : deviceIp.trim();
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus == null ? null : pingStatus.trim();
    }

    public String getPingAvg() {
        return pingAvg;
    }

    public void setPingAvg(String pingAvg) {
        this.pingAvg = pingAvg;
    }

    public String getPingLoss() {
        return pingLoss;
    }

    public void setPingLoss(String pingLoss) {
        this.pingLoss = pingLoss == null ? null : pingLoss.trim();
    }

    public String getTelnetStatus() {
        return telnetStatus;
    }

    public void setTelnetStatus(String telnetStatus) {
        this.telnetStatus = telnetStatus == null ? null : telnetStatus.trim();
    }

    public String getFreshTime() {
        return freshTime;
    }

    public void setFreshTime(String freshTime) {
        this.freshTime = freshTime == null ? null : freshTime.trim();
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk == null ? null : disk.trim();
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    public String getPrtInfo() {
        return prtInfo;
    }

    public void setPrtInfo(String prtInfo) {
        this.prtInfo = prtInfo == null ? null : prtInfo.trim();
    }

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}
    
	
    
}