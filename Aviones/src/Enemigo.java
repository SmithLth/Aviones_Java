public class Enemigo extends Nave {
    public int timeMisil = 0, timeMov = 0;
    public static int velocidad = 20;

    public Enemigo(int posx, int posy, int[][] forma, int vida) {
        super(posx, posy, forma, vida);
        d = b = true;
        if (posx < Mapa.ancho / 2) {
            i = true;
            d = false;
        }
    }

    @Override
    public void recibirImpacto() {
        super.recibirImpacto();
        revivir();
    }

    public void revivir() {
        posicion.x = (int) (Math.random() * (Mapa.ancho - forma[0].length) + 1);
        posicion.y = -20;
        vida++;
    }

    public void disparar() {
        if (timeMisil > 300) {
            Misil nuevoMisil = new Misil((int) posicion.x + forma[0].length / 2 + 1,
                    (int) posicion.y + forma.length, formaMisil, 1);
            misiles.add(nuevoMisil);
            timeMisil = 0;
        }
        timeMisil++;
    }

    public void mover() {
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
                posicion.y = -20;
            }
            timeMov = 0;
            super.mover();
        }
        timeMov++;
    }
}