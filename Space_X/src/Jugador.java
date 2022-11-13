public class Jugador extends Nave {
    public int balas = 20, vida = 10, puntos = 0;

    public Jugador(int posx, int posy) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, -1);
    }

    @Override
    public void forma() {
        ship.clear();
        int[][] forma = {
                { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 0, 0, 1, 0 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        };
        crear(forma);
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

    @Override
    public void moverBala(int velocidad) {
        missil.moverBala();
    }

    @Override
    public void disparar() {
        if (activo && balas > 0) {
            missil.disparar(getPico());
            balas--;
        }
    }

    public void recargar() {
        if (balas < 20) {
            balas++;
        }
    }

    public void choques(Enemigo enemigo) {
        for (int i = 0; i < enemigo.missil.missil.size(); i++) {
            for (int j = 0; j < missil.missil.size(); j++) {
                if (missil.bala && enemigo.missil.bala) {
                    if (missil.missil.get(j).equals(enemigo.missil.missil.get(i))) {
                        enemigo.missil.missil.remove(i);
                        missil.missil.remove(j);
                        puntos++;
                    }
                }
            }
        }

        for (int i = 0; i < enemigo.ship.size(); i++) {
            for (int j = 0; j < ship.size(); j++) {
                if (ship.get(j).equals(enemigo.ship.get(i))) {
                    enemigo.revivir();
                    puntos++;
                    vida--;
                }
            }
        }

        for (int i = 0; i < enemigo.ship.size(); i++) {
            for (int j = 0; j < missil.missil.size(); j++) {
                if (missil.missil.get(j).equals(enemigo.ship.get(i))) {
                    enemigo.revivir();
                    puntos++;
                    missil.missil.remove(j);
                }
            }
        }

        for (int i = 0; i < ship.size(); i++) {
            for (int j = 0; j < enemigo.missil.missil.size(); j++) {
                if (ship.get(i).equals(enemigo.missil.missil.get(j))) {
                    vida--;
                    enemigo.missil.missil.remove(j);
                }

            }
        }
    }
}