package it.polito.tdp.baseball.model;

public class Grado {
	private int grado;
	private People p;
	public Grado(int grado, People p) {
		super();
		this.grado = grado;
		this.p = p;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public People getP() {
		return p;
	}
	public void setP(People p) {
		this.p = p;
	}
	@Override
	public String toString() {
		return  grado + ", " + p.getPlayerID();
	}
	
}
