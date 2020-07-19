package com.meli.eval;

import com.meli.eval.model.Dna;
import com.meli.eval.service.MutantFinderService;
import com.meli.eval.utils.DnaPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
	classes = EvalApplication.class,
	properties ={
			"gene.verifyIntersections=true",
			"gene.maxIntersections=1",
	}
)
public class SingleIntersectionsTests {

	@Autowired
	MutantFinderService finder;

	@Test
	public void oneIntersectionTest() throws Exception {
		String[] code = {
				"AAAA",
				"CACC",
				"CACC",
				"CACC"};
		var isMutant = finder.isMutant(code);
		var dna = new Dna(code);
		var genes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + genes.size());
		DnaPrinter.printDna(dna,genes);
		assertEquals(true, isMutant, "Is mutant");
		assertEquals(2, genes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void twoIntersectionTest() throws Exception {
		String[] code = {
				"AAAA",
				"ACCA",
				"ACCA",
				"ACCA"};
		var isMutant = finder.isMutant(code);
		var dna = new Dna(code);
		var genes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + genes.size());
		DnaPrinter.printDna(dna,genes);
		assertEquals(true, isMutant, "Is mutant");
		assertEquals(3, genes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void threeIntersectionTest() throws Exception {
		String[] code = {
				"AAAA",
				"CACA",
				"CCAA",
				"CCCA"};
		var isMutant = finder.isMutant(code);
		var dna = new Dna(code);
		var genes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + genes.size());
		DnaPrinter.printDna(dna,genes);
		assertEquals(true, isMutant, "Is mutant");
		assertEquals(3, genes.size(), "The amount of found mutant genes is incorrect");
	}


}
