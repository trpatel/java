public class DList{
    
    public DLNode head;
    public DLNode tail;
    private int listSize = 0;
    
    public DList(){
        this.listSize = 0;
        this.head = null;
        this.tail = null;
    }
    
    public void delete(DLNode node){
	if (node.prev==null){
	    this.head = node.next;
	} else {
	    node.prev.next = node.next;
	}
	if (node.next == null){
	    this.tail = node.prev;
	} else {
	    node.next.prev = node.prev;
	}
	this.listSize--;
    }
    
    public void insertAfter(DLNode node, DLNode newNode){
        newNode.prev = node;
        newNode.next = node.next;
        if (node.next == null){
            this.tail = newNode;
        } else {
            node.next.prev = newNode;
        }
        node.next = newNode;
        this.listSize++;
    }
    
    public void insertBefore(DLNode node, DLNode newNode){
        newNode.next = node;
        newNode.prev = node.prev;
        if (node.prev == null){
            this.head = newNode;
        } else {
            node.prev.next = newNode;
        }
        node.prev = newNode;
        
        
        this.listSize++;
    }
    
    public void pushBeginning(DLNode newNode){
        if (head==null){
            head = newNode;
            tail = newNode;
            newNode.prev = null;
            newNode.next = null;
            listSize++;
        } else {
            insertBefore(this.head, newNode);
        }
        
    }
    
    public void pushEnd(DLNode newNode){
        if (tail==null){
            pushBeginning(newNode);
        } else {
            insertAfter(this.tail, newNode);
        }
        
    }
    
    public int getLength(){
        return this.listSize;
    }
    
}
