import java.util.ArrayList;
import processing.core.*;
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
    public void mover(){
        if(contador>=50){
            partes.clear();
            posicion.y = posicion.y + 1;
            contador=0;
        }
        if(Mapa.jugador.formaMisil != Forma.getFormaMisil()){
            if(contador2>=1000){
                Mapa.jugador.formaMisil=Forma.getFormaMisil(); 
                contador2=0;
            }
            contador2++;
        }
        contador++;
        crear();         
    }

    public void darAtributos(){
        posicion.x = 1;
        posicion.y = -1;
        if(tipoAtributo==0){
            Mapa.jugador.vida= Mapa.jugador.vida+10;   
        }   
        if(tipoAtributo==1){
            Mapa.jugador.formaMisil=Forma.formaMisil3;
        } 
        // if(tipoAtributo==2){
            // Mapa.jugador.formaMisil=Forma.getFormaMisil();
        // } 
        if(tipoAtributo==2){
            Mapa.jugador.formaMisil=Forma.formaAste;
        } 
    }
    
    
}