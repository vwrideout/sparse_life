import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * An implementation of the cellular automaton "Game of Life"
 * To be executed with three command-line arguments
 * 1-Name of input file for initial conditions
 * 2-Name of output file where final conditions will be stored
 * 3-Number of generations the game will simulate
 * @author Vincent Rideout, CS245 Project 1
 *
 */
public class Life {
	/**
	 * Helper function to process the input file
	 * @param inputFilename - name of the input file
	 * @return - MySparseArray object containing the initial conditions
	 * @throws IOException
	 */
	private static MySparseArray processInputFile(String inputFilename) throws IOException{
		MySparseArray output = new MySparseArray(0);
		Path inputFile = Paths.get(inputFilename);
		Pattern delimiter = Pattern.compile("[^0-9]+");
		Scanner fileScanner = new Scanner(inputFile).useDelimiter(delimiter);
		int row, col;
		while(fileScanner.hasNext()){
			row = Integer.parseInt(fileScanner.next());
			if(fileScanner.hasNext()){
				col = Integer.parseInt(fileScanner.next());
				output.setValue(row, col, 1);
			}
		}
		fileScanner.close();
		return output;
	}
	
	/**
	 * Helper function to process the output file
	 * @param output - MySparseArray object containing the final conditions
	 * @param outputFilename - name of the output file
	 * @throws IOException
	 */
	private static void printToFile(MySparseArray output, String outputFilename) throws IOException{
		PrintWriter out = new PrintWriter(outputFilename);
		RowIterator rowIter = output.iterateRows();
		ElemIterator elemIter;
		MatrixElem elem;
		while(rowIter.hasNext()){
			elemIter = rowIter.next();
			while(elemIter.hasNext()){
				elem = elemIter.next();
				out.println(elem.rowIndex() + "," + elem.columnIndex());
			}
		}
		out.close();
	}
	
	public static void main(String args[]){
		RowIterator rowIter;
		ElemIterator elemIter;
		MatrixElem elem;
		MySparseArray current = null;
		try{
			current = processInputFile(args[0]);
		}catch(IOException ioe){
			System.out.println("Error processing input file.");
			return;
		}
		MySparseArray neighbors = new MySparseArray(0);
		MySparseArray future = new MySparseArray(0);
		int generations = Integer.parseInt(args[2]);
		while(generations > 0){
			rowIter = current.iterateRows();
			while(rowIter.hasNext()){
				elemIter = rowIter.next();
				while(elemIter.hasNext()){
					elem = elemIter.next();
					for(int i = -1; i <= 1; i++){
						for(int j = -1; j <= 1; j++){
							if(!(i == 0 && j == 0))
								neighbors.setValue(elem.rowIndex()+i,elem.columnIndex()+j,(Integer)neighbors.elementAt(elem.rowIndex()+i,elem.columnIndex()+j)+1);
						}
					}
				}
			}
			rowIter = neighbors.iterateRows();
			while(rowIter.hasNext()){
				elemIter = rowIter.next();
				while(elemIter.hasNext()){
					elem = elemIter.next();
					if((Integer)elem.value() == 2 && !current.elementAt(elem.rowIndex(), elem.columnIndex()).equals(current.defaultValue()))
						future.setValue(elem.rowIndex(), elem.columnIndex(), 1);
					if((Integer)elem.value() == 3)
						future.setValue(elem.rowIndex(), elem.columnIndex(), 1);
				}
			}
			current = future;
			neighbors = new MySparseArray(0);
			future = new MySparseArray(0);
			generations--;
		}
		try{
			printToFile(current, args[1]);
		}catch(IOException ioe){
			System.out.println("Error processing output file.");
		}
	}
}
