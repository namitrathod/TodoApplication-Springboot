package com.springboot.todoApplication.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList();
	private static int todocount = 0;
	static {
		todos.add(new Todo(++todocount,"namit","Learn AWS", LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todocount,"namit","Learn DSA", LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todocount,"namit","Learn Spring boot", LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todocount,"namit","Learn dyjango", LocalDate.now().plusYears(1), false ));
	}
	public List<Todo> findByUsername(String username){
		return todos;
	}

	public void addtodos(String username, String description, LocalDate localDate, boolean done){
		Todo todo = new Todo(++todocount, username, description, localDate, done);
		todos.add(todo);
	}
	
}

