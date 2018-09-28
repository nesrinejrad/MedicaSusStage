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
public class Externe extends  Intervention{
    private String conatct;
	private float cout;
	public String getConatct() {
		return conatct;
	}
	public void setConatct(String conatct) {
		this.conatct = conatct;
	}
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public Externe(String conatct, float cout) {
		super();
		this.conatct = conatct;
		this.cout = cout;
	}
	public Externe() {
		super();
	}
	@Override
	public String toString() {
		return "Externe [conatct=" + conatct + ", cout=" + cout + "]";
	}
	
    
}
