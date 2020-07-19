package com.meli.eval;

import static org.junit.jupiter.api.Assertions.*;
import com.meli.eval.model.Dna;
import com.meli.eval.service.MutantFinderService;
import com.meli.eval.utils.DnaGenerator;
import com.meli.eval.utils.DnaPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EvalApplication.class)
public class EvalApplicationTests {

	@Autowired
	MutantFinderService finder;

	@Test
	public void baseTest() throws Exception {
		String[] code = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		Dna dna = new Dna(code);
		var mutantGenes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + mutantGenes.size());
		DnaPrinter.printDna(dna,mutantGenes);
		assertEquals(3, mutantGenes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void humanMock() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(10);
		Dna dna = new Dna(code);
		var mutantGenes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + mutantGenes.size());
		DnaPrinter.printDna(dna,mutantGenes);
		assertEquals(0, mutantGenes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void tinyDna() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(1);
		Dna dna = new Dna(code);
		var mutantGenes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + mutantGenes.size());
		DnaPrinter.printDna(dna,mutantGenes);
		assertEquals(0, mutantGenes.size(), "The amount of found mutant genes is incorrect");
	}

	@Test
	public void emptyDna() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(0);
		Dna dna = new Dna(code);
		var mutantGenes = finder.findAllMutantGenes(dna);
		System.out.println("Amount of mutant genes " + mutantGenes.size());
		DnaPrinter.printDna(dna,mutantGenes);
		assertEquals(0, mutantGenes.size(), "The amount of found mutant genes is incorrect");
	}


	@Test
	public void notSquaredDna() {
		String[] code = DnaGenerator.mockHumanCode(10);
		code[9] += "A";
		assertThrows(Exception.class, () -> finder.isMutant(code));
	}

	@Test
	public void wrongNnb() {
		String[] code = DnaGenerator.mockHumanCode(10);
		code[1] = code[1].replace('A','Z');
		assertThrows(Exception.class, () -> finder.isMutant(code));
	}

}
