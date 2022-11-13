import java.util.ArrayList;
import processing.core.*;

public class Procesos extends PApplet {
    private int alto = Mapa.alto * 5;
    private int ancho = Mapa.ancho * 5;
    private int bits = 5;
    private int x = 0;
    private boolean game = false;
    PImage fondo, corazon, welcomescreen, bala, start,logo;

    Colisiones todo = new Colisiones();

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
        if (!game || todo.nave.vida<=0) {
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
            todo();
            vidas();
            balas();
            textSize(15);
            text("Score :" + todo.puntos, 5, 15);
        }
    }

    private void vidas() {
        for (int i = 1; i <= todo.nave.vida; i++) {
            image(corazon, (25) * i, alto - 25, 20, 20);
        }
    }

    private void balas() {
        for (int i = 1; i <= todo.nave.balas; i++) {
            image(bala, ancho / 2 + (10) * i, alto - 25, 20, 20);
        }
    }

    public void todo() {
        todo.procesos();
        mostrar(todo.nave);
        todo.nave.moverBala(0);
        for (int i = 0; i < todo.enemigos.size(); i++) {
            mostrar(todo.enemigos.get(i));
            todo.enemigos.get(i).autoMovimiento(10 - (int) todo.puntos / 10);// velocidad de los aviones
        }
    }

    public void controlStart() {
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45 && mousePressed) {
            game = true;
            todo = new Colisiones();
        }
    }

    private void mostrar(Nave objeto) {
        fill(200, 200, 200);
        drawNaves(objeto.ship);
        if (objeto.missil.bala) {
            fill(100, 200, 200);
            drawNaves(objeto.missil.getMisil());
        }
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
    }
}
