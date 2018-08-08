package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListMockTest {

	List mock = mock(List.class);

	@Test
	public void TestSize_basic() {
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	public void TestSize_returnDifferentValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}
	
	@Test
	public void TestSize_returnWithParameters() {
		when(mock.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}

}
