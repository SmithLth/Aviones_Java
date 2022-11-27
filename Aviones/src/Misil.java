public class Misil extends Objeto {
    private int direccion;

    public Misil(int posx, int posy, int[][] forma, int direccion) {
        super(posx, posy, 1);
        this.forma = forma;
        this.direccion = direccion;
        crear();
    }

    public void mover() {
        partes.clear();
        posicion.y = posicion.y + direccion;
        crear();
    }
}