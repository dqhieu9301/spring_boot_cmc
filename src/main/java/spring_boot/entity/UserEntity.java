package spring_boot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Column
	private String username;

	@Column
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<TodoEntity> todosEntity = new ArrayList<>();

	public List<TodoEntity> getTodosEntity() {
		return todosEntity;
	}

	public void setTodosEntity(List<TodoEntity> todosEntity) {
		this.todosEntity = todosEntity;
	}

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