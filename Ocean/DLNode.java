public class DLNode{
    public TypeAndSize data;
    public int starveTime=-1;
    public DLNode next;
    public DLNode prev;
    public int start; 
    public int end;
    
    public DLNode(){
        this.data = new TypeAndSize(Ocean.EMPTY, 1);
        this.next = null;
        this.prev = null;
    }
    public DLNode(int type, int size){
        this.data = new TypeAndSize(type, size);
        this.next = null;
        this.prev = null;
    }
    public DLNode(int type, int size, int starveTime){
        this.data = new TypeAndSize(type, size);
        this.next = null;
        this.prev = null;
        this.starveTime = starveTime;
    }
    public void setStarveTime(int x){
        this.starveTime = x;
    }
    public void setStart(int x, int y, int width){
	this.start = y*width+x;
    }
    public void setEnd(int x, int y, int width){
	this.end = y*width+x;
    }
}
