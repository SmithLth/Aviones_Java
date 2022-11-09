import java.util.ArrayList;
import processing.core.*;

public class Procesos extends PApplet {
    private int bits = 5;
    private int x = 0;
    private boolean game = false;
    private int ancho = 560;
    private int alto = 700;
    PImage fondo, corazon, welcomescreen, bala, start,logo;


    @Override
    public void settings() {
        size(ancho, alto); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        background(0);
        fondo = loadImageIO("/img/fondo.jpg");
        logo = loadImage("/img/logo.png");
        corazon = loadImage("/img/corazon.png");
        bala = loadImage("/img/bala.png");
        start = loadImage("/img/start.png");
        welcomescreen = loadImage("/img/welcome.jpg");
        frameRate(alto / bits);
    }

    @Override
    public void draw() {
        if (!game) {
            controlStart();
            imageMode(CENTER);
            image(welcomescreen, ancho / 2, alto / 2);
            image(logo,ancho/2,alto/2-150);
            image(start,ancho/2,alto/2, 200,100);
        } else {
            imageMode(CORNER);
            image(fondo, 0, x);
            image(fondo, 0, x - fondo.width);
            x += 2;
            if (x == 560)
                x = 0;
            vidas();
            textSize(15);
            text("Score :" + 0, 5, 15);
        }
    }

    private void vidas() {
        for (int i = 1; i <= 10; i++) {
            image(corazon, (25) * i, alto - 25, 20, 20);
        }
    }


    public void controlStart() {
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45 && mousePressed) {
            game = true;
        }
    }


/*
 * @Override
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
    }
 */
    

}
