package egovframework.vaiv.kr.cmmn.qestnar.service;

public class QestnarTrgtVO {
	/** 설문조사 일련 번호 */ 
	private String qestnarSeqNo ="";
	/** 고유 ID */ 
	private String esntlId ="";
	/** 회원 명 */ 
	private String mberNm ="";
	/** 등록 일자 */ 
	private String registDe ="";
	
	
	
	
	
	
	/**
	 * @return the qestnarSeqNo
	 */
	public String getQestnarSeqNo() {
		return qestnarSeqNo;
	}
	/**
	 * @param qestnarSeqNo the qestnarSeqNo to set
	 */
	public void setQestnarSeqNo(String qestnarSeqNo) {
		this.qestnarSeqNo = qestnarSeqNo;
	}
	/**
	 * @return the esntlId
	 */
	public String getEsntlId() {
		return esntlId;
	}
	/**
	 * @param esntlId the esntlId to set
	 */
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	/**
	 * @return the mberNm
	 */
	public String getMberNm() {
		return mberNm;
	}
	/**
	 * @param mberNm the mberNm to set
	 */
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	/**
	 * @return the registDe
	 */
	public String getRegistDe() {
		return registDe;
	}
	/**
	 * @param registDe the registDe to set
	 */
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
}
