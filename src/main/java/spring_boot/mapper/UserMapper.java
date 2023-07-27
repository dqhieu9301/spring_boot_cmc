package spring_boot.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import spring_boot.data_request.CreateUserReq;
import spring_boot.dto.UserDTO;
import spring_boot.entity.UserEntity;

public class UserMapper {
	public static UserDTO entityToDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setId(userEntity.getId());
		userDTO.setPassword(userEntity.getPassword());
		return userDTO;
	}
	
	public static UserEntity toEntityCreate(CreateUserReq req) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(req.getUsername());	
		userEntity.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
		return userEntity;
	}
	
	public static List<UserDTO> toListEntityToListDTO(List<UserEntity> listUserEntity) {
		List<UserDTO> listUserDTO = new ArrayList<>();
		listUserEntity.forEach((userEntity) -> {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(userEntity.getUsername());
			userDTO.setId(userEntity.getId());
			userDTO.setPassword(userEntity.getPassword());
			listUserDTO.add(userDTO);
		});
		return listUserDTO;
		
	}
}
