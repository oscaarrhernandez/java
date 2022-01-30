package controlador;

import java.util.ArrayList;
import modelo.Profesteca;


public class Controlador {
    Profesteca profesteca;
    
    public void recibirPunteroAProfesteca(Profesteca profesteca){
        this.profesteca = profesteca;
    }
    
    public void darAltaProfesor(String nombre, int edad, ArrayList<String> asignaturas){
        profesteca.altaProfesor(nombre,edad,asignaturas);
    }
   
    public boolean darBajaProfesor(String nombre){
        return profesteca.darBajaProfesor(nombre);
    }
    
   public void modificarAtributoProfesor(String nombre, String opcion, String nuevoAtributo){
        profesteca.modificarAtributoProfesor(nombre, opcion, nuevoAtributo);
   }
   
   public boolean existeProfesor(String nombre){
       return profesteca.existeProfesor(nombre);
   }
    
   public String consultarAsignaturasProfesor(String nombre){
       return profesteca.consultarAsignaturasProfesor(nombre);
   }
   
   public String[][] listadoProfesores(){
       return profesteca.listadoProfesores();
   }
   
   public void exportarBinario(){
       profesteca.exportarBinario();
   }
}
