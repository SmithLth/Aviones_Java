public class Misil extends Objeto{
    public int direccion ;
    public Misil (int posx, int posy, int[][] forma, int direccion){
        super(posx,posy,0);
        this.forma=forma;
        vida=1;
        this.direccion = direccion;
        crear();  
    }

    public void mover(){
        partes.clear();
        posicion.y = posicion.y + direccion;
        crear();        
    }
}