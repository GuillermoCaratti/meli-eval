package com.meli.eval;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.eval.model.dto.DnaDto;
import com.meli.eval.utils.DnaGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = EvalApplication.class)
@AutoConfigureMockMvc
public class WebApplicationTests {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void httpIsMutantTest() throws Exception {
		String[] code = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		DnaDto dto = new DnaDto(code);
		mvc.perform(post("/mutant")
				.content(mapToJson(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void httpIsHumanTest() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(20);
		DnaDto dto = new DnaDto(code);
		mvc.perform(post("/mutant")
				.content(mapToJson(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test
	public void httpBadDnaTest() throws Exception {
		String[] code = DnaGenerator.mockHumanCode(10);
		code[9] += "A";
		DnaDto dto = new DnaDto(code);
		mvc.perform(post("/mutant")
				.content(mapToJson(dto))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
