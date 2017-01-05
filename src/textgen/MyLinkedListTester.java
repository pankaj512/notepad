/**
 *
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}


	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = this.list1.remove(0);
        Assert.assertEquals((String)"Remove: check a is correct ", (long)65, (long)a);
        Assert.assertEquals((String)"Remove: check element 0 is correct ", (Object)21, (Object)this.list1.get(0));
        Assert.assertEquals((String)"Remove: check size is correct ", (long)2, (long)(this.list1.size() - 1));
        a = this.longerList.remove(0);
        Assert.assertEquals((String)"Remove: check a is correct ", (long)0, (long)a);
        a = this.longerList.remove(this.longerList.size() - 1);
        Assert.assertEquals((String)"Remove: check a is correct ", (long)0, (long)a);
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
            this.shortList.add("C");
            this.shortList.add("D");
            this.shortList.add(null);
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + " 6");
        }
	}


	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		try {
            System.out.println(this.shortList.size());
            System.out.println(this.longerList.size());
            System.out.println(this.list1.size());
            System.out.println(this.emptyList.size());
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "size error ");
        }
	}



	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
            this.shortList.add(2, "E");
            this.shortList.add(2, null);
            this.longerList.add(0, 50);
            this.list1.add(-1, 51);
            this.emptyList.add(-1, 20);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    try {
            this.shortList.set(2, "E");
            this.longerList.set(0, 5);
            this.longerList.set(this.longerList.size() - 1, 10);
            this.longerList.set(1, 50);
            this.list1.set(3, 51);
            this.emptyList.set(-1, 20);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}


	// TODO: Optionally add more test methods.
	public static void printlist(MyLinkedList l) {
        for (int i = 0; i < l.size(); ++i) {
            System.out.print(l.get(i) + " ");
        }
    }
}
