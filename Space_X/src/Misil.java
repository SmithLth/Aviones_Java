import processing.core.*;
import java.util.ArrayList;

public class Misil extends Mapa {
    public ArrayList<PVector> missil = new ArrayList<>();
    private int cons;
    protected boolean bala = false;

    public Misil(int cons) {
        super();
        this.cons = cons;
    }

    public void disparar(PVector puntero) {
        PVector A = new PVector(puntero.x, puntero.y);
        missil.add(A);
        bala = true;
    }

    public void moverBala() {
        if (bala) {
            for (int i = 0; i < missil.size(); i++) {
                missil.get(i).set(missil.get(i).x, missil.get(i).y + cons);
                if (missil.get(i).y < 0 || missil.get(i).y >= Mapa.alto) {
                    missil.remove(i);
                }
            }
            if (missil.size() == 0) {
                bala = false;
            }
        }
    }

    public ArrayList<PVector> getMisil(){
        return missil;
    }
}