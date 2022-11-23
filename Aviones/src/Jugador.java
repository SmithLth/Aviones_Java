public class Jugador extends Nave {
    private int contador;
    public Jugador (int posx,int posy,int[][] forma,int vida){
        super(posx,posy,forma,vida);
        crear();
        
    }    
    
    @Override
    public void disparar(int [][] forma){
        misiles.add(new Misil ((int)(posicion.x+forma[0].length/2+1),(int)posicion.y,forma,-1));
    }

    @Override
    public void mover(){
        if (contador>velocidad) {
            super.mover();
            contador=0;
        }
        contador++;
    }
}