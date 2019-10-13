package com.dharam.deloitte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dharam.deloitte.config.auth.CurrentUser;
import com.dharam.deloitte.config.auth.UserPrincipal;
import com.dharam.deloitte.exception.ResourceNotFoundException;
import com.dharam.deloitte.model.Todo;
import com.dharam.deloitte.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/users/{userId}/todos")
	public List<Todo> getTodoListByUserId(@CurrentUser UserPrincipal currentUser, @PathVariable String userId) {
		return todoService.todoListByUserId(currentUser.getId());
	}

	@GetMapping("users/{userId}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@CurrentUser UserPrincipal currentUser, @PathVariable String userId, @PathVariable(value = "id") Long todoId)
			throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		return ResponseEntity.ok().body(todo);
	}

	@PostMapping("/users/{userId}/todos")
	public Todo createTodo(@CurrentUser UserPrincipal currentUser, @PathVariable String userId, @Valid @RequestBody Todo todo) {
		todo.setCreatedDate(new Date());
		return todoService.save(currentUser, todo);
	}

	@PutMapping("/users/{userId}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "id") Long todoId,
			@Valid @RequestBody Todo todoDetails) throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));

		todo.setDescription(todoDetails.getDescription());
		todo.setDone(todoDetails.isDone());
		todo.setTargetDate(todoDetails.getTargetDate());
		todo.setModifiedDate(new Date());

		final Todo updatedTodo = todoService.save(currentUser, todo);
		return ResponseEntity.ok(updatedTodo);
	}

	@DeleteMapping("/users/{userId}/todos/{id}")
	public Map<String, Boolean> deleteTodo(@CurrentUser UserPrincipal currentUser, @PathVariable(value = "id") Long todoId)
			throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));

		todoService.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
