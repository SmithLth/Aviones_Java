import java.util.ArrayList;

import processing.core.PVector;

public abstract class Cohete extends Nave{
    protected Misil missil;
    public boolean activo = true;

    public Cohete(int posx,int posy, int cons) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, cons);
        missil = new Misil(cons);
        forma();
    }

    abstract public void forma();

    abstract public void mover(String direccion);

    protected ArrayList<PVector> getBala(){
        return missil.getMisil();
    }

    protected void eliminarNave(){
        ship.clear();
        mapaCargar(ship);
        activo=false;
    }

    protected void eliminarMisil(PVector misil){
        missil.getMisil().remove(misil);
        missil.mapaCargar(missil.getMisil());
    }

    protected void disparar(){
        if (activo) {
            missil.disparar(getPico());
        }
    }

    abstract public void moverBala(int velocidad);

    protected boolean[][] mapaNave(){
        return mapa;
    }

    protected boolean[][] mapaMisil(){
        return missil.mapa;
    }
}
