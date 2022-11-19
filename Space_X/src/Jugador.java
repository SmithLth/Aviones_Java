import java.util.ArrayList;

import processing.core.PVector;

public class Jugador extends Nave {
    public int balas = 20, vida = 10, puntos = 0;
    public boolean mover = false;

    public Jugador(int posx, int posy, int forma[][], int formaM[][]) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, -1, forma, formaM);
    }

    @Override
    public boolean verificarMovimiento(String direccion) {
        if (direccion == "d" && (pos.x + 9) < Mapa.ancho) {
            return true;
        } else if (direccion == "a" && pos.x > 0) {
            return true;
        } else if (direccion == "w" && pos.y > 0) {
            return true;
        } else if (direccion == "s" && (pos.y + 9) < Mapa.alto) {
            return true;
        }
        return false;
    }

    public void movimiento() {
        if (mover) mover(direccion);
    }

    @Override
    public void moverBala(int velocidad) {
        missil.moverBala();
    }

    @Override
    public void disparar() {
        if (balas > 0) {
            missil.disparar(ship.get((forma.length) / 2));
            balas--;
        }
    }

    public void recargar() {
        if (balas < 20) {
            balas++;
        }
    }

    public void choques(ArrayList<Enemigo> enemigos) {
        for (int i = 0; i < enemigos.size(); i++) {
            int res = choques(enemigos.get(i));
            if (res > 0) {
                i = 0;
            }
        }
    }

    private int choques(Enemigo enemigo) {
        int res = 0;
        if (chocan(missil.missil, enemigo.missil.missil)) {
            res = res + 1;
            System.out.println("Misil con misil");
            puntos++;
        }
        if (chocan(ship, enemigo.ship)) {
            enemigo.revivir();
            System.out.println("Enemigo con enemigo");
            res = res + 1;
            puntos++;
            vida--;
        }
        if (chocan(enemigo.ship, missil.missil)) {
            res = res + 1;
            enemigo.revivir();
            System.out.println("enemigo con misil");
            puntos++;
        }
        if (chocan(ship, enemigo.missil.missil)) {
            res = res + 1;
            System.out.println("yo con misil");
            vida--;
        }
        return res;
    }

    private boolean chocan(ArrayList<PVector> objeto1, ArrayList<PVector> objeto2) {
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