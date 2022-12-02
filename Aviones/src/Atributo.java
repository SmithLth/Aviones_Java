public class Atributo extends Objeto{
    public int tipoAtributo ;
    public int contador=0;
    int contador2=0;
    public Atributo (int posx, int posy,int vida,int atributo){
        super(posx,posy,0);
        this.forma=Forma.formaEscudo;
        tipoAtributo=atributo;
        crear();  
    }
    
    @Override
    public void mover(){
        if(contador>=50){
            partes.clear();
            posicion.y = posicion.y + 1;
            contador=0;
        }
        contador++;
        crear();         
    }

    public void darAtributos(Nave jugador){
        posicion.y = -30;
        if(tipoAtributo==0){
            jugador.vida= jugador.vida+10;   
        }   
        if(tipoAtributo==1){
            jugador.formaMisil=Forma.formaMisil3;
        } 
        if(tipoAtributo==2){
            jugador.formaMisil=Forma.formaAste;
        } 
    }
    
    public void temporizadorAtributo(Nave jugador){
        if(jugador.formaMisil != Forma.getFormaMisil()){
            if(contador2>=1000){
                jugador.formaMisil=Forma.getFormaMisil(); 
                contador2=0;
            }
            contador2++;
        }
    }
}