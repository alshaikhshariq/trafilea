package com.test.trafilea.part2.service;

import com.test.trafilea.model.Value;
import com.test.trafilea.part2.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NumberCollectionServiceImpl implements NumberCollectionService {

    @Autowired
    private ValueRepository valueRepository;

    @Override
    public void saveNumber(int number, String numValue) {
        Map<Integer, String> numberCollection = new HashMap<>();
        Value value = valueRepository.findById(1L).orElse(null);
        if (value != null) {
            numberCollection.putAll(value.getValues());
        } else {
            value = new Value();
        }
        numberCollection.put(number, numValue);
        value.setValues(numberCollection);
        valueRepository.save(value);
    }

    @Override
    public String getNumber(int number) {
        List<Value> value = valueRepository.findAll();
        return value.get(0).getValues().get(number);
    }

    @Override
    public Map<Integer, String> getAllNumbers() {
        List<Value> value = valueRepository.findAll();
        return value.get(0).getValues();
    }
}
