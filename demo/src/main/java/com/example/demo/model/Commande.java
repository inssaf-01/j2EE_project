package com.example.demo.model;

import javax.persistence.Entity;
import  javax.persistence.GeneratedValue;
import  javax.persistence.GenerationType;
import  javax.persistence.Id;

import java.time.LocalDate;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int quantite;
    private LocalDate date;
    private double montant;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantite() {
        return quantite;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Long getProductId() {
        return 0L;
    }

    public void setPrix(double v) {
    }
}

