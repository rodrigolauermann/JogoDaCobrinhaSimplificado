package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Random;

public class Food {
    private Random rand;
    private Coordenada seed;

    public Food(Board board, Snake snake){
        rand = new Random();
        seed = new Coordenada();
        //geraSeed(board, snake);
    }

    public void geraSeed(Board board, Snake snake){
        boolean posicaoInvalida = false;

        seed.setX(rand.nextInt(15)); 
        seed.setY(rand.nextInt(15));

        //gera onde nao houver a snake e ainda dentro do board

        while(true){
            posicaoInvalida = false;
            for(Coordenada coord : snake.getposicoesCoord()){
                if(seed.equals(coord)){
                    seed.setX(rand.nextInt(15)); 
                    seed.setY(rand.nextInt(15));
                    posicaoInvalida = true;
                    
                    //break: sai do for
                    break; 
                }
            }

            if(posicaoInvalida==false){
                break;
            }
        }
    }

    public void executaSeed(Board board, Snake snake, Food food){
        geraSeed(board, snake);
        board.alteraPonto(food);
    }

    public Coordenada getSeed() {
        return seed;
    }
}
