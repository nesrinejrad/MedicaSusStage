/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author USER
 */
public class Fournisseur {
 
	private String id;
	private String  nom;
	private String adresse;
	private String telephone;
	private String email;
	private java.util.List<Materiel> materiels;
	private static final long serialVersionUID = 1L;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.List<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(java.util.List<Materiel> materiels) {
		this.materiels = materiels;
	}
	public Fournisseur(String id, String nom, String adresse, String telephone, String email,
			java.util.List<Materiel> materiels) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
	}
	public Fournisseur() {
		super();
	}
	@Override
	public String toString() {
		return nom;
	}
	
    
}
