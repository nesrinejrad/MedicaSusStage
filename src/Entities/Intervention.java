/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;

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
	private String materiel_id;	
	private int  ticket_id;

        
          public EtatTicket getEtatIntervention() {
		return etatIntervention;
	}
	public void setEtatIntervention(EtatTicket etatIntervention) {
		this.etatIntervention = etatIntervention;
	}
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
	public String getMateriel() {
		return materiel_id;
	}
	public void setMateriel(String materiel) {
		this.materiel_id = materiel;
	}
	public int getTicket() {
		return ticket_id;
	}
	public void setTicket(int ticket) {
		this.ticket_id = ticket;
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
