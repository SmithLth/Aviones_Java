import java.util.ArrayList;
import processing.core.*;
   

import javax.swing.JOptionPane;

public class Procesos extends PApplet {
    private int ancho = 560;
    private int alto = 700;
    public int bits = 5;

    private int estadoGame;
    
    Mapa mapa = new Mapa(bits,ancho,alto);
    
    PImage fondo, corazon, welcomescreen, bala, start,logo,gameOver;
    
    private int scroll =0;
    
    ArrayList< DatoJugador > tablaPuntajes = new ArrayList();
    @Override
    public void settings() {
        size(ancho, alto); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        background(0);
        fondo = loadImage("/img/fondo.jpg");
        logo = loadImage("/img/logo.png");
        corazon = loadImage("/img/corazon.png");
        bala = loadImage("/img/bala.png");
        start = loadImage("/img/start.png");
        welcomescreen = loadImage("/img/welcome.jpg");
        gameOver = loadImage("/img/gameOver.png");
        
        frameRate(110);
        estadoGame=0;
        try{
            tablaPuntajes = DataBase.leer();   }
        catch (java.io.IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public void draw() {
        controlGame();
        if(estadoGame==0){
            imageMode(CENTER);
            image(welcomescreen, ancho / 2, alto / 2);
            image(logo,ancho/2,alto/2-150);
            image(start,ancho/2,alto/2, 200,100);
        }else if (estadoGame==1){
            cargarFondoScroll ();
            cargarOjetos();
            cargarAvion();
            actualizar();
            controlGame();
        }else if (estadoGame == 2){
            noLoop();
            imageMode(CENTER);
            image(gameOver, width / 2, height / 2, 200, 100);  
            mensajeGameOver();
        }
    }
    
    public void cargarAvion(){
        image(corazon, mapa.jugador.posicion.x*bits, mapa.jugador.posicion.y*bits, 30, 30); 
        if(mapa.jugador.d){
            image(logo,mapa.jugador.posicion.x*bits, mapa.jugador.posicion.y*bits, 30, 30);
        }else if(mapa.jugador.i){
            image(start,mapa.jugador.posicion.x*bits, mapa.jugador.posicion.y*bits, 30, 30);    
        }
    }

    public void mensajeGameOver() {
        if(mapa.jugador.eliminado &&
            (tablaPuntajes.get(tablaPuntajes.size()-1).puntaje < Jugador.puntaje))
        {
            String name = JOptionPane.showInputDialog("tu nombre crack ;)");
            JOptionPane.showMessageDialog(null, "Nuevo record!!: " + Jugador.puntaje);
            tablaPuntajes.add(new DatoJugador(name,Jugador.puntaje));
            try{
                DataBase.guardar(tablaPuntajes);
            }catch (java.io.IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
    
    private void controlGame(){
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45 && mousePressed) {
            estadoGame=1;
        }
        if(mapa.jugador.eliminado){
            estadoGame=2;
        }
    }
    
    private void cargarFondoScroll (){
        imageMode(CORNER);
        image(fondo, 0, scroll);
        image(fondo, 0, scroll - alto);
        scroll += 5;
        if (scroll == alto){scroll = 0;}
    }
    
    private void cargarOjetos(){
        mapa.crearAsteroides();
        mapa.crearAtributos();
        mapa.crearEnemigos(Forma.getFormaEnemigo());
        dibujarObjeto(mapa.jugador.partes);
        dibujarMisiles();
        for (int i = 0; i < mapa.enemigos.size(); i++) {
            dibujarObjeto(mapa.enemigos.get(i).partes);
        }
        for (int i = 0; i < mapa.asteroides.size(); i++) {
            dibujarObjeto(mapa.asteroides.get(i).partes);
        }
        for (int i = 0; i < mapa.atributos.size(); i++) {
            dibujarObjeto(mapa.atributos.get(i).partes);
        }
    }
    
    private void actualizar(){
        mapa.actualizarAste();
        mapa.actualizarAtributos();
        mapa.actualizarEnemigos();
        mapa.actualizarJugador();
    }
    
    private void dibujarMisiles(){
        for (int i = 0; i < mapa.enemigos.size(); i++) {
            for (int j = 0; j < mapa.enemigos.get(i).misiles.size(); j++) {
                dibujarObjeto(mapa.enemigos.get(i).misiles.get(j).partes); 
            }
        }
        for (int i = 0; i < mapa.jugador.misiles.size(); i++) {
            dibujarObjeto(mapa.jugador.misiles.get(i).partes); 
        }  
    }
    
    private void dibujarObjeto(ArrayList<PVector> partes) {
        for (int i = 0; i < partes.size(); i++) {
            rect(partes.get(i).x*bits, partes.get(i).y*bits, bits, bits);
        } 
    }
    
    @Override
    public void keyPressed() {
        if (key == 'w' || keyCode == UP) {
            mapa.jugador.a=true;
        }
        if (key == 's' || keyCode == DOWN) {
            mapa.jugador.b=true;
        }
        if (key == 'a' || keyCode == LEFT) {
            mapa.jugador.i=true;
        }
        if (key == 'd' || keyCode == RIGHT) {
            mapa.jugador.d=true;
        }
        if (key == ' ') {
            mapa.jugador.disparar();
        }
        if (key == 'g') {
            mapa.jugador.disparar();
        }
        int k = keyCode;
        if (k == 'P'){
            if (looping){
                noLoop();
            }else{
                loop();
            }
        }
    }
    @Override
    public void keyReleased(){
        if (key == 'w' || keyCode == UP) {
            mapa.jugador.a=false;
        }
        if (key == 's' || keyCode == DOWN) {
            mapa.jugador.b=false;
        }
        if (key == 'a' || keyCode == LEFT) {
            mapa.jugador.i=false;
        }
        if (key == 'd' || keyCode == RIGHT) {
            mapa.jugador.d=false;
        }  
    }
}
