package visualizadorgrafo;

public class Vertice {
    private int coordX;
    private int coordY;
    private String rotulo;    
    
    Vertice(int X, int Y, String R){
        coordX = X;
        coordY = Y;
        rotulo = R;
    
    }
    
    int getCoordX(){
        return coordX;
    }
    void setCoordX(int dado){
        coordX = dado;
    }
    
    int getCoordY(){
        return coordY;
    }
    void setCoordY(int dado){
        coordY = dado;
    }
    
    String getRotulo(){
        return rotulo;
    }
    void setId(String dado){
        rotulo = dado;
    }
    void print(){
        System.out.println(rotulo+" "+coordX+" "+coordY);
    }
}
