package com.meli.eval.model;

import com.meli.eval.exception.InvalidDnaPosition;
import com.meli.eval.exception.MalformedDna;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DNA is a sequence of {@link NitrogenNucleobase} on a NxN matrix.
 */
public class Dna {
    /**
     * The size of the DNA matrix
     */
    public final Integer size;

    /**
     * List of {@link NitrogenNucleobase} that represents the full DNA
     * sequence.
     *
     * Starts at the first row, then the second... until the end. The
     * transformation from two dimensions to one dimension is
     * created by (<code>x + y * size</code>)
     */
    private final List<NitrogenNucleobase> code;

    /**
     * Dna constructor with the DNA code sequence
     *
     * @param dna code sequence
     * @throws MalformedDna if is not a square matrix or has an a wrong letter
     */
    public Dna(String[] dna) throws MalformedDna {
        this.size = dna.length;
        if (Stream.of(dna).anyMatch((gene) -> gene.length() != this.size)) {
            throw new MalformedDna();
        }
        this.code = Stream.of(dna)
                .flatMapToInt(String::chars)
                .mapToObj(e -> {
                    switch (e) {
                        case 'C': return NitrogenNucleobase.C;
                        case 'G': return NitrogenNucleobase.G;
                        case 'A': return NitrogenNucleobase.A;
                        case 'T': return NitrogenNucleobase.T;
                        default: throw new MalformedDna();
                    }
                })
                .collect(Collectors.toList());
    }



    public boolean isValidPosition(NnbPosition pos) {
        return isValidPosition(pos.x, pos.y);
    }

    public boolean isValidPosition(Integer x, Integer y) {
        return x < this.size && y < this.size;
    }

    /**
     * Give the {@link NitrogenNucleobase} for a given position
     * @param pos the position on the dna
     * @return the corresponded {@link NitrogenNucleobase}
     * @throws InvalidDnaPosition if the position is invalid
     */
    public NitrogenNucleobase getNnb(NnbPosition pos) throws InvalidDnaPosition{
        return getNnb(pos.x, pos.y);
    }

    /**
     * Give the {@link NitrogenNucleobase} for a given position
     * @param x the column position on the dna matrix
     * @param y the row position on the dna matrix
     * @return the corresponded {@link NitrogenNucleobase
     * @throws InvalidDnaPosition if the position is invalid
     */
    public NitrogenNucleobase getNnb(Integer x, Integer y) throws InvalidDnaPosition {
        if (!this.isValidPosition(x,y)) {
            throw new InvalidDnaPosition();
        }
        return this.code.get( x + ( y * this.size ) );
    }

    /**
     * @return the total amount of indexes for the {@link NitrogenNucleobase} of the current DNA
     */
    public Long getIndexSize() {
        return (long) this.size * this.size;
    }

    /**
     * Obtains the position by index
     * @param idx of the position in the Dna
     * @return the {@link NnbPosition} in the Dna
     */
    public NnbPosition getPosByIndex( Long idx ) {
        var x = idx % this.size;
        var y = idx / this.size;
        return new NnbPosition((int) x,(int) y);
    }

}
