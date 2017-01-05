package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 *
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
            this.head.next = this.tail = (LLNode<E>) new LLNode<Object>(null);
            this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element )
	{
		// TODO: Implement this method
		if (element != null) {
            LLNode<E> temp;
            this.tail.prev.next = temp = new LLNode<E>(element);
            temp.prev = this.tail.prev;
            temp.next = this.tail;
            this.tail.prev = temp;
            ++this.size;
            return true;
        }
        throw new NullPointerException();
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.
		LLNode<E> temp = this.head;
        if (index < this.size) {
            for (int i = 0; i <= index; ++i) {
                temp = temp.next;
            }
            return temp.data;
        }
        throw new IndexOutOfBoundsException();
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		// TODO: Implement this method
		 if (index < this.size || index == 0 && this.size == 0) {
            LLNode<E> temp = new LLNode<E>(element);
            LLNode<E> tracer = this.head;
            for (int i = 0; i < index; ++i) {
                tracer = tracer.next;
            }
            temp.next = tracer.next;
            tracer.next.prev = temp;
            tracer.next = temp;
            temp.prev = tracer;
            ++this.size;
        } else {
            if (element == null) {
                throw new NullPointerException();
            }
            throw new IndexOutOfBoundsException();
        }
	}


	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		return this.size;
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
		LLNode<E> finder = this.head;
        if (index < this.size) {
            for (int i = 0; i < index; ++i) {
                finder = finder.next;
            }
            Object data = finder.next.data;
            finder.next.next.prev = finder;
            finder.next = finder.next.next;
            --this.size;
            return (E) data;
        }
        throw new IndexOutOfBoundsException();
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
		LLNode<E> finder = this.head;
        if (index < this.size) {
            for (int i = 0; i < index; ++i) {
                finder = finder.next;
            }
            Object lastData = finder.next.data;
            finder.next.data = element;
            return (E) lastData;
        }
        if (element == null) {
            throw new NullPointerException();
        }
        throw new IndexOutOfBoundsException();
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

}
