package RECURSOS;
import java.util.ArrayList;
import processing.core.PVector;

public class Mapa {
    static public int alto = 700 / 5;
    static public int ancho = 560 / 5;
    protected boolean mapa[][] = new boolean[ancho][alto];

    public Mapa() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                mapa[i][j] = true;
            }
        }
    }

    private void actulizar() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                mapa[i][j] = true;
            }
        }
    }

    protected void mapaCargar(ArrayList<PVector> particulas) {
        actulizar();
        for (int i = 0; i < particulas.size(); i++) {
            int ejex = (int) particulas.get(i).x;
            int ejey = (int) particulas.get(i).y;
            mapa[ejex][ejey] = false;
        }
    }

    protected void Cargar(ArrayList<PVector> particulas) {
        for (int i = 0; i < particulas.size(); i++) {
            int ejex = (int) particulas.get(i).x;
            int ejey = (int) particulas.get(i).y;
            mapa[ejex][ejey] = false;
        }
    }
}