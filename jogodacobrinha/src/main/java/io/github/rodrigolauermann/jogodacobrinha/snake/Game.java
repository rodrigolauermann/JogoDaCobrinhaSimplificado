package io.github.rodrigolauermann.jogodacobrinha.snake;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Food food;
    private Board board;
    private Snake snake;
    private String direction = "d";

    public Game(){
        board = new Board();
        snake = new Snake(board);
        food = new Food(board, snake);
    }

    public void moveSnakeTime(){

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run(){
                
                snake.executeMoviment(direction, food, board, snake);
                board.printBoard();
                System.out.printf("%n%n%n%n");

                if(gameOver() == true){
                    System.out.println("WASTED!");
                    System.out.printf("%nJogo finalizado...%n%nDigite:%n%n1. '1' para reiniciar %n2. Escreva: 'sair' para encerrar jogo%n%n");            
                    timer.cancel();
                    return;
                }     
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public boolean gameOver(){
        //se a cobra colide com sigo mesma ou se ela sai do mapa
        for (Coordinate body : snake.getPositionsCoord()) {
            if(body == snake.getHead()){ //compara objetos
                break;
            }
            if(snake.getHead().getX()==body.getX() && snake.getHead().getY()==body.getY()){ //compara numeros
                return true;
            }
        }
        if(snake.getHead().getX()==board.getWidth()||snake.getHead().getY()==board.getHeight()||snake.getHead().getX()<0||snake.getHead().getY()<0){
            return true;
        }
        return false;
    }

    public void readContinuousInput(Scanner input){
        
        String tecla = "";

        while(true){
            tecla = input.nextLine();

            switch (tecla) {
                case "w": direction = "w"; break;  
                case "s": direction = "s"; break; 
                case "a": direction = "a"; break;  
                case "d": direction = "d"; break;  
                case "sair": 
                          System.out.printf("%nEncerrando...%n");
                          return;
                case "1": board = new Board();
                          snake = new Snake(board);
                          food = new Food(board, snake);
                          direction = "d";
                          System.out.printf("%n%nNovo jogo iniciado! (digite qualquer tecla para continuar jogando)%n%n");
                          food.executeSeed(board, snake, food); 
                          moveSnakeTime(); // reinicia o timer, mas sem empilhar novo loop
                          //runGame(input); //se usasse novamente, empilharia. Chamaria readContinuousInput novamente sem fechar a chamada anterior.
                          break;
            } 
        }
    }

    public void runGame(Scanner input){
        food.executeSeed(board, snake, food);
        moveSnakeTime();
        readContinuousInput(input);
    }
}
