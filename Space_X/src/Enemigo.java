import processing.core.*;

public class Enemigo extends Nave {

    public Enemigo() {
        super(0, 0, 1);
        revivir();
    }

    public void forma() {
        nave.clear();
        int[][] forma = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 1, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
        };
        llenarForma(forma);
    }

    public void revivir(){
        int x = (int) (Math.random()*(Mapa.ancho-5) + 5); 
        int y = 0;
        PVector nuevaPos = new PVector(x,y);
        pos = nuevaPos;
    }

    public boolean verificarMovimiento() {
        if (pos.x > 5 && pos.x < Mapa.ancho-5) {
            return true;
        }
        if (pos.y > Mapa.alto) {
            revivir();
        }
        return false;
    }
}
