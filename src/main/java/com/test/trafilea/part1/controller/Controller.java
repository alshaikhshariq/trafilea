package com.test.trafilea.part1.controller;

import com.test.trafilea.part1.service.EvaluatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("Controller")
@RequestMapping(value = "/part1")
public class Controller {

    private final EvaluatorService evaluatorService;

    public Controller(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @GetMapping("/printAll")
    public ResponseEntity<List<String>> printAllNumbers() {
        return ResponseEntity.ok(evaluatorService.values());
    }

}
