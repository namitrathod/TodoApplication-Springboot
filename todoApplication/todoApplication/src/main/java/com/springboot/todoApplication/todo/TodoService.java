package com.springboot.todoApplication.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

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
		Predicate<? super Todo> predicate= todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();}
		// // public List<Todo> findByUsername(String username){
		// 	Predicate<? super Todo> predicate = todo -> {
		// 		String todoUsername = todo.getUsername();
		// 		return todoUsername != null && todoUsername.equalsIgnoreCase(username);
		// 	};
		// 	return todos.stream().filter(predicate).toList();
		// }
		

	public void addtodos(String username, String description, LocalDate localDate, boolean done){
		Todo todo = new Todo(++todocount, username, description, localDate, done);
		todos.add(todo);
	}

	public void deleteById(int id){
		Predicate<? super Todo> predicate= todo -> todo.getId() == id;
		todos.removeIf(predicate); 
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate= todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();

		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
	
}

