package com.meli.eval.controller;

import com.meli.eval.exception.HumanFound;
import com.meli.eval.exception.MalformedDna;
import com.meli.eval.model.dto.DnaDto;
import com.meli.eval.model.dto.StatsDto;
import com.meli.eval.service.MutantFinderService;
import com.meli.eval.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private ValidationService service;

    private static final String welcome = "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP////////////////////////////////////////" +
            "//////////////////////////////////////////////2wBDAf////////////////////////////////////////////////////" +
            "//////////////////////////////////wgARCABkAGQDASIAAhEBAxEB/8QAFgABAQEAAAAAAAAAAAAAAAAAAAEC/8QAFAEBAAAAAA" +
            "AAAAAAAAAAAAAAAP/aAAwDAQACEAMQAAABpCoKyNENRk0zTQJLACULJQAUoJLAlCaMlBC1DQJnWC1DUBZABqDQIZKQpCggLc6KCZ1ACV" +
            "STQy1DOlKAAAAAAAD/xAAUEAEAAAAAAAAAAAAAAAAAAABw/9oACAEBAAEFAin/xAAUEQEAAAAAAAAAAAAAAAAAAABQ/9oACAEDAQE/AU" +
            "f/xAAUEQEAAAAAAAAAAAAAAAAAAABQ/9oACAECAQE/AUf/xAAUEAEAAAAAAAAAAAAAAAAAAABw/9oACAEBAAY/Ain/xAAkEAACAQMDBA" +
            "MBAAAAAAAAAAAAAREQITFBcfAgUWGBocHRQP/aAAgBAQABPyElEqkonolEomjwdrCvoLB6I8nwfJyBYJ8GtHgiYEhYOyIM4IdFgg1oyE" +
            "Qi2D6wamx9C7kIhEdEeT4ObmdD0R6EZ1I6GaG9PA7YJELueRO9GfZFiEIZrTNy8mtXii4j0Tgz+jyc3Mfh7ObizR0g0ETFixxEEGxejw" +
            "QiEaCsNmNzuItBYVHgeghYLi2HRo9UXFRly5Fi4qcRuR2I/g//2gAMAwEAAgADAAAAEIFHNBHACJICIEAPGEOENANLDFONANNDFBDAHH" +
            "NGJNAAAAAAAAP/xAAUEQEAAAAAAAAAAAAAAAAAAABQ/9oACAEDAQE/EEf/xAAUEQEAAAAAAAAAAAAAAAAAAABQ/9oACAECAQE/EEf/xA" +
            "AnEAEAAQMDAwQDAQEAAAAAAAABEQAhMUFh8FGB0RBxobGRweHxMP/aAAgBAQABPxBYu1vfD4re+HxQjitz4ahEzahHFKETrj03vh8Vvf" +
            "D4oDh9M3b7KUCwyeNqQogW28Vm9/FKXsvMPXfFEgWB0cfdFxtl49qCJvI+PvNF2Z07Oe2tQEoN/FAUIXdvFGft49M3b7KUIRY/RSDLGK" +
            "Eo38UovQYfgpTYiNDhUwcHfjQEwl88igRVTHjahKN/FDEbZ5pRn7ePSEM4oTC87UhledqLILfnTahFh1Ru9/wdKkHPTxUWi41XM/G2lK" +
            "QXuMbn9KkstTx4pK6vO1CYXnagFyfRJIpsAGSFLRu/eabL274+6E1BbwP6piZsQycf3NDq7s/qhZkUdDHwVdK7rn+YoIxKkWdNO1Jo7M" +
            "/uugYjMa+8NBBEzv6LBNSSTEK+0U4+/mkcwQ6cNamUNJgNqEmGnP3SlFhE9fuoxDOL8mlLBiLH4qzYfnl6IBGpL91IukePRQLTYk03/n" +
            "53q4Jn/asTLHNql2tG5yOlIVGwXtmfnrV4yQx5qS8ZKRHU/jpWCxMWPzvVodZvsz/vWry2to+mbt9lXsooe8R4pGZA3Y57a0tksObUWQ" +
            "vYnb3pJREa6p246VcIYtn80zoO6fJ45oYTM6XY34a0E3YO+fuonaNY/LTpOuaCIVSMsxp6CRKC0PSKjL00L0zKWX+lQRDMQ+8NAuTJ03" +
            "vRNiHnemSzrbZpf8nWpSlpKWE2oVAUQXnjR1InNvTN2+yoAVbnNKEwvO1BLDr4oMpt/JqS2lQK0ugeG+tRAtUl94aYN+lKEDy21BCRZC" +
            "e+elKQfTN2+ygpAW36KKNxx4rN7+Kbluk7wn1EVB0B1j+UWRJxp3onVTFnXa9RX3xG1TKJ2dc7VN++P1+qynFuzntr6CRKjrOdqjrOdq" +
            "lItK+KiMSREctQgCryXtqVCzMfpy3Woki7nalTMDnehzKmZ5aoJnX/AL//2Q==";

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaDto code) throws MalformedDna, HumanFound {
        service.validate(code.getDna());
        return ResponseEntity.ok(welcome);
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsDto> stats() {
        var stats = service.getStats();
        return ResponseEntity.ok(stats);
    }
}

