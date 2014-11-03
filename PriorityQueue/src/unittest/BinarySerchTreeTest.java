package unittest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import junit.framework.TestCase;

import org.junit.Test;

import queues.Deque;
import queues.Entry;
import queues.LinkedBinaryTree;
import queues.Position;
import queues.Queue;
import queues.SortedPriorityQueue;


public class BinarySerchTreeTest<E> extends TestCase{
	private int _value1; // defined value
	private int _value2; // defined value
	private SortedPriorityQueue sort;   // instance of LIFOQImpl designated to hold Integers
	private LinkedBinaryTree tree;
	private Deque<Entry> left,right;

	private int _capacity;

	public BinarySerchTreeTest(String testName) {
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

	
	@Test // test 
	public void testUnbalancedTree() throws Exception{ //This will make a tree that is a straight line left
		sort = new SortedPriorityQueue ();
		tree = new LinkedBinaryTree();
		boolean fail= false;
		for (int x = 15; x>0;x--) // creates a SortedPriorityQueue of 15 items
		{
			sort.insert(x,x);
		}
		Position <E> parent = null;
		for (int x = 0; x<15;x++) // goes through each and add it to the left
		{
			Entry y  =  sort.removeMin();
			if (parent==null)
			{
				tree.addRoot(y);
				parent = tree.root();
			}
			else 
			{
				parent = tree.addLeft(parent,y);
			}
			
			
		}
		
	}
	@Test
	public void testBalencedTree() throws Exception //This will make a tree that is more or less balanced
	{ 
		sort = new SortedPriorityQueue ();
		tree = new LinkedBinaryTree();
		ArrayDeque<Entry> left = new ArrayDeque<Entry>(); 
		ArrayDeque<Entry> right =  new ArrayDeque<Entry>(); 

		Position<E> parent = null;
		boolean balenced= false;
		for (int x = 15; x>0;x--)
		{
			sort.insert(x,x);
		}
		int[] totals = count(15);
		int leftTotal = totals[0];
		int rightTotal = totals[1];
		for (int x =0;x<leftTotal;x++) //manually sorts from the Priority Queue
		{
			Entry y  =  sort.removeMin();
			left.addFirst(y); // there are errors in this line due to the inability to instantiate Deque... unsure as to solution
		}
		Entry temp  =  sort.removeMin();
		parent = tree.addRoot(temp);
		for (int x =0;x<rightTotal;x++)
		{
			Entry y  =  sort.removeMin();
			right.addLast(y);
		}
		addChildren(parent,left,right,tree);
		System.out.println(tree.root());
		parent = tree.root();
		for (int x = 0; x<4;x++)
		{
			parent = tree.left(parent);
			System.out.println(parent);
		}
		assertEquals(parent,null); // there should be that many levels 
		
	}
	public void addChildren(Position <E> parent, ArrayDeque<Entry> left, ArrayDeque<Entry> right,LinkedBinaryTree tree)
	{
		Position <E> parentLeft = null, parentRight = null;
		
		/*Left Side */
		
		if (left.size() > 0) // when there are no more items, it stops 
		{
		int[] totals = count(left.size()); //gets totals in the form of a 2 item array 
		int leftTotal = totals[0];
		int rightTotal = totals[1];
		
			ArrayDeque<Entry> newLeft = new ArrayDeque<Entry> ();
			ArrayDeque<Entry> newRight = new ArrayDeque<Entry> ();
			for (int x=0; x<rightTotal;x++) // takes from the top (highest value) and adds it to the top (a.k.a push)
			{
				Entry y  =  left.removeFirst();
				newRight.addFirst(y);
			}
			parentLeft = tree.addLeft(parent, left.removeFirst()); //adds child at middle to the tree
	
			for (int x=0; x<leftTotal;x++) // takes from the bottom (lowest value) and adds it to the top (a.k.a push)
			{
				Entry y  =  left.removeLast();
				newLeft.addFirst(y);
			}
		
			addChildren(parentLeft,newLeft,newRight,tree); // recursivly calls addChildren 
		}
		
		/* Right Side */
		if (right.size() > 0) // when there are no more items, it stops 
		{
			int[] totals = count(right.size());
			int leftTotal = totals[0];
			int rightTotal = totals[1];
		
			ArrayDeque<Entry> newLeft = new ArrayDeque<Entry> ();
			ArrayDeque<Entry> newRight = new ArrayDeque<Entry> ();
			for (int x=0; x<leftTotal;x++) // takes from the top (lowest value) and adds it to the top (a.k.a push)
			{
				Entry y  =  right.removeFirst();
				newLeft.addFirst(y);
			}
			parentRight = tree.addRight(parent, right.removeFirst());//adds child at middle to the tree
			
			for (int x=0; x<rightTotal;x++) // takes from the bottom (highest value) and adds it to the top (a.k.a push)
			{
				Entry y  =  right.removeLast();
				newRight.addFirst(y);
			}
	
			addChildren(parentRight,newLeft,newRight,tree);// forms binary recurssion calls addChildren 
		}
	}

	public int[] count (int total)
	{
		int depth = 0;
		int leftTotal=0,rightTotal=0;
		
		while (0<total-1) //counts the number that must be in the left and right piles to ensure left alignment
		{
			for (int x= 0; x < Math.pow(2,depth);x++,total--) // the amount needed to fill a level is found by 2 raised to the power of the depth
			{
				if (total == 1) {break;} // breaks if total reaches 1, leaving room for the parent
				leftTotal++;;
			}
			for (int x= 0; x < Math.pow(2,depth);x++,total--) 
			{
				if (total == 1) {break;}
				rightTotal++;
			}
			depth ++;
		}
		int [] temp = {leftTotal,rightTotal};
		return temp;
			
	}




	
	
}
	

