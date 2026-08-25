package io.github.rodrigolauermann.jogodacobrinha.snake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake{
    
    private Deque<Coordinate> listBody; 
    @SuppressWarnings("unused")
    private Coordinate tail; //referencia para o primeiro elemento
    private Moviment lastMoviment;
    private Moviment listMovimentPermission[];
    private Coordinate head; //referencia para o ultimo elemento

    public Snake(Board board){
        listBody = new LinkedList<>();
        listMovimentPermission = new Moviment[2];
        initialSnakePosition(board);
    }   

    //posicoes inicias da cobra
    public void initialSnakePosition(Board board){
        int initialHeight = board.getHeight()/2;
        int initialWidth = 1;
        int finalWidth = initialWidth+3;
        
        //coordinates colocar na lista
        for(int i=initialWidth; i<finalWidth; i++){ 
            Coordinate coordinate = new Coordinate();
            coordinate.setX(i);
            coordinate.setY(initialHeight);
            listBody.add(coordinate);
            head = coordinate; 
        }
        lastMoviment = Moviment.D; 
    }

    //Nesse caso, o input é processado na classe Game e aqui, só é recebida a string correspondente ao sentido selecionado.
    public Moviment changeLastMoviment(String move){

        movimentPermission();

        //sentidos validos - A, W, S, D 

            if(move.toUpperCase().equals("A")){
                for (Moviment permitedMov : listMovimentPermission) {
                    if(permitedMov.equals(Moviment.A)){
                        lastMoviment = Moviment.A;
                    }
                }
            }
            if(move.toUpperCase().equals("W")){
                for (Moviment permitedMov : listMovimentPermission) {
                    if(permitedMov.equals(Moviment.W)){
                        lastMoviment = Moviment.W;
                    }
                }
            }
            if(move.toUpperCase().equals("S")){
                for (Moviment permitedMov : listMovimentPermission) {
                    if(permitedMov.equals(Moviment.S)){
                        lastMoviment = Moviment.S;
                    }
                }
            }
            if(move.toUpperCase().equals("D")){
                for (Moviment permitedMov : listMovimentPermission) {
                    if(permitedMov.equals(Moviment.D)){
                        lastMoviment = Moviment.D;
                    }
                }
            } 
        
        return lastMoviment;
    }

    public Moviment getLastMoviment() {
        return lastMoviment;
    }
 
    public void movimentPermission(){
        switch (lastMoviment) {
            case A:
                listMovimentPermission[0] = Moviment.W;
                listMovimentPermission[1] = Moviment.S;
                break;
            case W:
                listMovimentPermission[0] = Moviment.A;
                listMovimentPermission[1] = Moviment.D;
                break;
            case S:
                listMovimentPermission[0] = Moviment.A;
                listMovimentPermission[1] = Moviment.D;
                break;
            case D:
                listMovimentPermission[0] = Moviment.W;
                listMovimentPermission[1] = Moviment.S;
                break;
            default:
                break;
        }
    }

    public void executeMoviment(String input, Food food, Board board, Snake snake){
        board.changePoints(snake, food);

        movimentPermission();

        changeLastMoviment(input);
        
        moveSnake(food, board, snake);
    }

    public void moveSnake(Food food, Board board, Snake snake){

        Coordinate newCoordinate = new Coordinate();

        boolean fattenSnake = fattenSnake(food, board, snake);
            switch (lastMoviment) {
                case W:
                    if(fattenSnake==false){listBody.poll();} 
                    newCoordinate.setX(head.getX());
                    newCoordinate.setY(head.getY()-1); //vai de 0 a 14. Entao subir seria se aproximar de 0               

                    head = newCoordinate;

                    listBody.add(newCoordinate);
                    break;
            
                case S:
                    if(fattenSnake==false){listBody.poll();} 
                    newCoordinate.setX(head.getX());
                    newCoordinate.setY(head.getY()+1); //vai de 0 a 14. entao descer seria se aproximar de 14

                    head = newCoordinate;

                    listBody.add(newCoordinate);                
                    break;

                case A:
                    if(fattenSnake==false){listBody.poll();}  
                    newCoordinate.setX(head.getX()-1);
                    newCoordinate.setY(head.getY());

                    head = newCoordinate;

                    listBody.add(newCoordinate);
                    break;

                case D:
                    if(fattenSnake==false){listBody.poll();}
                    newCoordinate.setX(head.getX()+1);
                    newCoordinate.setY(head.getY());

                    head = newCoordinate;

                    listBody.add(newCoordinate);
                    break;

                default:
                    break;
            }
    }

    public boolean fattenSnake(Food food, Board board, Snake snake){
        //quando a cobra "come" a semente, tem que alterar posicao da semente
        if(getHead().equals(food.getSeed())){
            food.generateSeed(board, snake);
            board.changePoint(food);
            return true;
        }
        return false;
    }

    //retorna primeiro da lista 
    public Coordinate getTail() { 
        return getPositionsCoord().getFirst();
    }

    public Coordinate getHead(){
        return head; //getPositionsCoord().getLast();
    }

    public Deque<Coordinate> getPositionsCoord(){
        return listBody;
    }
    
}