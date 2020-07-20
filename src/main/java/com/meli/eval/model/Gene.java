package com.meli.eval.model;

import lombok.Data;

import java.util.List;

/**
 * Represent a list of positions of {@link NitrogenNucleobase}
 */
@Data
public class Gene {
    private final List<NnbPosition> nnbs;

    /**
     * Check for genes intersections or "path collisions"
     * @param otherGene the gene to compare
     * @return the amount of intersections between the two genes
     */
    public Long intersect(Gene otherGene) {
        return this.nnbs.stream().filter(otherGene.nnbs::contains).count();
    }

}
