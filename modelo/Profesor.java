package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Profesor implements Serializable{
    String nombre;
    int edad;
    ArrayList<String> asignaturas = new ArrayList<>();

    /**
     * Contructor Profesor con ArrayList
     * @param nombre
     * @param edad
     * @param asignaturas 
     */
    public Profesor(String nombre, int edad, ArrayList<String> asignaturas){
        this.nombre = nombre;
        this.edad = edad;
        this.asignaturas = asignaturas;
    }
    
    /**
     * Contructor Profesor String Asignaturas - 
     * @param nombre
     * @param edad
     * @param asignaturas 
     */
    public Profesor(String nombre,int edad, String asignaturas) {
        this.nombre = nombre;
        this.edad = edad;
        String[] arrayAuxiliar = asignaturas.split("-");
        this.asignaturas.addAll(Arrays.asList(arrayAuxiliar));
    }
    
    public static Profesor FactoryProfesor(String[] campos){
        String campo_0, campo_2;
        int campo_1;
        if(campos.length != 3){
            return null;
        }else{
            try{
                campo_0 = "";
                if(!"".equals(campos[0]))
                    campo_0 = campos[0];
                campo_1 = 0;
                if(!"".equals(campos[1]))
                    campo_1 = Integer.parseInt(campos[1]);
                campo_2 = "";
                if(!"".equals(campos[2]))
                    campo_2 = campos[2];
                return new Profesor(campo_0,campo_1,campo_2);
            }catch(NumberFormatException e){
                return null;
            }
        }
    }

    /**
     * Convertir cualquier objeto a String
     * Retorna: nombre#edad#
     * @return 
     */
    @Override
    public String toString(){
        String data= "";
        data = getNombre() + "#" + getEdad()+ "#";
        for(String asignature : asignaturas)
        {
            data = data + " " + asignature;
        }
        return data;
    }
    
    /**
     * GETTER SETTER NOMBRE Y EDAD.
     * @return 
    */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
