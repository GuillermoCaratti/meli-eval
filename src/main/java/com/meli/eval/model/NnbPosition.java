package com.meli.eval.model;

import lombok.Data;

/**
 * Represent a position of a {@link NitrogenNucleobase} in the {@link Dna}.
 */
@Data
public class NnbPosition {
    public final Integer x;
    public final Integer y;

    /**
     * Create a new positions from the current positions
     * @param x the amount of columns to move (increments to the right)
     * @param y the amount of row to move (increments to the bottom)
     * @return the new position
     */
    public NnbPosition move(Integer x, Integer y){
        return new NnbPosition(this.x + x, this.y + y);
    }
}
