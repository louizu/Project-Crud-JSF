package com.example.jsf_bdd_bean_form_devoir.model;

import java.io.Serializable;

public class Employee {
    private int id, sal;
    private String nom, prenom, email;

    public Employee(){
        super();
    }

    public Employee(int id, int sal, String nom, String prenom, String email) {
        this.id = id;
        this.sal = sal;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Employee(int sal, String nom, String prenom, String email) {
        this.sal = sal;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", sal=" + sal +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
