package com.springboot.todoApplication.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(username);
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
			todoService.addtodos(getLoggedInUsername(model), todo.getDescription(), todo.getTargetDate(), false);
			return "redirect:list-todo";
		}


		@RequestMapping("delete-todo")
		public String deleteTodos(@RequestParam int id) {
			todoService.deleteById(id);
			return "redirect:list-todo";
		}

		@RequestMapping(value ="update-todo", method = RequestMethod.GET)
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo = todoService.findById(id);
			model.addAttribute("todo",todo);
			return "addtodo";
		}

		@RequestMapping( value = "update-todo", method = RequestMethod.POST)
		public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result ) {
			if(result.hasErrors()){
				return "addtodo";
			}
			String username = getLoggedInUsername(model);
			todo.setUsername(username);
			todoService.updateTodo(todo);
			return "redirect:list-todo";
		}

		private String getLoggedInUsername(ModelMap model){
			Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
			return authentication.getName();
		}

}
