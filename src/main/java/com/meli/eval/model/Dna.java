package com.meli.eval.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dna {
    public final Integer size;
    private final List<NitrogenNucleobase> code;

    public Dna(String[] dna) throws Exception {
        this.size = dna.length;
        if (Stream.of(dna).anyMatch((gene) -> gene.length() != this.size)) {
            throw new Exception("Not valid DNA structure");
        }
        this.code = Stream.of(dna)
                .flatMapToInt(String::chars)
                .mapToObj(e -> {
                    switch (e) {
                        case 'C': return NitrogenNucleobase.C;
                        case 'G': return NitrogenNucleobase.G;
                        case 'A': return NitrogenNucleobase.A;
                        case 'T': return NitrogenNucleobase.T;
                        default: throw new RuntimeException("Not valid DNA structure");
                    }
                })
                .collect(Collectors.toList());
    }

    public  NitrogenNucleobase getNnb(NnbPosition pos) {
        return getNnb(pos.x, pos.y);
    }

    public boolean isValidPosition(NnbPosition pos) {
        return isValidPosition(pos.x, pos.y);
    }

    public boolean isValidPosition(Integer x, Integer y) {
        return x < this.size && y < this.size;
    }

    public NitrogenNucleobase getNnb(Integer x, Integer y) {
        if (!this.isValidPosition(x,y)) {
            throw new RuntimeException("Invalid petition");
        }
        return this.code.get( x + ( y * this.size ) );
    }

    public Long getIndexSize() {
        return (long) this.size * this.size;
    }

    public NnbPosition getPosByIndex( Long idx ) {
        var x = idx % this.size;
        var y = idx / this.size;
        return new NnbPosition((int) x,(int) y);
    }
}
