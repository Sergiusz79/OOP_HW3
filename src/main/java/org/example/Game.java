package org.example;

public interface Game {
    // a)	Интерфейс взаимодействий Game (должны быть описаны сигнатуры методов start, inputValue, getGameStatus)

    /**
     *
     * @param sizeWord Длина слова
     * @param maxTry Количество попыток
     */
    void start(Integer sizeWord, Integer maxTry);

    /**
     *
     * @param value
     * @return
     */
    Answer inputValue(String value);

    GameStatus getGameStatus();

    GameStatus setGameStatusEnd();


}