package it.polito.tdp.baseball.model;

public class ArcoMomentaneo {
	private People p1; 
	private People p2;
	public ArcoMomentaneo(People p1, People p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	public People getP1() {
		return p1;
	}
	public void setP1(People p1) {
		this.p1 = p1;
	}
	public People getP2() {
		return p2;
	}
	public void setP2(People p2) {
		this.p2 = p2;
	}
	

}
