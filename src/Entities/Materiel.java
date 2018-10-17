/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Materiel {
 
	private String id;
	private String reference;
	private String marque;
	private Date  dateAchat;
	private Integer dureeGarantie;
	private TypeMateriel typeMateriel;
	private java.util.List<Ticket> tickets;
	private java.util.List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
	private String fournisseur_id;
	private java.util.List<Intervention> interventions;

        
        public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	public Integer getDureeGarantie() {
		return dureeGarantie;
	}
	public void setDureeGarantie(Integer dureeGarantie) {
		this.dureeGarantie = dureeGarantie;
	}
	
	public String getFournisseur() {
		return fournisseur_id;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur_id = fournisseur;
	}
	
	
	public java.util.List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(java.util.List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public java.util.List<Intervention> getInterventions() {
		return interventions;
	}
	public void setInterventions(java.util.List<Intervention> interventions) {
		this.interventions = interventions;
	}
	
	@Override
	public String toString() {
		return reference;
	}
	public TypeMateriel getTypeMateriel() {
		return typeMateriel;
	}
	public void setTypeMateriel(TypeMateriel typeMateriel) {
		this.typeMateriel = typeMateriel;
	}
	public Materiel(String reference, String marque, Date dateAchat, Integer dureeGarantie,java.util.List<Utilisateur > utilisateurs,
			Fournisseur fournisseur, java.util.List<Intervention> interventions) {
		super();
		this.reference = reference;
		this.marque = marque;
		this.dateAchat = dateAchat;
		this.dureeGarantie = dureeGarantie;

	}
	public Materiel() {
		super();
	}
	
	
	

    
}
