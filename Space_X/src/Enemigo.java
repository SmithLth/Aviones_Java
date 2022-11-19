import processing.core.*;

public class Enemigo extends Nave {
    public int contador = 0, disparar = 0, velocidadBala = 0;

    public Enemigo(int matriz[][],int matrizM[][]) { // const = 1 es enemigo cons=-1 nave
        super(0, 0, 1,matriz,matrizM);
        revivir();
        missil = new Misil(1,Forma.formaMisil2);
        if (pos.x > Datos.ancho / 2) {
            direccion = "d";
        }
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
            if (pos.y > Datos.alto + 10) {
                revivir();
            }
        }
        return true;
    }

    public void revivir() {
        pos = new PVector((int) (Math.random() * (Datos.ancho - 9) + 1), ((int) Math.random() * (-50) -20));
        crear(forma);
    }
}