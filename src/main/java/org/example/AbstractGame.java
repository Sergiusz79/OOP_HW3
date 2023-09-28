package org.example;

import lombok.Getter;

import java.util.List;
import java.util.Random;

public abstract class  AbstractGame implements Game {
    @Getter
    Integer sizeWord;
    @Getter
    Integer maxTry;
    String word;
    GameStatus gameStatus = GameStatus.INIT;


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










}






