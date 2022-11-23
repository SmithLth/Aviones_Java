import processing.core.*;
import java.util.ArrayList;
public abstract class Objeto {
    ArrayList<PVector> partes = new ArrayList<>();
    PVector posicion = new PVector ();

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
            System.out.println("muerto");
        }
        System.out.println("impacto");
        
    }

    public void crear() {
        for (int i = 0; i < forma.length ; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[j][i]==1) {
                    PVector nuevaParte = new PVector(i+posicion.x,j+posicion.y);
                    partes.add(nuevaParte);
                }
            }
        }
    }

    protected abstract void mover ();
    
}