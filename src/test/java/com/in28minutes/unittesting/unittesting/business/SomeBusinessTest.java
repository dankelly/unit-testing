package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.*;

import org.junit.Test;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

public class SomeBusinessTest {

	@Test
	public void calculateSum_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actualResult = business.calculateSum(new int[] {1, 2, 3});
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSum_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actualResult = business.calculateSum(new int[] {});
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSum_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		int actualResult = business.calculateSum(new int[] {5});
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();

//		SomeDataService svc = ;
		
		business.setSomeDataService(new SomeDataService() {
			@Override
			public int[] retrieveAllData() {
				return new int[] {1, 2, 3};
			}
		});
		int actualResult = business.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

}
