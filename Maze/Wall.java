public class Wall {
    protected int x;
    protected int y;
    protected int type;
    
    public final static int Ver = 0;
    public final static int Hor = 1;
    
    public Wall(int i, int j, int ty){
	y = j;
	x = i;
	type = ty;
    }
}
