package com.plusplm.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Code")
public class Code implements Serializable {

	private static final long serialVersionUID = -3660738772171356266L;
	
	@Id
	private String id; // 코드ID
	private String masterId;// 마스터ID
	private String codeId; // 코드Code // 아직 논의가 필요한 부분
	private String codeName; // 코드NAME
	private String codeGroup; // 코드GROUP
	private String codeNote; // 코드NOTE
	private int order; // 순서
	private String useYN; // 사용YN
	private String delYN; // 삭제YN
	private String regId; // 등록ID
	private String regName; // 등록NAME
	private String regDate; // 등록DATE
	private String regIp; // 등록IP
	private String modId; // 수정ID
	private String modName; // 수정NAME
	private String modDate; // 수정DATE
	private String modIp; // 수정IP

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if ("".equals(id)) {
			this.id = null;
		} else {
			this.id = id;
		}
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public String getCodeNote() {
		return codeNote;
	}

	public void setCodeNote(String codeNote) {
		this.codeNote = codeNote;
	}
	
	public String getUseYN() {
		return useYN;
	}

	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}

	public String getDelYN() {
		return delYN;
	}

	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getModIp() {
		return modIp;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
