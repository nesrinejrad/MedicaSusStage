/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Utilisateur extends User{
    private String nom;
	private String prenom;
	private Role role;
	private String fonction;
	private java.util.List<Demande> demandes;
	private java.util.List<Ticket> tickets;
	private java.util.List<Materiel> materiels=new ArrayList<Materiel>();
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public java.util.List<Demande> getDemandes() {
		return demandes;
	}
	public void setDemandes(java.util.List<Demande> demandes) {
		this.demandes = demandes;
	}
	public java.util.List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(java.util.List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public java.util.List<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(java.util.List<Materiel> materiels) {
		this.materiels = materiels;
	}
	public Utilisateur(Role role, String fonction, java.util.List<Demande> demandes, java.util.List<Ticket> tickets,
			java.util.List<Materiel> materiels) {
		super();
		this.role = role;
		this.fonction = fonction;
	
	}
	public Utilisateur() {
		super();
	}
	@Override
	public String toString() {
		return nom+" "+prenom;
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
	
	
	
	
    
    
}
