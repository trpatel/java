public int winner(){
    int pro = findNetwork(GameBoard board, int x, int y, int color, int num, String curDir, String lastDir);
    int WHITEPROB = pro / WHITEWIN;
    int BLACKPROB = pro / BLACKWIN;
    if(pro == WHITEWIN){
	return "WHITEWIN" + pro;
    }else{
	if(pro == BLACKWIN){
	    return "BLACKWIN" + pro;
	}else{
	    if(WHITEPROB == (BLACKPROB * -1)){
		return "tie" + pro;
	    }else{
		if(WHITEPROB < (BLACKPROB * -1)){
		    return BLACKPROB + pro;
		}else{
		    return WHITEPROB;
		}
	    }
	}
    }
}
