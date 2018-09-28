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
public class Intervention {
   
	private String id;
	private Date dateIntervention;
	private String description;
	private Integer periode;
	private EtatTicket etatIntervention;
	public EtatTicket getEtatIntervention() {
		return etatIntervention;
	}
	public void setEtatIntervention(EtatTicket etatIntervention) {
		this.etatIntervention = etatIntervention;
	}
	
	private Materiel materiel;
	
	private Ticket  ticket;
	private static final long serialVersionUID = 1L;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateIntervention() {
		return dateIntervention;
	}
	public void setDateIntervention(Date dateIntervention) {
		this.dateIntervention = dateIntervention;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Materiel getMateriel() {
		return materiel;
	}
	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Intervention(String id, Date dateIntervention, String description) {
		super();
		this.id = id;
		this.dateIntervention = dateIntervention;
		this.description = description;
		
	}
	public Intervention() {
		super();
	}
	@Override
	public String toString() {
		return "Intervention [id=" + id + ", dateIntervention=" + dateIntervention + ", description=" + description
				+ "]";
	}
	public Integer getPeriode() {
		return periode;
	}
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}
	
	
	
    
}
