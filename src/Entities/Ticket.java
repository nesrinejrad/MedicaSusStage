/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class Ticket {
	private int id;
	private Date dateCreation;
	private String description;
	private TypeMateriel typeMateriel;
	private EtatTicket etatTicket;
	private Panne panne;
	private StatutTicket statutTicket;
	private Materiel materiel;
	private Utilisateur utilisateur;
	private List<Intervention> interventions;
	
	
	private static final long serialVersionUID = 1L;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSateCreation() {
		return dateCreation;
	}
	public void setSateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDiscription() {
		return description;
	}
	public void setDiscription(String discription) {
		this.description = discription;
	}
	public TypeMateriel getTypeMateriel() {
		return typeMateriel;
	}
	public void setTypeMateriel(TypeMateriel typeMateriel) {
		this.typeMateriel = typeMateriel;
	}
	public EtatTicket getEtatTicket() {
		return etatTicket;
	}
	public void setEtatTicket(EtatTicket etatTicket) {
		this.etatTicket = etatTicket;
	}
	public StatutTicket getStatutTicket() {
		return statutTicket;
	}
	public void setStatutTicket(StatutTicket statutTicket) {
		this.statutTicket = statutTicket;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public List<Intervention> getInterventions() {
		return interventions;
	}
	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}
	public Ticket(Date sateCreation, String discription, TypeMateriel typeMateriel, EtatTicket etatTicket,
			StatutTicket statutTicket, Utilisateur utilisateur,
			List<Intervention> interventions) {
		super();
		this.dateCreation = sateCreation;
		this.description = discription;
		this.typeMateriel = typeMateriel;
		this.etatTicket = etatTicket;
		this.statutTicket = statutTicket;
	}
	public Ticket() {
		super();
	}
	
	public Materiel getMateriel() {
		return materiel;
	}
	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", sateCreation=" + dateCreation + ", discription=" + description
				+ ", typeMateriel=" + typeMateriel + ", etatTicket=" + etatTicket + ", statutTicket=" + statutTicket
				+"]";
	}
	public Panne getPanne() {
		return panne;
	}
	public void setPanne(Panne panne) {
		this.panne = panne;
	}
	
    
    
}
