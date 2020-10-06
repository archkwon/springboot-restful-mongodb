package com.plusplm.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Code")
public class CommCode implements Serializable {

	@Id
	private String id; // 코드ID
	private String codeId; // 코드Code
	private String codeName; // 코드NAME
	private int order;

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

	public final String getCodeId() {
		return codeId;
	}

	public final void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public final String getCodeName() {
		return codeName;
	}

	public final void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
