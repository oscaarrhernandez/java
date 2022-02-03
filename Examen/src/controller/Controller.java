package controller;

import com.coti.tools.Rutas;
import com.coti.tools.OpMat;
import static com.coti.tools.OpMat.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import model.Model;

public class Controller {

	Model m = new Model();

	/**
	 * EJERCICIO 4 PARTE A
	 * 
	 * @param delim
	 * @throws Exception
	 */
	public void exportarPersonasConFormatoDelimitado(String delim) throws Exception {
		String nombrearchivo = m.getNameOfTextFile();
		Path ruta = Rutas.pathToFileOnDesktop(nombrearchivo);
		File f = ruta.toFile();
		m.ordenarPorApellidosNombreYdni();
		String[][] data = new String[m.getPersonas().size()][];
		for (int i = 0; i < m.getPersonas().size(); i++) {
			data[i] = m.getPersonas().get(i).estadoComoArrayDeCadenas();
		}
		exportToDisk(data, f, delim);
	}
}
