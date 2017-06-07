/**
 * Linked-list element to store objects in a MySparseArray
 * @author Vincent Rideout
 *
 */
public class MyMatrixElem implements MatrixElem{
	private int row;
	private int col;
	private Object val;
	private MyMatrixElem nextRow;
	private MyMatrixElem nextCol;
	
	/**
	 * Constructor to instantiate a new MyMatrixElem
	 * @param row - row coordinate of the new element
	 * @param col - column coordinate of the new element
	 * @param val - value to be stored in the new element
	 */
	public MyMatrixElem(int row, int col, Object val){
		this.row = row;
		this.col = col;
		this.val = val;
		nextRow = null;
		nextCol = null;
	}
	
	public int rowIndex(){
		return row;
	}
	public int columnIndex(){
		return col;
	}
	public Object value(){
		return val;
	}
	public void setValue(Object o){
		val = o;
	}
	public MyMatrixElem nextRow(){
		return nextRow;
	}
	public void setNextRow(MyMatrixElem elem){
		nextRow = elem;
	}
	public MyMatrixElem nextCol(){
		return nextCol;
	}
	public void setNextCol(MyMatrixElem elem){
		nextCol = elem;
	}
}
