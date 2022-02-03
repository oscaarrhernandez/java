package model;

import java.io.IOException;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Model {

	String nameOfTextFile;
	String nameOfBinaryFile;
	List<Persona> personas = new ArrayList<>();
	Map<String, Persona> mapa = new HashMap<>();

	public String getNameOfTextFile() {
		return nameOfTextFile;
	}

	public String getNameOfBinaryFile() {
		return nameOfBinaryFile;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public Map<String, Persona> getMapa() {
		return mapa;
	}

	/**
	 * EJERCICIO 1 PARTE B
	 * 
	 * @param ruta
	 */
	public void importar(Path ruta) {
		if (ruta.toFile().exists()) {
			try {
				ArrayList<String> lineas = (ArrayList<String>) readAllLines(ruta);
				String delim = "#";
				for (String i : lineas) {
					Persona p = Persona.factory(i, delim);
					if (p != null) {
						personas.add(p);
					} else {
						System.err.printf("Error.");
					}
				}
			} catch (IOException e) {
				System.err.printf("Error.");
			}
		}
	}

	/**
	 * EJERCICIO 2 LAS 3 PARTES
	 */
	public void ordenarPorApellidosNombreYdni() {
		personas.sort(Comparator.comparing(Persona::getAp1).thenComparing(Persona::getAp2).thenComparing(Persona::getNombre)
				.thenComparing(Persona::getDni));
	}

	public void ordenarPorEdadPesoTallaYdni() {
		personas.sort(Comparator.comparing(Persona::getEdad).thenComparing(Persona::getPeso)
				.thenComparing(Persona::getTalla).thenComparing(Persona::getDni));
	}

	public void ordenarPorEdadApellidosYpeso() {
		personas.sort(Comparator.comparing(Persona::getEdad).thenComparing(Persona::getAp1).thenComparing(Persona::getAp2)
				.thenComparing(Persona::getPeso));
	}

	/**
	 * EJERCICIO 3 PARTE A
	 */
	public void prepararMapa() {
		for (Persona p : personas) {
			mapa.put(p.getDni(), p);
		}
	}

	/**
	 * EJERCICIO 3 PARTE B
	 * 
	 * @param dni
	 * @param delim
	 * @return
	 */
	public String datosDePersonaComoStringDelimitado(String dni, String delim) {
		Persona p = mapa.get(dni);
		String token;
		token = p.getNombre() + delim + p.getAp1() + delim + p.getAp2() + delim + p.getDni() + delim + p.getEdad() + delim
				+ p.getTalla() + delim + p.getPeso() + delim;
		return token;
	}

}
