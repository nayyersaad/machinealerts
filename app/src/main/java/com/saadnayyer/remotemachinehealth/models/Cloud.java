package com.saadnayyer.remotemachinehealth.models;

public class Cloud {
    private double firstNumber, secondNumber, answer;

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double add(){
        return this.firstNumber + this.secondNumber;
    }

    public double subtract(){
        return this.secondNumber - this.firstNumber;
    }

    public double multiply(){
        return this.firstNumber * this.secondNumber;
    }

    public double divide(){
        return this.secondNumber / this.firstNumber;
    }
}
