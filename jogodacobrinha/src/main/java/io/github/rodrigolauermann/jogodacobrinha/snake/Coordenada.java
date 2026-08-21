package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Coordenada {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        //alterado para deixar o jogo lidar sem dar excessao para x<0
        //if(x<0 || x>15){
        //    throw new IllegalArgumentException("Coordenada nao valida para y");
        //}
        this.x = x;
    }

    public void setY(int y) {
        //if(y<0 || x>15){
        //    throw new IllegalArgumentException("Coordenada nao valida para y");
        //}
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordenada)) return false;

        Coordenada outra = (Coordenada) obj;
        return this.x == outra.x && this.y == outra.y;
    }
}

