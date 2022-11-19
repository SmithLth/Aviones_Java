import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PVector;

public class Datos {
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

    static public void choques(ArrayList<Enemigo> enemigos, Jugador nave) {
        for (int i = 0; i < enemigos.size(); i++) {
            int res = choques(enemigos.get(i), nave);
            if (res > 0) {
                i = 0;
            }
        }
    }

    static private int choques(Enemigo enemigo, Jugador nave) {
        int res = 0;
        if (chocan(nave.missil.missil, enemigo.missil.missil)) {
            res = res + 1;
            System.out.println("Misil con misil");
            nave.puntos++;
        }
        if (chocan(nave.ship, enemigo.ship)) {
            enemigo.revivir();
            System.out.println("Enemigo con enemigo");
            res = res + 1;
            nave.puntos++;
            nave.vida--;
        }
        if (chocan(enemigo.ship, nave.missil.missil)) {
            res = res + 1;
            enemigo.revivir();
            System.out.println("enemigo con misil");
            nave.puntos++;
        }
        if (chocan(nave.ship, enemigo.missil.missil)) {
            res = res + 1;
            System.out.println("yo con misil");
            nave.vida--;
        }
        return res;
    }

    static private boolean chocan(ArrayList<PVector> objeto1, ArrayList<PVector> objeto2) {
        for (int i = 0; i < objeto1.size(); i++) {
            for (int j = 0; j < objeto2.size(); j++) {
                if (objeto1.get(i).equals(objeto2.get(j))) {
                    objeto2.remove(j);
                    return true;
                }
            }
        }
        return false;
    }
}
