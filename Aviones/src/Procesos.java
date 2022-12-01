import java.util.ArrayList;
import processing.core.*;
import javax.swing.JOptionPane;
import ddf.minim.*;

public class Procesos extends PApplet {
    // Sonido
    static Minim minim;
    AudioPlayer soundMenu, soundJuego, soundGameOver, soundDisparar, soundPause;

    private int ancho = 560, alto = 700, bits = 5;
    private int estadoGame;
    Mapa mapa = new Mapa(bits, ancho, alto);
    // menu
    PImage fondo, welcomescreen, start, logo, boton, lCreditos, lRecords, astro, cohete;
    /// reconds
    PImage cintillo, copa1, copa2, copa3, goat, puntajes, atras;
    PFont font2;
    // en juego
    PImage jugador1, jugador2, jugador3, pauseFondo, pause;
    // game Over
    PImage alien, alienCon, luna, tierra, gameOver, resNo, resSi;
    // records

    private int scroll;
    private ArrayList<DatoJugador> tablaPuntajes = new ArrayList<>();

    @Override
    public void settings() {
        size(ancho, alto); // (ancho,alto) de la ventana
    }

    @Override
    public void setup() {
        minim = new Minim(this);

        soundMenu = minim.loadFile("/sound/soundJuego2.mp3");
        soundJuego = minim.loadFile("/sound/soundJuego.mp3");
        soundGameOver = minim.loadFile("/sound/soundGameOver.mp3");
        soundDisparar = minim.loadFile("/sound/soundDisparar.mp3");
        soundPause = minim.loadFile("/sound/soundPause.mp3");
        background(0);
        // menuInicio
        fondo = loadImage("/img/fondo.jpg");
        logo = loadImage("/img/space.png");
        start = loadImage("/img/star2.png");
        welcomescreen = loadImage("/img/fondo4-03.png");
        boton = loadImage("/img/boton.png");
        lCreditos = loadImage("/img/creditos.png");
        lRecords = loadImage("/img/records.png");
        astro = loadImage("/img/astro.png");
        cohete = loadImage("/img/cohetee.png");
        // en juego
        jugador1 = loadImage("/img/jugador2.png");
        pauseFondo = loadImage("/img/pauseFondo.png");
        pause = loadImage("/img/pause.png");
        // menuGameOver
        gameOver = loadImage("/img/gameOver2.png");
        alien = loadImage("/img/alien.png");
        alienCon = loadImage("/img/gameOverA.png");
        luna = loadImage("/img/luna.png");
        tierra = loadImage("/img/tierra.png");
        resNo = loadImage("/img/no.png");
        resSi = loadImage("/img/si.png");
        // menuRecords
        cintillo = loadImage("/img/cinta.png");
        copa1 = loadImage("/img/1ro.png");
        copa2 = loadImage("/img/2do.png");
        copa3 = loadImage("/img/3ro.png");
        goat = loadImage("/img/goat.png");
        puntajes = loadImage("/img/puntajes.png");
        font2 = createFont("Rockwell", 32);
        atras = loadImage("/img/atras.png");

        frameRate(110);
        estadoGame = 0;
        try {
            tablaPuntajes = DataBase.leer();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void draw() {
        controlGame();
        if (estadoGame == 0) {
            soundJuego.pause();
            soundGameOver.pause();
            if (!soundMenu.isPlaying()) {
                //soundMenu.setGain(-10);// bajar volumen
                soundMenu.play(1);
            }
            menuInicio();
        } else if (estadoGame == 1) {
            soundGameOver.pause();
            soundMenu.pause();
            if (!soundJuego.isPlaying()) {
                //soundJuego.setGain(-5);// bajar volumen
                soundJuego.play(1);
            }
            cargarFondoScroll();
            cargarOjetos();
            imageMode(CENTER);
            image(jugador1, mapa.jugador.posicion.x * bits + (mapa.jugador.forma[0].length / 2 + 1) * 5,
                    mapa.jugador.posicion.y * bits + (mapa.jugador.forma.length / 2) * 5, 50, 50);
            actualizar();
            if (mapa.jugador.vida == 0) {
                estadoGame = 2;
            }
        } else if (estadoGame == 2) {
            soundJuego.pause();
            soundMenu.pause();
            if (!soundGameOver.isPlaying()) {
                //soundGameOver.setGain(-10);// bajar volumen
                soundGameOver.play(1);
            }
            gameOver();
            mensajeGameOver();
        } else if (estadoGame == 3) {
            //soundMenu.setGain(-10);
            soundGameOver.pause();
            soundJuego.pause();
            if (!soundMenu.isPlaying()) {
                //soundMenu.setGain(-10);// bajar volumen
                soundMenu.play(1);
            }
            estadoRecords();
        }

    }

    public void menuInicio() {
        imageMode(CENTER);

        image(welcomescreen, ancho / 2, alto / 2, ancho, alto);
        image(astro, 130, alto - 150, 370, 380);
        image(cohete, ancho * 3 / 4 + 40, alto - 150, 310, 300);
        image(logo, ancho / 2, alto / 2 - 150, 400, 100);
        image(boton, ancho / 2, alto / 2 + 20, 200, 120);
        image(start, ancho / 2, alto / 2 + 10, 150, 50);
        imageMode(CORNER);

        image(boton, 30, alto - 100, 140, 70);
        image(lCreditos, 65, alto - 80, 80, 20);

        image(boton, ancho * 3 / 4 - 30, alto - 100, 140, 70);
        image(lRecords, ancho * 3 / 4, alto - 80, 80, 20);
    }

    public void gameOver() {
        imageMode(CORNER);
        image(welcomescreen, 0, 0);
        image(luna, ancho - 180, 30, 160, 160);
        image(alienCon, 110, alto / 2 - 40, 280, 140);

        image(gameOver, 30, 10, 320, 200);
        imageMode(CORNER);
        image(tierra, -50, alto - 160, 350, 370);
        image(alien, 5, alto / 2 + 40, 240, 260);

        image(resSi, ancho * 3 / 4 - 15, alto - 200, 140, 100);
        image(resNo, ancho * 3 / 4 - 15, alto - 100, 140, 100);
    }

    public void mensajeGameOver() {
        if (mapa.jugador.vida == 0 &&
                (tablaPuntajes.get(tablaPuntajes.size() - 1).getPuntaje() < ((Jugador) mapa.jugador).puntaje)) {
            String name = JOptionPane.showInputDialog("tu nombre crack ;)");
            JOptionPane.showMessageDialog(null, "Nuevo record!!: " + ((Jugador) mapa.jugador).puntaje);
            tablaPuntajes.add(new DatoJugador(name, ((Jugador) mapa.jugador).puntaje));
            if (tablaPuntajes.size() > 6) {
                tablaPuntajes.remove(0);
            }
            try {
                DataBase.guardar(tablaPuntajes);
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void estadoRecords() {
        imageMode(CORNER);
        image(welcomescreen, 0, 0);
        // botones

        image(boton, 30, alto - 100, 140, 70);
        image(atras, 75, alto - 95, 40, 50);
        image(boton, ancho * 3 / 4 - 30, alto - 100, 140, 70);
        // titulo copas
        imageMode(CENTER);
        image(puntajes, ancho / 2, 20, 150, 10);
        image(cintillo, ancho / 2, 180, 400, 50);
        image(goat, ancho / 2, 130, 400, 100);
        image(copa1, 100, 150, 70, 110);
        if (tablaPuntajes.size() > 2) {
            image(copa2, 120, 300, 40, 50);
            if (tablaPuntajes.size() > 3)
                image(copa3, 120, 340, 40, 50);
        }
        // tablaPuntajes
        textFont(font2);
        textSize(20);
        text("RANK", 100, 240);
        text("NOMBRE", 250, 240);
        text("SCORE", 400, 240);
        text(tablaPuntajes.get((tablaPuntajes.size() - 1)).getNombre(), 180, 180);
        text(tablaPuntajes.get((tablaPuntajes.size() - 1)).getPuntaje(), 350, 180);
        for (int i = tablaPuntajes.size() - 2, j = 1; i > 0 && j < 6; i--, j++) {
            text(i + 1, 115, 260 + i * 50);
            text(tablaPuntajes.get(i).getNombre(), 250, 260 + j * 50);
            text(tablaPuntajes.get(i).getPuntaje(), 430, 260 + j * 50);
        }
    }

    private void controlGame() {
        if (mouseX >= ancho / 2 - 95 && mouseX <= ancho / 2 + 95 // de soundMenu a juego
                && mouseY >= alto / 2 - 45 && mouseY <= alto / 2 + 45
                && mousePressed && estadoGame == 0) {
            mapa = new Mapa(bits, ancho, alto);
            Enemigo.velocidad = 20;
            estadoGame = 1;
        }
        if (mouseX >= ancho * 3 / 4 && mouseX <= ancho // de soundMenu a record
                && mouseY >= alto - 100 && mouseY <= alto
                && (mousePressed && estadoGame == 0)) {
            estadoGame = 3;
            mousePressed = false;
        } else if (mouseX >= ancho * 3 / 4 && mouseX <= ancho // de record a juego
                && mouseY >= alto - 100 && mouseY <= alto
                && (mousePressed && estadoGame == 3)) {
            mapa = new Mapa(bits, ancho, alto);
            Enemigo.velocidad = 20;
            estadoGame = 1;
        }
        if (mouseX >= 0 && mouseX <= ancho / 4 // de record a soundMenu
                && mouseY >= alto - 100 && mouseY <= alto
                && mousePressed && estadoGame == 3) {
            estadoGame = 0;
        }
        if (mouseX >= ancho * 3 / 4 - 15 && mouseX <= ancho // de game over a soundMenu
                && mouseY >= alto - 100 && mouseY <= alto
                && (mousePressed && estadoGame == 2)) {
            estadoGame = 0;
            mousePressed = false;
        } else if (mouseX >= ancho * 3 / 4 - 15 && mouseX <= ancho // de game over a juego
                && mouseY >= alto - 200 && mouseY <= alto - 120
                && (mousePressed && estadoGame == 2)) {
            Enemigo.velocidad = 20;
            mapa = new Mapa(bits, ancho, alto);
            estadoGame = 1;
        }

    }

    private void cargarFondoScroll() {
        imageMode(CORNER);
        image(fondo, 0, scroll);
        image(fondo, 0, scroll - alto);
        scroll += 5;
        if (scroll == alto) {
            scroll = 0;
        }
    }

    private void cargarOjetos() {
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

    private void actualizar() {
        mapa.actualizarAste();
        mapa.actualizarAtributos();
        mapa.actualizarEnemigos();
        mapa.actualizarJugador();
    }

    private void dibujarMisiles() {
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
            rect(partes.get(i).x * bits, partes.get(i).y * bits, bits, bits);
        }
    }

    @Override
    public void keyPressed() {
        if (key == 'w' || keyCode == UP) {
            mapa.jugador.a = true;
        }
        if (key == 's' || keyCode == DOWN) {
            mapa.jugador.b = true;
        }
        if (key == 'a' || keyCode == LEFT) {
            mapa.jugador.i = true;
        }
        if (key == 'd' || keyCode == RIGHT) {
            mapa.jugador.d = true;
        }
        if (key == ' ') {
            //soundDisparar.setGain(-30);
            soundDisparar.play(1);
            mapa.jugador.disparar();
        }
        if (keyCode == 'P') {
            if (looping) {
                image(pauseFondo, 0, 0, ancho * 5, alto * 5);
                imageMode(CENTER);
                image(pause, ancho / 2, alto / 2, 400, 80);
                soundJuego.pause();
                soundMenu.pause();
                if (!soundPause.isPlaying()) {
                    //soundPause.setGain(-10);// bajar volumen
                    soundPause.play(1);
                }
                noLoop();
            } else {
                soundPause.pause();
                soundJuego.play();
                loop();
            }
        }
    }

    @Override
    public void keyReleased() {
        if (key == 'w' || keyCode == UP) {
            mapa.jugador.a = false;
        }
        if (key == 's' || keyCode == DOWN) {
            mapa.jugador.b = false;
        }
        if (key == 'a' || keyCode == LEFT) {
            mapa.jugador.i = false;
        }
        if (key == 'd' || keyCode == RIGHT) {
            mapa.jugador.d = false;
        }
    }
}
