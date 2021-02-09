import java.util.TreeSet;
import java.util.Collection;

/**
 * This class permits to wrap a big number of java collections to have an array mixed
 * classes we implemented.
 */
public class CollectionFacadeSet implements SimpleSet {
    private Collection<String> collection;

    /**
     * Creates a new facade that wrapping the specified collection.
     * @param collection the collection we have to wrap.
     */
    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
        if (collection.size() > 0) {
            TreeSet<String> mySet = new TreeSet<>(collection);
            collection.clear();
            collection.addAll(mySet);
        }
    }

    /**
     * Method that adds an element to the set.
     * @param newValue the value to add.
     * @return True if the element was added successfully and False if it was already there.
     */
    public boolean add(String newValue) {
        return !collection.contains(newValue) && collection.add(newValue);
    }

    /**
     * Method that searches an element in the set.
     * @param searchVal the value to search.
     * @return True if the element was there, False otherwise.
     */
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Method that deletes an element from the set.
     * @param toDelete the value to delete.
     * @return True if the element was successfully deleted and False otherwise.
     */
    public boolean delete(java.lang.String toDelete) {
        return collection.remove(toDelete);
    }

    /**
     * @return the number of elements in the set.
     */
    public int size() {
        return collection.size();
    }
}
