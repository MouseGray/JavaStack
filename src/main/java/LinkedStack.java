import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> data = null;
    private int size = 0;

    public void push(E element) {
        data = new Node<>(element, data);
        size++;
    }

    public E pop() {
        if (data == null) return null;
        Node<E> node = data;
        data = data.ptr;
        size--;
        return node.value;
    }

    public E peek() {
        return this.data == null ? null : this.data.value;
    }

    public boolean empty() {
        return data == null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return empty();
    }

    public boolean contains(Object o) {
        for (Node<E> current = data; current != null; current = current.ptr) {
            if (current.value.equals(o)) return true;
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return get(index--).value;
            }
        };
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<E> current = data;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.ptr;
        }
        return result;
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        for (Node<E> current = data; current != null; current = current.ptr)
            a[i++] = (T) current.value;
        if (a.length > size) a[size] = null;
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
                size--;
                return true;
            }
            for (Node<E> node = data; ; node = node.ptr) {
                if (node.ptr == null) break;
                if (node.ptr.value == null) {
                    node.ptr = node.ptr.ptr;
                    size--;
                    return true;
                }
            }
        } else {
            if (data.value.equals(o)) {
                data = data.ptr;
                size--;
                return true;
            }
            for (Node<E> node = data; ; node = node.ptr) {
                if (node.ptr == null) break;
                if (node.ptr.value.equals(o)) {
                    node.ptr = node.ptr.ptr;
                    size--;
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
    public boolean removeAll(@NotNull Collection<?> c) {
        return retainOrRemove(c, true);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return retainOrRemove(c, false);
    }

    public void clear() {
        data = null;
        size = 0;
    }

    private Node<E> get(int n) {
        Node<E> result = data;
        for (int i = 0; i < n; i++) {
            result = result.ptr;
        }
        return result;
    }

    private boolean retainOrRemove(Collection<?> c, boolean switcher) {
        boolean result = false;
        while (c.contains(data.value) == switcher) {
            data = data.ptr;
            size--;
            result = true;
        }
        Node<E> node = data;
        while (node.ptr != null) {
            if (c.contains(node.ptr.value) == switcher) {
                node.ptr = node.ptr.ptr;
                size--;
                result = true;
            }
            else {
                node = node.ptr;
            }
        }
        return result;
    }

    private static class Node<T> {
        T value;
        Node<T> ptr;

        public Node(T value, Node<T> ptr) {
            this.value = value;
            this.ptr = ptr;
        }
    }
}
