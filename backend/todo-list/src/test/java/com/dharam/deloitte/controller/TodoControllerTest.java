package com.dharam.deloitte.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dharam.deloitte.config.auth.UserPrincipal;
import com.dharam.deloitte.model.Todo;
import com.dharam.deloitte.repository.TodoRepository;
import com.dharam.deloitte.service.TodoService;
import com.dharam.deloitte.service.TodoServiceImpl;

public class TodoControllerTest {


	@Mock
	TodoService todoService;
	
	TodoController todoController;
	
	@Mock
	UserPrincipal currentUser;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		todoService = new TodoServiceImpl();
		currentUser = new UserPrincipal(10L, "test", "test", "Test");
		
		todoController = new TodoController(todoService);
	}

	@Test
	public void getTodoListByUserId() {
		Todo todo = new Todo();
		List<Todo> todoData = new ArrayList<>();
				todoData.add(todo);
		String userId = "10";
		//when(currentUser.getId()).thenReturn(10L);
		when(todoService.todoListByUserId(currentUser.getId())).thenReturn(todoData);
		
		List<Todo> todos = todoController.getTodoListByUserId(null, userId);
		assertEquals(1, todos.size());
		
	}

}
