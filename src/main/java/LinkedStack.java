import com.sun.istack.internal.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {
    private Node data = null;

    public void push(E element) {
        data = new Node(element, data);
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (data == null) return null;
        Node node = data;
        data = data.ptr;
        return (E) node.value;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return this.data == null ? null : (E) this.data.value;
    }

    public boolean empty() {
        return data == null;
    }

    public int size() {
        int counter = 0;
        for (Node current = data; current != null; current = current.ptr) {
            counter++;
        }
        return counter;
    }

    public boolean isEmpty() {
        return empty();
    }

    public boolean contains(Object o) {
        for (Node current = data; current != null; current = current.ptr) {
            if (current.value.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = size() - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) get(index--).value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        Node current = data;
        for (int i = 0; i < size(); i++) {
            result[i] = current.value;
            current = current.ptr;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size())
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        int i = 0;
        for (Node current = data; current != null; current = current.ptr)
            a[i++] = (T) current.value;
        if (a.length > size()) a[size()] = null;
        return a;
    }

    public boolean add(E e) {
        this.push(e);
        return true;
    }

    public boolean remove(Object o) {
        if (data == null) return false;
        if (o == null) {
            if (data.value == null) {
                data = data.ptr;
                return true;
            }
            for (Node node = data; ; node = node.ptr) {
                if (node.ptr == null) break;
                if (node.ptr.value == null) {
                    node.ptr = node.ptr.ptr;
                    return true;
                }
            }
        } else {
            if (data.value.equals(o)) {
                data = data.ptr;
                return true;
            }
            for (Node node = data; ; node = node.ptr) {
                if (node.ptr == null) break;
                if (node.ptr.value.equals(o)) {
                    node.ptr = node.ptr.ptr;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            this.push(e);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (data.value.equals(o)) {
            data = data.ptr;
            return true;
        }
        for (Node node = data; ; node = node.ptr) {
            if (node.ptr == null) break;
            if (node.ptr.value.equals(o)) {
                node.ptr = node.ptr.ptr;
                return true;
            }
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        data = null;
    }

    private Node get(int n) {
        Node result = data;
        for (int i = 0; i < n; i++) {
            result = result.ptr;
        }
        return result;
    }

    private static class Node {
        Object value;
        Node ptr;

        public Node(Object value, Node ptr) {
            this.value = value;
            this.ptr = ptr;
        }
    }
}
