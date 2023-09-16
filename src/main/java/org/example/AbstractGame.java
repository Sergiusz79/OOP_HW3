package org.example;

import java.util.List;
import java.util.Random;

public abstract class  AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String word;
    GameStatus gameStatus = GameStatus.INIT;


    /**
     * @apiNote метод предзаполняет слова компьютера
     * @return
     */
    public String generateWord(){
        List<String> alphavit = generateCharList();
        Random r = new Random();
        String result = "";
        for (int i = 0; i < sizeWord; i++) {
            int index = r.nextInt(alphavit.size());
            result = result.concat(alphavit.get(index));
            alphavit.remove(index);
        }
        return result;
    }

    abstract List<String> generateCharList();

    @Override
    public void start(Integer sizeWord, Integer maxTry) {

        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        this.word = generateWord();
        gameStatus = GameStatus.START;
        System.out.println("подсказка: " + word);
    }

    @Override
    public Answer inputValue(String value) {
        maxTry--;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == value.charAt(i)){
                bulls++;
                cows++;
            } else{
                for (int j = 0; j < word.length(); j++) {
                    if(word.charAt(j) == value.charAt(i)){
                        cows++;
                    }
                }
            }
        }
        if(word.length() == bulls ){
            gameStatus = GameStatus.WINNER;
        }
        if(maxTry == 0 && !gameStatus.equals(GameStatus.WINNER)){
            gameStatus = GameStatus.LOSE;
        }

        return new Answer(value,bulls,cows);
    }


    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void selectVariantGame(int x){
        if (x == 1) {new GameNumber();}
        else if (x == 2) {new RUGame();}
        else if (x == 3) {new ENGame();}
        return;
        }

    }






