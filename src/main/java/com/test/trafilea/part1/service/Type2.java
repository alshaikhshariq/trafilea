package com.test.trafilea.part1.service;

public class Type2 implements RuleInterface {


    @Override
    public String Apply(int number) {
        return "Type2";
    }

    @Override
    public boolean DoesNotApply(int number) {
        return number % 5 == 0;
    }
}
