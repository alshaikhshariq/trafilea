package com.test.trafilea.part2.controller;

import com.test.trafilea.part2.service.NumberCollectionService;
import com.test.trafilea.part2.service.NumberTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/part2")
public class NumberController {

    private final NumberTypeService numberTypeService;
    private final NumberCollectionService numberCollectionService;

    public NumberController(NumberTypeService numberTypeService, NumberCollectionService numberCollectionService) {
        this.numberTypeService = numberTypeService;
        this.numberCollectionService = numberCollectionService;
    }

    @PostMapping("/number")
    public ResponseEntity<String> saveNumber(@RequestParam int number) {
        String value = numberTypeService.determineNumberType(number);
        numberCollectionService.saveNumber(number, value);
        return ResponseEntity.ok("Number saved successfully.");
    }

    @GetMapping("/get/{number}")
    public ResponseEntity<String> getNumberDetails(@PathVariable int number) {
        String value = numberCollectionService.getNumber(number);
        if (value != null) {
            return ResponseEntity.ok(value);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public Map<Integer, String> getNumberCollection() {
        return numberCollectionService.getAllNumbers();
    }
}
