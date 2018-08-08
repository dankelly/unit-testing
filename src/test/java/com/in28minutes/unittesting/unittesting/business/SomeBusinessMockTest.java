package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

public class SomeBusinessMockTest {

	SomeBusinessImpl business = new SomeBusinessImpl();
	SomeDataService dataServiceMock = mock(SomeDataService.class);

	@Before
	public void before() {
		business.setSomeDataService(dataServiceMock);	
	}
	
	@Test
	public void calculateSumUsingDataService_basic() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3});
		assertEquals(6, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(0, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {5});
		assertEquals(5, business.calculateSumUsingDataService());
	}

}
