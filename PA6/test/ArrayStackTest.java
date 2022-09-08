import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArrayStackTest {

	@Test
	public void testArrayStack() throws Exception {

	}

	@Test
	public void testGetSize() throws Exception {
		ArrayStack arrayStack = new ArrayStack();
		arrayStack.push(1);
		arrayStack.push(2);
		arrayStack.push(3);
		arrayStack.push(4);
		arrayStack.push(5);
		assertEquals(5, arrayStack.getSize());
	}


	@Test
	public void testPush() throws Exception {
		
	}

	@Test
	public void testPop() throws Exception {
		ArrayStack arrayStack = new ArrayStack();
		arrayStack.push(1);
		arrayStack.push(2);
		arrayStack.push(3);
		arrayStack.push(4);
		arrayStack.push(5);
		arrayStack.pop();
		int[] exp = arrayStack.getArrayStack();
		assertEquals(exp, arrayStack.getArrayStack());
	}

	@Test
	public void testPeek() throws Exception {

	}

	@Test
	public void testIsEmpty() throws Exception {

	}

	@Test
	public void testSize() throws Exception {

	}

	@Test
	public void testClear() throws Exception {

	}

	@Test
	public void testToString() throws Exception {

	}

	@Test
	public void testEquals() throws Exception {

	}

}
