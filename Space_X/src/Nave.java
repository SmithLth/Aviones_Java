import java.util.ArrayList;
import processing.core.*;

public abstract class Nave {
    protected PVector pos;
    protected ArrayList<PVector> nave = new ArrayList<>();
    protected Bala balas;

    public Nave(float posx, float posy, int orientacion) {
        balas = new Bala(orientacion);
        pos = new PVector(posx, posy);
        forma();
    }

    public void disparar() {
        balas.disparar(pos);
    }

    public void actualizacion() {
        balas.moverBala();
    }

    public void mover(String direccion) {
        if (verificarMovimiento()) {
            if (direccion == "d") {
                pos.x = pos.x + 1;
            } else if (direccion == "a") {
                pos.x = pos.x - 1;
            } else if (direccion == "w") {
                pos.y = pos.y - 1;
            } else if (direccion == "s") {
                pos.y = pos.y + 1;
            }
            forma();
        }
    }

    public abstract boolean verificarMovimiento();

    public void llenarForma(int [][] forma) {
        nave.clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (forma[i][j]==1) {
                    PVector nuevo = new PVector(i+pos.x,j+pos.y);
                    nave.add(nuevo);
                }
            }
        }
    }

    public abstract void forma();

}
