package model;

import java.io.Serializable;
import java.lang.String;

public class Persona implements Serializable {

	String nombre, ap1, ap2, dni;
	int edad;
	float talla;
	double peso;

	public Persona(String nombre, String ap1, String ap2, String dni, int edad, float talla, double peso) {
		this.nombre = nombre;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.dni = dni;
		this.edad = edad;
		this.talla = talla;
		this.peso = peso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getTalla() {
		return talla;
	}

	public void setTalla(float talla) {
		this.talla = talla;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp1() {
		return ap1;
	}

	public void setAp1(String ap1) {
		this.ap1 = ap1;
	}

	public String getAp2() {
		return ap2;
	}

	public void setAp2(String ap2) {
		this.ap2 = ap2;
	}

	/**
	 * EJERCICIO 1 PARTE A
	 * 
	 * @param linea
	 * @param delim
	 * @return
	 */
	public static Persona factory(String linea, String delim) {
		String[] campos = linea.split(delim);
		try {
			int token1 = Integer.parseInt(campos[4]);
			float token2 = Float.parseFloat(campos[5]);
			double token3 = Double.parseDouble(campos[6]);
			return new Persona(campos[0], campos[1], campos[2], campos[3], token1, token2, token3);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * EJERCICIO 4 PARTE B
	 * 
	 * @return
	 */
	public String[] estadoComoArrayDeCadenas() {
		String[] linea = new String[5];
		linea[0] = this.nombre;
		linea[1] = this.ap1;
		linea[2] = this.ap2;
		linea[3] = String.valueOf(this.edad);
		linea[4] = String.valueOf(this.talla);
		linea[5] = String.valueOf(this.peso);
		return linea;
	}

}
