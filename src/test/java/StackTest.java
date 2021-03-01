import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class StackTest {
    @Test
    public void arrayStackTest() {
        ArrayStack<String> arrayStack = new ArrayStack<>();

        LinkedList<String> arr = new LinkedList<>();
        arr.removeAll(arr);
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
    public void iteratorTest() {
        Random rnd = new Random();

        LinkedList<Integer> linkedStack = new LinkedList<>();



        for (int j = 0; j < 10000; j++) {
            linkedStack.add(rnd.nextInt());
        }
        //3100
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            LinkedList<Integer> res = (LinkedList<Integer>) linkedStack.stream().collect(new ReverseCollector<>(LinkedList::new));
        }

        System.out.println(System.currentTimeMillis() - start);
        //linkedStack.stream().forEach(System.out::println);



        //res.stream().forEach(System.out::println);
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
