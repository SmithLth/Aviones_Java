import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    static public int alto = 700 / 5;
    static public int ancho = 560 / 5;
    static File datos = new File("datos.txt");
    public static void crearArchivo() throws IOException{
        if (!datos.exists()) {
            datos.createNewFile();
        }
    }

    public static void asignarDatos(String nombre, int puntos) throws IOException {
        BufferedWriter escirbe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(datos,true)));
        escirbe.write(nombre+" "+puntos+"\n");
        escirbe.close();
    }

    public static ArrayList<String> mostrarDatos() throws IOException{
        Scanner entrada = new Scanner(new File("datos.txt"));
        BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
            String cadena;
            ArrayList<String> datos = new ArrayList<>();
            while ((cadena = br.readLine()) != null) {
                double puntos = entrada.nextDouble(); 
                datos.add(cadena);
            }
            return datos;
        
    }
}