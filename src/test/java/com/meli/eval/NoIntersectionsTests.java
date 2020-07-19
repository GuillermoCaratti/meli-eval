package com.meli.eval;

import com.meli.eval.model.Dna;
import com.meli.eval.service.MutantFinderService;
import com.meli.eval.utils.DnaGenerator;
import com.meli.eval.utils.DnaPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
	classes = EvalApplication.class,
	properties ={
			"gene.verifyIntersections=true",
			"gene.maxIntersections=0",
	}
)
public class NoIntersectionsTests {

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
		assertEquals(false, isMutant, "Is mutant");
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
		assertEquals(false, isMutant, "Is mutant");
		assertEquals(3, genes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void humanMock() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(10);
		Dna dna = new Dna(code);
		assertEquals(false, finder.isMutant(dna), "Is mutant");
		var mutantGenes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + mutantGenes.size());
		DnaPrinter.printDna(dna,mutantGenes);
		assertEquals(0, mutantGenes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void oneMutantGene() throws Exception {
		String[] code = {
				"AAAA",
				"GTGT",
				"TGTG",
				"GTGT"};
		var isMutant = finder.isMutant(code);
		var dna = new Dna(code);
		var genes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + genes.size());
		DnaPrinter.printDna(dna,genes);
		assertEquals(false, isMutant, "Is mutant");
		assertEquals(1, genes.size(), "The amount of found mutant genes is incorrect");
	}

}
