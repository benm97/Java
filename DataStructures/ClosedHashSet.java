/**
 * This class implements a closed hashset.
 */
public class ClosedHashSet extends SimpleHashSet {

    private String[] table;

    //Object that flags deleted element.
    private static final String DELETED = "deleted";

    //Variable that represents the number by which we multiply/divide the capacity in the rehash.
    private static final int FACTOR_CAPACITY = 2;

    //Variable that represents the minimal capacity of the table.
    private static final int MINIMAL_CAPACITY = 1;

    /**
     * Default constructor: create a new empty table with initial capacity 16, upper factor 0.75,
     * lower factor 0.25.
     */
    public ClosedHashSet() {
        super();
        table = new String[capacity];
    }

    /**
     * Constructor: create a new empty table with initial capacity 16 and the given factors.
     *
     * @param upperLoadFactor the upper factor
     * @param lowerLoadFactor the lower factor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        table = new String[capacity];
    }

    /**
     * Constructor: create a new empty table and adds the elements one by one with initial
     * capacity 16 and factors of 0.75 and 0.25.
     *
     * @param data values to add.
     */
    public ClosedHashSet(java.lang.String[] data) {
        this();
        for (String element : data)
            add(element);
    }

    /**
     * Create the new table and rehashing the elements.
     */
    private void rehash() {
        String[] oldTable = table;
        table = new String[capacity];
        currentSize = 0;
        for (String element : oldTable) {
            if (element != null && element != DELETED)
                noCheckAdd(element);
        }
    }

    /**
     * Methods that adds element without checking if we need to rehash.
     *
     * @param newValue element to add
     * @return True if the element successfully added and False if the element was already there.
     */
    private boolean noCheckAdd(java.lang.String newValue) {

        int hashCode = newValue.hashCode();
        int index;
        for (int i = 0; i < capacity; i++) {
            index = clamp((hashCode + (i + i * i) / 2));
            if (table[index] == null || table[index] == DELETED) {
                table[index] = newValue;
                currentSize++;
                break;
            } else if (table[index].equals(newValue)) {
                return false;
            }
        }
        return true;
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
     * Methods that searches element and return it index if it finds it and -1 otherwise.
     *
     * @param newValue: value to search.
     * @return The index of the element if it is there, -1 otherwise.
     */
    private int findElement(java.lang.String newValue) {
        int hashCode = newValue.hashCode();
        int index;
        for (int i = 0; i < capacity; i++) {
            index = clamp((hashCode + (i + i * i) / 2));

            if (table[index] == null)
                break;
            else if (table[index].equals(newValue) && table[index] != DELETED)
                return index;
        }
        return -1;
    }

    /**
     * Methods that searches an element and return True if it is there and False otherwise.
     *
     * @param searchVal value to search.
     * @return True if the element there, False otherwise.
     */
    public boolean contains(java.lang.String searchVal) {
        return findElement(searchVal) != -1;

    }

    /**
     * Methods that deletes an element.
     *
     * @param toDelete value to delete.
     * @return True if the element successfully deleted and False if it wasn't in the table.
     */
    public boolean delete(java.lang.String toDelete) {
        int index = findElement(toDelete);
        if (index != -1) {
            currentSize--;
            table[index] = DELETED;

            if (((float) (currentSize) / (float) (capacity)) < lowerLoadFactor && capacity >= MINIMAL_CAPACITY + 1) {
                capacity /= FACTOR_CAPACITY;
                rehash();
            }
            return true;
        }
        return false;
    }
}
