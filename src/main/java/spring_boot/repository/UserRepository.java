package spring_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring_boot.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	public UserEntity findByUsername(String username);
	public UserEntity findById(int id);
}
