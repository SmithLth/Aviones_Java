import processing.core.*;
import java.util.ArrayList;

public class Mapa {
    static public int ancho, alto;
    public int bits;

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
        jugador = new Jugador(ancho / 2, alto - 9, Forma.formaJugador, 7);
        asteroides = new ArrayList<>();
        atributos = new ArrayList<>();
    }
    
    public void controlPuntaje(){
        if(Enemigo.velocidad>1){
            Enemigo.velocidad=10-((Jugador) jugador).puntaje/3; 
            maxCantEnemigos=((Jugador) jugador).puntaje/5;//4
        }
        
    }
    
    public void crearAtributos() {
        if (contadorAtributo > deley-40) {
            int posxRandom = (int) (Math.random() * ((ancho-10)- 0) + 1);
            int atributoRandom = (int) (Math.random() * (3- 0) + 1);
            atributos.add(new Atributo(posxRandom, 1,1,atributoRandom));
            contadorAtributo = 0;
        }
        contadorAtributo++;
    }
    
    public void actualizarAtributos(){
        for (int i = 0; i < atributos.size(); i++) {
            atributos.get(i).mover();
             Atributo.temporizadorAtributo(jugador);
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
                System.out.println("nuevo enemigo");
                int posxRandom = (int) (Math.random() * (ancho - forma[0].length) + 1);
                enemigos.add(new Enemigo(posxRandom, -10, forma, 1));
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
                    if(((Atributo)objetos.get(i)).tipoAtributo==1){
                        if (!Procesos.soundAtributoVida.isPlaying()) {
                            Procesos.soundAtributoVida.setGain(+10);// bajar volumen
                            Procesos.soundAtributoVida.play(1);
                        }    
                    }else{
                        if (!Procesos.soundAtributo.isPlaying()) {
                            Procesos.soundAtributo.setGain(+50);// bajar volumen
                            Procesos.soundAtributo.play(1);
                        }
                    }
                    
                }else{
                    objetos.get(i).recibirImpacto();
                    nave.recibirImpacto();

                    if (!Procesos.soundExplosion.isPlaying()) {
                        Procesos.soundExplosion.setGain(+50);// bajar volumen
                        Procesos.soundExplosion.play(1);
                    }
                    if(nave instanceof Jugador){
                        System.out.println(nave.vida);
                    }else if(nave instanceof Enemigo){
                        ((Jugador) jugador).puntaje++;
                    }else if(nave instanceof Asteroide && nave.vida == 0){
                        ((Jugador) jugador).puntaje++;
                    }
                }
            }
            if(!posicionValidaY(objetos.get(i))&&objetos.get(i)!=null){
                objetos.remove(i);
            }
        }
    }

    boolean posicionValidaY(Objeto objeto) {
        boolean res = false;
        if (objeto.posicion.y <= alto - objeto.forma.length
                && objeto.posicion.y >= -25) {
            res = true;
        }
        return res;
    }

    boolean existeColicion(ArrayList<PVector> partes1, ArrayList<PVector> partes2) {
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