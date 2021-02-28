import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayStack<E> implements Stack<E> {
    Object[] data = new Object[0];
    int size = 0;

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
        for (Object e: data) {
            if (e.equals(o)) return true;
        }
        return false;
    }

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

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        for(int i = 0; i < a.length; i++) {
            a[i] = (T) data[i];
        }
        return a;
    }

    public boolean add(E e) {
        this.push(e);
        return true;
    }

    public boolean remove(Object o) {
        for(int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                erase(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object e: c) {
            result &= contains(e);
        }
        return result;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            this.push(e);
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for(int i = 0; i < size; i++) {
            if (c.equals(data[i])) {
                data[i] = null;
            }
        }
        return eraseAll();
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        size = 0;
    }

    private void resize() {
        Object[] nData = new Object[size == 0 ? 1 : 2*size];
        System.arraycopy(data, 0, nData, 0, data.length);
        data = nData;
    }

    private boolean eraseAll() {
        int offset = 0;
        boolean result = false;
        for(int i = 0; i < size; i++){
            if (data[i] == null) {
                result = true;
                offset++;
                size--;
            }
            data[i] = data[i + offset];
        }
        return result;
    }

    private void erase(int n) {
        size--;
        for(int i = n; i < size; i++){
            data[i] = data[i + 1];
        }
    }
}
