package org.example;

public class Answer {
    private String userInput;
    private Integer bull;
    private Integer cow;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void setBull(Integer bull) {
        this.bull = bull;
    }

    public void setCow(Integer cow) {
        this.cow = cow;
    }

    public String getUserInput() {
        return userInput;
    }

    public Integer getBull() {
        return bull;
    }

    public Integer getCow() {
        return cow;
    }

    @Override
    public String toString() {
        return "User Answer = " + userInput + "\n" +
                "Bull = " + bull + "\n" +
                "Cow = " + cow;
    }

    public Answer(String userInput, Integer bull, Integer cow) {
        this.userInput = userInput;
        this.bull = bull;
        this.cow = cow;
    }

}
