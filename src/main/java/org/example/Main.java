package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new GameNumber();

        game.start(5,2);
        Scanner scanner = new Scanner(System.in);

        while (!game.getGameStatus().equals(GameStatus.WINNER)
        &&!game.getGameStatus().equals(GameStatus.LOSE)){
            String scannerWord = scanner.nextLine();
            Answer answer = game.inputValue(scannerWord);
            System.out.println("answer = " + answer);
        }
        System.out.println(game.getGameStatus());
    }
}

