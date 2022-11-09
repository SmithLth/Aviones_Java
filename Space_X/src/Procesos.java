import java.util.ArrayList;

import processing.core.*;

public class Procesos extends PApplet {
    private int bits = 5;
    private int x = 0;
    private boolean game = false;
    public static int ancho = 560;
    public static int alto = 700;
    Nave nave = new Nave(ancho / 2, alto / 2, 1);
    PImage fondo, corazon, welcomescreen, bala, start, logo, jugador;

    @Override
    public void settings() {
        size(ancho, alto); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        background(0);
        fondo = loadImageIO("/img/fondo.jpg");
        jugador = loadImage("/img/player.png");
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
            estadoIncio();
        } else {
            cargarFondo();
            vidas();
            textSize(15);
            text("Score :" + 0, 5, 15);
            mostrarNave();
        }
    }

    private void vidas() {
        for (int i = 1; i <= 10; i++) {
            image(corazon, (25) * i, alto - 25, 20, 20);
        }
    }

    private void cargarFondo() {
        imageMode(CORNER);
        image(fondo, 0, x);
        image(fondo, 0, x - fondo.width);
        x += 2;
        if (x == 560) {
            x = 0;
        }
    }

    private void mostrarNave() {
        imageMode(CENTER);
        image(jugador, nave.pos.x, nave.pos.y);
        mostrarBalas(nave.getBalas());
        nave.actualizacion();
    }

    private void mostrarBalas(ArrayList<PVector> particulas) {
        fill(200, 200, 200);
        for (int i = 0; i < particulas.size(); i++) {
            rect(particulas.get(i).x * bits, particulas.get(i).y * bits, bits, bits);
        }
    }

    private void estadoIncio() {
        controlStart();
        imageMode(CENTER);
        image(welcomescreen, ancho / 2, alto / 2);
        image(logo, ancho / 2, alto / 2 - 150);
        image(start, ancho / 2, alto / 2, 200, 100);

    }

    public void controlStart() {
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45
                && mousePressed) {
            game = true;
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'w' || keyCode == UP) {
            nave.mover("w");
        }
        if (key == 's' || keyCode == DOWN) {
            nave.mover("s");
        }
        if (key == 'a' || keyCode == LEFT) {
            nave.mover("a");
        }
        if (key == 'd' || keyCode == RIGHT) {
            nave.mover("d");
        }
        if (key == ' ') {
            nave.disparar();
        }
    }

}
