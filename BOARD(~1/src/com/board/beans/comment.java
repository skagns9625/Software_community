package com.board.beans;

public class comment {//계층간 데이터 교환을 위한 자바빈즈
	//내용
	private String content;
	
	//아이디
	private String id;
	
	//작성일자
	private String boarddate;
	

	private int num;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(String boarddate) {
		this.boarddate = boarddate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
