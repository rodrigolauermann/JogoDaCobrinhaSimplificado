package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Board {
    private final int altura; //y
    private final int largura; //x
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

    public void imprimeBoard(){
        for(int i=0; i<altura; i++){
            for(int j=0; j<largura; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    //metodo que imprime board

    public void alteraPonto(Food food){
        board[food.getSeed().getY()][food.getSeed().getX()] = 'M';
    }

    public void alteraPontos(Snake snake, Food food){
        //sem isso a cobra so cresce
        preencheBoard();
        alteraPonto(food);
        for(Coordenada elem : snake.getposicoesCoord()){
            board[elem.getY()][elem.getX()] = '0';
        }
    }
}
