import java.util.ArrayList;
import processing.core.*;
public class Jugador extends Nave {
    private int contador;
    public static int puntaje;
    
    public Jugador (int posx,int posy,int[][] forma,int vida){
        super(posx,posy,forma,vida);
        puntaje =0;
    }    
    
    public void disparar(){
        int posx = (forma[0].length-formaMisil[0].length+2*(int)posicion.x)/2;
        if(misiles.size()<3){
            misiles.add(new Misil (posx, (int)posicion.y-formaMisil.length,formaMisil,-1));
        }
    }
        
    @Override
    public void mover(){
        if (contador>0 ){
            super.mover();
            contador=0;
        }
        contador++;
    }
    
}