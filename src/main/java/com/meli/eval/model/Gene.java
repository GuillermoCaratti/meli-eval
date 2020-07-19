package com.meli.eval.model;

import lombok.Data;

import java.util.List;

@Data
public class Gene {
    private final List<NnbPosition> nnbs;

    public Long intersect(Gene otherGene) {
        return this.nnbs.stream().filter(otherGene.nnbs::contains).count();
    }

}
