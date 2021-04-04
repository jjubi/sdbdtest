package egovframework.com.sec.ram.service;

public class AuthorHierarchyVO {
	/** 부모롤 */
	private String parntsRole = "";
	/** 자식롤 */
	private String chldrnRole = "";
	
	
	
	
	
	
	
	/**
	 * @return the parntsRole
	 */
	public String getParntsRole() {
		return parntsRole;
	}
	/**
	 * @param parntsRole the parntsRole to set
	 */
	public void setParntsRole(String parntsRole) {
		this.parntsRole = parntsRole;
	}
	/**
	 * @return the chldrnRole
	 */
	public String getChldrnRole() {
		return chldrnRole;
	}
	/**
	 * @param chldrnRole the chldrnRole to set
	 */
	public void setChldrnRole(String chldrnRole) {
		this.chldrnRole = chldrnRole;
	}
	
}
