package spring_boot.data_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserReq {
	@NotBlank(message = "The username is required.")
	@Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
