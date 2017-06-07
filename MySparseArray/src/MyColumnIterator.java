/**
 * Iterates columns of a MySparseArray.
 * @author Vincent Rideout
 *
 */
public class MyColumnIterator extends ColumnIterator{
	
	private MyIndexElem col;
	
	public MyColumnIterator(MyIndexElem cols){
		this.col = cols.next();
	}
	
	/**
	 * Returns an iterator to iterate through the elements of a column, moves to the next column.
	 */
	public MyElemIterator next(){
		MyElemIterator output = new MyElemIterator(col.first().nextRow(), col.index(), false);
		col = col.next();
		return output;
	}
	
	public boolean hasNext(){
		return !(col == null);
	}
}
