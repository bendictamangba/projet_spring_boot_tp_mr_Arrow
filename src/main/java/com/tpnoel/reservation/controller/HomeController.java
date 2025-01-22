package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.service.ServicesService;
@Controller
public class HomeController {



@GetMapping("/base") 
    public String homePage(Model model) {
        return "base"; // home.html
    }

@GetMapping("/res") 
public String res(Model model) {
    return "displayReservationHistory"; // home.html
}



@GetMapping("/user") 
    public String user(Model model) {
        return "users"; // home.html
    }
@Autowired
private ServicesService servicesService;


//
//@GetMapping("/")
//public String home(Model model) {
//    // Récupérer tous les services
//    List<Services> allServices = servicesService.getAllServices();
//
//    // Filtrer les services de type "Hôtel"
//    List<Services> hotelServices = allServices.stream()
//            .filter(service -> "Hôtel".equalsIgnoreCase(service.getType()))
//            .toList();
//
//    // Ajouter les services filtrés au modèle
//    model.addAttribute("services", hotelServices);
//
//    // Vérification des données filtrées dans la console
//    if (hotelServices.isEmpty()) {
//        System.out.println("Aucun service de type Hôtel trouvé !");
//    } else {
//        hotelServices.forEach(service -> System.out.println("Service Hôtel : " + service.getName()));
//    }
//
//    return "home"; // Correspond au fichier home.html
//}
//

@GetMapping("/")
public String home(Model model) {
    // Récupérer tous les services
    List<Services> allServices = servicesService.getAllServices();

    // Filtrer les services de type "Hôtel"
    List<Services> hotelServices = allServices.stream()
            .filter(service -> "Hôtel".equalsIgnoreCase(service.getType()))
            .toList();

    // Filtrer les services de type "Restaurant"
    List<Services> restaurantServices = allServices.stream()
            .filter(service -> "Restaurant".equalsIgnoreCase(service.getType()))
            .toList();

    // Ajouter les listes filtrées au modèle
    model.addAttribute("hotelServices", hotelServices);
    model.addAttribute("restaurantServices", restaurantServices);

    // Vérification des données dans la console
    if (hotelServices.isEmpty()) {
        System.out.println("Aucun service de type Hôtel trouvé !");
    } else {
        hotelServices.forEach(service -> System.out.println("Service Hôtel : " + service.getName()));
    }

    if (restaurantServices.isEmpty()) {
        System.out.println("Aucun service de type Restaurant trouvé !");
    } else {
        restaurantServices.forEach(service -> System.out.println("Service Restaurant : " + service.getName()));
    }

    return "home"; // Correspond au fichier home.html
}




}