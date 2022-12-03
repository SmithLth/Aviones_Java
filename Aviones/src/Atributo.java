public class Atributo extends Objeto{
    public int tipoAtributo ;
    public int contador=0;
    public static int contador2=0;

    public Atributo (int posx, int posy,int vida,int atributo){
        super(posx,posy,0);
        this.forma=Forma.formaMisil3;
        tipoAtributo=atributo;
        crear();  
    }
    
    @Override
    public void mover(){
        if(contador>=5){
            partes.clear();
            posicion.y = posicion.y + 1;
            contador=0;
        }
        contador++;
        crear();         
    }

    public void darAtributos(Nave jugador){
        posicion.y = -30;
        if(tipoAtributo==1){
            jugador.vida= jugador.vida+2;   
        }   
        if(tipoAtributo==2){
            jugador.formaMisil=Forma.formaMisil2;
        } 
        if(tipoAtributo==3){
            jugador.formaMisil=Forma.formaMisil3;
        } 
    }
    
    public static void temporizadorAtributo(Nave jugador){
        if(jugador.formaMisil != Forma.getFormaMisil()){
            if(contador2>900){
                jugador.formaMisil=Forma.getFormaMisil(); 
                contador2=0;
            }
            contador2++;
        }

    }
}