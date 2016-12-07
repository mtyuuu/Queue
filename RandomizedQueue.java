import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

// we can not use the linkedlist to realize the random access, so we must use the array to implement
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int count;
	
	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		count = 0;
	}
	
	// is the queue empty?
	public boolean isEmpty() {
        return count == 0;
    }
	
	// return the number of items on the queue
	public int size() {
		return count;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();
		if (count == queue.length)
			resize(2 * queue.length);
		queue[count] = item;
		++count;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		int index = StdRandom.uniform(count);
		Item item = queue[index];
		queue[index] = null;
		exchange(queue, index, count - 1);
		--count;
		if (count > 0 && count == queue.length / 4)
			resize(queue.length / 2);
		return item;
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		int index = StdRandom.uniform(count);
		return queue[index];
	}
	
	private void exchange(Item[] array, int i, int j) {
		if (i == j) return;
		Item temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < count; ++i)
			copy[i] = queue[i];
		queue = copy;
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item[] randomQueue;
		private int currentIndex;
		
		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			randomQueue = (Item[]) new Object[count];
			for (int i = 0; i < count; ++i)
				randomQueue[i] = queue[i];
			shuffle(randomQueue);
			currentIndex = 0;
		}

		private void shuffle(Item[] array) {
			// TODO Auto-generated method stub
			int n = array.length;
			for (int i = 0; i < n; ++i) {
				int index = i + StdRandom.uniform(n - i);
				exchange(array, i, index);
			}
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentIndex < randomQueue.length;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = randomQueue[currentIndex];
			++currentIndex;
			return item;
		}
		
	}
	
}
