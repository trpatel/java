/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

    
    
    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */
    private int x;
    private int y;
    private int starvetime;
    private DList dllist;
    private DLNode dlnode;
    private DLNode prev = new DLNode();


    /**
     *  The following methods are required for Part II.
     */

 
    /**
     *  RunLengthEncoding() (with three parameters) is a constructor that creates
     *  a run-length encoding of an empty ocean having width i and height j,
     *  in which sharks starve after starveTime timesteps.
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     */

    public RunLengthEncoding(int i, int j, int starveTime) {
	// Your solution here.
	this.x = i;
	this.y = j;
	this.starvetime = starveTime;
	this.dllist = new DList();
	DLNode startNode = new DLNode(Ocean.EMPTY, i*j, starvetime);
	this.dllist.pushEnd(startNode);
	prev.next = this.dllist.head;
	this.dlnode = prev;
    }

    /**
     *  RunLengthEncoding() (with five parameters) is a constructor that creates
     *  a run-length encoding of an ocean having width i and height j, in which
     *  sharks starve after starveTime timesteps.  The runs of the run-length
     *  encoding are taken from two input arrays.  Run i has length runLengths[i]
     *  and species runTypes[i].
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     *  @param runTypes is an array that represents the species represented by
     *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
     *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
     *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

    public RunLengthEncoding(int i, int j, int starveTime, int[] runTypes, int[] runLengths) {
	// Your solution here.
	this.x = i;
	this.y = j;
	this.starvetime = starveTime;
	this.dllist = new DList();
	int nRuns = runTypes.length;
	for(int a = 0; a < nRuns; a++){
	    dllist.pushEnd(new DLNode(runTypes[a], runLengths[a], this.starvetime));
	    prev.next = this.dllist.head;
	    this.dlnode = prev;
	}
    }
  

    /**
     *  restartRuns() and nextRun() are two methods that work together to return
     *  all the runs in the run-length encoding, one by one.  Each time
     *  nextRun() is invoked, it returns a different run (represented as a
     *  TypeAndSize object), until every run has been returned.  The first time
     *  nextRun() is invoked, it returns the first run in the encoding, which
     *  contains cell (0, 0).  After every run has been returned, nextRun()
     *  returns null, which lets the calling program know that there are no more
     *  runs in the encoding.
     *
     *  The restartRuns() method resets the enumeration, so that nextRun() will
     *  once again enumerate all the runs as if nextRun() were being invoked for
     *  the first time.
     *
     *  (Note:  Don't worry about what might happen if nextRun() is interleaved
     *  with addFish() or addShark(); it won't happen.)
     */

    /**
     *  restartRuns() resets the enumeration as described above, so that
     *  nextRun() will enumerate all the runs from the beginning.
     */
   
    public void restartRuns() {
	prev.next = this.dllist.head; //resets the enumeration
	// Your solution here.
	this.dlnode = prev;
    }

    /**
     *  nextRun() returns the next run in the enumeration, as described above.
     *  If the runs have been exhausted, it returns null.  The return value is
     *  a TypeAndSize object, which is nothing more than a way to return two
     *  integers at once.
     *  @return the next run in the enumeration, represented by a TypeAndSize
     *          object.
     */

    public TypeAndSize nextRun() {
	// Replace the following line with your solution.
	this.dlnode = this.dlnode.next;
	if(this.dlnode != null){
	    return this.dlnode.data;
	}else{
	    return null;
	}
    }

    /**
     *  toOcean() converts a run-length encoding of an ocean into an Ocean
     *  object.  You will need to implement the three-parameter addShark method
     *  in the Ocean class for this method's use.
     *  @return the Ocean represented by a run-length encoding.
     */

    public Ocean toOcean() {
	DLNode iter = this.dllist.head;
	Ocean ocean = new Ocean(this.x, this.y, this.starvetime);
	int runtype;
	int runsize;
	int a = 0;
	int b = 0;
	while(iter != null){
	    runtype = iter.data.type;
	    runsize = iter.data.size;
	    switch(runtype){
	    case Ocean.EMPTY:
		for(int i=0; i < runsize; i++){
		    if(a == this.x){
			a = 0;
			b++;
			a++;
		    }else{
			b++;
		    }
		}
		break;
	    case Ocean.FISH:
		for(int i=0; i < runsize; i++){
		    if(a == this.x){
			a = 0;
			b++;
			ocean.addFish(x, y);
			a++;
		    }else{
			ocean.addFish(x, y);
			a++;
		    }
		}
		break;
	    case Ocean.SHARK:
		for(int i = 0; i < runsize; i++){
		    if(a == this.x){
			a = 0;
			b++;
			ocean.addShark(a, b, iter.starveTime);
			a++;
		    }else{
			ocean.addShark(a, b, iter.starveTime);
			a++;
		    }
		}
		break;
	    }
	    iter = iter.next;
	}
	return ocean;
    }
    // Replace the following line with your solution.

    /**
       /**
       *  RunLengthEncoding() (with one parameter) is a constructor that creates
       *  a run-length encoding of an input Ocean.  You will need to implement
       *  the sharkFeeding method in the Ocean class for this constructor's use.
       *  @param sea is the ocean to encode.
       */

    public RunLengthEncoding(Ocean sea) {
	// Your solution here, but you should probably leave the following line
	//   at the end.
	this.x = sea.width();
	this.y = sea.height();
	this.starvetime = sea.starveTime();
	this.dllist = new DList();
	int type = sea.cellContents(0,0);
	int type2 = type;
	int l = 0;
	int hunger;
	int hunger2;
	if(type == Ocean.SHARK){
	    hunger = sea.sharkFeeding(0, 0);
	}else{
	    hunger = -1;
	}
	hunger2 = hunger;
	for (int b = 0; b < this.y; b++) {
	    for (int a = 0; a < this.x; a++) {
		type = sea.cellContents(x, y);
		if (type == Ocean.SHARK) {
		    hunger = sea.sharkFeeding(x, y);
		}
		if (type2 != type) {
		    if (type2 == Ocean.SHARK) {
			this.dllist.pushEnd(new DLNode(type2, l, hunger));
		    } else {
			this.dllist.pushEnd(new DLNode(type2, l));
		    }
		    type2 = type;
		    hunger2 = hunger;
		    l = 0;
		} else if (hunger2 != hunger && type == Ocean.SHARK) {
		    this.dllist.pushEnd(new DLNode(type2, l, hunger2));
		    hunger2 = hunger;
		    l = 0;
		}
		l++;
	    }
	}
	if(type == Ocean.SHARK){
	    this.dllist.pushEnd(new DLNode(type, l, hunger));
	}else{
	    this.dllist.pushEnd(new DLNode(type, l));
	}
	prev.next = this.dllist.head;
	this.dlnode = prev;
	check();
    }

    /**
     *  The following methods are required for Part IV.
     */

    /**
     *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.  The final run-length
     *  encoding should be compressed as much as possible; there should not be
     *  two consecutive runs of sharks with the same degree of hunger.
     *  @param x is the x-coordinate of the cell to place a fish in.
     *  @param y is the y-coordinate of the cell to place a fish in.
     */

    public void addFish(int x, int y) {
	// Your solution here, but you should probably leave the following line
	//   at the end.
	int width = this.x;
	int height = this.y;
	int EMPTY = Ocean.EMPTY;
	Ocean ocean = new Ocean(width, height, 1);
	DLNode node =  new DLNode(Ocean.FISH, 1);
	DLNode iter = new DLNode();
	x = ocean.wrapX(x);
	y = ocean.wrapY(y);
	node.setStart(x, y, width);
	node.setEnd(x, y, width);
	if(this.dllist.head == null){
	    if(x != 0 || y != 0){
		DLNode emptyb = new DLNode(EMPTY, node.start);
		emptyb.start = 0;
		emptyb.end = node.start - 1;
		this.dllist.pushEnd(emptyb);
	    }
	    this.dllist.pushEnd(node);
	    DLNode emptya = new DLNode(EMPTY, width * height-node.end-1);
	    emptya.start = node.end + 1;
	    emptya.end = width * height - 1;
	    this.dllist.pushEnd(emptya);
	}else{
	    iter = this.dllist.head;
	    iter.start = 0;
	    iter.end = iter.data.size + iter.start-1;
	    DLNode br = new DLNode(EMPTY, 1);
	    while(iter != null){
		if(node.start > iter.start && node.end < iter.end && iter.data.type == EMPTY){
		    br.end = iter.end;
		    br.start = node.end+1;
		    br.data.size = br.end-br.start+1;
		    iter.end = node.start-1;
		    iter.data.size = iter.end-iter.start+1;
		    this.dllist.insertAfter(iter, node);
		    this.dllist.insertAfter(node, br);
		    break;
		}else if(node.start == iter.start && node.end < iter.end && iter.data.type == EMPTY){
		    this.dllist.insertBefore(iter, node);
		    iter.data.size--;
		    iter.start++;
		    if(node.prev != null && node.prev.data.type == node.data.type){
			node.prev.end++;
			node.prev.data.size++;
			this.dllist.delete(node);
		    }
		    break;
		}else if(node.start > iter.start && node.end == iter.end && iter.data.type == EMPTY){
		    this.dllist.insertAfter(iter, node);
		    iter.data.size--;
		    iter.end--;
		    if(node.next != null && node.next.data.type == node.data.type){
			node.next.start--;
			node.next.data.size++;
			this.dllist.delete(node);
		    }
		    break;
		}else if(node.start == iter.start && node.end == iter.end && iter.data.type == EMPTY){
		    iter.data.type = Ocean.FISH;
		    node = iter;
		    if(node.prev != null && node.prev.data.type == node.data.type){
			node.next.start = node.start;
			node.next.data.size += node.data.size;
			this.dllist.delete(node);
		    }
		    break;
		}
		iter = iter.next;
		iter.start = iter.prev.end+1;
		iter.end = iter.start + iter.data.size-1;
	    }
	}
	check();
    }

    /**
     *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
     *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  The final run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs of sharks with the same degree
     *  of hunger.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */

    public void addShark(int x, int y) {
	int width = this.x;
	int height = this.y;
	int starveTime = this.starvetime;
	int EMPTY = Ocean.EMPTY;
	Ocean ocean = new Ocean(width, height, 1);
	DLNode node = new DLNode(Ocean.SHARK, 1, starveTime);
	DLNode iter = new DLNode();
	x = ocean.wrapX(x);
	y = ocean.wrapY(y);
	node.setStart(x, y, width);
	node.setEnd(x, y, width);
	if(this.dllist.head == null){
	    if(x != 0 || y != 0){
		DLNode emptyb = new DLNode(EMPTY, node.start);
		emptyb.start = 0;
		emptyb.end = node.start - 1;
		this.dllist.pushEnd(emptyb);
	    }
	    this.dllist.pushEnd(node);
	    DLNode emptya = new DLNode(EMPTY, width * height-node.end-1);
	    emptya.start = node.end + 1;
	    emptya.end = width * height - 1;
	    this.dllist.pushEnd(emptya);
	}else{
	    iter = this.dllist.head;
	    iter.start = 0;
	    iter.end = iter.data.size + iter.start-1;
	    DLNode br = new DLNode(EMPTY, 1);
	    while(iter != null){
		if(node.start > iter.start && node.end < iter.end && iter.data.type == EMPTY){
		    br.end = iter.end;
		    br.start = node.end+1;
		    br.data.size = br.end-br.start+1;
		    iter.end = node.start-1;
		    iter.data.size = iter.end-iter.start+1;
		    this.dllist.insertAfter(iter, node);
		    this.dllist.insertAfter(node, br);
		    break;
		}else if(node.start == iter.start && node.end < iter.end && iter.data.type == EMPTY){
		    this.dllist.insertBefore(iter, node);
		    iter.data.size--;
		    iter.start++;
		    if(node.prev != null && node.prev.data.type == node.data.type){
			node.prev.end++;
			node.prev.data.size++;
			this.dllist.delete(node);
		    }
		    break;
		}else if(node.start > iter.start && node.end == iter.end && iter.data.type == EMPTY){
		    this.dllist.insertAfter(iter, node);
		    iter.data.size--;
		    iter.end--;
		    if(node.next != null && node.next.data.type == node.data.type){
			node.next.start--;
			node.next.data.size++;
			this.dllist.delete(node);
		    }
		    break;
		}else if(node.start == iter.start && node.end == iter.end && iter.data.type == EMPTY){
		    iter.data.type = Ocean.FISH;
		    node = iter;
		    if(node.prev != null && node.prev.data.type == node.data.type){
			node.next.start = node.start;
			node.next.data.size += node.data.size;
			this.dllist.delete(node);
		    }
		    break;
		}
		iter = iter.next;
		iter.start = iter.prev.end+1;
		iter.end = iter.start + iter.data.size-1;
	    }
	}
	check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same contents, or if the sum of all run
     *  lengths does not equal the number of cells in the ocean.
     */

    private void check() {
	// a string which holds the run type of the current and previous run for comparison
	int size = this.x*this.y;
	int sizeCount = 0;
	DLNode iter = new DLNode();
	iter = this.dllist.head;
	while(iter != null){
	    sizeCount += iter.data.size;
	    iter = iter.next;
	}
	if(sizeCount != size){
	    System.out.printf("Length sum does not equal total length");
	}
	iter = this.dllist.head;
	while(iter != null && iter.next != null){
	    if(iter.data.type == iter.next.data.type){
		if(iter.data.type != Ocean.SHARK || iter.data.type == Ocean.SHARK && iter.starveTime == iter.next.starveTime){
		    System.out.printf("Not allowed");
		    break;
		}
		iter = iter.next;
	    }
	}
    }
}
