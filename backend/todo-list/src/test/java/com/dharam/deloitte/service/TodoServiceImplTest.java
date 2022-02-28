package com.dharam.deloitte.service;

import static org.junit.Assert.*;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.dharam.deloitte.model.Todo;
import com.dharam.deloitte.repository.TodoRepository;

import static org.hamcrest.CoreMatchers.is;  

public class TodoServiceImplTest {

	@Rule  
	public MockitoRule mockitorule = MockitoJUnit.rule();
	
	@InjectMocks
	private TodoServiceImpl todoServiceImpl;
	
	@Mock
	private TodoRepository todoRepository;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testTodoListByUserId() {
		//given
		Mockito.when(todoRepository.findAllByUserId(10L)).thenReturn(Lists.emptyList());
		
		//when
		List<Todo> todos = todoServiceImpl.todoListByUserId(10L);
		
		//then
		assertThat(todos.size(), is(0));
	}

}
