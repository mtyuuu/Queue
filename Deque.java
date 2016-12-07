import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int count;
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
		
		public Node(Item item) {
			this.item = item;
			next = null;
			prev = null;
		}
	}
	
	// construct an empty deque
	public Deque() {
		count = 0;
		first = null;
		last = null;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return count == 0;
	}
	
	// return the number of items on the deque
	public int size() {
		return count;
	}
	
	// add the item to the front
	public void addFirst(Item item)  {
		if (item == null)
			throw new NullPointerException();
		if (isEmpty()) {
			first = new Node(item);
			last = first;
		} else {
			Node node = new Node(item);
			node.next = first;
			first.prev = node;
			first = node;
		}
		++count;
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		if (isEmpty()) {
			last = new Node(item);
			first = last;
		} else {
			Node node = new Node(item);
			node.prev = last;
			last.next = node;
			last = node;
		}
		++count;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		if (first != null)
			first.prev = null;
		else
			last = null;
		--count;
		return item;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = last.item;
		last = last.prev;
		if (last != null)
			last.next = null;
		else
			first = null;
		--count;
		return item;
	}
	
	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
  //    deque.removeLast();
        System.out.println(deque.size());
        for (Integer i : deque) 
            System.out.println(i);
	}
	
}
