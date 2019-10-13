package com.dharam.deloitte.service;

import java.util.List;
import java.util.Optional;

import com.dharam.deloitte.config.auth.UserPrincipal;
import com.dharam.deloitte.model.Todo;

public interface TodoService {
	
	List<Todo> todoListByUserId(Long userId);

	Optional<Todo> findById(Long todoId);

	Todo save(UserPrincipal currentUser, Todo todo);

	void delete(Todo todo);

}
