import processing.core.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class TestMapa
{   
    Mapa mapa;
    public TestMapa()
    {
        mapa = new Mapa(5,560,700);
        mapa.contador=1;
        mapa.deley=0;
    }
    
    @Test
    public void testPosicionValidaY(){
        Objeto jugadorValido = new Jugador(5, 20, Forma.getFormaJugador(), 1);
        assertEquals(true,mapa.posicionValidaY(jugadorValido));
        
        Objeto jugadorNoValido = new Jugador(-10, -5, Forma.getFormaJugador(), 1);
        assertEquals(false,mapa.posicionValidaY(jugadorNoValido));
        
        Objeto jugadorEnMedio = new Jugador(Mapa.ancho / 2, Mapa.alto - 9, Forma.getFormaJugador(), 200);
        assertEquals(true,mapa.posicionValidaY(jugadorEnMedio));
    }
    
    @Test
    public void testExisteColicion(){
        ArrayList<PVector> partes1 = new ArrayList<>();
        ArrayList<PVector> partes2 = new ArrayList<>(); 
        partes1.add(new PVector (1,3));
        partes2.add(new PVector (1,3));
        
        assertEquals(true,mapa.existeColicion(partes1,partes2));
    }
    
    @Test
    public void testCrearEnemigos(){
        assertEquals(0,mapa.enemigos.size());
        
        mapa.crearEnemigos(Forma.getFormaEnemigo());
        assertEquals(1,mapa.enemigos.size());
        
        mapa.deley = 50;
        mapa.crearEnemigos(Forma.getFormaEnemigo());
        assertEquals(1,mapa.enemigos.size());
    }
    
}
