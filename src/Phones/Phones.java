package Phones;

public class Phones<T> {

    private int index = 0;
    private final Object[] collection;

    public Phones() {
        this.collection = new Object[5];
    }

    public void add(Object element) {

        this.collection[this.index++] = element;
    }

    public T get(int index) {
        return (T)this.collection[index];
    }
}
