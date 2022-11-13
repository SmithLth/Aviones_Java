import processing.core.*;
import java.util.ArrayList;

public class Bala {
    public ArrayList<PVector> balas;
    private int orientacion;
    private boolean disparo = false;
    

    public Bala(int orientacion) {
        balas = new ArrayList<>();
        this.orientacion = orientacion;
    }

    public void disparar(PVector posccion) {
        PVector nuevaBala = new PVector(posccion.x+5, posccion.y+orientacion);
        balas.add(nuevaBala);
        disparo = true;
    }

    public void moverBala() {
        if (disparo) {
            for (int i = 0; i < balas.size(); i++) {
                PVector actualizado = new PVector(balas.get(i).x, balas.get(i).y+orientacion);
                balas.set(i, actualizado);
                if (balas.get(i).y < 0 || balas.get(i).y >= Mapa.alto) {
                    balas.remove(i);
                }
            }
            if (balas.isEmpty()) {
                disparo = false;
            }
        }
    }

    public ArrayList<PVector> getMisil(){
        return balas;
    }
}