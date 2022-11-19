import processing.core.*;
import java.util.ArrayList;

public class Misil {
    public ArrayList<PVector> missil = new ArrayList<>(), misiles = new ArrayList<>();
    private int cons, forma[][];
    protected boolean bala = false;

    public Misil(int cons, int[][] forma) {
        this.forma=forma;
        this.cons = cons;
    }

    public void disparar(PVector puntero) {
        PVector A = new PVector(puntero.x, puntero.y);
        missil.add(A);
        bala = true;
        crear();
    }

    public void crear() {
        misiles.clear();
        for (int l = 0; l < missil.size(); l++) {
            for (int i = 0; i < forma.length; i++) {
                for (int j = 0; j < forma.length; j++) {
                    if (forma[j][i] == 1) {
                        PVector nuevo = new PVector(i + missil.get(l).x, j + missil.get(l).y);
                        misiles.add(nuevo);
                    }
                }
            }
        }
    }

    public void moverBala() {
        if (bala) {
            for (int i = 0; i < missil.size(); i++) {
                missil.get(i).set(missil.get(i).x, missil.get(i).y + cons);
                if (missil.get(i).y < 0 || missil.get(i).y >= Datos.alto) {
                    missil.remove(i);
                }
            }
            if (missil.size() == 0) bala = false;
        }
        crear();
    }

    public ArrayList<PVector> getMisil(){
        return missil;
    }
}