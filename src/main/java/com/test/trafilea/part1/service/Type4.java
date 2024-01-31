package com.test.trafilea.part1.service;

public class Type4 implements RuleInterface {


    @Override
    public String Apply(int number) {
        return String.valueOf(number);
    }

    @Override
    public boolean DoesNotApply(int number) {
        return number % 3 != 0 && number % 5 != 0;
    }
}


