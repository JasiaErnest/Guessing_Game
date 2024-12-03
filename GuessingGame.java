//Name: Juliana Serrano, Jasia Ernest, Thuong Nguyen
//Date: 09/30/2024
//Class: CS &145
//Assignment: Lab 1 - Guessing Game
/* Purpose:  This program allows the user to play a simple guessing game. 
This program is supposed to choose a random number between 1 and a desired number. 
It should let the user guess, giving clues if the number is larger or smaller. */

import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    //These constants are defined in the class because of global accesibility and readability
    public static int MAX_NUMBER = 9999;
    
    public static void main(String[] args) {
        //Puts scanner in main then calls the scanner to future methods
        Scanner input = new Scanner(System.in);
        //Variables used to track game results. In the main method so every method can call them
        float totalGames = 0;
        float totalGuess = 0;
        int bestGame = MAX_NUMBER; 

        intro();
        //Boolean to see if player wants to play again
        boolean playAgain = true;
        while (playAgain) {
            int guessNum = game(input);
            totalGuess += guessNum;
            totalGames++;
            
            playAgain = askToPlayAgain(input);
            //Tracks the user's best game
            if (totalGames == 1 || bestGame > guessNum) {
                bestGame = guessNum;
            }
        }

        results(totalGames, totalGuess, bestGame);
    }    
    //Introduces and explains the game to the player
    public static void intro() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and a number");
        System.out.println("of your choice and you will be able to guess until");
        System.out.println("you get it. For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower");
        System.out.println("than your guess.");
    }
    //Plays through the game and tracks the number of guesses per game
    public static int game(Scanner input) {
        Random random = new Random();

        //Gets guessing range from the player
        System.out.print("\nWhat range would you like to guess from: 1 to ");
        int range = input.nextInt();

        System.out.printf("%nI'm thinking of a number between 1 and %d...%n", range);
        int randomNumber = 1 + random.nextInt(range);
        int guessNum = 0;

        while (true) {
            System.out.print("Your Guess: ");
            int userGuess = input.nextInt();
            guessNum++; //Helps track total guesses in a game

            //Takes guess and checks how it compares to the answer
            if (userGuess == randomNumber) {
                if (guessNum == 1)
                    System.out.println("YOU GOT IT RIGHT ON THE FIRST TRY");
                    System.out.printf("You got it right in %d guess%s.%n", guessNum, (guessNum == 1 ? "" : "es"));
                    //Changes guess to guesses depending on how many guesses is taken for the player to get it right
                        break;
            } else if (userGuess < 1 || userGuess > MAX_NUMBER) {
                System.out.println("Invalid Response");
            } else if (userGuess > randomNumber) {
                System.out.println("The number is lower.");
            } else {
                System.out.println("The number is higher.");
            }
        }

        return guessNum;
    }
    //Boolean to ask player to play again instead of using for loop
    public static boolean askToPlayAgain(Scanner input) {
        while (true) {
            System.out.print("\nDo you want to play again? (Y/N)");
            String response = input.next();
            String firstLetter = response.toUpperCase().substring(0, 1);
            //exits game if player asnwers with a word beginning with n
            if (firstLetter.equals("N")) {
                return false;
            } else if ("y".equalsIgnoreCase(response)) {
                //contiues game if player answers with a word beginning with y
                return true;
            } else {
                //prints invalid response which asks the player to try again
                System.out.println("\nYour response is invalid. Please try again.");
            }
        }
    }
    //Reports back game data to user
    public static void results(float totalGames, float totalGuess, int bestGame) {
        //Calculates average amount of guesses
        float average = totalGames > 0 ? totalGuess / totalGames : 0;
        System.out.println("\nOverall results: ");
        System.out.printf("\ttotal games = %.0f", totalGames);
        System.out.printf("%n\ttotal guesses = %.0f", totalGuess);
        System.out.printf("%n\tguesses/game = %.1f", average);
        System.out.printf("%n\tbest game = %d", bestGame);
    }
}
