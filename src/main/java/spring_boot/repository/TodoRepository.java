package spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring_boot.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
	public TodoEntity findById(int id);
	public List<TodoEntity> findByUserId(int userId);
}
