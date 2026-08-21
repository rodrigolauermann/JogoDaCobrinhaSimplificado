package io.github.rodrigolauermann.jogodacobrinha.snake;

//import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class Snake{
    
    //private int tamanhoAtual;
    private Deque<Coordenada> listaCorpo; //x é baixo/cima y é esq/direita
    private Coordenada cauda; //ultima da queue //ex. remove varias vezes e vai ser a ultima //lembrar que cauda é apenas referencia
    private Moviment lastMoviment;
    private Moviment listaPermissaoMovimento[];
    //private Coordenada cabeca; //foi a primeira adicionada

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
            cauda = coordenada;

            //tamanhoAtual++;
        }
        lastMoviment = Moviment.W;
    }

    //movimento da cobra pelo input do usuario
    public Moviment changeLastMoviment(String move){
        //verificar antes de colocar em lastMoviment
        //String move = " ";
        //Moviment novoMoviment = lastMoviment;

        permissaoMovimento();

        //aterei para if, porque se o cara nao inserir corretamente nada ocorre de mudanca de movimento
        //while(lastMoviment.equals(novoMoviment)){

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
        //while(verifica == false){
            switch (lastMoviment) {
                case W:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cauda.getX());
                    newCoordinate.setY(cauda.getY()-1);//                    newCoordinate.setY(cauda.getY()+1);

                    cauda = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last
                    //verifica = true;
                    break;
            
                case S:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cauda.getX());
                    newCoordinate.setY(cauda.getY()+1); //                    newCoordinate.setY(cauda.getY()-1);

                    cauda = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last                
                    //verifica = true;
                    break;

                case A:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cauda.getX()-1);
                    newCoordinate.setY(cauda.getY());

                    cauda = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    //verifica = true;
                    break;

                case D:
                    if(engordaCobra==false){listaCorpo.poll();} //remove o primeiro
                    newCoordinate.setX(cauda.getX()+1);
                    newCoordinate.setY(cauda.getY());

                    cauda = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    //verifica = true;
                    break;

                default:
                    break;
            }
        //}
    }

    public boolean engordaCobra(Food food, Board board, Snake snake){
        //quando a cobra come a food, tem que gerar outra  //chamar a funcao de gerar seed obrigatoriamente ccria uma semente em uma posicao livre
        if(getCabeca().equals(food.getSeed())){
            food.geraSeed(board, snake);
            board.alteraPonto(food);
            return true;
        }
        return false;
    }

    //retorna ultimo da lista e não head (que é o início)
    public Coordenada getcauda() { 
        return cauda;
    }

    public Coordenada getCabeca(){
        return getposicoesCoord().getFirst();
    }

    public Deque<Coordenada> getposicoesCoord(){
        return listaCorpo;
    }

    //funciona isso?
    //public String posicoesCobra(){
    //    return listaCorpo.toString();
    //}

    /*
    public int getTamanhoAtual() {
        return tamanhoAtual;
    }
    */

    public void cresce(Food food){ //talvez nao precise
        if(cauda.equals(food.getSeed())){

        }
    }
    
}