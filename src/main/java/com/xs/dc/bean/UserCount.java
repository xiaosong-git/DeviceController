package com.xs.dc.bean;
/** 
* @author 作者 : xiaojf
* @Date 创建时间：2019年10月23日 上午10:42:57 
* 类说明 
*/
public class UserCount {
	private String totalUser;//已实名总用户
	
	private String totalCompanyUser;//已认证总公司员工
	
	private String totalVisitorUser;//总访客量
	
	private String totalAddUser;//当日新增总用户量
	
	private String totalEntryCompany;//入驻公司总量

	public String getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(String totalUser) {
		this.totalUser = totalUser;
	}

	public String getTotalCompanyUser() {
		return totalCompanyUser;
	}

	public void setTotalCompanyUser(String totalCompanyUser) {
		this.totalCompanyUser = totalCompanyUser;
	}

	public String getTotalVisitorUser() {
		return totalVisitorUser;
	}

	public void setTotalVisitorUser(String totalVisitorUser) {
		this.totalVisitorUser = totalVisitorUser;
	}

	public String getTotalAddUser() {
		return totalAddUser;
	}

	public void setTotalAddUser(String totalAddUser) {
		this.totalAddUser = totalAddUser;
	}

	public String getTotalEntryCompany() {
		return totalEntryCompany;
	}

	public void setTotalEntryCompany(String totalEntryCompany) {
		this.totalEntryCompany = totalEntryCompany;
	}
	
	
	
	
}
