package org.example;

import java.io.IOException;
import java.util.logging.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.println("Выберете вариант игры: \n 1-numbers \n 2-RU Word \n 3-EN Word");
        int num = in.nextInt();
        Game game = selectVariantGame(num);
        game.start(5, 2);

        while (!game.getGameStatus().equals(GameStatus.WINNER)
                && !game.getGameStatus().equals(GameStatus.LOSE)) {
            System.out.println("Введите вариант ответа: ");
            String scannerWord = in.next();
            Answer answer = game.inputValue(scannerWord);
            System.out.println(answer);
        }
        System.out.println(game.getGameStatus());


        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        logger.info("Test 1");

    }


    public static Game selectVariantGame(int numVariantGame) {
        switch (numVariantGame) {
            case 1:
                return new NumberGame();
            case 2:
                return new RUGame();
            case 3:
                return new ENGame();
            default:
                throw new IllegalStateException("Unexpected value: " + numVariantGame);
        }


    }


}

