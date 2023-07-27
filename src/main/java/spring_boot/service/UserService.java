package spring_boot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring_boot.data_request.LoginUserReq;
import spring_boot.security.JwtProvider;
import spring_boot.data_request.CreateUserReq;
import spring_boot.entity.UserEntity;
import spring_boot.exception.DuplicateRecordException;
import spring_boot.exception.InCorectException;
import spring_boot.mapper.UserMapper;
import spring_boot.repository.UserRepository;


@Service
public class UserService {
	@Autowired(required = true)
	private UserRepository userRepository;
	
	public ResponseEntity<?> createUser(CreateUserReq createUserReq) {
		
		UserEntity user = userRepository.findByUsername(createUserReq.getUsername());
		if(user != null) {
			throw new DuplicateRecordException("user does exist");
		}
		
		UserEntity userEntityCreate = UserMapper.toEntityCreate(createUserReq);
		userRepository.save(userEntityCreate);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "created user successfully");
		return ResponseEntity.created(null).body(response);
	}
	
	public ResponseEntity<?> login(LoginUserReq loginUserReq) {
		UserEntity user = userRepository.findByUsername(loginUserReq.getUsername());
		if(user == null) {
			throw new InCorectException("username or password incorect");
		}
		
		String accessToken = JwtProvider.generateAccessToken(user.getId(), user.getUsername());
		String refreshToken = JwtProvider.generateRefreshToken(user.getId(), user.getUsername());
				
		Map<String, String> response = new HashMap<>();
		response.put("accessToken", accessToken);
		response.put("refreshToken", refreshToken);
		
		return ResponseEntity.ok().body(response);
	}
	
}
