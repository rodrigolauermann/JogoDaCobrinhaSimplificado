package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Random;

public class Food {
    private Random rand;
    private Coordinate seed;

    public Food(Board board, Snake snake){
        rand = new Random();
        seed = new Coordinate();
    }

    public void generateSeed(Board board, Snake snake){
        boolean invalidPosition = false;

        seed.setX(rand.nextInt(15)); 
        seed.setY(rand.nextInt(15));

        //altera a coordenada da seed dentro do board para uma posicao na qual a snake nao esta  

        while(true){
            invalidPosition = false;
            for(Coordinate coord : snake.getPositionsCoord()){
                if(seed.equals(coord)){
                    seed.setX(rand.nextInt(15)); 
                    seed.setY(rand.nextInt(15));
                    invalidPosition = true;
                    
                    break; 
                }
            }

            if(invalidPosition==false){
                break;
            }
        }
    }

    public void executeSeed(Board board, Snake snake, Food food){
        generateSeed(board, snake);
        board.changePoint(food);
    }

    public Coordinate getSeed() {
        return seed;
    }
}
