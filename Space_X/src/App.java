import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet {
    public static void main(String[] args) {
        PApplet.main(new String[] { App.class.getName() });
    }

    private int bits = 5, x = 0, game = 0;
    PImage fondo, corazon, welcomescreen, bala, start, logo, gameOver;
    Colisiones todo = new Colisiones();

    @Override
    public void settings() {
        size(Mapa.ancho * 5, Mapa.alto * 5); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        fondo = loadImageIO("/img/fondo.jpg");
        logo = loadImage("/img/logo.png");
        corazon = loadImage("/img/corazon.png");
        bala = loadImage("/img/bala.png");
        start = loadImage("/img/start.png");
        gameOver = loadImage("/img/gameOver.png");
        welcomescreen = loadImage("/img/welcome.jpg");
        frameRate(height / bits);
    }

    @Override
    public void draw() {
        controlGame();
        if (game == 0 ) {
            controlStart();
            imageMode(CENTER);
            image(welcomescreen, width / 2, height / 2);
            image(logo, width / 2, height / 2 - 150);
            image(start, width / 2, height / 2, 200, 100);
        } else if(game == 1){
            imageMode(CORNER);
            image(fondo, 0, x);
            image(fondo, 0, x - fondo.width);
            x += 2;
            if (x == 560)
                x = 0;
            todo();
            vidas();
            balas();
            textSize(15);
            text("Score :" + todo.nave.puntos, 5, 15);
        }else if(game == 2){
            imageMode(CENTER);
            image(gameOver, width / 2, height / 2, 200, 100);
        }else if(game == 3){

        }else if(game == 4){

        }
    }

    private void vidas() {
        for (int i = 1; i <= todo.nave.vida; i++) {
            image(corazon, (25) * i, height - 25, 20, 20);
        }
    }

    private void balas() {
        for (int i = 1; i <= todo.nave.balas; i++) {
            image(bala, width / 2 + (10) * i, height - 25, 20, 20);
        }
    }

    public void todo() {
        todo.procesos();
        mostrar(todo.nave);
        todo.nave.moverBala(0);
        for (int i = 0; i < todo.enemigos.size(); i++) {
            mostrar(todo.enemigos.get(i));
            todo.enemigos.get(i).autoMovimiento(10 - (int) todo.nave.puntos / 10);// velocidad de los aviones
        }
    }

    public void controlStart() {
        if (mouseX >= width / 2 - 95 && mouseX <= width / 2 + 95 && mouseY >= height / 2 - 45
                && mouseY <= height / 2 + 45
                && mousePressed) {
            game = 1;
            todo = new Colisiones();
        }
    }

    public void controlGame(){
        if(todo.nave.vida == 0){
            game = 2;
        }
    }

    private void mostrar(Nave objeto) {
        fill(200, 200, 200);
        drawNaves(objeto.ship);
        drawNaves(objeto.missil.getMisil());
    }

    private void drawNaves(ArrayList<PVector> objeto) {
        for (int i = 0; i < objeto.size(); i++) {
            rect(objeto.get(i).x * bits, objeto.get(i).y * bits, bits, bits);
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'w' || keyCode == UP) {
            todo.nave.mover("w");
        }
        if (key == 's' || keyCode == DOWN) {
            todo.nave.mover("s");
        }
        if (key == 'a' || keyCode == LEFT) {
            todo.nave.mover("a");
        }
        if (key == 'd' || keyCode == RIGHT) {
            todo.nave.mover("d");
        }
        if (key == ' ') {
            todo.nave.disparar();
        }
        final int k = keyCode;

        if (k == 'P'){
            if (looping){
                noLoop();
            }else{
                loop();
            }
        }
    }
}