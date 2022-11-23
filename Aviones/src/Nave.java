import java.util.ArrayList;
public abstract class Nave extends Objeto{
    public ArrayList <Objeto> misiles = new ArrayList<>();
    public boolean a,b,d,i;
    protected int velocidad;

    public Nave(int posx,int posy,int [][]forma,int vida) { 
        super(posx,posy,vida);
        this.forma = forma;
        crear();
    }
    
    public void mover (){
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
        crear();
    }

    public void revivir(){
        int posxRandom = (int) (Math.random() * (Mapa.ancho - forma[0].length) + 1);
        posicion.x = posxRandom;
        posicion.y = -20;
        eliminado = false;
        vida++;
    }

    public abstract void disparar(int [][] forma);
}