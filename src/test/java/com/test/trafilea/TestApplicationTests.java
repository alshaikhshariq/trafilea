package com.test.trafilea;

import com.test.trafilea.part2.service.NumberCollectionService;
import com.test.trafilea.part2.controller.NumberController;
import com.test.trafilea.part2.service.NumberTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestApplicationTests {

	@Mock
	private NumberTypeService numberTypeService;

	@Mock
	private NumberCollectionService numberCollectionService;

	@InjectMocks
	private NumberController numberController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveNumber_ShouldReturnOkResponse() {
		int number = 3;
		String value = "Type 1";
		lenient().when(numberTypeService.determineNumberType(number)).thenReturn(value);

		ResponseEntity<String> response = numberController.saveNumber(number);

		verify(numberCollectionService, times(1)).saveNumber(number, value);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Number saved successfully.", response.getBody());
	}

	@Test
	void getNumberDetails_WhenNumberExists_ShouldReturnOkResponse() {
		int number = 5;
		String value = "Type 2";
		lenient().when(numberCollectionService.getNumber(number)).thenReturn(value);

		ResponseEntity<String> response = numberController.getNumberDetails(number);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(value, response.getBody());
	}

	@Test
	void getNumberDetails_WhenNumberDoesNotExist_ShouldReturnNotFoundResponse() {
		int number = 3;
		lenient().when(numberCollectionService.getNumber(number)).thenReturn(null);

		ResponseEntity<String> response = numberController.getNumberDetails(number);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void getNumberCollection_ShouldReturnMapOfNumbers() {
		Map<Integer, String> expectedMap = new HashMap<>();
		lenient().when(numberCollectionService.getAllNumbers()).thenReturn(expectedMap);

		Map<Integer, String> result = numberController.getNumberCollection();

		assertEquals(expectedMap, result);
	}

}
