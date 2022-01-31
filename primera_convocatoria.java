/**
  EXAMEN PRIMERA CONVOCATORIA DE PROGRAMACIÓN 3
  
  Considérese la clase Persona, que está definida en la forma siguiente:
  
  class Persona implements Serializable{
    String nombre, ap1, ap2, dni;
    int edad;
    float talla;
    double peso;
    
    public Persona(String nombre, String ap1, String ap2, String dni, int edad, float talla, double peso){
      this.nombre = nombre;
      this.ap1 = ap1;
      this.ap2 = ap2;
      this.dni = dni;
      this.edad = edad;
      this.talla = talla;
      this.peso = peso;
    }
  ····
  // Se suponen declarados todos los getters y setters.
  // Se prohíbe declararlos otra vez.
  }
  Considérese también la clase Modelo, que está definida en la forma siguiente:
  
  public class Model {
    String nameOfTextFile;
    String nameOfBinaryFile;
    List<Persona> personas = new ArrayList<>();
    Map<String,Persona> mapa = new HashMaps<>();
    ····
  }
  El programa hace uso de la arquitectura MVC, así que existen también las clases View(vista) y Controller(controlador).
  
  
  PRIMERA PARTE: IMPORTACIÓN
    A. Añadir a la clase Persona un método de factoría que admita como argumento una primera cadena(de formato delimitado) y otra cadena(el delimitador).
    B. Añadir a la clase Model un método que reciba como argumento la ruta de un archivo que contiene una cadena de formato delimitado en cada línea. 
       Cada línea de la tabla contiene la información de una Persona. El método debe añadir a la colección personas de la clase Model un ejemplar de Persona
       por cada línea del archivo, siempre que la información contenida en esa línea sea correcta.
  
  SEGUNDA PARTE: CLASIFICACIÓN
    A. Añadir a la clase Model un método llamado ordenarPorApellidosNombreYdni() que ordene la lista persona por orden de primer apellido, segundo, nombre y dni.
    B. Añadir a la clase Model un método llamado ordenarPorEdadPesoTallaYdni() que ordene la lista personas por orden de edad, peso, talla y dni.
    C. Añadir a la clase Model un método llamado ordenarPorEdadApellidosYpeso() que ordene la lista personas por edad, primer apellido, segundo y peso.
  Nota: Todos los métodos de clasificación deben usar el operador :: y no se consideran validas las soluciones que hagan uso de if(). 
        Esto es, deben utilizare los métodos estáticos Comparator.
  
  TERCERA PARTE: COLECCIONES
  Una vez importadas las personas, esto es, suponiendo que el atributo personas de la clase Model ya contiene información, se pide
    A. Crear un método llamado prepararMapa() en la clase Model que añada todas las parejas clave/valor (esto es, dni/persona) a la variable mapa.
    B. Añadir un método llamado datosDePersonaComoStringDelimitado() a la clase Model, que admita como primer argumento una cadena (un DNI), 
       y como segundo argumento otra cadena (un delimitador). El método producirá como resultado una cadena de formato delimitado que contendrá los valores
       de todos los atributos de la Persona cuyo DNI se proporciona como primer argumento. La búsqueda debe hacer uso del mapa definido en la clase Model.
  Nota: El orden de los atributos que aparecen en el resultado del método es el indicado en la declaración de Persona.
  
  CUARTA PARTE: ARCHIVOS
    A. Crear un método llamado exportarPersonasConFormatoDelimitado() en la clase Controlador que exporte el contenido de la variable personas de la clase Model 
       a un archivo de texto con formato delimitado. El método tiene un único argmento que es el delimitador. El archivo se encuentra en el Escritorio, 
       y su nombre se encuentra en la clase Model. La tabla está ordenada por primer apellido, segundo, nombre y dni.
       Con este fin, hay que añadir a la clase Persona un método llamado estadoComoArrayDeCadenas() que no recibe argumentos y devuelve todos los atributos 
       de ese ejemplar de Persona en forma de un array de cadenas.

Indicaciones adicionales
- Es recomendable reutilizar métodos ya creados en otras partes del examen.
- Todos los identificadores deben seguir las normas habituales de un programa en Java.
- Se valora positivamente indicar los import necesarios en cada pregunta.
- Se debe usar la biblioteca de Coti cuando sea posible, facilitando así el código.
*/

