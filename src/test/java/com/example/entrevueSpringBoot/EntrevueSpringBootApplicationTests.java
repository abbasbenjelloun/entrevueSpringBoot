package com.example.entrevueSpringBoot;

import com.example.entrevueSpringBoot.domains.Film;
import com.example.entrevueSpringBoot.repositories.FilmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class EntrevueSpringBootApplicationTests {

	@Value("classpath:data/data.json")
	public Resource dataSource;

	@Autowired
	public FilmRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference ref = new TypeReference<List<Film>>() { };

		List<Film> listFilms = (List<Film>)
				objectMapper.readValue(
						dataSource.getFile(),
						ref
				);
		repository.saveAll(listFilms);
	}

	@Test
	void contextLoads() throws Exception {
		List<Film> listFilms = repository.findAll();
		assertEquals(2,listFilms.size());

		mockMvc.perform(
			MockMvcRequestBuilders
			.get("http://localhost:8080/api/film/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.titre").value("Star Wars: The Empire Strikes Back"))
		 ;
	}

}