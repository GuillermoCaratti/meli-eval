package com.meli.eval.model;

import lombok.Data;

@Data
public class NnbPosition {
    public final Integer x;
    public final Integer y;

    public NnbPosition move(Integer x, Integer y){
        return new NnbPosition(this.x + x, this.y + y);
    }
}
