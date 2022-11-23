import java.util.ArrayList;
import processing.core.*;

public class Procesos extends PApplet {
    private int ancho = 560;
    private int alto = 700;
    public int bits = 5;
    
    Mapa mapa = new Mapa(bits,ancho,alto);
    
    private boolean game = false;
    PImage fondo, corazon, welcomescreen, bala, start,logo;
    private int x =0;
    public Nave jugador = mapa.jugador;
    
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
        frameRate(60);
    }

    @Override
    public void draw() {
        
        if(!game){
            controlStart();
            imageMode(CENTER);
            image(welcomescreen, ancho / 2, alto / 2);
            image(logo,ancho/2,alto/2-150);
            image(start,ancho/2,alto/2, 200,100);
        }else{
            cargarFondoScroll ();
            cargarOjetos();
            actualizar();
 
        }
    }

    private void controlStart() {
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45 && mousePressed) {
            game = true;
        }
    }  
    private void cargarFondoScroll (){
        imageMode(CORNER);
        image(fondo, 0, x );
        image(fondo, 0, x - alto );
        x += 5;
        if (x == alto){x = 0;}
    }
    
    private void cargarOjetos(){
        mapa.crearEnemigos(Forma.getFormaEnemigo());
        for (int i = 0; i < mapa.enemigos.size(); i++) {
            dibujarObjeto(mapa.enemigos.get(i).partes);
        }
        dibujarObjeto(mapa.jugador.partes);
        dibujarMisiles();
    }
    
    private void actualizar(){
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
            jugador.a=true;
        }
        if (key == 's' || keyCode == DOWN) {
            jugador.b=true;
        }
        if (key == 'a' || keyCode == LEFT) {
            jugador.i=true;
        }
        if (key == 'd' || keyCode == RIGHT) {
            jugador.d=true;
        }
        if (key == ' ') {
            jugador.disparar(Forma.getFormaMisil2());
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
            jugador.a=false;
        }
        if (key == 's' || keyCode == DOWN) {
            jugador.b=false;
        }
        if (key == 'a' || keyCode == LEFT) {
            jugador.i=false;
        }
        if (key == 'd' || keyCode == RIGHT) {
            jugador.d=false;
        }  
    }
}
