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
	private int panne_id;
	private StatutTicket statutTicket;
	private String materiel_id;
	private String utilisateur_id;
	private List<Intervention> interventions;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUtilisateur() {
		return utilisateur_id;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur_id = utilisateur;
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
	
	public String getMateriel() {
		return materiel_id;
	}
	public void setMateriel(String materiel) {
		this.materiel_id = materiel;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", sateCreation=" + dateCreation + ", discription=" + description
				+ ", typeMateriel=" + typeMateriel + ", etatTicket=" + etatTicket + ", statutTicket=" + statutTicket
				+"]";
	}
	public int getPanne() {
		return panne_id;
	}
	public void setPanne(int panne) {
		this.panne_id = panne;
	}
	
    
    
}
