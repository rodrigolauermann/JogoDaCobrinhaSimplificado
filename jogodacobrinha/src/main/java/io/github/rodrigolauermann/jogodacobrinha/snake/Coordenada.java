package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Coordenada {
    private int x;
    private int y;

    public Coordenada(){
        this.x=0;
        this.y=0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if(x<0 || x>15){
            throw new IllegalArgumentException("Coordenada nao valida para y");
        }
        this.x = x;
    }

    public void setY(int y) {
        if(y<0 || x>15){
            throw new IllegalArgumentException("Coordenada nao valida para y");
        }
        this.y = y;
    }
}
