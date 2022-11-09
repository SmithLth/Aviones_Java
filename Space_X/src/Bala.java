import processing.core.*;
import java.util.ArrayList;

public class Bala {
    public ArrayList<PVector> balas = new ArrayList<>();
    private int orientacion;
    protected boolean disparo = false;

    public Bala(int orientacion) {
        this.orientacion = orientacion;
    }

    public void disparar(PVector posccion) {
        PVector nuevaBala = new PVector(posccion.x, posccion.y + orientacion);
        balas.add(nuevaBala);
        disparo = true;
    }

    public void moverBala() {
        if (disparo) {
            for (int i = 0; i < balas.size(); i++) {
                balas.get(i).set(balas.get(i).x, balas.get(i).y + orientacion);
                if (balas.get(i).y < 0 || balas.get(i).y >= Procesos.alto/5) {
                    balas.remove(i);
                }
            }
            if (balas.isEmpty()) {
                disparo = false;
            }
        }
    }
}
