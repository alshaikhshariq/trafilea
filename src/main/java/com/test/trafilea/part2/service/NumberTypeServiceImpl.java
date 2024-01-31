package com.test.trafilea.part2.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class NumberTypeServiceImpl implements NumberTypeService {

    private static final Map<Predicate<Integer>, String> typeMap = new HashMap<>();

    static {
        typeMap.put(n -> n % 3 == 0 && n % 5 != 0, "Type 1");
        typeMap.put(n -> n % 3 != 0 && n % 5 == 0, "Type 2");
        typeMap.put(n -> n % 3 == 0 && n % 5 == 0, "Type 3");
    }

    @Override
    public String determineNumberType(int number) {

        return typeMap.entrySet().stream()
                .filter(entry -> entry.getKey().test(number))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(String.valueOf(number));

    }
}
