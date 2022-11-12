import processing.core.*;
import java.util.ArrayList;

public class Bala {
    private ArrayList<PVector> balas = new ArrayList<>();
    private int orientacion;
    private boolean disparo = false;

    public Bala(int orientacion) {
        this.orientacion = orientacion;
    }

    public void disparar(PVector posccion) {
        PVector nuevaBala = new PVector(posccion.x, posccion.y+orientacion);
        balas.add(nuevaBala);
        disparo = true;
        System.out.println("disparoooo " + balas.size());
        System.out.println(posccion.x + " " + posccion.y);
    }

    public void moverBala() {
        System.out.println("moveeer");
        if (disparo) {
            for (int i = 0; i < balas.size(); i++) {
                PVector actualizado = new PVector(balas.get(i).x, balas.get(i).y+orientacion);
                balas.set(i, actualizado);
                if (balas.get(i).y < 0 || balas.get(i).y >= Procesos.alto) {
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
