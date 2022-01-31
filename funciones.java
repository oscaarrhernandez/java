/**
    FACTORY
 */
public static Objeto factory(String[] data){
    int atr1,atr2;
    float atr3;

    if(data.length() != 5){
        return null;
    }else{
        try{
            atr1 = Integer.parseInt(data[0]);
            atr2 = Integer.parseInt(data[1]);
            atr3 = Float.parseFloat(data[3]);
        }catch(NumberFormatException e){
            return null;
        }
        return new Objeto(atr1,atr2,data[2],atr3,data[4]);
    }
}

/**
    ORDENAR
 */

public void ordenarObjetos(){
    //Ordenar por un parámetro
    Collections.sort(listaObjetos, Comparator.comparing(Objeto::getAtributo1));
    //Ordenar por mas de 1 parámetro (2 en este caso)
    Collections.sort(listaObjetos, Comparator.comparing(Objeto::getAtributo1).thenComparing(Objeto::getAtributo2));
}

/**
    DAR DE ALTA
 */
public void altaObjeto(String[] data){
    Objeto objeto = Objeto.factory(data);
    if(objeto != null){
        listaObjetos.add(objeto);
    }
}

/**
    DAR DE BAJA
 */
public void bajaObjeto(String eliminar){
    int indiceABorrar = -1;
    int existe=0;
    for(Objeto objeto : listaObjetos){
        if(eliminar.equalsIgnoreCase(objeto.eliminar)){
            existe=1;
            break;
        }
    }
    if(existe==1){
        for(int i=1;i<listaObjetos.size();i++){
            if(eliminar.equalsIgnoreCase(listaObjetos.get(i).eliminar)){
                indiceABorrar=i;
            }
        }
        listaObjetos.remove(indiceABorrar);
    }else{
        System.err.printf("No pudo borrarse");
    }
}

/**
    MODIFICAR ATRIBUTOS
 */
public void modificar(String Atributo, String newAtributo){
    for(Objeto objeto : listaObjetos){
        if(objeto.getAtributo1().equalsIgnoreCase(Atributo)){
            objeto.setAtributo1(newAtributo);
        }
    }
}

/**
    CONSULTAR OBJETOS
 */
public String consultar(String Atributo){
    String consulta="";
    for(Objeto objeto : listaObjetos){
        if(objeto.getAtributo1().equalsIgnoreCase(Atributo)){
            for(String aux : objeto.auxiliar){
                consulta = consulta + aux + "\n";
            }
        }
    }
}

/**
    ARCHIVOS DELIMITADOS IMPORTAR
 */
public void importar(){
    String[][] data;
    Path p = Rutas.pathToFileInFolderOnDesktop("FOLDER","archivo.txt");
    File f = p.toFile();
    try{
        data = importFromDisk(f,"#");
    }catch(IOException e){
        e.printStackTrace();
    }
    Objeto objeto;
    for(String[] x : data){
        objeto = Objeto.factory(x);
        if(objeto != null){
            listaObjetos.add(objeto);
        }
    }
}

/**
    ARCHIVOS DELIMITADOS EXPORTAR
 */
public void exportar(){
    Path p = Rutas.pathToFileInFolderOnDesktop("FOLDER","archivo.txt");
    File f = p.toFile();
    String[][] data = new String[listaObjetos.size()][];
    for(int i=0;i<listaObjetos.size();i++){
        data[i]=listaObjetos.get(i).toString().split("#");
    }
    try{
        exportToDisk(data,f,"#");
    }catch(IOException e){
        e.printStackTrace();
    }
}

/**
    ARCHIVOS BINARIOS CARGAR
 */
public void cargar(){
    Path p = Rutas.pathToFileInFolderOnDesktop("FOLDER","archivo.bin");
    File f = p.toFile();
    try{
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        listaObjetos = (ArrayList<Objeto>) ois.readObject();
        ois.close();
    }catch(IOException e ){
        e.printStackTrace();
    }
}

/**
    ARCHIVOS BINARIOS GUARDAR
 */
public void guardar(){
    Path p = Rutas.pathToFileInFolderOnDesktop("FOLDER","archivo.bin");
    File f = p.toFile();
    try{
        FileOutputStream fos = new FileOutputStream(f);
        BufferedOutputStream bos = new BufferedInputStream(fos);
        ObjectOutputStream oos = new ObjectInputStream(bos);
        oos.writeObject(listaObjetos);
        oos.close();
    } catch(IOException e ){
        e.printStackTrace();
    }
}

/**
    TOSTRING
 */
public void toString(){
    return atr1 + "#" + atr2 + "#" + atr3 + "#";
}
public void toString(){
    String data = "";
    data = getAtributo1() + "#" + getAtributo2() + "#";
    for(String aux : auxiliar){//auxiliar es un arraylist<string> por ejemplo profesores, asignaturas 
        data = data + " " + aux;
    }
    return data;
}

/**
    ARRAYLIST DE OBJETOS A MATRIZ DE STRING
 */
public String[][] ArrayListToMatrix(){
    String[][] data = new String[listaObjetos.size()][];
    for(int i=0;i<listaObjetos.size();i++){
        data[i]=listaObjetos.get(i).toString().split("#");
    }
    return data;
}
// MOSTRAR POR PANTALLA
public void mostrarObjetos(){
    String[][] data = ArrayListToMatrix();
    printToScreen3(data);
}
