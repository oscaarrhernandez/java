package modelo;

import com.coti.tools.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

public class Profesteca implements Serializable{
    ArrayList<Profesor> listaProfesores = new ArrayList<>();
    private final String ficheroDatos = "PROFESORES";
    private final String ficheroBinario = "profesores.bin";
    private final String ficheroTexto = "profesores.txt";
    
    /**
    * Intenta importar los archivos .bin, en caso de que no lo consiga
    * intetará importar los .txt.
    * @throws java.io.IOException
    */
    public void importarDatos() throws IOException{  
        if(importarBinario()){
        }else
            importarTexto();
    }
        
    /**
    * 1º Ruta al fichero profesores.txt que se encuentra en la carpeta PROFESORES
    * 2º Si el archivo de la ruta existe:
    *      - Trata de crear un ArrayList que contenga todo el archivo .txt a través 
    *        de la función readAllLines de Rutas.java
    *      - For desde el pricipio hasta el final del array separandolo por #,
    *        y metiendo los datos en el factory de profesor
    *        Si profesor no está vacio se agrega a la lista de profesores,
    *        si la carga de profesor produce un error, no se agrega
    * Return: Si no ha habido problemas con la carga o el fichero retorna true.
     * @return 
     * @throws java.io.IOException
    */
    public boolean importarTexto() throws IOException{

        Path ruta = Rutas.pathToFileInFolderOnDesktop(ficheroDatos,ficheroTexto);
        if(ruta.toFile().exists()){
            try{
                ArrayList<String> lineas = (ArrayList<String>) readAllLines(ruta);
                for(String i : lineas){
                    String[] campos = i.split("#");
                    Profesor profesor = Profesor.FactoryProfesor(campos);
                    if(profesor != null){
                        listaProfesores.add(profesor);
                    }else{
                        System.out.printf("Error en la carga en la linea %s", i); //Esto puede estar bien o no, ya que es control de erorres.
                    }   
                }
            }catch( IOException exeption){
                System.out.printf("Error en fichero %s", ruta.toFile().getAbsolutePath());
                return false;
            }
        }else{
            System.out.printf("Error en el fichero %s,", ruta.toFile().getAbsolutePath());
            return false;
        }
        System.out.printf("Importados un total de %d profesores.\n", listaProfesores.size());
        return true;
    }
    
    /**
    * 1º Ruta al fichero profesores.bin que se encuentra en la carperta PROFESORES
    * 2º Tratar de leer el archivo a través del metodo fis-bis-ois
    * Return: en caso de que se lea y se guarde correctamente devuelve true, en
    * caso contrario devuelve false.
     * @return 
    */
    public boolean importarBinario(){
        
        Path ruta = Rutas.pathToFileInFolderOnDesktop(ficheroDatos,ficheroBinario); 
        try {
            FileInputStream fis = new FileInputStream(ruta.toFile()); 
            BufferedInputStream bis = new BufferedInputStream(fis); 
            ObjectInputStream ois = new ObjectInputStream(bis); 
            listaProfesores = (ArrayList<Profesor>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
    * Añade a la lista de profesores el nuevo profesor dado de alta.
     * @param nombre
     * @param edad
     * @param asignaturas
    */
    public void altaProfesor(String nombre, int edad, ArrayList<String> asignaturas){
        
        listaProfesores.add(new Profesor(nombre,edad,asignaturas));
    }
    
    /**
    * Busca el nombre del profesor en la lista.
     * @param nombre
     * @return 
    */
    public boolean existeProfesor(String nombre){
        
        for(Profesor profesor : listaProfesores ){
            if(nombre.equalsIgnoreCase(profesor.nombre))
                return true;
        }
        
        return false;
    }
    
    /**
     * Eliminar un profesor de la lista
     * 1º Comprobar si existe el profesor
     *    - Si existe guardar indice
     *    - Si no existe return false
     * 2º Eliminar a través de remove el profesor marcado por el indice guardado.
     * @param nombre
     * @return 
     */
    public boolean darBajaProfesor(String nombre){    
        int indiceABorrar=-1;
        if(existeProfesor(nombre))
        {
            for(int i = 0; i < listaProfesores.size(); i ++){
                if(nombre.equalsIgnoreCase(listaProfesores.get(i).nombre))
                    indiceABorrar = i;
            }
        }else{
            return false;
        }
        
        listaProfesores.remove(indiceABorrar);
        return true;    
    }
    
    /**
     * A traves de los parámetros recoges el nombre del profesor y la opcion de modificar
     * el nombre y la edad
     * Si quiere modificar el nombre realiza un for del arraylist buscando el nombre 
     * del profesor y setear el nombre con el nuevo atributo
     * Si quiere modificar la edad realiza un for del arraylist buscando el nombre
     * del profesor y setear la edad con el nuevo atributo parseado.
     * @param nombre
     * @param opcion
     * @param nuevoAtributo 
     */
    public void modificarAtributoProfesor(String nombre, String opcion, String nuevoAtributo){
        if(opcion.equals("n")){
            for(Profesor profesor : listaProfesores){
                if(profesor.getNombre().equalsIgnoreCase(nombre)){
                    profesor.setNombre(nuevoAtributo);
                }
            }
        }else{
            for(Profesor profesor : listaProfesores){
                if(profesor.getNombre().equalsIgnoreCase(nombre)){
                    profesor.setEdad(Integer.parseInt(nuevoAtributo));
                }
            } 
        }    
    }
    
    /**
     * Consultar asignaturas de un profesor
     * Buscar al profesor en la arraylist
     * Con un string vacio realizar:
     *      for de las asignaturas del profesor marcado
     *      metes en el string las asignaturas que hay en el array asignaturas del profesor
     * @param nombre
     * @return 
     */
    public String consultarAsignaturasProfesor(String nombre){
        
        String consulta="";
        for(Profesor profesor : listaProfesores){
            if(profesor.getNombre().equalsIgnoreCase(nombre)){
                for(String asignaturas : profesor.asignaturas){
                    consulta = consulta + asignaturas + "\n";
                }
            }
        }
        
        return consulta;
    }
    
    /**
     * Ordenar Profesores por nombre.
     */
    public void ordenarProfesores(){
        listaProfesores.sort(Comparator.comparing(Profesor::getNombre));
    }
    
    /**
     * Crea un String[][] del tamaño de la lista cargandola con cada profesor(String).
     * @return 
     */
    public String[][] arrayListToMatrix() {
        //ordenarProfesores();
        String [][] matrizProfesores = new String[listaProfesores.size()][];
        for (int i = 0; i < listaProfesores.size(); i++) {
            matrizProfesores[i] = listaProfesores.get(i).toString().split("#");
        }
        return matrizProfesores;
    }
    public String[][] listadoProfesores(){
       String[][] matrizProfesores = arrayListToMatrix();
       return matrizProfesores;
    }
    
    /**
    * 1º Path a la ruta del binario y crear archivo en esa ruta
    * 2º Escribir en el archivo a través de fos-bos-oos.
    */
    public void exportarBinario() {
        
        Path p = Rutas.pathToFileInFolderOnDesktop(ficheroDatos,ficheroBinario);
        File f = p.toFile();

        try {
            FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(listaProfesores);
            oos.close();
        } catch (IOException e) {
        }
    }
}
