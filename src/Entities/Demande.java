/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Demande {
      
	private int id;
	private TypeMateriel typeMateriel;
	private Date dateDemande;
	private String description;
	private StatutTicket status;
	private Utilisateur utilisateur;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeMateriel getTypeMateriel() {
		return typeMateriel;
	}
	public void setTypeMateriel(TypeMateriel typeMateriel) {
		this.typeMateriel = typeMateriel;
	}
	public Date getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	public Demande(int id, TypeMateriel typeMateriel, Date dateDemande, String description, Utilisateur utilisateur
			) {
		super();
		this.id = id;
		this.typeMateriel = typeMateriel;
		this.dateDemande = dateDemande;
		this.description = description;
		this.utilisateur = utilisateur;
	}
	public Demande() {
		
	}
	@Override
	public String toString() {
		return description;
	}
	public StatutTicket getStatus() {
		return status;
	}
	public void setStatus(StatutTicket status) {
		this.status = status;
	}
	
	
	
    
}
