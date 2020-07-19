package com.meli.eval.utils;

import com.meli.eval.model.Dna;
import com.meli.eval.model.Gene;
import com.meli.eval.model.NitrogenNucleobase;
import com.meli.eval.model.NnbPosition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DnaPrinter {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static void printDna(Dna dna, List<Gene> mutantGenes){
        List<NnbPosition> solutions = mutantGenes.stream()
                .flatMap( g -> g.getNnbs().stream() )
                .collect(Collectors.toList());
        IntStream.range(0,dna.size)
            .forEach( y -> {
                var row = IntStream.range(0,dna.size)
                  .mapToObj( x -> formatNnb(dna.getNnb(x,y), solutions.contains(new NnbPosition(x, y))))
                  .collect(Collectors.joining( " "));
                System.out.println(row);
            });
    }

    private static String formatNnb(NitrogenNucleobase nnb, boolean isSolution) {
        switch (nnb) {
            case A: return isSolution ? ANSI_BLACK + ANSI_GREEN_BACKGROUND + "A" + ANSI_RESET : ANSI_GREEN + "A" + ANSI_RESET;
            case T: return isSolution ? ANSI_BLACK + ANSI_BLUE_BACKGROUND +  "T" + ANSI_RESET : ANSI_BLUE + "T" + ANSI_RESET;
            case C: return isSolution ? ANSI_BLACK + ANSI_CYAN_BACKGROUND +  "C" + ANSI_RESET : ANSI_CYAN + "C" + ANSI_RESET;
            case G: return isSolution ? ANSI_BLACK + ANSI_RED_BACKGROUND +   "G" + ANSI_RESET : ANSI_RED + "G" + ANSI_RESET;
        }
        return ANSI_RED_BACKGROUND + ANSI_YELLOW + "?" + ANSI_RESET;
    }
}
