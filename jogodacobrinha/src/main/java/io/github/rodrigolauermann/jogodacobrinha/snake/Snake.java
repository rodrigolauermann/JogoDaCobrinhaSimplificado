package io.github.rodrigolauermann.jogodacobrinha.snake;

import java.util.Scanner;
import java.util.LinkedList;
//import java.util.List;
import java.util.Queue;

public class Snake{
    
    private int tamanhoAtual;
    private Queue<Coordenada> listaCoord; //x é baixo/cima y é esq/direita
    private Coordenada cabeca; //ultima da queue //ex. remove varias vezes e vai ser a ultima //lembrar que cabeca é apenas referencia
    private Coordenada cabecaAnterior;
    //private char lastMoviment;
    private Board board;
    private Moviment lastMoviment;

    public Snake(){
        listaCoord = new LinkedList<>();
        posicoesCobraInicial();
    }   

    public void posicoesCobraInicial(){
        int alturaInicial = board.getAltura()/2;
        int larguraInicial = 1;
        int larguraFinal = larguraInicial+3;
        
        cabeca.setY(alturaInicial);

        for(int i=larguraInicial; i<larguraFinal; i++){
            cabeca.setY(larguraFinal);
            listaCoord.add(cabeca);
            tamanhoAtual++;
        }
    }

    public void pegaMoviment(Scanner input){
        //verificar antes de colocar em lastMoviment
        lastMoviment = Moviment.A;
        
    }

    public Moviment getLastMoviment() {
        return lastMoviment;
    }

    /* 
    public int permissaoMovimento(){
        //cabeca = listaCoord.peek(); //ve e armazena em cabeca qual foi a ultima coordenada adicionada
        switch (ca) {
            case value:
                
                break;
        
            default:
                break;
        }
        //w s a d 
        //tem que ver o contrario:
        //se foi w entao nao pode w e s
        //se foi s nao pode s e w
        //se foi a nao pode a e d
        //se foi d nao pode d e a
        return 0;
    }
    */

    public void par(Scanner input){
        //String tecla;
        boolean verifica = false;

        while(verifica == false){
            System.out.println("Digite a tecla: ");
            //tecla = input.nextLine();

            switch (tecla) {
                case "w":
                    listaCoord.poll(); //remove o primeiro
                    cabeca.setY(cabeca.getY()+1);
                    listaCoord.add(cabeca);//basicamente add first remove last
                    verifica = true;
                    break;
            
                case "s":
                    listaCoord.poll(); //remove o primeiro
                    cabeca.setY(cabeca.getY()-1);
                    listaCoord.add(cabeca);//basicamente add first remove last                
                    verifica = true;
                    break;

                case "a":
                    listaCoord.poll(); //remove o primeiro
                    cabeca.setX(cabeca.getX()-1);
                    listaCoord.add(cabeca);
                    verifica = true;
                    break;

                case "d":
                    listaCoord.poll(); //remove o primeiro
                    cabeca.setX(cabeca.getX()+1);
                    listaCoord.add(cabeca);
                    verifica = true;
                    break;

                default:
                    break;
                }
        }
    }

    public Coordenada getCabeca() {
        return cabeca;
    }

    public String posicoesCobra(){
        return listaCoord.toString();
    }

    public int getTamanhoAtual() {
        return tamanhoAtual;
    }

    public void cresce(){
        tamanhoAtual++;
    }
    
}

/*

    cabecaAnterior.setX(cabeca.getX());
    cabecaAnterior.setY(cabeca.getY());
*/