import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class StackTest {
    @Test
    public void arrayStackTest() {
        ArrayStack<String> arrayStack = new ArrayStack<>();

        Assertions.assertTrue(arrayStack.empty());

        arrayStack.push("A");
        Assertions.assertEquals(arrayStack.peek(), "A");
        arrayStack.push("B");
        Assertions.assertEquals(arrayStack.peek(), "B");
        arrayStack.push("C");
        Assertions.assertEquals(arrayStack.peek(), "C");
        arrayStack.push("D");
        Assertions.assertEquals(arrayStack.peek(), "D");
        arrayStack.push("E");
        Assertions.assertEquals(arrayStack.peek(), "E");

        Assertions.assertFalse(arrayStack.empty());

        Assertions.assertEquals(arrayStack.pop(), "E");
        Assertions.assertEquals(arrayStack.pop(), "D");
        Assertions.assertEquals(arrayStack.pop(), "C");
        Assertions.assertEquals(arrayStack.pop(), "B");
        Assertions.assertEquals(arrayStack.pop(), "A");

        Assertions.assertTrue(arrayStack.empty());

        Assertions.assertNull(arrayStack.pop());

        Assertions.assertTrue(arrayStack.empty());
    }

    @Test
    public void linkedStackTest() {
        LinkedStack<String> linkedStack = new LinkedStack<>();

        Assertions.assertTrue(linkedStack.empty());

        linkedStack.push("A");
        Assertions.assertEquals(linkedStack.peek(), "A");
        linkedStack.push("B");
        Assertions.assertEquals(linkedStack.peek(), "B");
        linkedStack.push("C");
        Assertions.assertEquals(linkedStack.peek(), "C");
        linkedStack.push("D");
        Assertions.assertEquals(linkedStack.peek(), "D");
        linkedStack.push("E");
        Assertions.assertEquals(linkedStack.peek(), "E");

        Assertions.assertFalse(linkedStack.empty());

        Assertions.assertEquals(linkedStack.pop(), "E");
        Assertions.assertEquals(linkedStack.pop(), "D");
        Assertions.assertEquals(linkedStack.pop(), "C");
        Assertions.assertEquals(linkedStack.pop(), "B");
        Assertions.assertEquals(linkedStack.pop(), "A");

        Assertions.assertNull(linkedStack.pop());

        Assertions.assertTrue(linkedStack.empty());
    }

    @Test
    public void stackCollectionTest() {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("A");
        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("C");
        linkedStack.push("D");
        linkedStack.push("E");
        collectionTest(linkedStack);

        ArrayStack<String> arrayStack = new ArrayStack<>();
        arrayStack.push("E");
        arrayStack.push("D");
        arrayStack.push("C");
        arrayStack.push("C");
        arrayStack.push("B");
        arrayStack.push("A");
        arrayStack.push("A");
        collectionTest(arrayStack);
    }

    @Test
    public void collectionTest(Stack<String> stack)
    {
        String[] expected = {"E", "D", "C", "C", "B", "A", "A"};
            Assertions.assertArrayEquals(expected, stack.toArray());

        String[] blankArray = {};
            Assertions.assertArrayEquals(expected,stack.toArray(blankArray));

        Object[] castArray = {};
            Assertions.assertArrayEquals(expected,stack.toArray(castArray));

        List<String> removeElements = Arrays.asList("A", "C");
        String[] expected2 = {"E", "D", "B"};
            Assertions.assertTrue(stack.removeAll(removeElements));
            Assertions.assertArrayEquals(expected2,stack.toArray());

        List<String> retainElements = Collections.singletonList("D");
        String[] expected3 = {"D"};
            Assertions.assertTrue(stack.retainAll(retainElements));
            Assertions.assertArrayEquals(expected3,stack.toArray());

        String[] expected4 = {};
            Assertions.assertTrue(stack.remove("D"));
            Assertions.assertArrayEquals(expected4,stack.toArray());
    }

    @Test
    public void reverseTest() {
        LinkedStack<String> linkedStack = new LinkedStack<>();

        Assertions.assertTrue(linkedStack.empty());

        linkedStack.push("A");
        Assertions.assertEquals(linkedStack.peek(), "A");
        linkedStack.push("B");
        Assertions.assertEquals(linkedStack.peek(), "B");
        linkedStack.push("C");
        Assertions.assertEquals(linkedStack.peek(), "C");
        linkedStack.push("D");
        Assertions.assertEquals(linkedStack.peek(), "D");
        linkedStack.push("E");
        Assertions.assertEquals(linkedStack.peek(), "E");

        Assertions.assertFalse(linkedStack.empty());

        LinkedStack<String> res = (LinkedStack<String>) linkedStack.stream().collect(new ReverseCollector<>(LinkedStack::new));

        Assertions.assertEquals(res.pop(), "A");
        Assertions.assertEquals(res.pop(), "B");
        Assertions.assertEquals(res.pop(), "C");
        Assertions.assertEquals(res.pop(), "D");
        Assertions.assertEquals(res.pop(), "E");

        Assertions.assertNull(res.pop());

        Assertions.assertTrue(res.empty());
    }
}
