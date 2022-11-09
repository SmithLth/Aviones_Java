import processing.core.*;
import java.util.ArrayList;

public class Nave extends Mapa{
    protected ArrayList<PVector> ship = new ArrayList<>();
    protected int x,y,cons,pico;

    public Nave(int posx,int posy, int cons) { // const = 1 es enemigo cons=-1 nave
        super();
        this.x=posx;
        this.y=posy;
        this.cons = cons;
    }

    protected void crear(int a, int b, int f) {
        for (int i = 0; i < f; i++) {
            PVector A1 = new PVector(x + a, y + cons*(b + i));
            PVector A2 = new PVector(x - a, y + cons*(b + i));
            ship.add(A1);
            pico++;
            if (a!= 0) {
                ship.add(A2);
                pico++;
            }
        }
    }

    protected PVector getPico(){
        return ship.get(pico-2);
    }
}
