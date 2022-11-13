import java.util.ArrayList;

public class Colisiones{
    private int control = 1;
    public int puntos = 0;
    private int velocidad = 0;
    ArrayList<Enemigo> enemigos = new ArrayList<>();
    Mapa mapa = new Mapa();
    public Jugador nave = new Jugador(Mapa.ancho / 2, Mapa.alto - 10);

    public void procesos() {
        crearEnemigos();
        for (int i = 0; i < enemigos.size(); i++) {
            nave.choques(enemigos.get(i));
        }
        if (velocidad == 200){
            nave.recargar();
            control++;
            velocidad = 0;
        } else {
            velocidad++;
        }
    }

    public void crearEnemigos() {
        while (enemigos.size() < control) {
            enemigos.add(new Enemigo(0, 0));
            if (control > Math.random() * (10) + 1) {
                control = 0;
            }
        }
    }
}
