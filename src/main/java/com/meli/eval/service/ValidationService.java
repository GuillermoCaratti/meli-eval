package com.meli.eval.service;

import com.meli.eval.entity.DnaLog;
import com.meli.eval.exception.HumanFound;
import com.meli.eval.exception.MalformedDna;
import com.meli.eval.model.Dna;
import com.meli.eval.model.dto.StatsDto;
import com.meli.eval.repository.DnaLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationService {

    @Autowired
    DnaLogRepository repo;

    @Autowired
    MutantFinderService finder;

    public void validate(String[] code) throws MalformedDna, HumanFound {
        var dna = new Dna(code);
        var isMutant = finder.isMutant(dna);
        var log = new DnaLog();
        log.setCode(String.join("", code));
        log.setSize(code.length);
        log.setIsMutant(isMutant);
        repo.save(log);
        if(!isMutant){
            throw new HumanFound();
        }
    }

    public StatsDto getStats (){
        var report = repo.stats();
        var countMutantDna = report.get("mutantAmount",Long.class);
        var countHumanDna = report.get("humanAmount",Long.class);
        if (countHumanDna == null || countMutantDna == null ){
            return new StatsDto(0L,0L,Optional.empty());
        }
        var totalAmount = countMutantDna + countHumanDna;
        Optional<Float> ratio = totalAmount == 0 ? Optional.empty() : Optional.of( (float) countMutantDna / totalAmount);
        return new StatsDto(countMutantDna,countHumanDna,ratio);
    }
}
