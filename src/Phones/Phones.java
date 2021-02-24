package Phones;

public class Phones<T> {

    private byte index = 0;
    private final Object[] collection;

    public Phones() {

        this.collection = new Object[5];
    }

    /**
     * Method that adds a new Serial Number to the Custom Array List
     * @param element The Serial Number that we want to add
     */
    public void add(Object element) {

        this.collection[this.index++] = element;
    }

    /**
     * Method that returns the pointed Serial NUmber
     * @param index The pointer to the Serial Number
     * @return The Serial Number
     */
    public T get(int index) {
        return (T)this.collection[index];
    }
}
