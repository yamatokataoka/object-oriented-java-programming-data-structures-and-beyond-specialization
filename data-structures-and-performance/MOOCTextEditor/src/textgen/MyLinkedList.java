package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
	    head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        size = 0;
        head.next = tail;
        tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
		    throw new NullPointerException();
		}

		new LLNode<E>(element, tail);

		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
	    LLNode<E> current = head;

	    if (index >= size || index < 0 || size == 0) {
	        throw new IndexOutOfBoundsException();
	    }

		for (int k=0; k<=index; k++) {
		    current = current.next;
		}

		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
	    LLNode<E> current = head;

	    if (element == null) {
            throw new NullPointerException();
        }

	    if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int k=0; k<=index; k++) {
            current = current.next;
        }

        new LLNode<E>(element, current);

        size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e,LLNode<E> nextNode) 
    {
	    this(e);
	    this.next = nextNode;
	    this.prev = nextNode.prev;
	    nextNode.prev.next = this;
	    nextNode.prev = this;
    }
}
