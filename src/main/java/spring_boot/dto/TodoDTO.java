package spring_boot.dto;

import java.sql.Date;

public class TodoDTO extends BaseDTO{
	private int id;
	private String content;
	private Date expried;
	private int idUser;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getExpried() {
		return expried;
	}
	public void setExpried(Date expried) {
		this.expried = expried;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
	
}
