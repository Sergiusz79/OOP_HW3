package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.logging.*;


public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try (FileInputStream ins = new FileInputStream("d:\\Users\\Admin\\OneDrive\\Документы\\Учёба" +
                "\\Programming\\OOP_HW3\\src\\Properties\\log.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Main.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game;
        try {
                logger.info(" Start ");
                Scanner in = new Scanner(System.in);
                System.out.println("Выберете вариант игры: \n 1-numbers \n 2-RU Alphabet \n 3-EN Alphabet");
                int num = in.nextInt();
                game = selectVariantGame(num);
                if (num == 1) {
                    logger.info(" Select variant game - Numbers ");
                }
                if (num == 2) {
                    logger.info(" Select variant game - RU Alphabet ");
                }
                if (num == 3) {
                    logger.info(" Select variant game - EN Alphabet ");
                }
                logger.info(" GameStatus = " + game.getGameStatus());

                game.start(5, 2);

                while (!game.getGameStatus().equals(GameStatus.WINNER)
                        && !game.getGameStatus().equals(GameStatus.LOSE)) {

                    System.out.println("Введите вариант ответа: ");
                    logger.info(" User input answer ");
                    String scannerWord = in.next();

                    Answer answer = game.inputValue(scannerWord);

                    System.out.println(answer);
                    logger.info(" " + answer);
                }
                System.out.println(game.getGameStatus());
                logger.info(" GameStatus = " + game.getGameStatus());

            game.setGameStatusEnd();
            System.out.println(game.getGameStatus());
            logger.info(" GameStatus = " + game.getGameStatus());

        } catch (Exception e) {

            logger.log(Level.WARNING, "что-то пошло не так", e);

        }
    }


    protected static Game selectVariantGame(int numVariantGame) {
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

