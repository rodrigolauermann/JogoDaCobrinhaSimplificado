package io.github.rodrigolauermann.jogodacobrinha.snake;

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class Snake{
    
    private int tamanhoAtual;
    private Deque<Coordenada> listaCorpo; //x é baixo/cima y é esq/direita
    private Coordenada cabeca; //ultima da queue //ex. remove varias vezes e vai ser a ultima //lembrar que cabeca é apenas referencia
    private Moviment lastMoviment;
    private Moviment listaPermissaoMovimento[];

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
            cabeca = coordenada;
            tamanhoAtual++;
        }
        lastMoviment = Moviment.W;
    }

    //movimento da cobra pelo input do usuario
    public Moviment changeLastMoviment(Scanner input){
        //verificar antes de colocar em lastMoviment
        String move = " ";
        Moviment novoMoviment = lastMoviment;

        permissaoMovimento();

        while(lastMoviment.equals(novoMoviment)){
            //o usuario insere uma direcao: A W S D 
            move = input.nextLine();

            if(move.toUpperCase().equals("A")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.A)){
                        lastMoviment = Moviment.A;
                    }
                }
            }
            else if(move.toUpperCase().equals("W")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.W)){
                        lastMoviment = Moviment.W;
                    }
                }
            }
            else if(move.toUpperCase().equals("S")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.S)){
                        lastMoviment = Moviment.S;
                    }
                }
            }
            else if(move.toUpperCase().equals("D")){
                for (Moviment permitedMov : listaPermissaoMovimento) {
                    if(permitedMov.equals(Moviment.D)){
                        lastMoviment = Moviment.D;
                    }
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

    //pegaMovimento vai passar o ultimo movimento. Com base nele, alteramos a lista (corpo cobra) e atualizamos a referência para cabeca

    //permissoesMovimento -> changeLastMoviment -> moveCobra

    public void moveCobra(){

        boolean verifica = false;
        Coordenada newCoordinate = new Coordenada();

        while(verifica == false){
            switch (lastMoviment) {
                case W:
                    listaCorpo.poll(); //remove o primeiro
                    newCoordinate.setX(cabeca.getX());
                    newCoordinate.setY(cabeca.getY()+1);

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last
                    verifica = true;
                    break;
            
                case S:
                    listaCorpo.poll(); //remove o primeiro
                    newCoordinate.setX(cabeca.getX());
                    newCoordinate.setY(cabeca.getY()-1);

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);//basicamente add first remove last                
                    verifica = true;
                    break;

                case A:
                    listaCorpo.poll(); //remove o primeiro
                    newCoordinate.setX(cabeca.getX()-1);
                    newCoordinate.setY(cabeca.getY());

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    verifica = true;
                    break;

                case D:
                    listaCorpo.poll(); //remove o primeiro
                    newCoordinate.setX(cabeca.getX()+1);
                    newCoordinate.setY(cabeca.getY());

                    cabeca = newCoordinate;

                    listaCorpo.add(newCoordinate);
                    verifica = true;
                    break;

                default:
                    break;
            }
        }
    }

    //retorna ultimo da lista e não head (que é o início)
    public Coordenada getCabeca() { 
        return cabeca;
    }

    public Deque<Coordenada> getposicoesCoord(){
        return listaCorpo;
    }

    //funciona isso?
    public String posicoesCobra(){
        return listaCorpo.toString();
    }

    public int getTamanhoAtual() {
        return tamanhoAtual;
    }

    public void cresce(){ //talvez nao precise
        tamanhoAtual++;
    }
    
}