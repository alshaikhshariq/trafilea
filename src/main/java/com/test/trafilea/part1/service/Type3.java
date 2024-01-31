package com.test.trafilea.part1.service;

public class Type3 implements RuleInterface {


    @Override
    public String Apply(int number) {
        return "Type3";
    }

    @Override
    public boolean DoesNotApply(int number) {
        return number % 3 == 0 && number % 5 == 0;
    }
}

