package com.meli.eval.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class HumanFound extends Exception {

    public HumanFound() {
        super("I’ve been at the mercy of men just following orders. Never again");
    }
}
