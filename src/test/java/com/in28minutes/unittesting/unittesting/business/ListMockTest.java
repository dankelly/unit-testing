package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ListMockTest {

	List<String> mock = mock(List.class);

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
		assertEquals(null, mock.get(1));
	}
	
	@Test
	public void TestSize_returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}
	
	@Test
	public void TestSize_verificationBasics() {
		// Somewhere in the code we want to test, get() is called with a parameter of 0.
		// We want to verify this exact method call takes place.

		// For this test we simulate a scenario where SUT  ( "system under test" )
		// is assumed to have performed these calls.
		String value1 = mock.get(0);
		String value2 = mock.get(1);
		
		// Verify
		verify(mock).get(0);  // implicit times(1)
		verify(mock, times(1)).get(1);
		//verify(mock).get(anyInt());  // would fail due to implicit times(1)
		verify(mock, times(2)).get(anyInt());  // does not fail
		verify(mock, atLeast(1)).get(1);
		verify(mock, atLeastOnce()).get(1);
		verify(mock, never()).get(2);
	}

	@Test
	public void TestSize_argumentCapturing() {
		// Assume SUT performs...
		mock.add("SomeString");
		
		// Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		// In addition to verifying the add method was implicitly called at some point in SUT,
		// this captures whichever arg was passed to the add method.
		verify(mock).add(captor.capture());
		assertEquals("SomeString",  captor.getValue());
	}
}
