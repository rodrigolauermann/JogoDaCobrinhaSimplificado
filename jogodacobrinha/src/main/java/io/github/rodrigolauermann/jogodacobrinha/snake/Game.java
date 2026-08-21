package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    //interacao com comida e impressoes
    private Food food;
    private Board board;
    private Snake snake;
    private String direcao = "d";

    public Game(){
        board = new Board();
        snake = new Snake(board);
        food = new Food(board, snake);
    }

    public void moveCobraTempo(){

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run(){
                
                snake.executaMovimento(direcao, food, board, snake);
                board.imprimeBoard();
                System.out.println("\n\n\n");

                if(gameOver() == true){
                    System.out.println("GAME OVER!");
                    timer.cancel();
                    return; // evita imprimir o board depois do game over
                }     
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public boolean gameOver(){
        //se a cobra bate em si mesma ou se sai do mapa
        if(snake.getcauda().getX()==board.getLargura()||snake.getcauda().getY()==board.getLargura()||snake.getcauda().getX()<0||snake.getcauda().getY()<0){
            return true;
        }
        return false;
    }

    public void lerInputContinuo(Scanner input){
        
        String tecla = "";

        while(true){
            tecla = input.nextLine();
            //usar switch case
            switch (tecla) {
                case "w": direcao = "w"; break;  
                case "s": direcao = "s"; break; 
                case "a": direcao = "a"; break;  
                case "d": direcao = "d"; break;  
            }
        }
    }

    public void rodaJogo(Scanner input){
        food.executaSeed(board, snake, food);
        moveCobraTempo();
        lerInputContinuo(input);
    }
}
