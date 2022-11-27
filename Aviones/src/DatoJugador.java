public class DatoJugador {
    private int puntaje;
    private String nombre;

    public DatoJugador(String nombre, int puntaje){
        this.nombre= nombre;
        this.puntaje=puntaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        String res = nombre + "$"+puntaje;
        return res;
    }
}