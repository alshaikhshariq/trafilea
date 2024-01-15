package com.test.trafilea;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {

    private final NumberTypeService numberTypeService;
    private final NumberCollectionService numberCollectionService;

    public NumberController(NumberTypeService numberTypeService, NumberCollectionService numberCollectionService) {
        this.numberTypeService = numberTypeService;
        this.numberCollectionService = numberCollectionService;
    }

    @PostMapping("/save/{number}")
    public ResponseEntity<String> saveNumber(@PathVariable int number) {
        String value = numberTypeService.determineNumberType(number);
        numberCollectionService.saveNumber(number, value);
        return ResponseEntity.ok("Number saved successfully.");
    }

    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberDetails(@PathVariable int number) {
        String value = numberCollectionService.getNumber(number);
        if (value != null) {
            return ResponseEntity.ok(value);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/collection")
    public Map<Integer, String> getNumberCollection() {
        return numberCollectionService.getAllNumbers();
    }
}
