import java.util.ArrayList;

public class Colisiones {
    private int control = 1, velocidad = 0;
    public ArrayList<Enemigo> enemigos = new ArrayList<>();
    public Jugador nave = new Jugador(Mapa.ancho / 2, Mapa.alto - 10);

    public void procesos() {
        crearEnemigos();
        for (int i = 0; i < enemigos.size(); i++) {
            nave.choques(enemigos.get(i));
        }
        if (velocidad == 200) {
            nave.recargar();
            control++;
            velocidad = 0;
        } else {
            velocidad++;
        }
    }

    public void crearEnemigos() {
        while (enemigos.size() < control) {
            enemigos.add(new Enemigo());
            if (control > Math.random() * (10) + 1) {
                control = 0;
            }
        }
    }
}