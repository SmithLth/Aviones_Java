public class Jugador extends Nave {
    public int balas = 20, vida = 10, puntos = 0;
    public boolean mover = false;

    public Jugador(int posx, int posy, int forma[][], int formaM[][]) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, -1, forma, formaM);
    }

    @Override
    public boolean verificarMovimiento(String direccion) {
        if (direccion == "d" && (pos.x + 9) < Datos.ancho) {
            return true;
        } else if (direccion == "a" && pos.x > 0) {
            return true;
        } else if (direccion == "w" && pos.y > 0) {
            return true;
        } else if (direccion == "s" && (pos.y + 9) < Datos.alto) {
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
}