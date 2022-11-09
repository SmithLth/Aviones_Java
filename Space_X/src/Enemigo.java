public class Enemigo extends Cohete {
    private String direccion;
    private int contador = 0;
    private int disparar = 0;
    private int velocidadBala = 0;

    public Enemigo(int posx, int posy) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, 1);
        missil = new Misil(1);
        if (posx > Mapa.ancho / 2) {
            direccion = "d";
        } else {
            direccion = "a";
        }
    }

    @Override
    public void forma() {
        crear(2, 0, 1);
        crear(1, 0, 2);
        crear(0, 1, 5);
    }

    public void autoMovimiento(int velocidad) {
        moverBala(velocidad);
        if (contador > velocidad) {
            mover("s");
            moverIzqDer();
            mover(direccion);
            contador = 0;
        } else {
            contador++;
        }
        if (disparar > 400) {
            disparar();
            disparar = 0;
        } else {
            disparar++;
        }
    }

    private void moverIzqDer() {
        for (int i = 0; i < ship.size(); i++) {
            if (ship.get(i).x == 0) {
                direccion = "d";
            } else if (ship.get(i).x == ancho - 1) {
                direccion = "a";
            }
        }
    }

    @Override
    public void moverBala(int velocidad) {
        if (velocidadBala > (int) 3*velocidad/10) {
            missil.moverBala();
            velocidadBala = 0;
        } else {
            velocidadBala++;
        }
    }

    @Override
    public void mover(String direccion) {
        if (ship.size() > 0) {
            for (int i = 0; i < ship.size(); i++) {
                if (direccion == "d") {
                    ship.get(i).set(ship.get(i).x + 1, ship.get(i).y);
                } else if (direccion == "a") {
                    ship.get(i).set(ship.get(i).x - 1, ship.get(i).y);
                } else if (direccion == "w") {
                    ship.get(i).set(ship.get(i).x, ship.get(i).y - 1);
                } else if (direccion == "s") {
                    ship.get(i).set(ship.get(i).x, ship.get(i).y + 1);
                }
                if (ship.get(i).y >= Mapa.alto) {
                    ship.remove(i);
                    activo = false;
                }
            }
            mapaCargar(ship);
        }
    }
}
