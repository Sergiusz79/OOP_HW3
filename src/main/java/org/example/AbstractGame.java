package org.example;

import lombok.Getter;

import java.io.FileInputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;

import static org.example.Main.selectVariantGame;

public abstract class  AbstractGame implements Game {
    @Getter
    Integer sizeWord;
    @Getter
    Integer maxTry;
    String word;
    GameStatus gameStatus = GameStatus.INIT;
    static Logger logger = Logger.getLogger(AbstractGame.class.getName());
    static {
        try (FileInputStream ins = new FileInputStream("d:\\Users\\Admin\\OneDrive\\Документы\\Учёба" +
                "\\Programming\\OOP_HW3\\src\\Properties\\log.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Main.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
//    String res;

    /**
     * @apiNote метод предзаполняет слова компьютера
     * @return слово для угадывания
     */

    public String generateWord(){
        List<String> alphabet = generateCharList();
        Random r = new Random();
        String result = "";
        for (int i = 0; i < sizeWord; i++) {
            int index = r.nextInt(alphabet.size());
            result = result.concat(alphabet.get(index));
            alphabet.remove(index);
        }
        return result;
    }


    abstract List<String> generateCharList();

    @Override
    public void start(Integer sizeWord, Integer maxTry) {

        this.sizeWord = sizeWord;
        Main.logger.info(" sizeWord = " + sizeWord);
        this.maxTry = maxTry;
        Main.logger.info(" maxTry = " + maxTry);
        this.word = generateWord();
        Main.logger.info(" GenerateWord = " + word);
        gameStatus = GameStatus.START;
        Main.logger.info(" GameStatus changed to = " + gameStatus);
        System.out.println("подсказка: " + word);
// Здесь мы генерируем слово и меняем статус с INIT на START
    }

    @Override
    public Answer inputValue(String value) {
        maxTry--;
        Main.logger.info(" Number of attempts = " + maxTry);
        int bulls = 0;
        Main.logger.info(" Number of bulls = " + bulls);
        int cows = 0;
        Main.logger.info(" Number of cows = " + cows);

        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == value.charAt(i)){
                Main.logger.info("Comparison of " + i + " elements ");
                bulls++;
                Main.logger.info(" Number of bulls = " + bulls);
                cows++;
                Main.logger.info(" Number of cows = " + cows);
            } else{
                for (int j = 0; j < word.length(); j++) {
                    if(word.charAt(j) == value.charAt(i)){
                        Main.logger.info("Comparison of " + j +" & " + i + " elements ");
                        cows++;
                        Main.logger.info(" Number of cows = " + cows);
                    }
                }
            }
        }
// Подводим итоги
        if(word.length() == bulls ){
            Main.logger.info("word.length = " + word.length() + " = " + "bulls = " + bulls);
            gameStatus = GameStatus.WINNER;
            Main.logger.info(" GameStatus changed to = " + gameStatus);
        }
        if(maxTry == 0 && !gameStatus.equals(GameStatus.WINNER)){
            Main.logger.info("All attempts are spent & gameStatus is not WINNER ");
            gameStatus = GameStatus.LOSE;
            Main.logger.info(" GameStatus changed to = " + gameStatus);
        }
        return new Answer(value,bulls,cows);

    }


    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public GameStatus setGameStatusEnd() {
        return gameStatus = GameStatus.END;
    }

//    @Override
//    public void restart(GameStatus gameStatus) {
//
//        if (gameStatus.equals(GameStatus.RESTART){
//
//        Scanner in = new Scanner(System.in);
//        System.out.println("{Хотите сыграть ещё? y/n");
//        String res = in.next();
//        if (res == "y") {
//
//            logger.info(" Start ");
//            Scanner enter = new Scanner(System.in);
//            System.out.println("Выберете вариант игры: \n 1-numbers \n 2-RU Alphabet \n 3-EN Alphabet");
//            int num = enter.nextInt();
//            Game game = selectVariantGame(num);
//            if (num == 1) {
//                logger.info(" Select variant game - Numbers ");
//            }
//            if (num == 2) {
//                logger.info(" Select variant game - RU Alphabet ");
//            }
//            if (num == 3) {
//                logger.info(" Select variant game - EN Alphabet ");
//            }
//
//
//            logger.info(" GameStatus = " + game.getGameStatus());
//
//            game.start(5, 2);
//
//            while (!game.getGameStatus().equals(GameStatus.WINNER)
//                    && !game.getGameStatus().equals(GameStatus.LOSE)) {
//
//                System.out.println("Введите вариант ответа: ");
//                logger.info(" User input answer ");
//                String scannerWord = in.next();
//
//                Answer answer = game.inputValue(scannerWord);
//
//                System.out.println(answer);
//                logger.info(" " + answer);
//            }
//            System.out.println(game.getGameStatus());
//            logger.info(" GameStatus = " + game.getGameStatus());
//            game.setGameStatusEnd();
//            System.out.println(game.getGameStatus());
//            logger.info(" GameStatus = " + game.getGameStatus());
//        }else {
//            gameStatus = GameStatus.END;
//        }
//    }
}






