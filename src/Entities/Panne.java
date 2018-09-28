/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;

/**
 *
 * @author USER
 */
public class Panne {
	private int id;
	private String description;
	private String solution;
	private TypeMateriel typeMateriel;
	private List<Ticket> tickets;
	private static final long serialVersionUID = 1L;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public Panne(String description, String solution) {
		super();
		this.description = description;
		this.solution = solution;
	}
	public Panne() {
		super();
	}
	
	public TypeMateriel getTypeMateriel() {
		return typeMateriel;
	}
	public void setTypeMateriel(TypeMateriel typeMateriel) {
		this.typeMateriel = typeMateriel;
	}
	@Override
	public String toString() {
		return  description;
	}
	/*public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}*/
	
	
	
	

    
    
}
