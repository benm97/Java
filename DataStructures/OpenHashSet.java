/**
 * This class implements an open hashset.
 */
public class OpenHashSet extends SimpleHashSet {
    private MyLinkedList[] table;

    //Variable that represents the number by which we multiply/divide the capacity in the rehash.
    private static final int FACTOR_CAPACITY = 2;

    //Variable that represents the minimal capacity of the table.
    private static final int MINIMAL_CAPACITY = 1;

    /**
     * Default constructor: create a new empty table with initial capacity 16, upper factor 0.75,
     * lower factor 0.25.
     */
    public OpenHashSet() {
        super();
        table = new MyLinkedList[capacity];
    }

    /**
     * Constructor: create a new empty table with initial capacity 16 and the given factors.
     *
     * @param upperLoadFactor the upper factor
     * @param lowerLoadFactor the lower factor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        table = new MyLinkedList[capacity];
    }

    /**
     * Constructor: create a new empty table and adds the elements one by one with initial
     * capacity 16 and factors of 0.75 and 0.25.
     *
     * @param data values to add.
     */
    public OpenHashSet(java.lang.String[] data) {
        this();
        for (String element : data)
            add(element);
    }

    /**
     * Create the new table and rehashing the elements.
     */
    private void rehash() {
        MyLinkedList[] oldTable = table;
        table = new MyLinkedList[capacity];
        currentSize = 0;
        for (MyLinkedList list : oldTable) {
            if (list != null) {
                for (String element : list.getMyList()) {
                    noCheckAdd(element);
                }
            }
        }
    }

    /**
     * Methods that adds element without checking if we need to rehash.
     *
     * @param newValue value to add.
     * @return True if the element successfully added and False if the element was already there.
     */
    private boolean noCheckAdd(java.lang.String newValue) {
        int index = clamp(newValue.hashCode());
        if (table[index] == null)
            table[index] = new MyLinkedList();
        if (!table[index].getMyList().contains(newValue)) {
            table[index].getMyList().add(newValue);
            currentSize++;
            return true;
        }
        return false;
    }

    /**
     * Method that adds element.
     *
     * @param newValue value to add
     * @return True if the element successfully added and False if the element was already there.
     */
    public boolean add(java.lang.String newValue) {
        if ((float) (currentSize + 1) / (float) (capacity) > upperLoadFactor) {
            capacity *= FACTOR_CAPACITY;
            rehash();
        }
        return noCheckAdd(newValue);
    }

    /**
     * Methods that searches an element and return True if it is there and False otherwise.
     *
     * @param searchVal value to search.
     * @return True if the element there, False otherwise.
     */
    public boolean contains(java.lang.String searchVal) {
        int index = clamp(searchVal.hashCode());
        return table[index] != null && table[index].getMyList().contains(searchVal);
    }

    /**
     * Methods that deletes an element.
     *
     * @param toDelete value to delete.
     * @return True if the element successfully deleted and False if it wasn't in the table.
     */
    public boolean delete(java.lang.String toDelete) {
        int index = clamp(toDelete.hashCode());
        if (table[index] == null)
            return false;
        if (table[index].getMyList().contains(toDelete)) {
            table[index].getMyList().remove(toDelete);
            currentSize--;
            if (((float) (currentSize) / (float) (capacity)) < lowerLoadFactor && capacity >= MINIMAL_CAPACITY + 1) {
                capacity /= FACTOR_CAPACITY;
                rehash();
            }
            return true;
        }
        return false;
    }
}
