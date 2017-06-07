/** 
 * A linked list-based implementation of a two-dimensional array.
 * Stores a default value for the array elements, this value will not be added to the array.
 * @author Vincent Rideout
 *
 */
public class MySparseArray implements SparseArray{
	private Object defVal;
	private MyIndexElem cols;
	private MyIndexElem rows;
	
	/**
	 * Constructor to instantiate a new MySparseArray
	 * @param o - the default value for the array
	 */
	public MySparseArray(Object o){
		defVal = o;
		cols = new MyIndexElem(-1, false);
		cols.setFirst(null);
		rows = new MyIndexElem(-1, true);
		rows.setFirst(null);
	}
	
	public Object defaultValue(){
		return defVal;
	}
	public RowIterator iterateRows(){
		return new MyRowIterator(rows);
	}
	public ColumnIterator iterateColumns(){
		return new MyColumnIterator(cols);
	}
	
	/**
	 * Returns the object stored at the given coordinates of the array.
	 * @param row - row index
	 * @param col - column index
	 */
	public Object elementAt(int row, int col){
		if(row < 0 || col < 0){
			System.out.println("bad input to elementAt");
			return defVal;
		}
		MyIndexElem tmp = rows;
		while(tmp.next() != null && tmp.next().index() <= row)
			tmp = tmp.next();
		if(tmp.index() == row){
			MyMatrixElem tmp2 = tmp.first();
			while(tmp2.nextCol() != null && tmp2.nextCol().columnIndex() <= col)
				tmp2 = tmp2.nextCol();
			if(tmp2.columnIndex() == col)
				return tmp2.value();
		}
		return defVal;
	}
	
	/**
	 * Insert an element into the array.
	 * @param row - row index for insertion
	 * @param col - column index for insertion
	 * @param value - value to place at the coordinates. using the default value of the array
	 * will not add an element, and will delete the element at the coordinates if it exists.
	 */
	public void setValue(int row, int col, Object value){
		if(row < 0 || col < 0){
			return;
		}
		MyIndexElem rowChecker = rows;
		MyMatrixElem rowTraverser;
		while(rowChecker.next() != null && rowChecker.next().index() < row){
			rowChecker = rowChecker.next();
		}
		if(rowChecker.next() == null || rowChecker.next().index() != row){
			MyIndexElem newRow = new MyIndexElem(row, true);
			newRow.setNext(rowChecker.next());
			rowChecker.setNext(newRow);
		}
		rowTraverser = rowChecker.next().first();
		while(rowTraverser.nextCol() != null && rowTraverser.nextCol().columnIndex() < col){
			rowTraverser = rowTraverser.nextCol();
		}
		MyIndexElem colChecker = cols;
		MyMatrixElem colTraverser;
		while(colChecker.next() != null && colChecker.next().index() < col){
			colChecker = colChecker.next();
		}
		if(colChecker.next() == null || colChecker.next().index() != col){
			MyIndexElem newCol = new MyIndexElem(col, false);
			newCol.setNext(colChecker.next());
			colChecker.setNext(newCol);
		}
		colTraverser = colChecker.next().first();
		while(colTraverser.nextRow() != null && colTraverser.nextRow().rowIndex() < row){
			colTraverser = colTraverser.nextRow();
		}
		if(rowTraverser.nextCol() != null && rowTraverser.nextCol().columnIndex() == col){
			if(value.equals(defVal)){
				rowTraverser.setNextCol(rowTraverser.nextCol().nextCol());
				colTraverser.setNextRow(colTraverser.nextRow().nextRow());
				if(rowChecker.next().first().nextCol() == null){
					rowChecker.setNext(rowChecker.next().next());
				}
				if(colChecker.next().first().nextRow() == null){
					colChecker.setNext(colChecker.next().next());
				}
			}
			else{
				rowTraverser.nextCol().setValue(value);
			}
		}
		else if(!value.equals(defVal)){
			MyMatrixElem newElem = new MyMatrixElem(row, col, value);
			newElem.setNextRow(colTraverser.nextRow());
			colTraverser.setNextRow(newElem);
			newElem.setNextCol(rowTraverser.nextCol());
			rowTraverser.setNextCol(newElem);
		}
	}
}