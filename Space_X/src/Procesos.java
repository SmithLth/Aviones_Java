import java.util.ArrayList;
import processing.core.*;

public class Procesos extends PApplet {
    private int bits = Mapa.bits;
    private int x = 0;
    private boolean game = false;
    Jugador nave = new Jugador();
    PImage fondo, corazon, welcomescreen, bala, start, logo, jugador;
    ArrayList<Enemigo> enemigos = new ArrayList<>();

    @Override
    public void settings() {
        size(Mapa.ancho*bits, Mapa.alto*bits); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        for (int i = 2; i < 12; i++) {
            enemigos.add(new Enemigo());
        }
        background(0);
        fondo = loadImageIO("/img/fondo.jpg");
        jugador = loadImage("/img/player.png");
        logo = loadImage("/img/logo.png");
        corazon = loadImage("/img/corazon.png");
        bala = loadImage("/img/bala.png");
        start = loadImage("/img/start.png");
        welcomescreen = loadImage("/img/welcome.jpg");
        frameRate(Mapa.alto);
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
            nave.actualizacion();
        }
    }

    private void vidas() {
        for (int i = 1; i <= 10; i++) {
            image(corazon, (25) * i, height - 25, 20, 20);
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
        mostrarEnemigos();
        mostrarBalas(nave.balas.getMisil());
        imageMode(CENTER);
        image(jugador, nave.pos.x *bits, nave.pos.y*bits,9*bits,9*bits);
    }

    private void mostrarEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            nave.choques(enemigos.get(i));
            mostrarBalas(enemigos.get(i).balas.getMisil());
            imageMode(CENTER);
            image(jugador, enemigos.get(i).pos.x *bits, enemigos.get(i).pos.y*bits,9*bits,9*bits);
        }
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
        image(welcomescreen, width / 2, height / 2);
        image(logo, width / 2, height / 2 - 150);
        image(start, width / 2, height / 2, 200, 100);

    }

    public void controlStart() {
        if (mouseX >= width / 2 - 95 && mouseX <= width / 2 + 95 && mouseY >= height / 2 - 45 && mouseY <= height / 2 + 45
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
