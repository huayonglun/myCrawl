package com.liuyong;

public class Data {
	String title;
	String summary;
	
	public Data(String title, String summary) {
		super();
		this.title = title;
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Data [title=" + title + ", summary=" + summary + "]";
	}
	
	
	

}
