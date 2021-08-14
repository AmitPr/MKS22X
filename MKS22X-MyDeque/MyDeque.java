import java.util.NoSuchElementException;

public class MyDeque<E> {
    private E[] data;
    private int size, start, end;

    @SuppressWarnings("unchecked")
    public MyDeque() {
        data = (E[]) new Object[10];
        size = 10;
        start = end = 0;
    }

    @SuppressWarnings("unchecked")
    public MyDeque(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        size = initialCapacity;
        start = end = 0;
    }

    public int size() {
        int s = 0;
        if (end < start) {
            s += (size - start);
            s += end;
        } else {
            s = end - start;
        }
        return s;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (end > start) {
            for (int i = start; i < end; i++) {
                if (data[i] != null)
                    sb.append(data[i].toString() + " ");
            }
        } else {
            for (int i = start; i < size; i++) {
                if (data[i] != null)
                    sb.append(data[i].toString() + " ");
            }
            for (int i = 0; i < end; i++) {
                if (data[i] != null)
                    sb.append(data[i].toString() + " ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newLength = data.length * 2;
        E[] temp = (E[]) new Object[newLength];
        int max = 0;
        if (end > start) {
            int index = 0;
            for (int i = start; i < end; i++) {
                temp[index] = data[i];
                index++;
            }
            max = index;
        } else {
            int index = 0;
            for (int i = start; i < size; i++) {
                temp[index] = data[i];
                index++;
            }
            for (int i = 0; i < end; i++) {
                temp[index] = data[i];
                index++;
            }
            max = index;
        }
        start = 0;
        size = newLength;
        end = max;
        data = temp;
    }

    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (start == 0) {
            if (end == data.length - 1) {
                resize();
            }
            start = data.length - 1;
            data[start] = element;
        } else {
            if (start - 1 == end) {
                resize();
                start = data.length - 1;
            } else {
                start--;
            }
            data[start] = element;
        }
    }

    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (end < start) {
            data[end] = element;
            end++;
            if (end + 1 == start) {
                resize();
            }
        } else {
            if (end == data.length - 1) {
                if (start > 0) {
                    data[end] = element;
                    end = 0;
                } else {
                    resize();
                    data[end] = element;
                    end++;
                }
            } else {
                data[end] = element;
                end++;
            }
        }
    }

    public E removeFirst() {
        if (end == start) {
            throw new NoSuchElementException();
        }
        E element = data[start];
        data[start] = null;
        if (start <= end) {
            start++;
        } else {
            if (start == data.length - 1) {
                start = 0;
            } else {
                start++;
            }
        }
        return element;
    }

    public E removeLast() {
        if (end == start) {
            throw new NoSuchElementException();
        }
        if (end > start) {
            end--;
        } else {
            if (end > 0) {
                end--;
            } else {
                end = data.length - 1;
            }
        }
        E element = data[end];
        data[end] = null;
        return element;
    }

    public E getFirst() {
        if (end == start) {
            throw new NoSuchElementException();
        }
        return data[start];
    }

    public E getLast() {
        if (end == start) {
            throw new NoSuchElementException();
        }
        if (end == 0) {
            return data[data.length - 1];
        }
        return data[end - 1];
    }
}