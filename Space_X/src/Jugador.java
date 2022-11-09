public class Jugador extends Cohete {
    public int balas = 20;

    public Jugador(int posx, int posy) { // const = 1 es enemigo cons=-1 nave
        super(posx, posy, -1);
        mapaCargar(ship);
    }

    @Override
    public void forma() {
        crear(5, 0, 6);
        crear(4, 1, 2);
        crear(3, 2, 6);
        crear(2, 3, 5);
        crear(1, 2, 7);
        crear(0, 0, 11);
    }

    @Override
    public void mover(String direccion) {
        if (ship.size()>0) {
            for (int i = 0; i < ship.size(); i++) {
                if (direccion == "d") {
                    ship.get(i).set(ship.get(i).x+1,ship.get(i).y);
                } else if (direccion == "a") {
                    ship.get(i).set(ship.get(i).x-1,ship.get(i).y);
                }else if (direccion == "w") {
                    ship.get(i).set(ship.get(i).x,ship.get(i).y-1);
                }else if (direccion == "s") {
                    ship.get(i).set(ship.get(i).x,ship.get(i).y+1);
                }
            }
            verificar(direccion);
            mapaCargar(ship);
        }
    }

    public void verificar(String dir){
        for (int i = 0; i < ship.size(); i++) {
            if (ship.get(i).x<0 || ship.get(i).x>Mapa.ancho-1) {
                contrario(dir);
            }else if (ship.get(i).y<0 || ship.get(i).y>Mapa.alto-1) {
                contrario(dir);
            }
        }
    }

    private void contrario(String dir){
        if (dir == "a") {
            mover("d");
        } else if(dir == "s"){
            mover("w");
        }else if(dir == "d"){
            mover("a");
        }else if(dir == "w"){
            mover("s");
        }
    }

    @Override
    public void moverBala(int velocidad){
        missil.moverBala();
    }

    @Override
    public void disparar() {
        if (activo && balas > 0) {
            missil.disparar(getPico());
            balas--;
        }
    }

    public void recargar() {
        if (balas<20) {
            balas++;
        }
    }
}
