package com.meli.eval.service;

import com.meli.eval.exception.MalformedDna;
import com.meli.eval.model.Dna;
import com.meli.eval.model.Gene;
import com.meli.eval.model.NnbPosition;
import com.meli.eval.utils.ListUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * This service has all the logic to distinguish a mutant {@link Dna} from a human.
 */
@ConfigurationProperties(prefix = "gene")
@ConstructorBinding
@Data
public class MutantFinderService {

    // All the variables are filed with the application configuration (See application.properties)
    private final Integer minChain;
    private final Integer maxChain;
    private final Integer minGenes;
    private final Boolean verifyIntersections;
    private final Integer maxIntersections;


    /**
     * @param dna code to test
     * @return {@code true} if is a mutant, {@code false} if is not
     * @throws MalformedDna if the genetic code has problems
     */
    public Boolean isMutant (final String[] dna) throws MalformedDna {
        return this.isMutant(new Dna(dna));
    }

    /**
     * @param dna to test
     * @return {@code true} if is a mutant, {@code false} if is not
     * @throws MalformedDna if the genetic code has problems
     */
    public Boolean isMutant (final Dna dna) {
        if (verifyIntersections){
            var mutGenes = findAllMutantGenes(dna);
            if (mutGenes.isEmpty()){
                return minGenes == 0;
            }
            return this.hasSolution(mutGenes);
        }
        return streamMutantGenes(dna).limit(minGenes).count() >= minGenes;
    }

    /**
     * Finds if exist preventable intersections between genes
     *
     * @param mutantGenes list of all mutant genes
     * @return {@code true} if is a mutant, {@code false} if is not
     */
    private Boolean hasSolution (List<Gene> mutantGenes) {
        if (mutantGenes.size() < minGenes) {
            return false;
        }
        return ListUtils.generateCombinations(mutantGenes.size(), minGenes)
                .stream()
                .map( c -> Arrays.stream(c).mapToObj(mutantGenes::get).collect(Collectors.toList()) )
                .map(this::maxIntersections)
                .anyMatch( intersections -> intersections <= maxIntersections);
    }

    /**
     * @param mutantGenes list of all mutant genes
     * @return the maximum number of genes collisions between a given list
     */
    private Long maxIntersections(List<Gene> mutantGenes) {
        return IntStream.range(0, mutantGenes.size() - 1)
                .mapToLong( i -> {
                    var othersGenes = mutantGenes.subList(i + 1,mutantGenes.size());
                    var gene = mutantGenes.get(i);
                    return othersGenes.stream().map(gene::intersect).reduce(0L,Long::sum);
                }).max().orElse(0L);
    }

    /**
     * @param dna genetic code
     * @return a {@link Stream} of mutant {@link Gene}s
     */
    private Stream<Gene> streamMutantGenes(final Dna dna) {
        if(dna.size < minChain) {
            return Stream.empty();
        }
        return LongStream.range(0,dna.getIndexSize())
                .mapToObj(dna::getPosByIndex)
                .flatMap( nnbPos -> this.geneFinderFrom(dna, nnbPos));
    }
    /**
     * @param dna genetic code
     * @param start position of the dna to find mutant genes
     * @return a {@link Stream} of mutant {@link Gene}s
     */
    private Stream<Gene> geneFinderFrom (final Dna dna, final NnbPosition start){
        return Stream.of(
                geneFinder(dna,List.of(start),Direction.LEFT),
                geneFinder(dna,List.of(start),Direction.LEFT_DOWN),
                geneFinder(dna,List.of(start),Direction.DOWN)
            ).filter(Optional::isPresent)
            .map(Optional::get);
    }

    /**
     * @param dna genetic code
     * @return ALL the mutant {@link Gene}'s found (with or without collisions)
     */
    public List<Gene> findAllMutantGenes(final Dna dna) {
       return streamMutantGenes(dna).collect(Collectors.toList());
    }

    /**
     * A recursive function to search for lineal sequences of the same {@link com.meli.eval.model.NitrogenNucleobase}
     * @param dna the genetic code
     * @param path the current path
     * @param direction of the sequence
     * @return {@code Optional.of(Gene)} if is found or {@code Optional.empty()} if is not.
     */
    private Optional<Gene> geneFinder(final Dna dna, List<NnbPosition> path, final Direction direction){
        if(path.size() >= maxChain) {
            return Optional.of(new Gene(path));
        }
        var lastStep = path.get( path.size() - 1 );
        var nexStep = direction.moveFrom(lastStep);
        if (!dna.isValidPosition(nexStep)
            || !dna.getNnb(lastStep).equals(dna.getNnb(nexStep)) ) {
            return path.size() >= minChain ? Optional.of(new Gene(path)) : Optional.empty();
        }
        var newPath = ListUtils.append(path,nexStep);
        return geneFinder(dna, newPath, direction);
    }

    /**
     * Represents the finding direction for the {@link #geneFinder(Dna, List, Direction) geneFinder} method
     */
    private enum Direction {
        LEFT(1,0),
        LEFT_DOWN(1,1),
        DOWN(0,1);

        private final Integer x;
        private final Integer y;

        Direction(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public NnbPosition moveFrom(final NnbPosition pos){
            return pos.move(this.x, this.y);
        }
    }
}
