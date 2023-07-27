package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import spring_boot.data_request.AddTodoReq;
import spring_boot.data_request.UpdateTodoReq;
import spring_boot.security.JwtProvider;
import spring_boot.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/addTodo")
	public ResponseEntity<?> addTodo(@Valid @RequestBody AddTodoReq req, HttpServletRequest httpServletRequest ) {
		String token = JwtProvider.resolveToken(httpServletRequest);
		return todoService.addTodo(req, token);
	}
	
	@DeleteMapping("/delete-todo/{id}")
	public ResponseEntity<?> deleteTodo(HttpServletRequest httpServletRequest, @PathVariable int id) {
		String token = JwtProvider.resolveToken(httpServletRequest);
		return todoService.deleteTodo(id, token);
	}
	
	@PostMapping("update-todo/{id}")
	public ResponseEntity<?> updateTodo(HttpServletRequest httpServletRequest, @Valid @RequestBody UpdateTodoReq req, @PathVariable int id) {
		String token = JwtProvider.resolveToken(httpServletRequest);
		return todoService.updateTodo(req, token, id);
	}
	
	@GetMapping("get-listTodo")
	public ResponseEntity<?> getListTodo(HttpServletRequest httpServletRequest) {
		String token = JwtProvider.resolveToken(httpServletRequest);
		return todoService.getListTodo(token);
	}
}
