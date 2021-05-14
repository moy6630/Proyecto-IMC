package com.mg.login.models;


public class IMC {
	
	public String nombre;
	public double estatura;
	public int peso;
	public double theIMC;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getEstatura() {
		return estatura;
	}
	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public double getTheIMC() {
		double e=this.estatura;
		int p=this.peso;
		double r=(p/(e*e)*10000);
		theIMC=r;
		setTheIMC(theIMC);
		return theIMC;
	}
	public void setTheIMC(double theIMC) {
		this.theIMC = theIMC;
	}
}
