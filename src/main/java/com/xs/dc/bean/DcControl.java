package com.xs.dc.bean;

public class DcControl {
    private Long id;

    private String pingavg;

    private String pingloss;

    private String cpu;

    private String memory;

    private String deviceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPingavg() {
        return pingavg;
    }

    public void setPingavg(String pingavg) {
        this.pingavg = pingavg == null ? null : pingavg.trim();
    }

    public String getPingloss() {
        return pingloss;
    }

    public void setPingloss(String pingloss) {
        this.pingloss = pingloss == null ? null : pingloss.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }
}