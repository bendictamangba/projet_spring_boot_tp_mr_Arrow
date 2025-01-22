package com.tpnoel.reservation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema="reservationschema", name="reservation")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference // Empêche la récursion sur la relation inverse
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    
    private String amount;
    private String payement_date;
    private String payement_method;
    private String status;
    private int numberOfUsers;
    private String arrival_date; 
    
    private String reservation_date;  // Date de réservation

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    
    
    // Date d'arrivée
    public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	private String departure_date;  // Date de départ

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public int getNumberOfUsers() { 
        return numberOfUsers; 
    }

    public void setNumberOfUsers(int numberOfUsers) { 
        this.numberOfUsers = numberOfUsers; 
    }

    public User getUser() { 
        return user; 
    }

    public void setUser(User user) { 
        this.user = user; 
    }

    public Services getService() { 
        return service; 
    }

    public void setService(Services service) { 
        this.service = service; 
    }

    
    public String getAmount() { 
        return amount; 
    }

    public void setAmount(String amount) { 
        this.amount = amount; 
    }

    public String getPayement_date() { 
        return payement_date; 
    }

    public void setPayement_date(String payement_date) { 
        this.payement_date = payement_date; 
    }

    public String getPayement_method() { 
        return payement_method; 
    }

    public void setPayement_method(String payement_method) { 
        this.payement_method = payement_method; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
