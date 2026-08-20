package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    //interacao com comida e impressoes
    private Food food;
    private Board board;
    private Snake snake;

    public Game(){
        board = new Board();
        snake = new Snake(board);
        food = new Food(board, snake);
    }

    public void moveCobraTempo(Scanner input){

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run(){
                if(gameOver() == true){
                    System.out.println("GAME OVER!");
                    timer.cancel();
                }
                board.imprimeBoard();
                snake.executaMovimento(input, food, board, snake);
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public boolean gameOver(){
        //se a cobra bate em si mesma ou se sai do mapa
        if(snake.getCabeca().getX()>board.getLargura()||snake.getCabeca().getY()>board.getLargura()){
            return true;
        }
        return false;
    }
    
    public void rodaJogo(Scanner input){
        moveCobraTempo(input);
    }
}
