import list.DList;
import list.DListNode;

public MoveQual alphabeta(int color, int depth, int alpha, int beta){
    MoveQual WMove = new MoveQual(); 
    MoveQual enemyBestMove; //self titled, enemy's best move
    int myOpponent;
    boolean isWhiteWin; 
    boolean isBlackWin;
    if(this.color == WHITE){ //sets the chips colors to the corresponding person
	myOpponent = BLACK;
    }else{
	myOpponent = this.WHITE;
    }
    //determines if either side has a network/win
    for(int a = 1; a < 6; a++){
	if(this.grid[0][a] != this.EMPTY){
	    isWhiteWin = this.isNetwork(0, a, 2, this.WHITE, new DList());
	}
	if(this.grid[a][0] != this.EMPTY){
	    isBlackWin = this.isNetwork(a, 0, 0, this.BLACK, new DList());
	}
    }
    if(!isWhiteWin && !isBlackWin){
	//scoring algorithm callWMove.qual = Calculate(this); //some way to make sure the the move is the best possible move out of all the possible moves there are
	return WMove;
    }
    if(myColor != myOpponent){ //when it's your turn
	WMove.qual = beta; //your best score is your worst score
    }else{
	WMove.qual = alpha; //your best score is your best score
    }
    while(test){ //ensures the conjured up moves are valid moves
	//is this the best way to loop this?
	DList totMoves = (DList) findMoves(myColor); //creates a DList of the total moves for the corresponding player
	DListNode totMovesNode  = (DListNode) totMoves.front(); //enters the possible moves into the DList as nodes
	while(totMovesNode.isValidNode()){ //starts a loop to test the nodes
	    Gamethis tryMove = this; //this to try the moves on
	    this.makeMove((Move)totMovesNode.item(), myColor); //try to make the move
	    enemyBestMove = alphabeta(myOpponent, searchDepth - 1, alpha, beta); //figure out the enemy's best move on the current this but decreasing the depthSearch level
	    this = tryMove; //going back to the this you tried the move on
	    if((myColor != myOpponent) && (enemyBestMove.qual < WMove.qual)){ //when its the player's turn and the opponent's score is less than the player's, you start this loop
		WMove.move = (Move)totMovesNode.item(); //the player's best move would be the move that was performed on the test this
		WMove.qual = enemyBestMove.qual; //the player's score is the same as opponent's
		beta = enemyBestMove.qual; //the worst case score is the opponent's score
	    }else{
		if((myColor == myOpponent) && (enemyBestMove.qual > WMove.qual)){//when its the opponent's turn and it's score is greater than the players, it starts this loop
		    WMove.move = (Move)totMovesNode.item(); //the player's best move would still be the move that was performed on the test this
		    WMove.qual = enemyBestMove.qual; //the player's score is the same as the opponent's
		    alpha = enemyBestMove.qual; //the best case score is the opponent's score
		}
	    }
	    if(beta <= alpha){ //if the worst case score is less than or the same as the best case score, return the move
		return WMove;
	    }
	    totMovesNode = (DListNode) totMovesNode.next(); //check through the DList to find the next move that is the best move for you to do
	}
    }
    return WMove; //return the best move
}
