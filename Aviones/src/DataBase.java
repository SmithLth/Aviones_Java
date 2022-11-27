import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class DataBase {
    static File archivo = new File("datosPuntajes.txt");

    public static void guardar(ArrayList<DatoJugador> datos) throws IOException {
        FileWriter escritorAlarchivo = new FileWriter(archivo);
        BufferedWriter escritor = new BufferedWriter(escritorAlarchivo);
        for (DatoJugador dato : datos) {
            escritor.write(dato.toString()+ "\n");
        }
        escritor.close();
    }

    public static ArrayList<DatoJugador> leer() throws IOException {
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        ArrayList<DatoJugador> listaPuntaje = new ArrayList<>();
        FileReader lectorDelarchivo = new FileReader(archivo);
        BufferedReader lector = new BufferedReader(lectorDelarchivo);
        String linea = lector.readLine();
        while (linea != null) {
            String[] cadena = linea.split("[$]");
            DatoJugador dato = new DatoJugador(cadena[0], Integer.parseInt(cadena[1]));
            listaPuntaje.add(dato);
            linea = lector.readLine();
        }
        lector.close();
        return listaPuntaje;
    }
}