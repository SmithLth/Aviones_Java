import processing.core.*;
import java.util.ArrayList;
import ddf.minim.*;

public class Mapa {
    static public int ancho, alto;
    public int bits;
    AudioPlayer colision, mover;

    public ArrayList<Nave> enemigos;
    public Nave jugador;
    public ArrayList<Objeto> asteroides, atributos;
    public int deley = 1500;

    int maxCantEnemigos;
    int contador = 1000;
    int contadorAsteroide = 1200;
    int contadorAtributo = 1200;
    
    public Mapa(int bits, int anchoo, int altoo) {
        ancho = anchoo / bits;
        alto = altoo / bits;
        enemigos = new ArrayList<>();
        jugador = new Jugador(ancho / 2, alto - 9, Forma.getFormaMisil2(), 10);
        asteroides = new ArrayList<>();
        atributos = new ArrayList<>();
    }
    
    public void controlPuntaje(){
        if(Enemigo.velocidad>1){
            Enemigo.velocidad=5-((Jugador) jugador).puntaje/5; 
            maxCantEnemigos=((Jugador) jugador).puntaje/3;//4
        }
        
    }
    
    public void crearAtributos() {
        if (contadorAtributo > deley-700) {
            int posxRandom = (int) (Math.random() * ((ancho-10)- 0) + 1);
            int atributoRandom = (int) (Math.random() * (2- 0) + 1);
            atributos.add(new Atributo(posxRandom, 1,1,atributoRandom));
            contadorAtributo = 0;
        }
        contadorAtributo++;
    }
    
    public void actualizarAtributos(){
        for (int i = 0; i < atributos.size(); i++) {
            atributos.get(i).mover();
            ((Atributo) atributos.get(i)).temporizadorAtributo(jugador);
            colicionObjetos(atributos, jugador);
        }    
    }
    
    public void crearAsteroides() {
        if (contadorAsteroide > deley-700) {
            int posX=1;
            int posxRandom = (int) (Math.random() * (3-0) + 1);
            if(posxRandom != 1){
                posX=ancho- Forma.formaAste.length;  
            }
            int posyRandom = (int) (Math.random() * ((alto-50) - Forma.formaAste[0].length) + 1);
            asteroides.add(new Asteroide(posX, posyRandom,3));
            contadorAsteroide = 0;
        }
        contadorAsteroide++;
    }
    
    public void actualizarAste(){
        for (int i = 0; i < asteroides.size(); i++) {
            asteroides.get(i).mover();
            colicionObjetos(jugador.misiles, asteroides.get(i));
            colicionObjetos(asteroides, jugador); 
        }    
    }
    
    public void actualizarEnemigos() {
        colicionObjetos(convertir_a_Objeto(enemigos), jugador);
        for (int i = 0; i < enemigos.size(); i++) {
            enemigos.get(i).disparar();
            for (int j = 0; j < enemigos.get(i).misiles.size(); j++) {
                enemigos.get(i).misiles.get(j).mover();
            }
            colicionObjetos(enemigos.get(i).misiles, jugador); 
            colicionObjetos(jugador.misiles, enemigos.get(i)); 
            colicionObjetos(asteroides,enemigos.get(i)) ;
            enemigos.get(i).mover();
        }
    }

    public void actualizarJugador() {
        jugador.mover();
        for (int i = 0; i < jugador.misiles.size(); i++) {
            jugador.misiles.get(i).mover();
            if (!posicionValidaY(jugador.misiles.get(i))) {
                jugador.misiles.remove(i);
            }
        }
        controlPuntaje();
    }

    public void crearEnemigos(int[][] forma) {
        if (contador > deley) {
            if (enemigos.size() <= maxCantEnemigos) {
                int posxRandom = (int) (Math.random() * (ancho - forma[0].length) + 1);
                enemigos.add(new Enemigo(posxRandom, 1, forma, 1));
                contador = 0;
            }
        }
        contador++;
    }

    public void colicionObjetos(ArrayList<Objeto> objetos, Objeto nave) {
        for (int i = 0; i < objetos.size(); i++) {
            if (existeColicion(objetos.get(i).partes, nave.partes)) {
                if((objetos.get(i) instanceof Atributo)){
                    ((Atributo)objetos.get(i)).darAtributos(jugador);
                }else{
                    nave.recibirImpacto();
                    objetos.get(i).recibirImpacto();
                    if(nave instanceof Jugador){
                        System.out.println(nave.vida);
                    }else if(nave instanceof Enemigo){
                        ((Jugador) jugador).puntaje++;
                        
                        System.out.println(((Jugador) jugador).puntaje);
                    }else if(nave instanceof Asteroide && nave.vida == 0){
                        ((Jugador) jugador).puntaje++;

                        System.out.println(((Jugador) jugador).puntaje);
                    }
                }
            }
            if(!posicionValidaY(objetos.get(i))&&objetos.get(i)!=null){
                objetos.remove(i);
            }
        }
    }

    private boolean posicionValidaY(Objeto objeto) {
        boolean res = false;
        if (objeto.posicion.y <= alto - objeto.forma.length
                && objeto.posicion.y >= 0) {
            res = true;
        }
        return res;
    }

    private boolean existeColicion(ArrayList<PVector> partes1, ArrayList<PVector> partes2) {
        boolean existe = false;
        for (int j = 0; j < partes1.size(); j++) {
            for (int k = 0; k < partes2.size(); k++) {
                if (partes1.get(j).equals(partes2.get(k))) {
                    return true;
                }
            }
        }
        return existe;
    }
    
    private ArrayList<Objeto> convertir_a_Objeto(ArrayList<Nave> objetos){
        ArrayList<Objeto> enemigosCon = new ArrayList<>();
        for (int i = 0; i < objetos.size(); i++) {
            Objeto naveConver = (Objeto)objetos.get(i);
            enemigosCon.add(naveConver);
        }
        return enemigosCon;
    }
}