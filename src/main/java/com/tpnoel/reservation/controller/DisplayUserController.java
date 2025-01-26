package com.tpnoel.reservation.controller;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.tpnoel.reservation.model.User;
import  com.tpnoel.reservation.service.UserService;
@Controller
@RequestMapping("/users")
public class DisplayUserController {
	
	@Autowired
	 private UserService userService;
	 
	@Autowired
    private RestTemplate restTemplate;
	 
	

	    
	@GetMapping ("/allUser")
	private String listUser(Model model) {
		model.addAttribute("users", userService.findAll());		
		return "displayUsers";
 }
	  
//	  private final String BASE_URL = "http://localhost:8082/users";
//
//
//	  // Afficher tous les utilisateurs dans une page Thymeleaf
//	  @GetMapping("/allUser")
//	  public String getAllUsers(Model model) {
//	      try {
//	          User[] users = restTemplate.getForObject(BASE_URL + "/all", User[].class);
//	          model.addAttribute("users", Arrays.asList(users));
//	          return "displayUsers"; // Renvoie à la vue Thymeleaf avec la liste des utilisateurs
//	      } catch (Exception e) {
//	          model.addAttribute("error", "Erreur lors de la récupération des utilisateurs");
//	          return "errorPage"; // Affiche une page d'erreur
//	      }
//	  }



	  
 
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



}
