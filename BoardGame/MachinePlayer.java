/* MachinePlayer.java */

package player;

import list.*;
/**
 * 
 * An implementation of an automatic Network player. Keeps track of moves made
 * 
 * by both players. Can select a move for itself.
 */

public class MachinePlayer extends Player {
    public GameBoard board;
    private int color;
    private int SDEPTH;
    private int opponent;
    private boolean test = false;
    public final static int WHITEWIN = 2047;
    public final static int BLACKWIN = -2047;

    public String getPlayer() {
	if (this.color == 1) {
	    return "WHITE";
	} else {
	    return "BLACK";
	}
    }

    // Creates a machine player with the given color. color is either 0 (black)
    // or 1 (white). (White has the first move.)
    public MachinePlayer(int color) {
	this(color, 2); // 2 is default search depth
    }

    // Creates a machine player with the given color and search depth. color is
    // either 0 (black) or 1 (white). (White has the first move.)
    public MachinePlayer(int color, int SDEPTH) {
	this.color = color;
	this.SDEPTH = SDEPTH;
	this.board = new GameBoard();
    }

    // Returns a new move by "this" player. Internally records the move (updates
    // the internal game board) as a move by "this" player.
    public Move chooseMove() { // new move by player, keeps track of the move

	MoveQual move; //create the type move
	int alpha = -1; //just test values
	int beta = 1;   //just test values
	move = alphabeta(color, SDEPTH, alpha, beta); //start the helper
	return move.move; //return the best possible move
	Move WMove = move.move; //assign the best possible move to this variable to be used next
	board.makeMove(WMove, color); //perform the best possible move
	return WMove;
    }


    public DList findMoves(int checkColor){ //figures the possible moves that can be performed and inserts it into a DList
	DList totMoves = new DList(); //creates a DList
	int totChips; //keeps track of the number of chips used
	if(checkColor == board.BLACK){ //checks what color is being examined and adds up the chips used
	    totChips = board.blackNumber;
	}else{
	    totChips = board.whiteNumber;
	}
	int[][] chipCords = new int[10][]; //creates an array with the possibilities of the 10 chips need to be placed
	int ind = 0;
	if(totChips != 10){ //generate valid moves for all chips not yet set (add moves)
	    for(int b = 0; b <= 7; b++){
		for(int c = 0; c <= 7; c++){
		    if(board.grid[c][b] == board.EMPTY){ //looks to see if the place on the board is empty
			Move maybeMove = new Move(c, b); //creates the variable with the new move for the corresponding player
			if(isLegal(c, b, color)){ //checks to see if the move is legal for the corresponding player
			    totMoves.insertBack(maybeMove); //if the move is legal, we insert is back into our DList
			}
		    }
		}
	    }
	    //stuck here}else{ //(step moves)
	    for(int d = 0; d <= 7; d++){
		for(int e = 0; e <= 7; e++){
		    if(board.grid[e][d] == board.EMPTY){

		    }
		}
	    }
	}
	return totMoves; //return the DList for alphabeta to pick the best possible (legal) move
    }
    
    public MoveQual alphabeta(int color, int SDEPTH, int alpha, int beta){
	DList moves = findMoves(color);
	
	//* For m in moves
	for(int i = 0; i< moves.getSize(); i++){
	        
	}
	
	return MoveQual;
    }
    
    // If the Move m is legal, records the move as a move by the opponent
    // (updates the internal game board) and returns true. If the move is
    // illegal, returns false without modifying the internal state of "this"
    // player. This method allows your opponents to inform you of their moves.
    public boolean opponentMove(Move m) {
	if (isLegal(m.x1, m.y1, opponent)) {
	    board.makeMove(m, opponent);
	    return true;
	} else
	    return false;
    }

    // If the Move m is legal, records the move as a move by "this" player
    // (updates the internal game board) and returns true. If the move is
    // illegal, returns false without modifying the internal state of "this"
    // player. This method is used to help set up "Network problems" for your
    // player to solve.
    public boolean forceMove(Move m) {
	if (board.isLegal(m.x1, m.y1, color)) {
	    board.makeMove(m, color);
	    return true;
	} else
	    return false;
    }

    public boolean isLegal(int x, int y, int chip) {
	// check that the square is in bounds
	if (outOfBounds(x, y)) {
	    return false;
	}
	// check that square is not already occupied
	// check that it is not in one of the corners
	if (((x == 0) && (y == 0)) || ((x == 0) && (y == 7))
	    || ((x == 7) && (y == 0)) || ((x == 7) && (y == 7))) {
	    return false;
	}
	// check that white is not in black's scoring areas
	else if ((chip == 1) && ((y == 0) || (y == 7))) {
	    return false;
	}
	// check that black is not in white's scoring areas
	else if ((chip == 0) && ((x == 0) || (x == 7))) {
	    return false;
	}
	// check that no more than one of the surrounding squares contain chips
	else if (surroundingCoordinates(x, y, chip) == true) {
	    return false;
	} else {
	    return true;
	}
    }

    // checks to see if the surrounding coordinates already contain chips. If
    // more than one of these contains a chip, it return true
    public boolean surroundingCoordinates(int x, int y, int chip) {
	int counter = 0;
	if (this.board.getElementAt(x - 1, y - 1) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x, y - 1) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x + 1, y - 1) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x - 1, y) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x + 1, y) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x - 1, y + 1) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x, y + 1) == chip) {
	    counter++;
	} else if (this.board.getElementAt(x + 1, y + 1) == chip) {
	    counter++;
	}
	if (counter < 2) {
	    return true;
	} else
	    return false;
    }

    public boolean outOfBounds(int x, int y) {
	if ((x < 0) || (y < 0) || (x > 0) || (y > 0)) {
	    return true;
	} else {
	    return false;
	}
    }

    public static int generateOpponent(int color) {
	if (color == 1) {
	    return 2;
	} else {
	    return 0;
	}
    }
}
