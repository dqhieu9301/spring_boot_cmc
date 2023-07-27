package spring_boot.data_request;

public class UpdateTodoReq {
	private String content;
	private String expired;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
}
