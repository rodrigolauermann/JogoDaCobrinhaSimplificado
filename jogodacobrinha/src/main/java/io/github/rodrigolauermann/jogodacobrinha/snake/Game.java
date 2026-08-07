package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Game {
    //interacao com comida e impressoes
    private Food food;
    private Board board;

    public Game(){
        food = new Food();
        board = new Board();
    }

    public void rodaJogo(){
        board.preencheBoard();
        board.alteraPonto(food);
        board.imprimeBoard();
    }
}
