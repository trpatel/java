package player;

import list.DList;
import list.DListNode;
import list.InvalidNodeException;

public class GameBoard {
    private final static int DIMENSION = 8;
    private final static int WHITE = 1;
    private final static int BLACK = 0;
    private final static int EMPTY = 2;
    private int[][] grid;
    protected int whiteNumber;
    protected int blackNumber;
    protected Pair[] whitePieces;
    protected Pair[] blackPieces;

    /**
     * Invariants: (1) grid.length == DIMENSION. (2) for all 0 <= i < DIMENSION,
     * grid[i].length == DIMENSION. (3) for all 0 <= i, j < DIMENSION,
     * grid[i][j] >= 0 and grid[i][j] <= 2.
     **/
    /**
     * Construct a new board in which all cells are zero.
     */
    public GameBoard() {
	grid = new int[DIMENSION][DIMENSION];
	for (int i = 0; i < DIMENSION; i++) {
	    for (int j = 0; j < DIMENSION; j++) {
		grid[i][j] = EMPTY;
	    }
	}
	whiteNumber = 0;
	blackNumber = 0;
	whitePieces = new Pair[10];
	blackPieces = new Pair[10];
    }

    /**
     * Set the cell (x, y) in the board to the given value mod 3.
     * 
     * @param value
     *            to which the element should be set (normally 0, 1, or 2).
     * @param x
     *            is the x-index.
     * @param y
     *            is the y-index.
     * @exception ArrayIndexOutOfBoundsException
     *                is thrown if an invalid index is given.
     **/
    public void setElementAt(int x, int y, int value) {
	grid[x][y] = value % 3;
	if (grid[x][y] < 0) {
	    grid[x][y] = grid[x][y] + 3;
	}
    }

    /**
     * Get the valued stored in cell (x, y).
     * 
     * @param x
     *            is the x-index.
     * @param y
     *            is the y-index.
     * @return the stored value (between 0 and 2).
     * @exception ArrayIndexOutOfBoundsException
     *                is thrown if an invalid index is given.
     */
    public int getElementAt(int x, int y) {
	return grid[x][y];
    }

    public void makeMove(Move m, int color) {
	if (m.moveKind == 0) // quit move
	    {
		return;
	    }
	if (m.moveKind == 2) // step move
	    {
		setElementAt(m.x1, m.y1, color);
		setElementAt(m.x2, m.y2, 2);
		Pair[] piecesList = (color == BLACK ? this.blackPieces
				     : this.whitePieces);
		int index = 0;
		for (; index < piecesList.length; index++) {
		    if (piecesList[index].x == m.x2 && piecesList[index].y == m.y2)
			break;
		}
		System.out.println("Looking for: (" + m.x2 + ", " + m.y2 + ") in "
				   + ((color == BLACK) ? "blackPieces: " : "whitePieces: "));
		System.out.print("blackPieces: ");
		for (int i = 0; i < blackPieces.length; i++) {
		    System.out.print(blackPieces[i] + " ");
		}
		System.out.print("\nwhitePieces: ");
		for (int i = 0; i < whitePieces.length; i++) {
		    System.out.print(whitePieces[i] + " ");
		}
		System.out.println("\n");
		piecesList[index].x = m.x1;
		piecesList[index].y = m.y1;
		System.out.println(this);
	    } else { // add move
	    // System.out.println("Makemove called");
	    // System.out.println(m.x1 + " " + m.y1);
	    setElementAt(m.x1, m.y1, color);
	    if (color == BLACK)
		blackNumber++;
	    else
		whiteNumber++;
	    Pair[] piecesList = (color == BLACK ? this.blackPieces
				 : this.whitePieces);
	    int index = 0;
	    for (; index < piecesList.length && piecesList[index] != null; index++) {
	    }
	    // System.out.println("Adding " + m.x1 + ", " + m.y1);
	    piecesList[index] = new Pair(m.x1, m.y1);
	    // System.out.print(color + ": ");
	    // for(int i = 0; i < piecesList.length; i++)
	    // {
	    // System.out.print(piecesList[i] + " ");
	    // }
	    // System.out.println((color == BLACK) ? "blackNumber: " +
	    // blackNumber : "whiteNumber: " + whiteNumber);
	}
    }

    public boolean isNetwork(int x, int y, int from, int chip, DList used) { // call
	// ONLY
	// on
	// top
	// or
	// left!
	if (chip != WHITE && chip != BLACK) {
	    System.out.println("Illegal Chip");
	    System.exit(0);
	}
	int answer = 0;
	int i = 0;
	int originX = x;
	int originY = y;
	while (i <= 7 && answer == 0) {
	    System.out.println("X: " + x + " Y: " + y + " i: " + i + " chip: "
			              + chip + " from: " + from + " origin: (" + originX + ", "
			       + originY + ")");
	    if (i == from || i == (from + 4) % 8) { // moving in illegal
		// direction
		System.out.println("Illegal direction");
		i++;
	    } else if (!isLegalLoc(x, y, chip)) { // location is illegal
		System.out.println("Illegal location!");
		x = originX;
		y = originY;
		i++;
	    } else if (isHome(x, y, originX, originY) || isEmpty(x, y, this)) { // home
		// or
		// empty
		// -
		// need
		// to
		// keep
		// going
		System.out.println("Keep looking");
		x = updateX(x, i);
		y = updateY(y, i);
	    } else if (getElementAt(x, y) == chip) { // found chip with correct
		// color - need to
		// either exit or recur
		System.out.println("Found chip at (" + x + ", " + y + ")");
		if (isGoal(x, y, chip)) { // Reached goal
		    System.out.println("Reached goal!\n ");
		    answer = 1;
		} else { // check if we can include this chip we just found in
		    // the network
		    if (isUsed(x, y, used)) {
			System.out.println("Chip already used");
			i++;
			x = originX;
			y = originY;
		    } else {
			System.out.println("Recursive call");
			DList potentialUsed = used;
			potentialUsed.insertBack(new Pair(x, y));
			answer += (isNetwork(x, y, i, chip, potentialUsed)
				   && potentialUsed.length() >= 6 ? 1 : 0);
			try {
			    potentialUsed.back().remove();
			} catch (InvalidNodeException e) {
			    System.out.println("InvalidNodeException!");
			}
			x = originX;
			y = originY;
			System.out.println("Back to x: " + x + " y: " + y
					   + " i: " + i + " from: " + from);
			i++;
		    }
		}
	    } else if (this.getElementAt(x, y) == ((chip + 1) % 2)) { // found
		// chip
		// of
		// other
		// color
		// -
		// this
		// path
		// won't
		// work
		System.out.println((chip + 1) % 2 + " found at (" + x + ", "
				   + y + ")");
		x = originX;
		y = originY;
		i++;
	    } else { // ???
		System.out.println("Error!!!");
		System.out.println("isHome returns: "
				   + isHome(x, y, originX, originY));
		System.out.println("isLegalLoc returns: "
				   + isLegalLoc(x, y, chip));
		System.out.println("isEmpty returns: " + isEmpty(x, y, this));
		System.out.println("element at current position is: "
				   + getElementAt(x, y));
		System.out.println("from is " + from);
	    }
	}
	return answer > 0;
    }

    public static boolean isUsed(int x, int y, DList used) {
	list.DListNode node = (DListNode) used.front();
	boolean answer = false;
	while (answer == false) {
	    try {
		System.out.println(node.item());
		if (x == ((Pair) node.item()).x && y == ((Pair) node.item()).y)
		    answer = true;
		node = (DListNode) node.next();
	    } catch (InvalidNodeException e) {
		System.out.println("Chip not already used");
		return answer;
	    }
	}
	return answer;
    }

    public static boolean isEmpty(int x, int y, GameBoard board) {
	return board.getElementAt(x, y) == EMPTY;
    }

    public static boolean isHome(int x, int y, int originX, int originY) {
	return (x == originX && y == originY);
    }

    public static boolean isLegalLoc(int x, int y, int chip) {
	return !((x < 0 || x > 7 || y < 0 || y > 7) || // location is out of
		 // bounds
		 ((x == 0 || x == 7) && (y == 0 || y == 7)) // location is a
		 // corner
		 || (chip == WHITE && (y == 0 || y == 7)) // white is on black's
		 // goal row
		 || (chip == BLACK && (x == 0 || x == 7))); // black is on white's goal
	// row
    }

    public static boolean isGoal(int x, int y, int chip) {
	return (chip == WHITE && x == 7) || (chip == BLACK && y == 7);
    }

    public static int updateX(int x, int i) {
	switch (i) {
	case 0:
	    return (x + 1);
	case 1:
	    return (x + 1);
	case 2:
	    return x;
	case 3:
	    return (x - 1);
	case 4:
	    return (x - 1);
	case 5:
	    return (x - 1);
	case 6:
	    return x;
	default:
	    return (x + 1);
	}
    }

    public static int updateY(int y, int i) {
	switch (i) {
	case 0:
	    return y;
	case 1:
	    return (y + 1);
	case 2:
	    return (y + 1);
	case 3:
	    return (y + 1);
	case 4:
	    return y;
	case 5:
	    return (y - 1);
	case 6:
	    return (y - 1);
	default:
	    return (y - 1);
	}
    }

    public String toString() {
	String str = "[";
	for (int i = 0; i < DIMENSION; i++) {
	    for (int j = 0; j < DIMENSION; j++) {
		if (j == 0 && i != 0)
		    str += " ";
		str += ((getElementAt(j, i) == 2 ? "-" : getElementAt(j, i)) + " ");
		if (j == 7 && i != 7)
		    str += "\n";
	    }
	}
	return str + "]";
    }
}
