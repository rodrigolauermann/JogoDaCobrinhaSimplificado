package io.github.rodrigolauermann.jogodacobrinha;
import java.util.Scanner;

import io.github.rodrigolauermann.jogodacobrinha.snake.Game;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        game.rodaJogo(input);
        
    }
}

/*
        char [][] abacate = new char [1][1];
        System.out.println(abacate.length);
        abacate[0][0] = 'a';
        System.out.println(abacate[0][0]);/*

*/