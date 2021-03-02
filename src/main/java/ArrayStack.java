import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayStack<E> implements Stack<E> {
    private Object[] data = new Object[0];
    private int size = 0;

    public void push(E element) {
        if (data.length == size) resize();
        data[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (size == 0) return null;
        return (E) data[--size];
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) return null;
        return (E) data[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return empty();
    }

    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null) return true;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(data[i])) return true;
        }
        return false;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) data[index++];
            }
        };
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) a[size] = null;
        return a;
    }

    public boolean add(E e) {
        this.push(e);
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null) {
                    erase(i);
                    return true;
                }
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(data[i])) {
                    erase(i);
                    return true;
                }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c)
            this.push(e);
        return !c.isEmpty();
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return removeOrRetain(c, false);
    }

    public boolean retainAll(@NotNull Collection<?> c) {
        return removeOrRetain(c, true);
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    private void resize() {
        Object[] nData = new Object[size == 0 ? 1 : 2*size];
        System.arraycopy(data, 0, nData, 0, data.length);
        data = nData;
    }

    private void erase(int n) {
        if (size-- - n >= 0)
            System.arraycopy(data, n + 1, data, n, size - n);
        data[size] = null;
    }

    private boolean removeOrRetain(Collection<?> c, boolean switcher) {
        int offset = 0;
        for (int i = 0; i < size; i++)
            if (c.contains(data[i]) == switcher)
                data[offset++] = data[i];
        for (int i = offset; i < size; i++)
            data[i] = null;
        boolean result = (offset != size);
        size = offset;
        return result;
    }
}
