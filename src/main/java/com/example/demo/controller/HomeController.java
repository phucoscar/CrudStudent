package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/viewUsers")
	public String viewUsers(Model model) {
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "Users";
	}

	@GetMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "AddUser";
	}

	@PostMapping(value = "/addUser")
	public String addUser(@ModelAttribute("user") User user) {
		userService.createUser(user);
		return "redirect:/viewUsers";
	}
	
	@GetMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "EditUser";
	}
	
	@PostMapping("/editUser")
	public String editUser(@ModelAttribute User user) {
		userService.update(user);
		return "redirect:/viewUsers";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "DeleteUser";
	}
	
	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/viewUsers";
	}
}
