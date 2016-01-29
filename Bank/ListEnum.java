/* ListEnum.java */

//package sortedlist;

import java.util.Enumeration;
/**
 * The ListEnum class implements an Enumeration for singly-linked lists.
 * ListEnum objects are mutable.
 **/

public class ListEnum implements Enumeration {
    private ListNode t;

    /**
     * Creates a new enumeration for the elements linked with ListNodes
     * starting with l. Requires that there are no cycles in the list.
     */
    public ListEnum( ListNode l ) {
	t = l;
    }

    /**
     * Tests if this enumeration contains more elements.
     * @return true if this enumeration contains more elements; false otherwise
     */
    public boolean hasMoreElements() {
	return (t != null);
    }

    /**
     * Returns the next element of this enumeration.
     * Modifies the enumeration to move the enumeration past the returned
     * element of the list.
     * @return the next element of this enumeration.
     */
    public Object nextElement() {
	Object retItem = t.item;
	t = t.next;
	return retItem;
    }
}
