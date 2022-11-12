package RECURSOS;
import java.util.ArrayList;

import processing.core.PVector;

public class Colisiones{
    public int vida = 10;
    private int control = 1;
    public int puntos = 0;
    private int velocidad = 0;
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    Mapa mapa = new Mapa();
    public Jugador nave = new Jugador(Mapa.ancho / 2, Mapa.alto - 10);

    public void procesos() {
        crearEnemigos();
        eliminarEnemigos();
        if (velocidad == 200) {
            nave.recargar();
            control++;
            velocidad = 0;
        } else {
            velocidad++;
        }
    }

    public void nuevoEnemigo() {
        int numero = (int) (Math.random() * (Mapa.ancho - 2) + 2);
        Enemigo enemigo = new Enemigo(numero, 0);
        if (verificar(mapa.mapa, enemigo.mapa) && verificar(enemigo.ship)) {
            enemigos.add(enemigo);
            for (int i = 0; i < enemigos.size(); i++) {
                mapa.Cargar(enemigos.get(i).ship);
            }
        } else {
            nuevoEnemigo();
        }
    }

    public boolean verificar(ArrayList<PVector> nuevo) {
        for (int i = 0; i < nuevo.size(); i++) {
            if (nuevo.get(i).x < 0 || nuevo.get(i).x >= Mapa.ancho) {
                return false;
            }
        }
        return true;
    }

    public boolean verificar(boolean[][] antiguo, boolean[][] nuevo) {
        for (int i = 0; i < Mapa.ancho; i++) {
            for (int j = 0; j < Mapa.alto; j++) {
                if (antiguo[i][j] == false && nuevo[i][j] == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public void crearEnemigos() {
        while (enemigos.size() < control) {
            nuevoEnemigo();
            if (control > Math.random() * (10) + 1) {
                control = 0;
            }
        }
    }

    public PVector posiconImpacto(boolean[][] antiguo, boolean[][] nuevo) {
        PVector posicion = new PVector();
        for (int i = 0; i < Mapa.ancho; i++) {
            for (int j = 0; j < Mapa.alto; j++) {
                if (antiguo[i][j] == false && nuevo[i][j] == false) {
                    posicion = new PVector(i, j);
                }
            }
        }
        return posicion;
    }

    public void eliminarEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            if (enemigos.get(i).ship.size() == 0 && !enemigos.get(i).missil.bala) {
                enemigos.remove(i);
            }
        }
        for (int i = 0; i < enemigos.size(); i++) {
            if (!verificar(enemigos.get(i).mapaNave(), nave.mapaMisil())) {
                PVector posicion = posiconImpacto(enemigos.get(i).mapaNave(), nave.mapaMisil());
                enemigos.get(i).eliminarNave();
                nave.eliminarMisil(posicion);
                puntos++;
            }
        }
        for (int i = 0; i < enemigos.size(); i++) {
            if (!verificar(enemigos.get(i).mapaNave(), nave.mapaNave())) {
                enemigos.get(i).eliminarNave();
                vida = vida - 2;
                if (vida < 1) {
                    nave.eliminarNave();
                }
            }
        }
        for (int i = 0; i < enemigos.size(); i++) {
            if (!verificar(enemigos.get(i).mapaMisil(), nave.mapaNave())) {
                PVector posicion = posiconImpacto(enemigos.get(i).mapaMisil(), nave.mapaNave());
                enemigos.get(i).eliminarMisil(posicion);
                vida = vida - 1;
                ;
                if (vida < 1) {
                    nave.eliminarNave();
                }
            }
        }

        for (int i = 0; i < enemigos.size(); i++) {
            if (!verificar(enemigos.get(i).mapaMisil(), nave.mapaMisil())) {
                PVector posicion = posiconImpacto(enemigos.get(i).mapaMisil(), nave.mapaNave());
                enemigos.get(i).eliminarMisil(posicion);
                nave.eliminarMisil(posicion);
            }
        }
    }
}
