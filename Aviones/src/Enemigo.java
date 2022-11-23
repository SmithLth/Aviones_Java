public class Enemigo extends Nave {
    public int delay = 1, timeMisil = 0, timeMov = 0;

    public Enemigo(int posx, int posy, int[][] forma, int vida) {
        super(posx, posy, forma, vida);
        d = b = true;
        if (posx < Mapa.ancho / 2) {
            i = true;
            d = false;
        }

    }

    @Override
    public void disparar(int[][] forma) {
        if (!eliminado) {
            if (timeMisil > delay * 50) {
                Misil nuevoMisil = new Misil((int) posicion.x + forma[0].length / 2 + 1,
                        (int) posicion.y + forma.length, forma, 1);
                misiles.add(nuevoMisil);
                timeMisil = 0;
            }
            timeMisil++;
        }
    }

    public void mover() {
        if (!eliminado) {
            if ((timeMov > velocidad)) {
                partes.clear();
                if (posicion.x <= 0) {
                    d = true;
                    i = false;
                } else if (posicion.x >= Mapa.ancho - forma[0].length) {
                    i = true;
                    d = false;
                }
                if (posicion.y >= Mapa.alto - 9) {
                   revivir();
                }
                timeMov = 0;
                super.mover();
            }
            timeMov++;
        }
        crear();
    }
}