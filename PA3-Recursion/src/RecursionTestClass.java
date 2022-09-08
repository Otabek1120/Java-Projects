/*
 * This file will hold all of your testcases. Remember, to run all
 * of your tests, right-click on 'RunTests.java' and select 'Run As' ->
 * JUnit Test.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RecursionTestClass {
	
	/*
	 * Here I have provided an example of one of the tests that I
	 * would write. For each of your tests, leave a brief comment
	 * above the test specifying its purpose. For example, for this
	 * test, I might write, "indexOf_test1 tests when s2 is not a 
	 * substring of s1. This should return -1."
	 */
    @Test
    public void test_indexOf_test1() {
        int result = Recursion.indexOf("Hello", "World");
        System.out.println("indexOf(Hello, World), got " + result);
        assertEquals(-1, result);
    }

    @Test
	public void test_indexOf_test2() {
        int result = Recursion.indexOf("abcdefgh", "abc");
        System.out.println("indexOf(abcdefgh, abc), got " + result);
        assertEquals(0, result);
    }

    @Test
	public void test_indexOf_test3() {
        int result = Recursion.indexOf("abcdefgh", "def");
        System.out.println("indexOf(abcdefgh, def), got " + result);
        assertEquals(3, result);
    }

    @Test
	public void test_indexOf_test4() {
        int result = Recursion.indexOf("abcdefgh", "fgh");
        System.out.println("indexOf(abcdefgh, fgh), got " + result);
        assertEquals(5, result);
    }

    @Test
	public void test_indexOf_test5() {
        int result = Recursion.indexOf("abcdefgh", "ghi");
        System.out.println("indexOf(abcdefgh, ghi), got " + result);
        assertEquals(-1, result);
    }

    @Test
	public void test_indexOf_test6() {
        int result = Recursion.indexOf("abcdefgh", "");
        System.out.println("indexOf(abcdefgh, ''), got " + result);
        assertEquals(-1, result);
    }

    @Test
	public void test_indexOf_test7() {
        int result = Recursion.indexOf("abc", "abcdefg");
        System.out.println("indexOf(abc, abcdefg), got " + result);
        assertEquals(-1, result);
    }
    @Test
	public void remove_even_numbers_test1() {
        Stack<Integer> stack = new Stack<Integer>();
		stack.push(2); stack.push(3); stack.push(4); stack.push(55);
		stack.push(6); stack.push(17); stack.push(8);
		int result = Recursion.removeEvenNumbers(stack, 2);
        System.out.println("removeEvenNumbers([2,3,4,55,6,7,8], 2), got " + result);
        assertEquals(2, result);
    }
    
    @Test
    public void remove_even_numbers_test2() {
        Stack<Integer> stack = new Stack<Integer>();
		stack.push(2); stack.push(3);
		int result = Recursion.removeEvenNumbers(stack, 2);
        System.out.println("removeEvenNumbers([2,3], 2), got " + result);
        assertEquals(1, result);
    }

    @Test
    public void remove_even_numbers_test3() {
        Stack<Integer> stack = new Stack<Integer>();
		int result = Recursion.removeEvenNumbers(stack, 2);
        System.out.println("removeEvenNumbers([2,3], 2), got " + result);
        assertEquals(0, result);
    }

    @Test
    public void remove_even_numbers_test4() {
        Stack<Integer> stack = new Stack<Integer>();
		int result = Recursion.removeEvenNumbers(stack, 0);
        System.out.println("removeEvenNumbers([2,3], 0), got " + result);
        assertEquals(0, result);
    }
    
    @Test
    public void even_digits_test1() {
        int result = Recursion.evenDigits(-123456);
        System.out.println("evenDigits(-123456), got " + result);
        assertEquals(246, result);
    }

    @Test
    public void even_digits_test2() {
        int result = Recursion.evenDigits(123456);
        System.out.println("evenDigits(123456), got " + result);
        assertEquals(246, result);
    }

    @Test
    public void even_digits_test3() {
        int result = Recursion.evenDigits(0);
        System.out.println("evenDigits(0), got " + result);
        assertEquals(0, result);
    }

    @Test
    public void even_digits_test4() {
        int result = Recursion.evenDigits(24680);
        System.out.println("evenDigits(24680), got " + result);
        assertEquals(24680, result);
    }

    @Test
    public void even_digits_test5() {
        int result = Recursion.evenDigits(13579);
        System.out.println("evenDigits(24680), got " + result);
        assertEquals(0, result);
    }

    @Test
    public void repeat_stack_test1() {
        Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); stack.push(2); stack.push(3);
        Recursion.repeatStack(stack);
        
        Stack<Integer> newStack = new Stack<Integer>();
        newStack.push(1); newStack.push(1); newStack.push(2);
        newStack.push(2); newStack.push(3); newStack.push(3);

        System.out.println("repeatStack([1,2,3]), got " + stack);
        assertEquals(newStack, stack);
    }

    @Test
    public void double_elements_test1() {
        Queue<Integer> q2 = new LinkedList<Integer>();
		q2.add(34); q2.add(15); q2.add(0);
        Recursion.doubleElements(q2);

        Queue<Integer> doubleQueue = new LinkedList<Integer>();
        doubleQueue.add(68); doubleQueue.add(30); doubleQueue.add(0);

        System.out.println("doubleElements([34, 15, 0], got " + q2);
        assertEquals(doubleQueue, q2);
    }
    




}
