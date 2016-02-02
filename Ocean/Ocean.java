/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

    /**
     *  Do not rename these constants.  WARNING:  if you change the numbers, you
     *  will need to recompile Test4.java.  Failure to do so will give you a very
     *  hard-to-find bug.
     */

    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

    /**
     *  Define any variables associated with an Ocean object here.  These
     *  variables MUST be private.
     */
    private int x;
    private int y;
    private int starvetime;
    private int[][] oceangrid;
    private int[][] hungergrid;
  


    /**
     *  The following methods are required for Part I.
     */

    /**
     *  Ocean() is a constructor that creates an empty ocean having width i and
     *  height j, in which sharks starve after starveTime timesteps.
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     */

    public Ocean(int i, int j, int starveTime) {
	x = i;
	y = j;
	this.starvetime = starveTime;
	oceangrid = new int[i][j];
	hungergrid = new int[i][j];
	// Your solution here.
    }

    /**
     *  width() returns the width of an Ocean object.
     *  @return the width of the ocean.
     */

    public int width() {
	// Replace the following line with your solution.
	return x;
    }

    /**
     *  height() returns the height of an Ocean object.
     *  @return the height of the ocean.
     */

    public int height() {
	// Replace the following line with your solution.
	return y;
    }

    /**
     *  starveTime() returns the number of timesteps sharks survive without food.
     *  @return the number of timesteps sharks survive without food.
     */

    public int starveTime() {
	// Replace the following line with your solution.
	return starvetime;
    }

    /**
     * wrapAround() calculates a valid position in the grid.
     * by "wrapping around" at the edges.
     * wrapX() for X-coordinates.
     * wrapY() for Y-coordinates.
     */
    public int wrapAround(int i, int n) {
	int r = i % n;
	if (r < 0) {
	    r += n;
	}
	return r;
    }
    public int wrapX(int i) {
	return wrapAround(i, x);
    }
    public int wrapY(int i) {
	return wrapAround(i, y);
    }
  
  
    /**
     * neighbors() see who are around a cell.
     * number of empty, fish, shark cells in the surrounding cells.
     */
    private int[] neighbors(int r, int t) {
	int[] circle = new int[3];
	for (int i = r - 1; i <= r + 1; i++) {
	    for (int j = t - 1; j <= t + 1; j++) {
		if (i != r || j != t) {
		    int something = cellContents(wrapX(i), wrapY(t));
		    if (something == Ocean.FISH) {
			circle[2]++;  // number of fish
		    } else if (something == Ocean.SHARK) {
			circle[1]++;  // number of sharks
		    } else {
			circle[0]++;  // number of empty cells
		    }
		}
	    }
	}
	return circle;
    }
  
    /**
     *  addEmpty() empty cell (x, y).
     *  @param x is the x-coordinate of the cell to empty.
     *  @param y is the y-coordinate of the cell to empty
     */

    public void addEmpty(int x, int y) {
	// Your solution here.
	x = wrapX(x);
	y = wrapY(y);
	oceangrid[x][y] = EMPTY;
	hungergrid[x][y] = 0;
    }
  
  
  
    /**
     *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a fish in.
     *  @param y is the y-coordinate of the cell to place a fish in.
     */

    public void addFish(int x, int y) {
	// Your solution here.
	x = wrapX(x);
	y = wrapY(y);
	if(cellContents(x, y) == EMPTY){
	    oceangrid[x][y] = FISH;
	    hungergrid[x][y] = 0;
	}
    }

    /**
     *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
     *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */

    public void addShark(int x, int y) {
	// Your solution here.
	x = wrapX(x);
	y = wrapY(y);
	if(cellContents(x,y) == EMPTY){
	    oceangrid[x][y] = SHARK;
	    hungergrid[x][y] = 0;
	}
    }

    /**
     *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
     *  a fish, and SHARK if it contains a shark.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */

    public int cellContents(int x, int y) {
	// Replace the following line with your solution.
	x = wrapX(x);
	y = wrapY(y);
	return oceangrid[x][y];
    }

    /**
     *  timeStep() performs a simulation timestep as described in README.
     *  @return an ocean representing the elapse of one timestep.
     */

    public Ocean timeStep() {
	// Replace the following line with your solution.
	int w = x;
	int h = y;
	int s = starvetime;
	Ocean newOcean = new Ocean(w, h, s);
	for(int a = 0; a < w; a++){
	    for(int b = 0; b < h; b++){
		int[] c = neighbors(x, y);
		int nshark = c[1];
		int nfish = c[2];
		switch (cellContents(x, y)){
		case EMPTY:
		    if(nfish < 2){
			//stay empty
		    }else if(nfish >= 2 && nshark <= 1){
			//create fish
			newOcean.addFish(x, y);
		    }else if(nfish >= 2 && nshark >= 2){
			//create shark
			newOcean.addShark(x, y);
		    }
		    break;
		case SHARK:
		    if (nfish == 0){
			hungergrid[x][y]++;
			if(hungergrid[x][y] <= starvetime){
			    newOcean.addShark(x, y);
			    newOcean.hungergrid[x][y] = this.hungergrid[x][y];
			}else{
			    newOcean.addEmpty(x, y);
			}
		    }else if(nfish > 0){
			newOcean.addShark(x, y);
		    }
		    break;
		case FISH:
		    if(nshark == 0){
			newOcean.addFish(x, y);
		    }else if(nshark == 1){
			newOcean.addEmpty(x, y);
		    }else if(nshark >= 2){
			newOcean.addShark(x, y);
		    }
		    break;
		}
		
	    }
	}
	return newOcean;
    }

    /**
     *  The following method is required for Part II.
     */

    /**
     *  addShark() (with three parameters) places a shark in cell (x, y) if the
     *  cell is empty.  The shark's hunger is represented by the third parameter.
     *  If the cell is already occupied, leave the cell as it is.  You will need
     *  this method to help convert run-length encodings to Oceans.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     *  @param feeding is an integer that indicates the shark's hunger.  You may
     *         encode it any way you want; for instance, "feeding" may be the
     *         last timestep the shark was fed, or the amount of time that has
     *         passed since the shark was last fed, or the amount of time left
     *         before the shark will starve.  It's up to you, but be consistent.
     */

    public void addShark(int x, int y, int feeding) {
	// Your solution here.
	if(cellContents(x, y) == EMPTY){
	    oceangrid[x][y] = SHARK;
	    hungergrid[x][y] = feeding;
	}
    }

    /**
     *  The following method is required for Part III.
     */

    /**
     *  sharkFeeding() returns an integer that indicates the hunger of the shark
     *  in cell (x, y), using the same "feeding" representation as the parameter
     *  to addShark() described above.  If cell (x, y) does not contain a shark,
     *  then its return value is undefined--that is, anything you want.
     *  Normally, this method should not be called if cell (x, y) does not
     *  contain a shark.  You will need this method to help convert Oceans to
     *  run-length encodings.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */

    public int sharkFeeding(int x, int y) {
	// Replace the following line with your solution.
	if(cellContents(x, y) ==  SHARK){
	    return hungergrid[x][y];
	}else{
	    return -1;
	}
    }

}
