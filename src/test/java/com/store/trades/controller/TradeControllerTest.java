package com.store.trades.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.trades.model.StoreTrade;

@WebMvcTest(TradeController.class)
public class TradeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeController controller;

	private StoreTrade storeTrade;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		storeTrade = new StoreTrade();
		storeTrade.setTradeId("T1");
		storeTrade.setVersion(1);
		storeTrade.setCpId("CP-2");
		storeTrade.setBookId("B1");
		storeTrade.setMaturityDate(LocalDate.now());
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testSaveTrade() throws Exception {
		ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		given(controller.save(storeTrade)).willReturn(expectedResponse);
		String uri = "/trade";
		String inputJson = mapToJson(storeTrade);

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(inputJson)
		.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

		verify(controller, times(1)).save(storeTrade);
		verifyNoMoreInteractions(controller);
	}
}
