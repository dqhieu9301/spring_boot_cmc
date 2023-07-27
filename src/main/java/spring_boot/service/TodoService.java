package spring_boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring_boot.data_request.AddTodoReq;
import spring_boot.data_request.UpdateTodoReq;
import spring_boot.dto.TodoDTO;
import spring_boot.entity.TodoEntity;
import spring_boot.entity.UserEntity;
import spring_boot.exception.NotFoundException;
import spring_boot.mapper.TodoMapper;
import spring_boot.repository.TodoRepository;
import spring_boot.repository.UserRepository;
import spring_boot.security.JwtProvider;

@Service
public class TodoService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	public ResponseEntity<?> addTodo(AddTodoReq req, String token) {

		Map<String, String> inforUser = JwtProvider.getInforUserJWT(token);
		String id = inforUser.get("id");
		UserEntity user = userRepository.findById(Integer.parseInt(id));
		
		TodoEntity todoEntity = TodoMapper.toEntityAddTodo(req, user);
		
		todoRepository.save(todoEntity);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "add todo successfully");
		return ResponseEntity.created(null).body(response);
	}
	
	public ResponseEntity<?> deleteTodo(int id, String token) { 
		TodoEntity todoEntity = todoRepository.findById(id);
		Map<String, String> inforUser = JwtProvider.getInforUserJWT(token);
		String username = inforUser.get("username");
		
		if(todoEntity == null || !username.equals(todoEntity.getUser().getUsername())) {
			throw new NotFoundException("id does not exist");
		}
		todoRepository.deleteById(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "delete todo successfully");
		return ResponseEntity.created(null).body(response);
	}
	
	public ResponseEntity<?> updateTodo(UpdateTodoReq req, String token, int id) { 
		Map<String, String> inforUser = JwtProvider.getInforUserJWT(token);
		String username = inforUser.get("username");
		
		TodoEntity todoEntity = todoRepository.findById(id);
		if(todoEntity == null || !username.equals(todoEntity.getUser().getUsername())) {
			throw new NotFoundException("id does not exist");
		}
		
		TodoEntity todoUpdate = TodoMapper.toEntityUpdateTodo(req, id, todoEntity.getUser());
		todoRepository.save(todoUpdate);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "update todo successfully");
		return ResponseEntity.created(null).body(response);
	}
	
	public ResponseEntity<?> getListTodo(String token) { 
		Map<String, String> inforUser = JwtProvider.getInforUserJWT(token);
		String idUser = inforUser.get("id");
		
		List<TodoEntity> listTodoEntity = todoRepository.findByUserId(Integer.parseInt(idUser));
		List<TodoDTO> listTodoDTO = TodoMapper.toListEntityToListDTO(listTodoEntity);
		
		Map<String, List<TodoDTO>> response = new HashMap<>();
		response.put("data", listTodoDTO);
		return ResponseEntity.ok().body(response);
	}
}
