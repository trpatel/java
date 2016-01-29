/* ListSorts.java */

import list.*;

public class ListSorts {

    private final static int SORTSIZE = 100000;

    /**
     *  makeQueueOfQueues() makes a queue of queues, each containing one item
     *  of q.  Upon completion of this method, q is empty.
     *  @param q is a LinkedQueue of objects.
     *  @return a LinkedQueue containing LinkedQueue objects, each of which
     *    contains one object from q.
     **/
    public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
	LinkedQueue retq = new LinkedQueue();
	LinkedQueue t;
	while(q.size() > 0){
	    try{
		t = new LinkedQueue();
		t.enqueue(q.dequeue());
		retq.enqueue(t);
	    }catch (QueueEmptyException e){
		System.out.println("QueueEmptyExeption error");
	    }
	}
	// Replace the following line with your solution.
	return retq;
    }

    /**
     *  mergeSortedQueues() merges two sorted queues into a third.  On completion
     *  of this method, q1 and q2 are empty, and their items have been merged
     *  into the returned queue.
     *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @return a LinkedQueue containing all the Comparable objects from q1
     *   and q2 (and nothing else), sorted from smallest to largest.
     **/
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
	LinkedQueue rq = new LinkedQueue();
	Comparable l = null;
	LinkedQueue remove = null;
	Comparable one;
	Comparable two;
	while(remove == null || remove.size() > 0){
	    try{
		if(l == null){
		    one = (Comparable) q1.dequeue();
		    two = (Comparable) q2.dequeue();
		}else{
		    if(remove == q1){
			one = (Comparable) remove.dequeue();
			two = l;
		    }else{
			one = l;
			two = (Comparable) remove.dequeue();
		    }
		}
		if(one.compareTo(two) <= 0){
		    rq.enqueue(one);
		    l = two;
		    remove = q1;
		}else{
		    rq.enqueue(two);
		    l = one;
		    remove = q2;
		}
	    }catch (QueueEmptyException e){
		System.out.println("QueueEmptyException thrown; items on queue");
	    }
	}
	if(l != null){
	    rq.enqueue(l);
	}
	while(q1.size() > 0){
	    try{
		rq.enqueue(q1.dequeue());
	    }catch (QueueEmptyException e){
		System.out.println("QueueEmptyException thrown; size > 0");
	    }
	}
	while(q2.size() > 0){
	    try{
		rq.enqueue(q2.dequeue());
	    }catch (QueueEmptyException e){
		System.out.println("QueueEmptyException thrown; size > 0");
	    }
	}
	// Replace the following line with your solution.
	return rq;
    }

    /**
     *  partition() partitions qIn using the pivot item.  On completion of
     *  this method, qIn is empty, and its items have been moved to qSmall,
     *  qEquals, and qLarge, according to their relationship to the pivot.
     *  @param qIn is a LinkedQueue of Comparable objects.
     *  @param pivot is a Comparable item used for partitioning.
     *  @param qSmall is a LinkedQueue, in which all items less than pivot
	     *    will be enqueued.
	 *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
	     *    will be enqueued.
	 *  @param qLarge is a LinkedQueue, in which all items greater than pivot
	     *    will be enqueued.
	     **/
    public static void partition(LinkedQueue qIn, Comparable pivot,
				 LinkedQueue qSmall, LinkedQueue qEquals,
				 LinkedQueue qLarge) {
	while(qIn.size() > 0){
	    try{
		Comparable t = (Comparable) qIn.dequeue();
		if(t.compareTo(pivot) < 0){
		    qSmall.enqueue(t);
		}else if(t.compareTo(pivot) == 0){
		    qEquals.enqueue(t);
		}else{
		    qLarge.enqueue(t);
		}
	    }catch (QueueEmptyException e){
		System.out.println("QueueEmptyException thrown; size > 0");
	    }
	}
	if(qSmall.size() > 1){
	    quickSort(qSmall);
	}
	if(qLarge.size() > 1){
	    quickSort(qLarge);
	}
	qIn.append(qSmall);
	qIn.append(qEquals);
	qIn.append(qLarge);
	// Your solution here.
    }

    /**
     *  mergeSort() sorts q from smallest to largest using mergesort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void mergeSort(LinkedQueue q) {
	LinkedQueue x = makeQueueOfQueues(q);
	while(x.size() > 1){
	    try{
		x.enqueue(mergeSortedQueues((LinkedQueue) x.dequeue(), (LinkedQueue) x.dequeue()));
	    }catch (QueueEmptyException e){
		System.out.println("Thrown QueueEmptyException; x size > 1");
	    }
	}
	try{
	    q.append((LinkedQueue) x.dequeue());
	}catch (QueueEmptyException e){
	    System.out.println("Thrown QueueEmptyException; dequeue large");
	}
	// Your solution here.
    }

    /**
     *  quickSort() sorts q from smallest to largest using quicksort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void quickSort(LinkedQueue q) {
	if(q.size() > 0){
	    int pivot;
	    pivot = (((int) (Math.random() * 10.0)) % q.size()) + 1;
	    Comparable piv = (Comparable) q.nth(pivot);
	    partition(q, piv, new LinkedQueue(), new LinkedQueue(), new LinkedQueue());
	}
	// Your solution here.
    }

    /**
     *  makeRandom() builds a LinkedQueue of the indicated size containing
     *  Integer items.  The items are randomly chosen between 0 and size - 1.
     *  @param size is the size of the resulting LinkedQueue.
     **/
    public static LinkedQueue makeRandom(int size) {
	LinkedQueue q = new LinkedQueue();
	for (int i = 0; i < size; i++) {
	    q.enqueue(new Integer((int) (size * Math.random())));
	}
	return q;
    }

    /**
     *  main() performs some tests on mergesort and quicksort.  Feel free to add
     *  more tests of your own to make sure your algorithms works on boundary
     *  cases.  Your test code will not be graded.
     **/
    public static void main(String [] args) {

	LinkedQueue q = makeRandom(10);
	System.out.println(q.toString());
	mergeSort(q);
	System.out.println(q.toString());

	q = makeRandom(10);
	System.out.println(q.toString());
	quickSort(q);
	System.out.println(q.toString());

	// Remove these comments for Part III.
	Timer stopWatch = new Timer();
	q = makeRandom(SORTSIZE);
	stopWatch.start();
	mergeSort(q);
	stopWatch.stop();
	System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
			   stopWatch.elapsed() + " msec.");

	stopWatch.reset();
	q = makeRandom(SORTSIZE);
	stopWatch.start();
	quickSort(q);
	stopWatch.stop();
	System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
			   stopWatch.elapsed() + " msec.");
	//
    }

}
