package com.test.trafilea.part2.service;

import java.util.Map;

public interface NumberCollectionService {

    void saveNumber(int number, String value);

    String getNumber(int number);

    Map<Integer, String> getAllNumbers();
}
