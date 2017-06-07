/**
 * Coordinate  linked-list elements for navigating MySparseArray, can represent a column index or a row index. 
 * @author Vincent Rideout
 *
 */
public class MyIndexElem{
	private MyIndexElem next;
	private MyMatrixElem first;
	private int index;
	
	/**
	 * Constructor to instantiate a new MyIndexElem. Creates a MyMatrixElem linked list with a dummy element.
	 * @param index - the index of the element
	 * @param isRow - true if this element is part of a row in the MySparseArray
	 */
	public MyIndexElem(int index, boolean isRow){
		this.index = index;
		if(isRow)
			first = new MyMatrixElem(index, -1, null);
		else
			first = new MyMatrixElem(-1, index, null);
		next = null;
	}
	
	public int index(){
		return index;
	}	
	public void setIndex(int i){
		index = i;
	}
	public MyIndexElem next(){
		return next;
	}
	public void setNext(MyIndexElem elem){
		next = elem;
	}
	public MyMatrixElem first(){
		return first;
	}
	public void setFirst(MyMatrixElem elem){
		first = elem;
	}
}
