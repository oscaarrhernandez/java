/**
*     Importar .txt | .bin | .col
*/
public void importarTexto() throws IOException{
  Path ruta = Rutas.pathToFileInFolderOnDesktop(namefolder,namefiletxt);
  File file = ruta.toFile();
  if(file.exists()){
    try{
      ArrayList<String> lineas = (ArrayList<String>) readAllLines(ruta);
      for(String i : lineas){
        String[] campos = i.split(delim);
        Objeto objeto = Objeto.factory(campos);
        if(objeto!=null){
          listaObjetos.add(objeto);
        }else{
          System.err.printf("Error.%n");
        }
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }else{
    System.err.printf("Error.%n");
  }
}

public void importarBin(){
  Path ruta =Rutas.pathToFileInFolderOnDesktop(namefolder,namefilebin);
  File file = ruta.toFile();
  try{
    FileInputStream fis = new FileInputStream(file);
    BufferedInputStream bis = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(bis);
    listaObjetos = (ArrayList<String>) ois.readObject();
    ois.close();
  }catch(IOException | ClassNotFoundException ex){
    ex.printStackTrace();
  }
}

public void importarCol(){

}
/**
*     Exportar .txt | .bin | .col
*/
public void exportarTexto(){
  Path ruta = Rutas.pathToFileInFolderOnDesktop(namefolder,namefiletxt);
  File file = ruta.toFile();
  String[][] data = new String[listaObjetos.size()][];
  for(int i=0;i<listaObjetos.size();i++){
    data[i] = listaObjetos.get(i).toString.split(delim);
  }
  try{
    exportToDisk(data,file,delim);
  }catch(IOException e){
    e.printStackTrace();
  }
}

public void exortarBinario(){
  Path ruta = Rutas.pathToFileInFolderOnDesktop(namefolder,namefilebin);
  File file = ruta.toFile();
  try{
    FileOutputStream fos = new FileOutputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(listaObjetos);
    oos.close();
  }catch(IOException e){
    e.printStackTrace();
  }
}

public void exportarCol() throws Exception{
  boolean aux = true;
  File file = new File(Rutas.pathToFolderOnDesktop(folder).toFile() + separador +  "directores" + ".col");
  Path p = f.toPath();
  List<String> obj = new ArrayList<>();
  for(Objetos o : listaobjetos){
    obj.add(o.exportStateAsCol(aux));
  }
  Files.write(p,obj,Charset.forName("UTF-8"));
}
//en objeto
public String exportStateAsCol(boolean aux) throws ParseException{
  String tituto = new String();
  if(aux == true){
    for(int i=0; i < this.caracteristicas.size();i++){
      titulo += this.caracteristicas.get(i).getCaracteristica() + "\t"; 
    }
  }else{
    return String.format("%-30s%-20s%-20s%-70s",this.Atr1,this.Atr2,this.Atr3,this.Atr4);
  }
  return String.format("%-30s%-20s%-20s%-70s%-80s",this.Atr1,this.Atr2,this.Atr3,this.Atr4,caracteristica);
}

/**
*     Mapas
*/
public void prepararMapa(){
  for(Objeto obj : listaObjetos){
    mapa.put(obj.getAtr2,obj);
  }
}

public String datosComoStringDelimMapa(String Atr2, String delim){
  Objeto obj = mapa.get(Atr2);
  String token = obj.getAtr1)+delim+obj.getAtr2()+delim+obj.getAtr3)+delim;
  return token;
}

/**
*     Modificar o Consultar
*/
public void modificar(String Atr1, String newAtr1){
  for(Objeto obj : listaObjetos){
    if(obj.getAtr1().equalsIgnoreCase(Atr1)){
      obj.setAtr1(newAtr1);
    }
  }
}

public String consultar(String Atr1){
  String consulta = "";
  for(Objeto obj : listaObjetos){
    if(obj.getAtr1().equalsIgnoreCase(Atr1)){
      consulta=obj.getAtr1()+" "+obj.getAtr2()+" "+obj.getAtr3()+" ";
      for(String caracteristicas :  objeto.caracteristicas){
        consulta=consulta+carecteristicas+"\n";
      }
    }
  }
  return consulta;
}

/**
*     Ordenar
*/
public void ordenar(){
  listaObjetos.sort(Comparator.comparing(Objeto::getAtr1).thenComparing(Objeto::getAtr2).thenComparing(Objeto::getAtr3));
}

/**
*     ToString y Mostrar por pantalla
*/
public void toString(){
	return atr1+"#"+atr2+"#"+atr3+"#"+atr4+"#";
}
public void toString(){
	String data = "";
	data = o.getAtr1()+"#"+o.getAtr2()+"#"+o.Atr3+"#"+o.Atr4+"#";
	for(String i : Atr5){
		data = data +" "+i;
	}
}

public String[] estadoComoArrayDeCadenas(){
	String[] linea = null;
	linea[0] = this.nombre;
	linea[1] = this.ap1;
    linea[2] = this.ap2;
	linea[3] = String.valueOf(this.edad);
	linea[4] = String.valueOf(this.talla);
	linea[5] = String.valueOf(this.peso);
	return linea;
}

public void ArrayListToMatrix(){
	String[][] data = new String[listaObjetos.size()][];
	for(int i=0;i<listaObjetos.size();i++){
		data[i] = listaObjetos.get(i).toString().split("#");
	}
	return data;
  printToScreen3(data);
}


/**
*     Factory
*/
public static Objeto factory(String linea, String delim){
	String[] campos = linea.split(delim);
	try{
		int tok1 = Integer.parseInt(campos[4]);
		float tok2 = Float.parseFloat(campos[5]);
		double tok3 = Double.parseDouble(campos[6]);
		return new Objeto(campos[0],campos[1],campos[2],campos[3],tok1,tok2,tok3);
	}catch(NumberFormatException e){
		e.printStackTrace();
		return null;
	}
}
public static Objeto factory(String[] campos){
	try{
		int tok1 = Integer.parseInt(campos[4]);
		float tok2 = Float.parseFloat(campos[5]);
		double tok3 = Double.parseDouble(campos[6]);
		return new Objeto(campos[0],campos[1],campos[2],campos[3],tok1,tok2,tok3);
	}catch(NumberFormatException e){
		e.printStackTrace();
		return null;
	}	
}



