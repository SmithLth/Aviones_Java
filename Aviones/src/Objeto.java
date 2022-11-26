import processing.core.*;
import java.util.ArrayList;
public abstract class Objeto {
    public ArrayList<PVector> partes = new ArrayList<>();
    public PVector posicion = new PVector ();
    boolean eliminado;
    int [][]forma;
    int vida;
    public Objeto(int posx,int posy,int vida) { 
        posicion.x=posx;
        posicion.y=posy;
        eliminado=false;
        this.vida=vida;   
    }
    
    public void recibirImpacto (){
        vida--;
        if(vida==0){
            eliminado=true;
            posicion.x = 1;
            posicion.y = -1;
        }
    }

    public void crear() {
        partes.clear();
        for (int i = 0; i < forma.length ; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]==1) {
                    PVector nuevaParte = new PVector(j+posicion.x,i+posicion.y);
                    partes.add(nuevaParte);
                }
            }
        }
    }
    protected abstract void mover ();
    
}