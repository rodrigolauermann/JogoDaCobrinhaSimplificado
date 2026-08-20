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
