package com.meli.eval.controller;

import com.meli.eval.model.dto.DnaDto;
import com.meli.eval.service.MutantFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantFinderService service;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaDto code) {
        try {
            if (service.isMutant(code.getDna())) {
                return ResponseEntity.ok("OK");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FORBIDDEN");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

