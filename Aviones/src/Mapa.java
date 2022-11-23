import processing.core.*;
import java.util.ArrayList;

public class Mapa{
    int puntos = 0;
    static public int ancho, alto;
    public int bits;
    public ArrayList<Nave> enemigos;
    public Nave jugador;
    
    public int deley =200;

    int maxCantidad=3;
    int contador =0;

    public Mapa (int bits,int anchoo, int altoo){
        ancho=anchoo/bits;
        alto=altoo/bits;
        enemigos = new ArrayList<>();
        jugador = new Jugador (ancho/2, alto-9, Forma.getFormaJugador(),9);
    }
    
    public void actualizarEnemigos(){
        for (int i = 0; i < enemigos.size(); i++) {
            //mueve enemigos
            enemigos.get(i).mover();
            //hace disparar a los enemigos
            enemigos.get(i).disparar(Forma.getFormaMisil2());
            //hace mover los misiles
            for (int j = 0; j < enemigos.get(i).misiles.size(); j++) { 
                enemigos.get(i).misiles.get(j).mover();
            }
            //verifica Coliciones//////
            colicionObjetos(enemigos.get(i).misiles,jugador);
            colicionObjetos(jugador.misiles, enemigos.get(i));
            colicionEntreNaves(enemigos, jugador);
            if(enemigos.get(i).eliminado){
                enemigos.get(i).posicion.x=-10;
                enemigos.get(i).posicion.y=-10;
                if(enemigos.get(i).misiles.isEmpty()){
                    enemigos.remove(i); 
                }   
            }
        }
    }
    
    public void actualizarJugador(){
        jugador.mover();  
        //mueve misiles jugador
        for (int i = 0; i < jugador.misiles.size(); i++) {
            jugador.misiles.get(i).mover();
            if(!posicionValidaY(jugador.misiles.get(i))){
                jugador.misiles.remove(i);       
            } 
        }
    }

    
    public void crearEnemigos(int[][]forma){
        if(contador==deley)   { 
            if(enemigos.size()<maxCantidad){
                int posxRandom = (int) (Math.random() * (ancho-forma[0].length)+ 1);
                enemigos.add(new Enemigo(posxRandom,1,forma,1));
                contador=0;
            }
        }
        contador++;
    }
 
    public void colicionObjetos (ArrayList<Objeto> objetos, Nave nave){
        for(int i = 0; i<objetos.size();i++){ 
            if(existeColicion (objetos.get(i).partes, nave.partes)){
                objetos.get(i).posicion.x=10;
                objetos.get(i).posicion.y=10;
                nave.recibirImpacto();
            } 
            if(!objetos.isEmpty()){
                if(!posicionValidaY(objetos.get(i))){
                    objetos.remove(i);
                } 
            }
        } 
    }
    
    public void colicionEntreNaves (ArrayList<Nave> naves, Nave nave){
        for(int i = 0; i<naves.size();i++){
            if(existeColicion (naves.get(i).partes, nave.partes)){
                naves.get(i).recibirImpacto();
                nave.recibirImpacto();
            }    
        } 
    }

    public boolean posicionValidaY(Objeto objeto){
        boolean res = false;
        if(objeto.posicion.y<=alto-objeto.forma.length
            && objeto.posicion.y>=0){
            res = true;        
        }
        return res;
    }

    public boolean existeColicion (ArrayList<PVector> partes1,ArrayList<PVector> partes2){
        boolean existe = false;
        for(int j = 0; j<partes1.size();j++){
            for(int k = 0; k <partes2.size();k++){
                if (posicionIgual(partes1.get(j), partes2.get(k))){
                    return true;
                }
            }
        }
        return existe;
    }
    
    public boolean posicionIgual(PVector pos1, PVector pos2){
        boolean res=false;
        if(pos1.x==pos2.x&&pos1.y==pos2.y){
            res = true;
        }
        return res;
    }
    
}