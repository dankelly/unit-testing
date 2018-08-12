package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ListMockTest {

	List<String> mock = mock(List.class);

	@Test
	public void testSize_basic() {
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	public void testSize_returnDifferentValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}
	
	@Test
	public void testSize_returnWithParameters() {
		when(mock.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals(null, mock.get(1));
	}
	
	@Test
	public void testSize_returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", mock.get(0));
		assertEquals("in28Minutes", mock.get(1));
	}
	
	@Test
	public void testSize_verificationBasics() {
		// Somewhere in the code we want to test, get() is called with a parameter of 0.
		// We want to verify this exact method call takes place.

		// For this test we simulate a scenario where SUT  ( "system under test" )
		// is assumed to have performed these calls.
		mock.get(0);
		mock.get(1);
		
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
	public void testSize_argumentCapturing() {
		// Assume SUT performs...
		mock.add("SomeString");
		
		// Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		// In addition to verifying the add method was implicitly called at some point in SUT,
		// this captures whichever arg was passed to the add method.
		verify(mock).add(captor.capture());
		assertEquals("SomeString",  captor.getValue());
	}

	@Test
	public void testSize_multipleArgumentCapturing() {
		// Assume SUT performs...
		mock.add("SomeString1");
		mock.add("SomeString2");
		
		// Verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		// In addition to verifying the add method was implicitly called at some point in SUT,
		// this captures whichever args were passed to the add method.
		verify(mock, times(2)).add(captor.capture());
		List<String> allValues = captor.getAllValues();
		assertEquals("SomeString1",  allValues.get(0));
		assertEquals("SomeString2",  allValues.get(1));
	}
	
	@Test
	public void testSize_mocking() {
		ArrayList<String> arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));	// ==> null (default)
		System.out.println(arrayListMock.size());	// ==> 0    (default)
		
		// Mocks do NOT retain original behavior of the class they mock!
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());	// ==> still 0!
		
		// b/c remember this is how they "implement" behavior:
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());	// ==> 5
		
	}
	
	@Test
	public void testSize_spying() {
		// Spies, however, *DO* retain original implementation by default
		ArrayList<String> arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0)); // would have thrown exception without previous add()
		System.out.println(arrayListSpy.size());
		
		arrayListSpy.add("Test1");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size()); // ==> 3
												 // override imminent...
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size()); // ==> 5
		
		// size() implementation has been overridden and no longer reports real size
		arrayListSpy.add("Test3");
		System.out.println(arrayListSpy.size()); // ==> 5, still
		
		// There is no actual fifth element, though,
		// so this would throw an IndexOutOfBoundsException.
		//System.out.println(arrayListSpy.get(4));
	}

}
