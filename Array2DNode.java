package hw05;

/**
 * 
 * @author Juan Tiguila.
 *
 * */

/**
 * This class is the structure of a Array2DNode.
 * It has two data fields of itself, one to move forward and one downward.
 * 
 * Its data fields are initialized as null.
 *
 * */

public class Array2DNode<E> {
	protected E item;
	
	/** Instance of the this class itself as data fields to advance to the next
	 * row or column. */
	protected Array2DNode<E> nextCol;
	protected Array2DNode<E> nextRow;
	
	/**  @param item The value of an instance of this class.
	 * Constructor that initializes the data fields as null. */ 
	Array2DNode(E item) {
		this.item = item;
		this.nextCol = null;
		this.nextRow = null;
	}
}
