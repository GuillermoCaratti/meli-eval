package com.meli.eval.repository;

import com.meli.eval.entity.DnaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;

@Repository
public interface DnaLogRepository extends JpaRepository<DnaLog,Integer> {

    @Query(value = "SELECT SUM(case when e.isMutant = true THEN 1 ELSE 0 END) as mutantAmount, " +
            "SUM(case when e.isMutant = true THEN 0 ELSE 1 END) as humanAmount " +
            "FROM DnaLog e ")
    Tuple stats();

}
