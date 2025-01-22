package com.tpnoel.reservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema="reservationschema", name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message, date_send;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getMessage() {
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }

    public String getDate_send() { 
        return date_send; 
    }

    public void setDate_send(String date_send) { 
        this.date_send = date_send; 
    }

    public Long getId() { 
        return id; 
    }
}
