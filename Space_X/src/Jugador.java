import processing.core.*;

public class Jugador extends Nave{
    private int vida;

    public Jugador() {
        super(Mapa.ancho / 2, Mapa.alto / 2, -1);
        vida = 10;
    }

    public void forma() {
        nave.clear();
        int[][] forma = {
                { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };
        llenarForma(forma);
    }

    public void choques(Enemigo enemigo) {
        for (int i = 0; i < enemigo.balas.balas.size(); i++) {
            for (int j = 0; j < balas.balas.size(); j++) {
                if (balas.balas.get(i).equals(enemigo.balas.balas.get(j))) {
                    enemigo.balas.balas.remove(j);
                    balas.balas.remove(i);
                }
            }
        }
        
        for (int i = 0; i < enemigo.nave.size(); i++) {
            for (int j = 0; j < nave.size(); j++) {
                if (nave.get(i).equals(enemigo.nave.get(j))) {
                    enemigo.revivir();
                    vida--;
                }
            }
        }

        for (int i = 0; i < enemigo.nave.size(); i++) {
            for (int j = 0; j < balas.balas.size(); j++) {
                if (balas.balas.get(j).equals(enemigo.nave.get(i))) {
                    enemigo.revivir();
                }
            }
        }

        for (int i = 0; i < enemigo.balas.balas.size(); i++) {
            for (int j = 0; j < nave.size(); j++) {
                if (nave.get(i).equals(enemigo.balas.balas.get(j))) {
                    vida--;
                    enemigo.balas.balas.remove(j);
                }
            }
        }
    }

    public boolean verificarMovimiento() {
        if (pos.x > 5 && pos.x < Mapa.ancho-5 && pos.y > 5 && pos.x < Mapa.alto-5) {
            return true;
        }
        return false;
    }
}
