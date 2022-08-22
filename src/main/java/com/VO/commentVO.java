package com.VO;

// 댓글의 정보를 담을 클래스
public class commentVO {
	
	private String comment_id;
	private String post_id;
	private String content;
	private String comment_writer;
	private String comment_date;
	
	public commentVO(String comment_id, String post_id, String content, String comment_writer, String comment_date) {
		super();
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.content = content;
		this.comment_writer = comment_writer;
		this.comment_date = comment_date;
	}
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	
	
}
