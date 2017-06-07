/**
 * Iterates the elements of a MySparseArray by row.
 * @author Vincent Rideout
 *
 */
public class MyRowIterator extends RowIterator{
	
	private MyIndexElem row;
	
	public MyRowIterator(MyIndexElem rows){
		this.row = rows.next();
	}
	
	/**
	 * Returns an iterator to iterate through the elements of a row, then moves to the next row.
	 */
	public MyElemIterator next(){
		MyElemIterator output = new MyElemIterator(row.first().nextCol(), row.index(), true);
		row = row.next();
		return output;
	}
	
	public boolean hasNext(){
		return !(row == null);
	}
}
