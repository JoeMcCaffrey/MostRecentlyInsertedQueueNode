
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MostRecentlyInsertedQueue<T> extends AbstractQueue<T> {

	// Define globals
	private int maxSize;
	private int actualSize;
	private LLNode<T> head;
	private LLNode<T> tail;
	
	// constructor takes queue max size
	public MostRecentlyInsertedQueue(int size){
		// Throw an error if invalid size is given
		if(size <= 0){
			throw new NoSuchElementException("Queue has invalid size");
		}
		
		maxSize = size;
		actualSize = 0;
		head = null;
		tail = null;
	}
	
	// Enqueue operation 
	public boolean offer(T value) {
		// Not allowing null to be put into the queue
		if(value == null){
			return false;
		}
		
		// create new node to put into the queue 
		LLNode<T> node = new LLNode<T>(value);
		
		// if the queue is at max size evict the head and add to the tail
		// reincrement the size back to max
		
		if(actualSize == maxSize){
			this.poll();
			actualSize++;
			tail.setNext(node);
			tail = node;	
		}
		else{
			// if this is the first element in the queue point head and tail to it
			if(actualSize == 0){
				head = node;
				tail = node;
				actualSize++;
				
			}
			else{
				// otherwise update the tail only
				tail.setNext(node);
				tail = node;
				actualSize++;
			}
		}
		
		return true;
	}

	// Look at what the head is
	public T peek() {
		// Queue is empty return null
		if(actualSize == 0){
			return null;
		}
		// Return the data of the head node
		return head.getNodeData();
	}

	// remove the head of the queue
	public T poll() {
		// if the queue is empty return null
		if(actualSize == 0 ){ 
			return null;
		}
		// get the value of the head node
		T value = head.getNodeData();
		// reset the head to the next node in the queue
		// if we remove the only item in the queue heat will be reset to null
		head = head.getNext();
		// decrement the size of the queue 
		actualSize --;
		return value;
	}

	// return the actual size global
	public int size() {
		return actualSize;
	}
	
	public Iterator<T> iterator() {
		
		return new QueueIterator(head, tail);
	}
	
	private class QueueIterator implements Iterator<T>{

		private LLNode<T> currentNode;
		private LLNode<T> Tail;
		
		// pass in the head and tail of the queue for head to tail traversal
		public QueueIterator(LLNode<T> first, LLNode<T> last){
			
			currentNode = first;
			Tail = last;
			
		}
		// if the current node is null we dont have a next node
		public boolean hasNext() {
			if(currentNode == null){ return false;}
			return true;
			
		}

		@Override
		public T next() {
			// if we are not at the tail set the current node to the next node
			if(currentNode != Tail){
				T value = currentNode.getNodeData();
				currentNode = currentNode.getNext();
				return value;
			}
			// if we are at the tail get the data and set the current to null 
			// to trigger hasNext()
			else if(currentNode == Tail){
				T value = currentNode.getNodeData();
				currentNode = null;
				return value;
			}
			// if the queue is empty just return null.
	        currentNode = null;
			return null;

		}
		
	}

}
