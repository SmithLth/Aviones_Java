public class Asteroide extends Objeto{
    int direccion=1;
    int delay=8;
    int contador=0;
    public Asteroide(int posx, int posy, int vida) {
        super(posx, posy, vida);
        if(posicion.x==1){
            direccion=1;
        }
        else {direccion=-1;}
        this.forma = Forma.formaAste;
        crear();
    }
    public void mover(){
        if(contador>=delay){
            partes.clear();
            int posxRandom = (int) (Math.random() * (3- 0) );
            posicion.y = posicion.y + posxRandom;
            posicion.x = posicion.x + direccion;
            contador=0;
        }
        contador++;
        crear();        
    }
    @Override
    public void recibirImpacto(){
        super.recibirImpacto();
        if(eliminado){
            Jugador.puntaje++;
            System.out.println(Jugador.puntaje);
        }
    }
}