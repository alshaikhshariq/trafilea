package com.test.trafilea.part1.service;

public class Type1 implements RuleInterface {


    @Override
    public String Apply(int number) {
        return "Type1";
    }

    @Override
    public boolean DoesNotApply(int number) {
        return number % 3 == 0;
    }
}
