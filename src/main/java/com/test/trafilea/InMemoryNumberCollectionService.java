package com.test.trafilea;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InMemoryNumberCollectionService implements NumberCollectionService {

    private final Map<Integer, String> numberCollection = new HashMap<>();

    @Override
    public void saveNumber(int number, String value) {
        numberCollection.put(number, value);
    }

    @Override
    public String getNumber(int number) {
        return numberCollection.get(number);
    }

    @Override
    public Map<Integer, String> getAllNumbers() {
        return new HashMap<>(numberCollection);
    }
}
