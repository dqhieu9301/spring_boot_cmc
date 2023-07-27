package spring_boot.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import spring_boot.data_request.AddTodoReq;
import spring_boot.data_request.UpdateTodoReq;
import spring_boot.dto.TodoDTO;
import spring_boot.entity.TodoEntity;
import spring_boot.entity.UserEntity;

public class TodoMapper {
	public static TodoEntity toEntityAddTodo(AddTodoReq req, UserEntity userEntity) {
		TodoEntity todoEntity = new TodoEntity();
		String dateFormat = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date;
        
		try {
			date = sdf.parse(req.getExpired());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			todoEntity.setContent(req.getContent());
			todoEntity.setExpired(sqlDate);
			todoEntity.setUser(userEntity);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return todoEntity;
       
	}
	
	public static TodoEntity toEntityUpdateTodo(UpdateTodoReq req, int id, UserEntity userEntity) {
		TodoEntity todoEntity = new TodoEntity();
		String dateFormat = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date;
        
        try {
			date = sdf.parse(req.getExpired());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			todoEntity.setContent(req.getContent());
			todoEntity.setExpired(sqlDate);
			todoEntity.setId(id);	
			todoEntity.setUser(userEntity);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return todoEntity;
	}
	
	public static List<TodoDTO> toListEntityToListDTO(List<TodoEntity> listTodoEntity) {
		List<TodoDTO> listTodoDTO = new ArrayList<>();
		listTodoEntity.forEach((todoEntity) -> {
			TodoDTO todoDTO = new TodoDTO();
			todoDTO.setContent(todoEntity.getContent());
			todoDTO.setExpried(todoEntity.getExpired());
			todoDTO.setIdUser(todoEntity.getUser().getId());
			todoDTO.setId(todoEntity.getId());
			listTodoDTO.add(todoDTO);
		});
		return listTodoDTO;
		
	}
}
