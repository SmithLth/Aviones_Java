public class Forma{
    
    static private int[][] formaJugador = {
                { 0, 0, 0, 1, 0, 0, 0},
                { 0, 0, 1, 1, 1, 0, 0},
                { 0, 1, 1, 0, 1, 1, 0},
                { 1, 1, 0, 0, 0, 1, 1},
                { 1, 1, 0, 0, 0, 1, 1},
                { 1, 0, 0, 0, 0, 0, 1},
                { 1, 1, 1, 1, 1, 1, 1},


        };
        
    static private int[][] formaEnemigo = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 1, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
    };
    
    static private int [][] formaMisil = {
                { 0, 0, 1, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 0 },
                { 0, 0, 1, 0, 0 },
    };
    static private int [][] formaMisil2 = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 },
 
    };
    static public int[][] getFormaEnemigo(){
        return formaEnemigo;
    }
    
    static public int[][] getFormaJugador(){
        return formaJugador;
    }
    static public int[][] getFormaMisil(){
        return formaMisil;
    }
    static public int[][] getFormaMisil2(){
        return formaMisil2;
    }
}