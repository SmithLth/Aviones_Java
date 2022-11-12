import processing.core.*;

public class Nave {
    protected PVector pos;
    public Bala balas;

    public Nave(float posx, float posy, int orientacion) { // orientaciont = 1 es enemigo orientacion=-1 nave
        pos = new PVector(posx, posy);
        this.balas = new Bala(orientacion);
    }

    public void disparar() {
        balas.disparar(pos);
    }

    public void actualizacion() {
        balas.moverBala();
        //System.out.println(balas.balas.size());
    }

    public void mover(String direccion) {
        if (direccion == "d") {
            pos.x = pos.x + 1;
        } else if (direccion == "a") {
            pos.x = pos.x - 1;
        } else if (direccion == "w") {
            pos.y = pos.y - 1;
        } else if (direccion == "s") {
            pos.y = pos.y + 1;
        }
    }
}
