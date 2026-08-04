package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Random;

public class Food {
    private Random rand;
    private Coordenada seed;

    public Food(){
        rand = new Random();
        geraSeed();
    }

    public void geraSeed(){
        //gera onde nao houver a snake e ainda dentro do board
        seed.setX(rand.nextInt(15)); 
        seed.setY(rand.nextInt(15));
    }

    public Coordenada getSeed() {
        return seed;
    }
}
