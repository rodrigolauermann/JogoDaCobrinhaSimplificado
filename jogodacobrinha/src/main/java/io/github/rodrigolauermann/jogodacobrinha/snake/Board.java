package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Board {
    private final int altura;
    private final int largura;
    private char [][] board;

    public Board(){
        this.altura = 15;
        this.largura = 15;
        this.board = new char[altura][largura];
        preencheBoard();
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public void preencheBoard(){
        for(int i=0; i<altura; i++){
            for(int j=0; j<largura; j++){
                board[i][j] = '.';
            }
        }
    }

    //metodo que imprime board
    
}
