package filesprocessing;

import filters.Filter;
import orders.Order;

/**
 * A class to represent a section from the command file
 */
class Section {
    /* section's filter object */
    Filter filter;
    /* section's order object */
    Order order;
    /* section's potential error message */
    String[] msgList;

    /**
     * Constructor
     *
     * @param filter  section's filter object
     * @param order   section's order object
     * @param msgList section's potential error message
     */
    Section(Filter filter, Order order, String[] msgList) {
        this.filter = filter;
        this.order = order;
        this.msgList = msgList;
    }
}
