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
        //se a cobra bate em si mesma ou se sai do mapa
        for (Coordenada corpo : snake.getposicoesCoord()) {
            if(corpo == snake.getCabeca()){ //compara obejtos
                break;
            }
            if(snake.getCabeca().getX()==corpo.getX() && snake.getCabeca().getY()==corpo.getY()){ //compara numeros
                return true;
            }
        }
        if(snake.getCabeca().getX()==board.getLargura()||snake.getCabeca().getY()==board.getLargura()||snake.getCabeca().getX()<0||snake.getCabeca().getY()<0){
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
                case "sair": 
                          System.out.printf("%nEncerrando...%n");
                          return;
                case "1": board = new Board();
                          snake = new Snake(board);
                          food = new Food(board, snake);
                          direcao = "d";
                          System.out.printf("%n%nNovo jogo iniciado! (digite qualquer tecla para continuar jogando)%n%n");
                          food.executaSeed(board, snake, food); //repete o processo, mas sem c
                          moveCobraTempo(); // reinicia o timer, sem empilhar novo loop
                          //rodaJogo(input); //se usar novamente, empilha chamaria lerInputContinuo de novo sem fechar chamada anterior
                          break;
            } 
        }
    }

    public void rodaJogo(Scanner input){
        food.executaSeed(board, snake, food);
        moveCobraTempo();
        lerInputContinuo(input);
    }
}
