package io.github.rodrigolauermann.jogodacobrinha.snake;

//import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class Snake{
    
    //private int tamanhoAtual;
    private Deque<Coordenada> listaCorpo; //x é baixo/cima y é esq/direita
    @SuppressWarnings("unused")
    private Coordenada cauda; //primeira da queue //ex. remove varias vezes e vai ser a primeira da fila 
    private Moviment lastMoviment;
    private Moviment listaPermissaoMovimento[];
    private Coordenada cabeca; //foi a primeira adicionada //em questao de queue é sempre a última 

    public Snake(Board board){
        listaCorpo = new LinkedList<>();
        listaPermissaoMovimento = new Moviment[2];
        posicoesCobraInicial(board);
    }   

    //posicoes inicias da cobra
    public void posicoesCobraInicial(Board board){
        int alturaInicial = board.getAltura()/2;
        int larguraInicial = 1;
        int larguraFinal = larguraInicial+3;
        
        //coordenadas colocar na lista
        for(int i=larguraInicial; i<larguraFinal; i++){ 
            Coordenada coordenada = new Coordenada();
            coordenada.setX(i);
            coordenada.setY(alturaInicial);
            listaCorpo.add(coordenada);
            cabeca = coordenada; //adiciona na cabeca

            //tamanhoAtual++;
        }
        lastMoviment = Moviment.D; //w
    }

    //movimento da cobra pelo input do usuario
    public Moviment changeLastMoviment(String move){

        permissaoMovimento();

        //o usuario insere uma direcao: A W S D 
            //move = input.nextLine();

            if(move.toUpperCase().equals("A")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.A)){
                        lastMoviment = Moviment.A;
                    }
                }
            }
            if(move.toUpperCase().equals("W")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.W)){
                        lastMoviment = Moviment.W;
                    }
                }
            }
            if(move.toUpperCase().equals("S")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.S)){
                        lastMoviment = Moviment.S;
                    }
                }
            }
            if(move.toUpperCase().equals("D")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
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
 
    //permissoes de movimento. Metodo pegaMovimento precisa verificar disponilidade de movimento
    public void permissaoMovimento(){
        switch (lastMoviment) {
            case A:
                listaPermissaoMovimento[0] = Moviment.W;
                listaPermissaoMovimento[1] = Moviment.S;
                break;
            case W:
                listaPermissaoMovimento[0] = Moviment.A;
                listaPermissaoMovimento[1] = Moviment.D;
                break;
            case S:
                listaPermissaoMovimento[0] = Moviment.A;
                listaPermissaoMovimento[1] = Moviment.D;
                break;
            case D:
                listaPermissaoMovimento[0] = Moviment.W;
                listaPermissaoMovimento[1] = Moviment.S;
                break;
            default:
                break;
        }
    }

    public void executaMovimento(String input, Food food, Board board, Snake snake){
        board.alteraPontos(snake, food);

        permissaoMovimento();

        changeLastMoviment(input);
        
        moveCobra(food, board, snake);
    }

    //pegaMovimento vai passar o ultimo movimento. Com base nele, alteramos a lista (corpo cobra) e atualizamos a referência para cauda

    //permissoesMovimento -> changeLastMoviment -> moveCobra

    public void moveCobra(Food food, Board board, Snake snake){

        //boolean verifica = false;
        Coordenada newCoordinate = new Coordenada();

        boolean engordaCobra = engordaCobra(food, board, snake);
            switch (lastMoviment) {
                case W:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cabeca.getX());
                    newCoordinate.setY(cabeca.getY()-1);//                    newCoordinate.setY(cauda.getY()+1);

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last
                    break;
            
                case S:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cabeca.getX());
                    newCoordinate.setY(cabeca.getY()+1); //                    newCoordinate.setY(cauda.getY()-1);

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last                
                    break;

                case A:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro //o topo 
                    newCoordinate.setX(cabeca.getX()-1);
                    newCoordinate.setY(cabeca.getY());

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    break;

                case D:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro //cauda
                    newCoordinate.setX(cabeca.getX()+1);
                    newCoordinate.setY(cabeca.getY());

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    break;

                default:
                    break;
            }
        //}
    }

    public boolean engordaCobra(Food food, Board board, Snake snake){
        //quando a cobra come a semente, tem que gerar outra //chamar a funcao de gerar seed obrigatoriamente cria uma semente em uma posicao livre
        if(getCabeca().equals(food.getSeed())){
            food.geraSeed(board, snake);
            board.alteraPonto(food);
            return true;
        }
        return false;
    }

    //retorna primeiro da lista 
    public Coordenada getCauda() { 
        return getposicoesCoord().getFirst();
    }

    public Coordenada getCabeca(){
        return cabeca; //getposicoesCoord().getLast();
    }

    public Deque<Coordenada> getposicoesCoord(){
        return listaCorpo;
    }
    
}