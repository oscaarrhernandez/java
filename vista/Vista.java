package vista;

import static com.coti.tools.Esdia.*;
import static com.coti.tools.OpMat.printToScreen3;
import controlador.Controlador;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Profesteca;

public class Vista {
    
    Profesteca profesteca = new Profesteca();
    Controlador controlador = new Controlador();
    
    public void menuPrincipal() throws IOException, Exception{
        profesteca.importarDatos();
        controlador.recibirPunteroAProfesteca(profesteca);
        
        String [] possbopc = {"A","B","M","C","L","S"};
        boolean exit = false;
        while(!exit){
            String mainMenu="%n== BASE DE DATOS DE PROFESORES =="
                    +"%nA) Dar de alta un profe."
                    +"%nB) Dar de baja un profe."
                    +"%nM) Modificar algun atributo de un profesor."
                    +"%nC) Consultar asignaturas impartidas por un profesor."
                    +"%nL) Listado de todos los profes ordenados por nombre y edad."
                    +"%nS) Salir"
                    + ">"
                    + "%n";
            String option = readString(mainMenu,possbopc).toLowerCase();
            switch (option) {
                case "a" -> {
                    darAltaProfesor();
                }
                case "b" -> {
                    darBajaProfesor();
                }
                case "m" -> {
                    modificarProfesor();
                }
                case "c" -> {
                    consultarAsignaturasProfesor();
                }
                case "l" -> {
                    listadoProfesores();
                }
                case "s" -> {
                    exit = yesOrNo("¿Desea realmente salir?");
                    exportarBinario();
                }
                default -> System.out.printf("Opcion incorrecta%n%n");
            }
        }
    }
    
    /**
     * Importar profesteca.
     */
    public void pasarPunteroAProfesteca(){
        controlador.recibirPunteroAProfesteca(profesteca);
    }
    
    /**
         * Leer y pasar al controlador los datos del profesor para darlo de alta.
    */
    public void darAltaProfesor(){
        String nombre;
        int edad;
        int cuantas;
        ArrayList<String> asignaturas = new ArrayList<>();
   
        nombre = readString("Ingrese el nobre del profesor: ");
        edad = readInt("Ingrese edad del profesor: ");
        cuantas = readInt("Ingrese cuantas asignaturas imparte el profesor: ");
        for(int i=0;i<cuantas;i++){
            asignaturas.add(readString("Ingrese nombre de la asignatura: "));
        }
        controlador.darAltaProfesor(nombre,edad,asignaturas);
    }
    
    /**
     * Leer y pasar al controlador los datos del profesor para darlo de baja.
     */
    public void darBajaProfesor(){
        String nombre = readString("Ingrese el noombre del profesor que desea borrar: ");
        if(controlador.darBajaProfesor(nombre)){
            System.out.println("Profesor borrado exitosamente de la base de datos.");
        }else
            System.out.println("El profesor que desea borrar, no se encuentra en la base de datos.");
    }
    
    /**
     * Modificar los datos del profesor seleccionado.
     */
    public void modificarProfesor(){
        String nuevoAtributo;
        String nombre = readString("Ingrese el nombre del profesor que desea modificar: ");
        
        if(controlador.existeProfesor(nombre))
        {
            boolean exit = false;
            while(!exit){
                String mainMenu="%n== QUE ATRIBUTO DESEA MODIFICAR =="
                    +"%nN) Nombre."
                    +"%nE) Edad."
                    +"%nS) Salir"
                    + ">"
                    + "%n";
                String option = readString(mainMenu).toLowerCase();
                switch (option) {
                    case "n" ->{
                        nuevoAtributo = readString("Ingrese el nuevo nombre: ");
                        controlador.modificarAtributoProfesor(nombre,option,nuevoAtributo);
                    }
                    case "e" ->{
                        nuevoAtributo = readString("Ingrese la nueva edad: ");
                        controlador.modificarAtributoProfesor(nombre, option,nuevoAtributo);
                    }
                    case "s" -> {
                    exit = yesOrNo("¿Desea realmente salir?");
                    }
                }
            }
        }else
            System.out.println("El profesor que desea modificar, no se encuentra en la base de datos.");  
    }
    
    /**
     * Consultar asignaturas de un profesor
     * Comprueba si existe el profesor y consulta e imprime las asignaturas.
     */
    public void consultarAsignaturasProfesor(){
        String nombre = readString("Ingrese el nombre del profesor a consultar: ");
        if(controlador.existeProfesor(nombre)){
            String asignaturas = controlador.consultarAsignaturasProfesor(nombre);
            System.out.println(asignaturas);
        }
    }
    
    /**
     * Crea matriz de profesores y lo imprime por pantalla a través de printToScreen3
     * @throws Exception 
     */
    public void listadoProfesores() throws Exception{
        String[][] matrizProfesores = controlador.listadoProfesores();
        printToScreen3(matrizProfesores);
    }
    
    /**
     * Exporta El ArrayList de los profesores a un archivo en .bin.
     */
    public void exportarBinario(){
        controlador.exportarBinario();
    }
}
