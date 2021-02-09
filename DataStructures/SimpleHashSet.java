/**
 * An abstract class that represents the general architecture of a simple set
 * OpenHashSet and ClosedHashSet implement it.
 */
public abstract class SimpleHashSet implements SimpleSet {

    //Variable that represents the initial capacity of the set
    private static final int INITIAL_CAPACITY = 16;

    //Variable that represents the default upper factor
    private static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    //Variable that represents the default lower factor
    private static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    protected int capacity;
    protected float upperLoadFactor;
    protected float lowerLoadFactor;
    protected int currentSize;

    /**
     * Default constructor: create a new empty table with initial capacity 16, upper factor 0.75,
     * lower factor 0.25.
     */
    protected SimpleHashSet() {
        capacity = INITIAL_CAPACITY;
        currentSize = 0;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Constructor: create a new empty table with initial capacity 16 and the given factors.
     *
     * @param upperLoadFactor the upper factor
     * @param lowerLoadFactor the lower factor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        capacity = INITIAL_CAPACITY;
        currentSize = 0;
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * @return the current capacity of the table.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * @return the size of the set.
     */
    public int size() {
        return currentSize;
    }

    /**
     * @return the lowerLoadFactor.
     */
    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }

    /**
     * @return the upperLoadFactor.
     */
    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }

    /**
     * Clamps an index to a valid one.
     *
     * @param index the index to clamp
     * @return Valid index
     */
    protected int clamp(int index) {
        return index & (capacity - 1);
    }
}
