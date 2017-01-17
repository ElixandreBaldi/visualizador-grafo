package visualizadorgrafo;

public class Vertice {
    private int id;
    private int coordX;
    private int coordY;
    private String rotulo;    
    
    Vertice(int ID, int X, int Y, String R){
        id = ID;
        coordX = X;
        coordY = Y;
        rotulo = R;
    
    }
    int getId(){
        return id;
    }
    void setId(int dado){
        id = dado;
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
        System.out.println(id+" "+rotulo+" "+coordX+" "+coordY);
    }
}
