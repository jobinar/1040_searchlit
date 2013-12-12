package com.example.build2;

public class Item {
	private String id;
	private String title;
	private String desc;
	private String pubdate;
	private String link;
	private String link1;
	private String isbn;
	
	Item(){
		 id="";
		 title="";
		 desc="";
		 pubdate="";
		 link="";
		 link1="";
		 isbn="";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String getLink1() {
		return link1;
	}

	public void setLink1(String link) {
		this.link1 = link;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String link) {
		this.isbn = link;
	}
}
