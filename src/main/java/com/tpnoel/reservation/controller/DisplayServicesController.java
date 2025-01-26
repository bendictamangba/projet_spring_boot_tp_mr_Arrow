package com.tpnoel.reservation.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.ServicesRepository;
import com.tpnoel.reservation.service.ServicesService;


@Controller
@RequestMapping("/displayfacilities")
public class DisplayServicesController {
	
    @Autowired
    private RestTemplate restTemplate;
	 @Autowired
	 private ServicesService servicesService;
	 @Autowired
	  private ServicesRepository servicesRepository;
//	  private final String BASE_URL = "http://localhost:8082/services";

	 
	 @GetMapping ("/facilities")
		private String listService(Model model) {
			model.addAttribute("facilities", servicesService.getAllServices());
			return "displayService";
	 }
////	 
//	 private final String BASE_URL = "http://localhost:8082/services";
//
//
//	 @GetMapping("/facilities")
//	 public String getAllServices(Model model) {
//	    
//	         Services[] services = restTemplate.getForObject(BASE_URL + "/all", Services[].class);
//	         model.addAttribute("services", Arrays.asList(services));
//	         return "displayServices"; // Renvoie à la vue Thymeleaf avec la liste des services
//	    
//	 }
//	  
	 
	 
	 @GetMapping("/facilitiesfree")
	 private String listAvailableFacilities(Model model) {
	     // Définir des valeurs par défaut pour name et type si non spécifiés
	     String name = "";  // Valeur par défaut
	     String type = "";  // Valeur par défaut

	     // Recherche des services disponibles
	     model.addAttribute("facilities", servicesService.searchServices(name, type, "true"));

	     
	     return "displayFreeService";
	 }

	
	@GetMapping("/add")
	public String addService(Model model) {
		model.addAttribute("services", new Services());
		return  "addService";
		 
	}
//	@PostMapping("/add")
//	public String saveServices(@ModelAttribute("services") Services services) {
//		servicesService.createService(services);
//		return "redirect:/displayfacilities/facilities";
//}

	
	
	@PostMapping("/add")
	public String saveServices(@ModelAttribute Services services, @RequestParam("image") MultipartFile image) {
	    // Validation du prix
	    if (services.getPrice() <= 0) {
	        // Ajouter une erreur de validation ou afficher un message personnalisé
	        
	        throw new IllegalArgumentException("Le prix doit être supérieur à 0.");
	    }

	    // Si l'image n'est pas vide, la sauvegarder
	    if (!image.isEmpty()) {
	        // Sauvegarder l'image sur le disque et obtenir le chemin
	        String filePath = saveImage(image);
	        if (filePath != null) {
	            // Stocker le chemin de l'image dans l'objet services
	            services.setImagePath(filePath);
	        }
	    }

	    
	    servicesService.createService(services);

	   
	    return "redirect:/displayfacilities/facilities";
	}

	private String saveImage(MultipartFile image) {
	    try {
	        // Sauvegarder le fichier avec un nom unique
	        String fileName = System.currentTimeMillis() + "-" + image.getOriginalFilename();
	        String uploadDir = "src/main/resources/static/uploads"; 
	        Path path = Paths.get(uploadDir, fileName);
	        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        return fileName;  // Retourner le nom du fichier enregistré
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;  // Retourner null en cas d'erreur
	    }
	}

@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable Long id) {
	servicesService.deleteService(id);
	return "redirect:/displayfacilities/facilities";


	}

    
   

}