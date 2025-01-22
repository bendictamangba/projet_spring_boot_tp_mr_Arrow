package com.tpnoel.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tpnoel.reservation.model.User;
import  com.tpnoel.reservation.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/users")
public class DisplayUserController {
	
	@Autowired
	 private UserService userService;
	 
	
	@GetMapping ("/allUser")
	private String listUser(Model model) {
		model.addAttribute("users", userService.findAll());
		//model.addAttribute("nom", "toto");
		return "displayUsers";
 }
 
@GetMapping("/add")
public String addUser(Model model) {
	model.addAttribute("user", new User());
	return  "addUser";
	 
}
@PostMapping("/add")
public String saveUser(@ModelAttribute("user") User user) {
	userService.saveUser(user);
	return "redirect:/users/allUser";
}
 
@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable Long id) {
	userService.deleteUser(id);
	return "redirect:/users/allUser";

}
@GetMapping("/edit/{id}")
public String editUserForm(@PathVariable Long id, Model
	model) {
	model.addAttribute("user", userService.getUserById(id));
	return "edit_user";
}

//
//@PostMapping("/edit/{id}")
//public String updateUser(@PathVariable int id,
//		
//@ModelAttribute("user") User user) {
//	user.setId(id);
//	userService.saveUser(user);
//	return "redirect:/users/allUser";
//}
// 


@PostMapping("/create")

private String CreateUser(HttpServletRequest request) {
	
	System.out.print("toto");
	String address=(String) request.getParameter("address");
	String email=(String) request.getParameter("email");
	String password=(String) request.getParameter("password");
	String phoneNumber=(String) request.getParameter("phoneNumber");
	String sex=(String) request.getParameter("sex");
	String role=(String) request.getParameter("role");
	String surname=(String) request.getParameter("surname");
	User user= new User();
	user.setAddress(address);
	user.setEmail(email);
	user.setPassword(password);
	user.setPhoneNumber(phoneNumber);
	user.setSurname(surname);
	user.setSex(sex);
	user.setRole(role);
	user.setSurname(surname);
	
	
	userService.saveUser(user);
	return "redirect:/users";
	
}


}
