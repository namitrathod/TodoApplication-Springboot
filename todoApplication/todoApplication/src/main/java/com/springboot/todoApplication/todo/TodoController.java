package com.springboot.todoApplication.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class TodoController {
	
	private TodoService todoService;
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todo")
		public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("namit");
		model.addAttribute("todos", todos);
			return "listTodo";
		}

		// GET method
	@RequestMapping( value = "add-todo", method = RequestMethod.GET)
		public String addTodosPage(ModelMap model) {
			String username = (String)model.get("name");
			Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1), false);
			model.put("todo",todo);
			return "addtodo";
		}
		// POST method
	@RequestMapping( value = "add-todo", method = RequestMethod.POST)
		public String saveTodos(ModelMap model, @Valid Todo todo, BindingResult result ) {
			if(result.hasErrors()){
				return "addtodo";
			}
			todoService.addtodos((String)model.get("name"), todo.getDescription(), LocalDate.now().plusYears(1), false);
			return "redirect:list-todo";
		}


		@RequestMapping("delete-todo")
		public String deleteTodos(@RequestParam int id) {
			todoService.deleteById(id);
			return "redirect:list-todo";
		}

		@RequestMapping("update-todo")
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo = todoService.findById(id);
			model.addAttribute("todo",todo);
			return "addtodo";
		}

}
