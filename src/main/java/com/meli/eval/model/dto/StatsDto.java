package com.meli.eval.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {
    private Long countMutantDna;
    private Long countHumanDna;
    /* The ratio can be "null" is there no information */
    private Optional<Float> ratio;
}
