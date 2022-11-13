import processing.core.*;
import java.util.ArrayList;

public abstract class Nave extends Mapa {
    protected Misil missil;
    public boolean activo = true;
    public ArrayList<PVector> ship = new ArrayList<>();
    protected PVector pos;
    protected int cons, pico;

    public Nave(int posx, int posy, int cons) { // const = 1 es enemigo cons=-1 nave
        super();
        this.pos = new PVector(posx, posy);
        this.cons = cons;
        missil = new Misil(cons);
        forma();
    }

    protected PVector getPico() {
        return ship.get(6);
    }

    public void crear(int[][] forma) {
        ship.clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (forma[j][i] == 1) {
                    PVector nuevo = new PVector(i + pos.x, j + pos.y);
                    ship.add(nuevo);
                }
            }
        }
    }

    public void mover(String direccion) {
        if (verificarMovimiento(direccion)) {
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

    protected void disparar() {
        if (activo) {
            missil.disparar(getPico());
        }
    }

    public abstract boolean verificarMovimiento(String direccion);

    public abstract void forma();

    public abstract void moverBala(int velocidad);
}