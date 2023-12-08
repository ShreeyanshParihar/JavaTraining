package com.seclore.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.seclore.exceptions.NoMoreElementException;
import com.seclore.pojo.JavaStack;

@DisplayName("A stack")
public class JavaStackTest {

	JavaStack<Integer> stack;

	@Test
	@DisplayName("instantiates without error")
	public void testInstantiation() {
		stack = new JavaStack<>();
	}

	@Nested
	@DisplayName("when empty")
	class WhenEmpty {
		@BeforeEach
		public void createNewStack() {
			stack = new JavaStack<>();
		}

		@Test
		@DisplayName("has size 0")
		public void testIsEmpty() {
			assertEquals(stack.size(), 0);
		}

		@Test
		@DisplayName("has proper toString")
		public void testToString() {
			assertEquals(stack.toString(), "[]");
		}

		@Test
		@DisplayName("throws exception when pop() is called")
		public void testPop() {
			assertThrows(NoMoreElementException.class, stack::pop);
		}

		@Test
		@DisplayName("throws exception when peek() is called")
		public void testPeek() {
			assertThrows(NoMoreElementException.class, stack::peek);
		}

		@Test
		@DisplayName("pushes without error")
		public void testPush() {
			stack.push(0);
		}

		@Nested
		@DisplayName("after pushing an element")
		class AfterPushing {
			@BeforeEach
			public void pushElement() {
				stack.push(1);
			}

			@Test
			@DisplayName("has size 1")
			public void testSize() {
				assertEquals(stack.size(), 1);
			}

			@Test
			@DisplayName("has proper toString")
			public void testToString() {
				assertEquals(stack.toString(), "[1]");
			}

			@Test
			@DisplayName("pops properly")
			public void testPop() {
				assertEquals(stack.pop(), 1);
				assertEquals(stack.size(), 0);
			}

			@Test
			@DisplayName("peeks properly")
			public void testPeek() {
				assertEquals(stack.peek(), 1);
				assertEquals(stack.size(), 1);
			}
		}

		@Nested
		@DisplayName("after pushing 5 elements")
		class AfterPushingMultiple {
			@BeforeEach
			public void pushElement() {
				stack.push(3);
				stack.push(4);
				stack.push(1);
				stack.push(2);
				stack.push(5);
			}

			@Test
			@DisplayName("has size 5")
			public void testSize() {
				assertEquals(stack.size(), 5);
			}

			@Test
			@DisplayName("has proper toString")
			public void testToString() {
				assertEquals(stack.toString(), "[3, 4, 1, 2, 5]");
			}

			@Test
			@DisplayName("pops in order")
			public void testPop() {
				assertEquals(stack.pop(), 5);
				assertEquals(stack.pop(), 2);
				assertEquals(stack.pop(), 1);
				assertEquals(stack.pop(), 4);
				assertEquals(stack.pop(), 3);
				assertThrows(NoMoreElementException.class, stack::pop);
			}

			@Test
			@DisplayName("peeks last element")
			public void testPeek() {
				assertEquals(stack.peek(), 5);
			}

			@Test
			@DisplayName("sorts in order")
			public void testSort() {
				stack.sort(new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});
				assertEquals(stack.toString(), "[1, 2, 3, 4, 5]");
			}
		}
	}

}