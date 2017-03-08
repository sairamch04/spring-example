package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.service.UserService;
import com.example.validator.UserFormValidator;

@Controller
public class HelloWorldController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserFormValidator userFormValidator;

	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld(Model model){
		return "helloworld";		
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userForm")  @Validated User user,
			BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {  
			// form validation failed
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm", result);
			redirectAttributes.addFlashAttribute("user",user);
			return "redirect:/users/add";
		}
		User userByName = userService.findByName(user.getUserName());
		if(userByName == null){ 
			//create the user
			user.setId(userService.insert(user));			
			redirectAttributes.addFlashAttribute("userAdded",true);
			redirectAttributes.addFlashAttribute("user",user);
		}
		else{
			user.setId(userByName.getId());
		}
			
		return "redirect:/users/" + user.getId();
	}
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
	public String getUserDetails(@PathVariable("id")  int userId, Model model){
		User user =  userService.findById(userId);		
		model.addAttribute("user",user);
		return "userLoggedIn";
		
	}
	
	
	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		if(!model.containsAttribute("user")){
			model.addAttribute("userForm", new User());
		}		
		return "userLogin";

	}
}
