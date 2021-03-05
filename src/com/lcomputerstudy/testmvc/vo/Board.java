package com.lcomputerstudy.testmvc.vo;


public class Board {
	
	private int b_num;
	private String b_title;
	private String b_date;
	private int b_hits;
	private String b_content;
	private String c_comment;
	private String u_id;
	private int Rownum;
	private String c_id;
	
	public int getB_num() {
		
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num= b_num;
	}
	
	public String getB_date() {
		return b_date;
	}
	
	public void setB_date(String b_date) {
		this.b_date= b_date;

	}
	
	public String getB_content() {
		return b_content;
	}
	
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	
	public int getB_hits() {
		
		return b_hits;
	}
	public void setB_hits(int b_hits) {
		this.b_hits= b_hits;
	}
	
	public String getC_comment() {
		return c_comment;
	}
	
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}
	
	public String getB_title() {
		return b_title;
	}
	
	public void setB_title(String b_title) {
		this.b_title =b_title;
	}
	
	public String getU_id() {
		return u_id;
	}
	
	public void setU_id(String u_id) {
		this.u_id =u_id;
	}
	
	public void setRownum(int Rownum) {
		this.Rownum= Rownum;
	}
	
	public int getRownum() {
		return Rownum;
	}
	
	public void setC_id(String c_id) {
		this.c_id= c_id;
		
	}
	
	public String getC_id() {
		return c_id;
	}
	
	
	
}
	

