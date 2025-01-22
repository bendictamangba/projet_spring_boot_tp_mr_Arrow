package com.tpnoel.reservation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.UserRepository;
@Controller
public class AuthController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
    @GetMapping("/login")
    public String login() {
        return "login";     //cela affiche le formulaire de connexion
    }
    

    @GetMapping("/register")
    public String registerUser(Model model)//Le paramètre model est un objet fourni par Spring qui permet de passer des données du contrôleur à la vue.
    {
    	model.addAttribute("user", new User());//Crée un nouvel objet de type User (probablement une classe définie dans votre application, représentant un utilisateur).
    	//Ajoute cet objet au modèle avec le nom "user".
        return "register";     //cela affiche le formulaire de connexion
    }
    
    
    @PostMapping("/register")
    public String registerUser(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    	return "redirect:/dashboard";
    }
    

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Redirige vers la page du tableau de bord
        return "dashboard";
    }
 
    
}