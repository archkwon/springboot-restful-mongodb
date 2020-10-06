package com.plusplm.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	// private String imgLocation = "/home/Repository/img";
	// private String docLocation = "/home/Repository/doc";
	// private String imgLocation = "src/main/resources/img";

	private static String OS = System.getProperty("os.name").toLowerCase();

	private String imgLocation;
	private String docLocation;
	private String dwgLocation;
	private String aiLocation;

	// 선주, 선급, 관청
	public String getImgLocation() {
		if (OS.indexOf("win") >= 0) {
			imgLocation = "/home/Repository/img";
		} else if (OS.indexOf("mac") >= 0) {
			imgLocation = "src/main/resources/img";

		} else {
			imgLocation = "/home/Repository/img";
		}

		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	// 호선관리, Task, 이슈
	public String getDocLocation() {

		if (OS.indexOf("win") >= 0) {
			docLocation = "/home/Repository/doc";
		} else if (OS.indexOf("mac") >= 0) {
			docLocation = "src/main/resources/doc";
		} else {
			docLocation = "/home/Repository/doc";
		}

		return docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	// 도면실적등록
	public String getDwgLocation() {

		if (OS.indexOf("win") >= 0) {
			dwgLocation = "/home/Repository/dwg";
		} else if (OS.indexOf("mac") >= 0) {
			dwgLocation = "src/main/resources/dwg";

		} else {
			dwgLocation = "/home/Repository/dwg";
		}

		return dwgLocation;
	}

	public void setDwgLocation(String dwgLocation) {
		this.dwgLocation = dwgLocation;
	}
	
	// AI유사선탐색
	public String getAiLocation() {
		if (OS.indexOf("win") >= 0) {
			aiLocation = "/home/Repository/ai/buildspec/data";
		} else if (OS.indexOf("mac") >= 0) {
			aiLocation = "src/main/resources/ai/buildspec/data";
		} else {
			aiLocation = "/home/Repository/ai/buildspec/data";
		}
		return aiLocation;
	}

	public void setAiLocation(String aiLocation) {
		this.aiLocation = aiLocation;
	}
}
