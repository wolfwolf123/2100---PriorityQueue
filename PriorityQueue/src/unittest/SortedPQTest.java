package unittest;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import queues.*;

public class SortedPQTest extends TestCase{
	private int _value1; // defined value
	private int _value2; // defined value
	private SortedPriorityQueue sort;   // instance of LIFOQImpl designated to hold Integers
	private int _capacity;

	public SortedPQTest(String testName) {
		super(testName);  // constructs a test with given name
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("SortTest::setUp "); // UNCLEAR What does "Sets up the fixture" mean? 
		_value1 = 3;
		_value2 = 5;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("SortTest::tearDown ");  // UNCLEAR What does "Tears Down the fixture" mean? 
		_value1 = 0;
		_value2 = 0;
	}

	
	@Test // test conversly sorted arrays
	public void testRandomArrayUnsorted() throws Exception{
		sort = new SortedPriorityQueue ();
		boolean fail= false;
		for (int x = 15; x>0;x--)
		{
			sort.insert(x,x);
		}
		for (int x = 15; x>0;x--)
		{
			int y = (int) sort.removeMin().getValue();
			System.out.println("The " + x + " Object is:" + y);
			if ((x + y) > 16)
			{
				fail = true;
			}
		}
	
		assertEquals(false,fail);
		
	}
	
}
