package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Random;

public class Food {
    private Random rand;
    private Coordenada seed;

    public Food(Board board, Snake snake){
        rand = new Random();
        seed = new Coordenada();
        geraSeed(board, snake);
    }

    public void geraSeed(Board board, Snake snake){
        boolean verifica = false;

        seed.setX(rand.nextInt(15)); 
        seed.setY(rand.nextInt(15));

        //gera onde nao houver a snake e ainda dentro do board

        while(true){
            verifica = false;
            for(Coordenada coord : snake.getposicoesCoord()){
                if(seed.equals(coord)){
                    seed.setX(rand.nextInt(15)); 
                    seed.setY(rand.nextInt(15));
                    verifica = true;
                    
                    //break: sai do for
                    break; 
                }
            }
            if(verifica==false){
                break;
            }
        }
    }

    public Coordenada getSeed() {
        return seed;
    }
}
