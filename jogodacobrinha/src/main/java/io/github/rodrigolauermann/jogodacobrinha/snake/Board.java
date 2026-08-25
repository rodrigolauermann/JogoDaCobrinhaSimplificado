package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Board {
    private final int height; //y
    private final int width; //x
    private char [][] board;

    public Board(){
        this.height = 15;
        this.width = 15;
        this.board = new char[height][width];
        fillBoard();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void fillBoard(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                board[i][j] = '.';
            }
        }
    }

    public void printBoard(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void changePoint(Food food){
        board[food.getSeed().getY()][food.getSeed().getX()] = 'M';
    }

    public void changePoints(Snake snake, Food food){
        fillBoard();
        changePoint(food);
        for(Coordinate elem : snake.getPositionsCoord()){
            board[elem.getY()][elem.getX()] = '0';
        }
    }
}
