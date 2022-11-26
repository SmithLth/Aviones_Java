import java.util.ArrayList;
import processing.core.*;
public  abstract class Nave extends Objeto{
    public ArrayList <Objeto> misiles = new ArrayList<>();
    public boolean a,b,d,i;
    public int [][] formaMisil;
    public Nave(int posx,int posy,int [][]forma,int vida) { 
        super(posx,posy,vida);
        this.forma = forma;
        this.formaMisil=Forma.getFormaMisil();
        crear();
    }
    
    public void mover (){
        if(!eliminado){
            partes.clear();
            if (d&&posicion.x<Mapa.ancho-forma[0].length) {
                posicion.x = posicion.x + 1;
            }
            if (i&&posicion.x>0) {
                posicion.x = posicion.x - 1;
            }
            if (a&&posicion.y>0) {
                posicion.y = posicion.y - 1;
            }
            if (b&&posicion.y<Mapa.alto-forma.length) {
                posicion.y = posicion.y + 1;
            }
        }
        crear();
    }

    public  abstract void disparar();
}