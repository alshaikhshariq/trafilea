package com.test.trafilea.part1.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluatorService {

    private final List<RuleInterface> _rules = new ArrayList<>();

    public EvaluatorService() {
        _rules.add(new Type3());
        _rules.add(new Type1());
        _rules.add(new Type2());
        _rules.add(new Type4());
    }

    public String evaluate(int number) {
        for (RuleInterface rule : _rules) {
            {
                if (rule.DoesNotApply(number)) {
                    return rule.Apply(number);
                }
            }
        }
        return null;
    }

    public List<String> values() {
        List<String> values = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            values.add(evaluate(i));
        }
        return values;
    }
}
