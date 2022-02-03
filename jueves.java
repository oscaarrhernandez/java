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


/**
*	ARCHIVOS DELIMITADOS
*/
public void importar(){
	String[][] data;
	Path p = Rutas.pathToFileInFolderOnDesktop("Folder","archivo.txt");
	File f = p.toFile();
	try{
		data = importFromDisk(f,"#");
	}catch(IOException e){
		e.printStackTrace();
	}
	Objeto obj = new Objeto();
	for(String[] x : data){
		obj = o.factory(x);
		if(obj!=null){
			listaObjetos.add(obj);
		}
	}
}
public void importar(){
	Path p = Rutas.pathToFileInFolderOnDesktop("Folder","archivo.txt");
	if(p.toFile().exists()){
		try{
			ArrayList<String> lineas = (ArrayList<String>) readAllLines(p);
			String delim = "#";
			for(String i : lineas){
				Objeto obj = o.factory(i,delim);
				if(obj!=null){
					listaObjetos.add(obj);
				}else{
					System.err.printf("Error.%n");
				}
			}
		}catch(IOException e){
			System.err.printf("Error.%n");
		}
	}
}

public void exportar(){
	Path p = Rutas.pathToFileInFolderOnDesktop("Folder","archivo.txt");
	File f = p.toFile();
	String[][] data = new String[listaObjetos.size()+1][];
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
*	ARCHIVOS BINARIOS
*/
public void cargar(){
	Path p = Rutas.pathToFileInFolderOnDesktop("Folder","archivo.bin");
	File f = p.toFile();
	try{
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		listaObjetos = (ArrayList<Objeto>) ois.readObject();
		ois.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}
public void guardar(){
	Path p = Rutas.pathToFileInFolderOnDesktop("Folder","archivo.bin");
	File f = p.toFile();
	try{
		FileOutputStream fos = new FileOutputStream(f);
		BuferredOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(listaObjetos);
		oos.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}


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

public String[][] ArrayListToMatrix(){
	String[][] data = new String[listaObjetos.size()][];
	for(int i=0;i<listaObjetos.size();i++){
		data[i] = listaObjetos.get(i).toString().split("#");
	}
	return data;
}

/**
*	ORDENAR
*/
public void ordenarPorAtr1Atr2YAtr3(){
	listaObjetos.sort(Comparator.comparing(Objeto::getAtr1).thenComparing(Objeto::getAtr2).thenComparing(Objeto::getAtr3));
}

/**
*	MAPAS
*/	
public void prepararMapa(){
	for(Objeto o : listaObjetos){
		mapa.put(o.getAtr3(),o);
	}
}
public String datosComoStringDelimitadoMapa(String Atr1, String delim){
	Objeto o = mapa.get(Atr1);
	String token;
	token = o.getAtr1()+delim+o.getAtr2()+delim+o.getAtr3()+delim;
	return token;
}

public void modificar(String Atr, String newAtr){
	for(Objeto o : listaObjetos){
		if(o.getAtr1().equalsIgnoreCase(Atr)){
			o.setAtr1(newAtr);
		}
	}
}
