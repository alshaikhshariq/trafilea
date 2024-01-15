package com.test.trafilea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TrafileaApplicationTests {

	@Mock
	private NumberTypeService numberTypeService;

	@Mock
	private NumberCollectionService numberCollectionService;

	@InjectMocks
	private NumberController numberController;

	@Test
	public void testSaveNumber() {
		int number = 10;

		Mockito.when(numberTypeService.determineNumberType(number)).thenReturn("Type 1");
		Mockito.doNothing().when(numberCollectionService).saveNumber(number, "Type 1");

		ResponseEntity<String> response = numberController.saveNumber(number);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Number saved successfully.", response.getBody());

		Mockito.verify(numberTypeService, Mockito.times(1)).determineNumberType(number);
		Mockito.verify(numberCollectionService, Mockito.times(1)).saveNumber(number, "Type 1");
	}

	@Test
	public void testGetNumberDetails() {
		int number = 5;

		Mockito.when(numberCollectionService.getNumber(number)).thenReturn("Type 2");

		ResponseEntity<String> response = numberController.getNumberDetails(number);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Type 2", response.getBody());

		Mockito.verify(numberCollectionService, Mockito.times(1)).getNumber(number);
	}

	@Test
	public void testGetNumberDetailsNotFound() {
		int number = 7;

		Mockito.when(numberCollectionService.getNumber(number)).thenReturn(null);

		ResponseEntity<String> response = numberController.getNumberDetails(number);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetNumberCollection() {
		Map<Integer, String> expectedCollection = Collections.singletonMap(1, "Type 1");

		Mockito.when(numberCollectionService.getAllNumbers()).thenReturn(expectedCollection);

		Map<Integer, String> result = numberController.getNumberCollection();

		assertNotNull(result);
		assertEquals(expectedCollection, result);

		Mockito.verify(numberCollectionService, Mockito.times(1)).getAllNumbers();
	}

}
