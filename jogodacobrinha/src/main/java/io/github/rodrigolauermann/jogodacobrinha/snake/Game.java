package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    //interacao com comida e impressoes
    private Food food;
    private Board board;
    private Snake snake;
    private Timer time;
    private TimerTask task;

    
    public Game(){
        board = new Board();
        snake = new Snake(board);
        food = new Food(board, snake);
    }

    public void moveCobraTempo(){

        Scanner input = new Scanner(System.in);

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            int segundos = 10;

            @Override
            public void run(){

                System.out.println(segundos);
                if(segundos == 0){
                    System.out.println("Tempo esgotado!");
                    timer.cancel();
                }
                segundos--;
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
        snake.permissaoMovimento();
        Moviment novoMoviment = snake.getLastMoviment();
        snake.changeLastMoviment(input);
        snake.moveCobra();
    }

    public void rodaJogo(){

        board.alteraPonto(food);
        board.alteraPontos(snake);
        
        board.imprimeBoard();
        
    }
}
