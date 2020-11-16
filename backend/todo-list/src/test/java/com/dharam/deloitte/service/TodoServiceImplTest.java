package com.dharam.deloitte.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.dharam.deloitte.repository.TodoRepository;

public class TodoServiceImplTest {

	@Mock
	private TodoRepository todoRepository;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testTodoListByUserId() {
		fail("Not yet implemented");
	}

}
