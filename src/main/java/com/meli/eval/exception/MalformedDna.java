package com.meli.eval.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MalformedDna extends RuntimeException{

    public MalformedDna() {
        super("Corrupted or malformed dna");
    }
}
