import processing.core.*;

public class Enemigo extends Nave {
    private String direccion = "a";
    private int contador = 0;
    private int disparar = 0;
    private int velocidadBala = 0;

    public Enemigo() { // const = 1 es enemigo cons=-1 nave
        super(0, 0, 1);
        revivir();
        missil = new Misil(1);
        moverIzqDer();
        if (pos.x > Mapa.ancho / 2) {
            direccion = "d";
        }
    }

    @Override
    public void forma() {
        ship.clear();
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
        crear(forma);
    }

    public void autoMovimiento(int velocidad) {
        moverBala(velocidad);
        if (contador > velocidad) {
            mover("s");
            mover(moverIzqDer());
            contador = 0;
        }
        if (disparar > 400) {
            disparar();
            disparar = 0;
        }
        contador++;
        disparar++;
    }

    private String moverIzqDer() {
        for (int i = 0; i < ship.size(); i++) {
            if (ship.get(i).x == 0) {
                direccion = "d";
            } else if (ship.get(i).x == ancho - 1) {
                direccion = "a";
            }
        }
        return direccion;
    }

    @Override
    public void moverBala(int velocidad) {
        if (velocidadBala > (int) 3 * velocidad / 10) {
            missil.moverBala();
            velocidadBala = 0;
        }
        velocidadBala++;
    }

    @Override
    public boolean verificarMovimiento(String direccion) {
        if (direccion == "s") {
            if (pos.y > Mapa.alto + 10) {
                revivir();
            }
        }
        return true;
    }

    public void revivir() {
        pos = new PVector((int) (Math.random() * (Mapa.ancho - 9) + 1), -20);
        forma();
    }
}