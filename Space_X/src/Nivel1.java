import java.util.ArrayList;

public class Nivel1 {
    private int velocidad = 0;
    public ArrayList<Enemigo> enemigos = new ArrayList<>();
    public Jugador nave = new Jugador(Mapa.ancho / 2, Mapa.alto - 20, Forma.formaJugador, Forma.formaMisil);

    public Nivel1(int n){
        for (int i = 0; i < n; i++) {
            enemigos.add(new Enemigo(Forma.formaEnemigo,Forma.formaMisil2));
        }
    }

    public void procesos() {
        nave.movimiento();
        for (int i = 0; i < enemigos.size(); i++) {
            autoMovimiento(enemigos.get(i),10 - (int) nave.puntos / 5);// velocidad de los aviones
        }
        if (velocidad == 100) {
            nave.recargar();
            velocidad = 0;
        }
        velocidad++;
    }

    public void autoMovimiento(Enemigo enemigo,int velocidad) {
        enemigo.moverBala(velocidad);
        if (enemigo.contador > velocidad) {
            enemigo.mover("s");
            enemigo.mover(moverIzqDer(enemigo));
            enemigo.contador = 0;
        }
        if (enemigo.disparar > 400) {
            enemigo.disparar();
            enemigo.disparar = 0;
        }
        enemigo.contador++;
        enemigo.disparar++;
    }

    private String moverIzqDer(Enemigo enemigo) {
        for (int i = 0; i < enemigo.ship.size(); i++) {
            if (enemigo.ship.get(i).x == 0) {
                enemigo.direccion = "d";
            } else if (enemigo.ship.get(i).x == Mapa.ancho - 1) {
                enemigo.direccion = "a";
            }
        }
        return enemigo.direccion;
    }
}