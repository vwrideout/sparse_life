/**
 * Iterates elements of a MySparseArray. Created by MyColumnIterator and MyRowIterator.
 * @author Vincent Rideout
 *
 */
public class MyElemIterator extends ElemIterator{
	
	private MyMatrixElem elem;
	private int index;
	private boolean isRow;
	
	/**
	 * Constructor to instantiate a new MyElemIterator.
	 * @param elem - the first element in the list to be iterated.
	 * @param index - the index of the row or column being iterated.
	 * @param isRow - true if the list being iterated is a row.
	 */
	public MyElemIterator(MyMatrixElem elem, int index, boolean isRow){
		this.elem = elem;
		this.index = index;
		this.isRow = isRow;
	}
	
	public boolean iteratingRow(){
		return isRow;
	}
	
	public boolean iteratingCol(){
		return !isRow;
	}
	
	public int nonIteratingIndex(){
		return index;
	}
	
	/**
	 * Returns the current element and moves to the next.
	 */
	public MyMatrixElem next(){
		MyMatrixElem output = elem;
		if(isRow)
			elem = elem.nextCol();
		else
			elem = elem.nextRow();
		return output;
	}
	
	public boolean hasNext(){
		return !(elem == null);
	}
}
