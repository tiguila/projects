package hw05;
import java.util.ArrayList;

/**
 * 
 * @author Juan Tiguila
 *
 * */

/** This class has a default constructor and a constructor that initializes a 2
 * dimensional array of nodes that are linked vertically and horizontally.
 * 
 * Some functionalities include adding and removing first and last column of nodes;
 * inserting a column or row of nodes at a specified index; getting an element
 * at a specified index; getting a row or column of nodes at specified index;
 * setting the value of a node at a specified index and other methods. */

public class Array2D<E> {
	
	/** Data fields to keep track of number of rows and columns. */
	private int rowSize;
	private int colSize;
	
	/** Data fields to connect more nodes to.*/
	protected Array2DNode<E> head;
	protected Array2DNode<E> rowTail;
	protected Array2DNode<E> colTail;
	
	/** Default constructor. Linked list is empty. */
	public Array2D(){
		this.rowSize = this.colSize = 0;
		this.head = this.rowTail = this.colTail = null;
	}
	
	/** Constructor that initializes a 2 dimensional array of nodes.
	 * @param values 	The values to initialize the 2 dimensional array of nodes.*/ 
	public Array2D(E[][] values) {
		
		/** Set the number of rows and column. */
		rowSize = values.length;
		colSize = values[0].length;
		
		/** Add first row of nodes. */
		this.helperAddFirstRow(values);
		
		/**Add and link the remaining rows to the first row.*/
		this.helperAddRestRows(values);
	}
	
	/** This method adds a new row of nodes at the beginning of the 2D nodes.
	 * and returns nothing. 
	 * @param values 	The values to populate the new row of nodes. */
	public void addFirstRow(E ... values) {
		Array2DNode<E> current = this.head;
		int index = 0;
		
		if(values.length!=this.colSize) {
			throw new IllegalArgumentException("values.length does not match this.rowSize");
		}
		else {
			
			/** Loop current is not null. */
			while (current != null) {
				Array2DNode<E> newNode = new Array2DNode<>(values[index]);

				/** Set the first node as the new head. */
				if (index == 0) {
					newNode.nextRow = current;
					this.head = newNode;

					this.colTail = this.head;
				}
				else {
					
					/** Connect the new node current. */
					newNode.nextRow = current.nextCol;
					
					/** Update the colTail. */
					this.colTail.nextCol = newNode;
					this.colTail = this.colTail.nextCol;
					newNode.nextRow = current;
				}
				/** Move the current row to the next column. */
				current = current.nextCol;
				index++;
			}
		}
		this.rowSize++;
	}
	
	/** This method adds a new column of nodes at the beginning of the 2D nodes.
	 * and returns nothing.
	 * 
	 * @param values 	The values to populate the new column of nodes. */
	public void addFirstCol(E ... values) {
		Array2DNode<E> current = this.head;
		int index = 0;
		
		if(values.length!=this.rowSize) {
			throw new IllegalArgumentException("values.length does not match this.rowSize");
		}
		else {
			
			/** Loop if current is not in the last column.*/
			while (current != null) {
				Array2DNode<E> newNode = new Array2DNode<>(values[index]);
				
				/** Connect the new node to current and update the head. */
				if (index == 0) {
					newNode.nextCol = current;
					this.head = newNode;
					this.rowTail = this.head;
				}

				/**Link the new node to the current head and update the head.
				 * Point head to the new node. */
				else {
					newNode.nextCol = current.nextRow;
					this.rowTail.nextRow = newNode;
					this.rowTail = this.rowTail.nextRow;
					newNode.nextCol = current;
				}
				/** Move the current row to the next row. */
				current = current.nextRow;
				index++;
			}
		}
		this.colSize++;
	}
	
	/** @param index	Index to insert new column.
	 * @param values Values to populate new column to add. */
	public void insertCol(int index, E...values) {
		if (values.length != this.rowSize) {
			throw new IllegalArgumentException("Error caused by values.length.");
		}
		if(index<0 || index>=(this.colSize-1)) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		
		/** Call addFirstCol() if index equals 0. */
		if (index == 0) {
			this.addFirstCol(values);
		}
		
		/** Call addLastCol() if index equals number of rows -1. */
		else if (index == (this.rowSize-1)) {
			this.addLastCol(values);
		}
		else {
			int index1 = 0;
			Array2DNode<E> current = this.head;
			while (index1 != (index - 1)) {
				current = current.nextCol;
				index1++;
			}
			
			/** Reference to column after the current column. */
			Array2DNode<E> holder2 = current.nextCol;
			
			/** Create the first in the new column. */
			Array2DNode<E> newNode = new Array2DNode<>(values[0]);
			
			/** Link current to the new node. */
			current.nextCol = newNode;
			
			/** Link the new node to the node after the current column. */
			newNode.nextCol = holder2;
			
			int index3 = 1;
			while (index3 < values.length) {
				
				/** Move current to next row. */				
				current = current.nextRow;
				
				/** Advance the node after current to the next row. */
				holder2 = holder2.nextRow;
				
				// connect the next node from the values the head of the row to insert.
				newNode.nextRow = new Array2DNode<>(values[index3]);;
				
				// move the new node to the next column so it does at stay at the head place.
				newNode = newNode.nextRow;
				
				// connect the row just above the new node to the new node.
				current.nextCol = newNode;
				
				// connect the new node to the node right below it.
				newNode.nextCol = holder2;
				index3++;
			}
		}
		colSize++;
	}
	
	/** This method inserts a row of nodes at specified index.
	 * @param index 	Index to insert new row of values.
	 * @param values 	Comma separated values to insert to existing nodes.*/
	public void insertRow(int index, E ... values) {
		/** check if number of values match current number of columns. */
		if (values.length != this.colSize) {
			throw new IllegalArgumentException("Error caused by values.length.");
		}
		/** check that index is in bounds. */
		else if(index<0 || index>=this.rowSize) {
			throw new IndexOutOfBoundsException("Index out of bounds exception.");
		}
		// First case to consider
		if (index == 0) {
			this.addFirstRow(values);
		}
		// seconds case to consider.
		else if (index == (this.colSize-1)) {
			this.addLastRow(values);
		}
		// Third case to consider.
		else {
			int index1 = 0;
			Array2DNode<E> current = this.head;
			
			// get to the row of insertion.
			while (index1 != (index - 1)) {
				current = current.nextRow;
				index1++;
			}
			
			// Reference of row  after current.
			Array2DNode<E> holder2 = current.nextRow;
			
			// add first node at given row index.
			Array2DNode<E> newNode = new Array2DNode<>(values[0]);
			
			/** Connect current with newNode and link new node to node after
			 * current. */
			current.nextRow = newNode;
			newNode.nextRow = holder2;
			int index3 = 1;
			while (index3 < values.length) {
				
				/** Move the current row to the next column. */
				current = current.nextCol;
				
				/** holder2 saves the row after current, and move holder2 to
				 * the next column. */
				holder2 = holder2.nextCol;
				
				// connect the newNode with the next node.
				newNode.nextCol = new Array2DNode<>(values[index3]);;
				
				/** Move the new node to the next column. */
				newNode = newNode.nextCol;
				
				/** Connect the row just above the new node to the new node. */
				current.nextRow = newNode;
				
				/** Connect the new node to the node right below it. */
				newNode.nextRow = holder2;
				index3++;
			}
		}
		rowSize++;
	}
	
	
	/** This method removes the row of nodes from the 2D nodes at the given 
	 * parameter row index.
	 * 
	 * @param index 	Hold the index of the row to delete. */
	public void deleteRow(int index) {
		/** Check if the parameter index is not out of bounds. */
		if(index < 0 || index >= this.rowSize) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		
		/** Invoke the deleteFirstRow() method if parameter index is 0. */
		if(index == 0) {
			deleteFirstRow();
		}
		/** Invoke the deleteLastRow() method if parameter index equals rowSize-1. */
		else if (index == (this.rowSize - 1)) {
			this.deleteLastRow();
		}
		
		/** Otherwise, delete the rows between 1 and rowSize-1*/
		else {
			/** Create a reference to the head. */
			Array2DNode<E> current = this.head;
			int currentIndex = 0;
			
			/** Loop until one before row index. */
			while (currentIndex != (index - 1)) {
				/** Move to the next row. */
				current = current.nextRow;
				currentIndex++;
			}

			/** Loop until the last column. */
			while (current != null) {

				/** Link the current node to node after the next one. */
				current.nextRow = current.nextRow.nextRow;

				/** Move to the next column. */
				current = current.nextCol;
			}
			/** Subtract 1 from the number of rows. */
			this.rowSize--;
		}
	}
	
	/**This method deletes a column of nodes.
	 * @param index Index to delete column of nodes. */
	public void deleteCol(int index) {
		/** Check if the parameter index is not out of bounds. */
		if(index < 0 || index >= this.colSize) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		/** Case 1 when index equals 0. */
		if(index == 0) {
			deleteFirstCol();
		}
		/** Case 1 when index equals number of column -1. */
		else if (index == (this.colSize - 1)) {
			this.deleteLastCol();
		}
		else {
			/** Create a reference to the head. */
			Array2DNode<E> current = this.head;
			int currentIndex = 0;

			/** Loop until one before the column index. */
			while (currentIndex != (index - 1)) {
				/** Move to the next column. */
				current = current.nextCol;
				currentIndex++;
			}
			
			/** Loop until current is in the last row. */
			while (current != null) {
				/** Link the current node to node after the next one. */
				current.nextCol = current.nextCol.nextCol;

				/** Move to the next row. */
				current = current.nextRow;
			}
			this.colSize--;
		}
	}
	
	/** This method removes the first column of nodes from the 2D Nodes and
	 * returns nothing. */
	public void deleteFirstCol() {
		Array2DNode<E> current = this.head;
		Array2DNode<E> temp1 = this.head;
		Array2DNode<E> temp2 = null;
		
		/** Continue looping if it is not the last node in the first row. */
		while (temp1 != null) {
			
			/** Reference the current head to the next node to the right of it.*/
			this.head = current.nextCol;
			
			/** Keep a reference of the column to be deleted. */
			temp2 = temp1;
			
			/** Move the current head to the next row. */
			temp1 = temp1.nextRow;
		}
		/** Change the reference of the rowTail. */
		this.rowTail = temp2.nextCol;
		/** Reduce the column size by 1. */
		this.colSize--;
	}
	
	/** This method removes the first row of nodes from the 2D nodes and
	 * returns nothing. */
	public void deleteFirstRow() {
		Array2DNode<E> current = this.head;
		Array2DNode<E> temp1 = this.head;
		Array2DNode<E> previous = this.head;

		/** Continue looping if it is not the last node in the first row. */
		while (temp1 != null) {

			/** Reference the current head tail to the next tail from the
			 * following row. */
			this.head = current.nextRow;

			/** Keep a reference of the old row. */
			previous = temp1;

			/** Move the current column to next column. */
			temp1 = temp1.nextCol;
		}
		/** Update the colTail, using the previous colTail. */
		this.colTail = previous.nextRow;
		rowSize--;
	}
	
	
	/** This method removes the last column of nodes from the 2D of nodes and
	 * return nothing. */
	public void deleteLastCol() {
		/**Create two references to head so there is access to the one-before
		 * last row of nodes. */
		Array2DNode<E> current = this.head;
		Array2DNode<E> previous = this.head;
		
		/** Loop until one-before last row of nodes in the liked list. */
		while (current.nextCol != null) {
			/**Keep a reference to the previous row of data.*/
			previous = current;
			
			/** Move current to the next row. */
			current = current.nextCol;
		}
		/** Reference the rowTail to the one-before last column. */
		this.colTail = previous;
		
		/** Loop until the next row is null. */
		while (previous != null) {
			previous.nextCol = null;

			 /** Delete each node in the last row. */
			this.colTail.nextCol = null;

			/** Move current column to the next one. */
			previous = previous.nextRow;
		}
		colSize--;
	}
	
	/** This method removes the last row of nodes from the 2D nodes and
	 * returns nothing. */
	public void deleteLastRow() {
		/** Create two references to head so there is access to the one-before
		 * last row of nodes. */
		Array2DNode<E> current = this.head;
		Array2DNode<E> previous = this.head;

		/** Loop until one-before the last row of nodes. */
		while (current.nextRow != null) {
			/** Keep a reference to the previous row of data. */
			previous = current;

			/** Move to the next row. */
			current = current.nextRow;
		}
		/** Update the rowTail. */
		this.rowTail = previous;

		/** Keep track if the the one-before last row of nodes is not null. */
		Array2DNode<E> temp1 = this.colTail;

		while (temp1 != null) {
			/** Delete each node in the last row. */
			this.rowTail.nextRow = null;

			/** Move current column to the next one. */
			temp1 = temp1.nextCol;
		}
		rowSize--;
	}
	
	/** @return returns the node at the given row and column indices.
	 * 
	 * @param rowIndex 	The row index of the requested value from the 2D node.
	 * @param colIndex 	The column index of the requested value from the 2D node.*/
	public E get(int rowIndex, int colIndex) {
		/** Throw an error if any of the parameters is out of bound. */
		if (rowIndex < 0 || colIndex< 0 || rowIndex >= this.rowSize || colIndex>= this.colSize) {
			throw new IndexOutOfBoundsException("ERROR");
		}
		
		Array2DNode<E> current = this.head;
		int rIndex = 0, cIndex = 0;
		
		/** Iterate until you find the given row. */
		while(rIndex != rowIndex) {
			current = current.nextRow;
			rIndex++;
		}
		
		/** Iterate until you find the given column. */
		while(cIndex != colIndex) {
			current = current.nextCol;
			cIndex++;
		}
		/** Return the node at the given index. */
		return current.item;	
	}
	
	/** @return returns a generic ArrayList<E> that stores the nodes at the
	 * parameter column index from the existing 2D nodes. 
	 * 
	 * @return colIndex The column index of the the requested row of data from
	 * the 2D nodes. */
	public ArrayList<E> getCol(int colIndex) {
		
		if(colIndex < 0 || colIndex>= this.colSize) {
			throw new IndexOutOfBoundsException("Index is out of bound.");
		}
		ArrayList<E> getCol = new ArrayList<>();
		Array2DNode<E> current = this.head;
		int rIndex = 0;
		
		/** Iterate until you find the given index. */
		while(rIndex != colIndex) {
			/** Move to the next column. */
			current = current.nextCol;
			rIndex++;
		}
		
		/** Loop until you reach the last row. */
		while(current != null) {
			
			/** Add each item to the array list. */
			getCol.add(current.item);
			
			/** Move to the next row. */
			current = current.nextRow;
		}
		return getCol;
	}
	
	/** @return Returns a generic ArrayList<E> that stores the nodes at the
	 * parameter row index from the existing 2D nodes. 
	 * 
	 * @param rowIndex 	The row index of the requested of row of data from the 
	 * 2D nodes.*/
	public ArrayList<E> getRow(int rowIndex) {
		
		if(rowIndex < 0 || rowIndex>= this.rowSize) {
			throw new IndexOutOfBoundsException("Index is out of bound.");
		}
		
		ArrayList<E> getRow = new ArrayList<>();
		Array2DNode<E> current = this.head;
		int rIndex = 0;
		
		/** Iterate until you find the given index. */
		while(rIndex != rowIndex) {
			
			/** Move to the next row. */
			current = current.nextRow;
			rIndex++;
		}
		
		/** Iterate until you reach the last column. */
		while(current != null) {
			
			/** Add each item to the array list. */
			getRow.add(current.item);
			
			/** Move to the next column. */
			current = current.nextCol;
		}		
		return getRow;
	}

	
	/**	This method sets the existing value from the 2D nodes at given row and
	 * column indices.
	 * @param rowIndex 	The row to set the value.
	 * @param colIndex 	The column to set the value in the 2D nodes.
	 * @param item		The value that replaces the existing value at specified
	 * location. */
	public void set(int rowIndex, int colIndex, E item) {
		Array2DNode<E> curr = this.head;
		/** Since the data fields in Array2DNode are protected, it is possible to set
		 * them here. */
		
		/** Set the head. */
		if(rowIndex == 0 && colIndex == 0) {
			this.head.item = item;
		}
		
		else {
			/** Get to the specified row. */
			for(int row=0; row<rowIndex; row++) {
				curr = curr.nextRow;
			}
			
			/** Get to colIndex */
			for(int col=0; col<colIndex; col++) {
				curr = curr.nextCol;
			}
			
			/** Set the value of the node at the given row and column. */
			curr.item = item;	
		}
	}
	
	/** @return returns the column size. */
	public int getColSize() {
		return colSize;
	}

	/** @return returns the number of rows of nodes. */
	public int getRowSize() {
		return rowSize;
	}
	
	/** @return String representation of the 2D linked list. */
	@Override
	public String toString() {
		/** Return null if linked list is empty. */
		if(isEmpty()) {
			return null;
		}
		
		/** Create a StringBuilder object to add each node's data to it.*/
		StringBuilder sb = new StringBuilder();

		/** Next column reference hopping */
		Array2DNode<E> nColumn = this.head;
		
		/** Next row reference hopping */
		Array2DNode<E> nRow = this.head;
		int c = 0;

		/** iterate if the next row is not null. */
        while (nRow != null) {
        	nColumn = nRow;
        	
        	/** Iterate if the next column is not null. */
            while (nColumn != null) {
            	
//				/** Add each node's data to StringBuilder. */            	
//            	if(c==this.colSize-1) {
//            		sb.append(nColumn.item);
//            	}
//            	else {
            		sb.append(String.format("%s ", nColumn.item));
//            	}
            	
				/** In the current row, move to the next column. */
            	nColumn = nColumn.nextCol;
            	c++;
            }
            sb.append("\n");
            
			/** Move to the next row. */
            nRow = nRow.nextRow;
            
			/** Reset the column index to 0 so that it starts next row at index 0. */
            c=0;
        }
		return sb.substring(0, sb.length()-1);
	}
	
	/**@return	returns a String representation of 2D nodes and prints the
	 * columns row by row. */
	public String toStringColByCol() {
		
		/** Return null if linked list is empty. */
		if(isEmpty()) {
			return null;
		}
		
		/** Create a StringBuilder object to add each node's data to it.*/
		StringBuilder sb = new StringBuilder();

		/** next column reference hopping */
		Array2DNode<E> nextcol = this.head;
		
		/** next row reference hopping */
		Array2DNode<E> nextrow = this.head;
		int c = 0;

		/** Continue the loop if the next row is not null. */
        while (nextcol != null) {
        	nextrow = nextcol;
        	
        	/** Continue the loop if the next column is not null. */
            while (nextrow != null) {
            	
				/** Add each node's data to StringBuilder. */            	
            	if(c==this.colSize-1) {
            		sb.append(nextrow.item);
            	}
            	else {
            		sb.append(String.format("%s ", nextrow.item.toString()));
            	}
            	
				/** Move to the next column. */
            	nextrow = nextrow.nextRow;
            	c++;
            }
            sb.append("\n");
            
			/** Move to the next row. */
            nextcol = nextcol.nextCol;//#
            
			/** Reset the column index to 0 so that it starts next row at index 0. */
            c=0;
        	
        }
		return sb.substring(0, sb.length()-1);
	}
	
	/** This is a helper method that initializes the 2D nodes using the
	 * constructor values. It connects the first row of nodes to the rest of
	 * rows from the constructor parameter.
	 * 
	 * @param values 	The values to populate the initial 2D nodes. */ 
	private void helperAddRestRows(E[][] values) {
		int row = 1, col = 0;
		Array2DNode<E> current = this.rowTail;
		Array2DNode<E> currentRowTail=  this.rowTail;
		
		while (row < values.length) {
			while (col < values[row].length) {
				Array2DNode<E> newNode = new Array2DNode<>(values[row][col]);
				current.nextRow = newNode;
				
				/** If it is the head of next row, then make the new node rowTail. */
				if (col==0) {
					this.rowTail = newNode;
					currentRowTail = this.rowTail;
				}
				
				/** Connect the new node to the current column */
				else {
					currentRowTail.nextCol = newNode;
					currentRowTail = newNode;
				}
				// In the current row, move to the next column.
				current = current.nextCol;
				col++;
			}
			
			/** Reset the current to rowTail rather than the head. */
			current = this.rowTail;
			
			/** Reset the column index to 0 to traverse the next row. */
			col = 0;
			
			/** Move to row index in the 2D array values. */
			row++;	
		}
	}
	
	/** Initializes the first row of 2D nodes passed to the constructor.
	 * and returns nothing. */
	private void helperAddFirstRow(E[][] values) {
		int col = 0;
		
		while (col < values[0].length) {
			if (col == 0) {
				/** The head, rowTail, and colTail point to the first node in
				 * the 2D of nodes.*/
				this.head = new Array2DNode<>(values[0][0]);
				this.rowTail = this.colTail = this.head;
			}
			else {
				/** Connect the next nodes to the head. */
				this.colTail.nextCol = new Array2DNode<>(values[0][col]);
				this.colTail = this.colTail.nextCol;
			}
			col++;
		}
	}
	
	/** This method adds a new row of nodes at the end of 2D node.
	 * @param values 	Holds the new row of nodes' data/values. */
	public void addLastRow(E... values) {
		if(values.length != this.rowSize) {
			throw new IllegalArgumentException("Error caused by values.length");
		}
		/** Current rowTail. */
        Array2DNode<E> oldColTail = this.rowTail;
        
		/** Update rowTail. */
        this.rowTail = new Array2DNode<>(values[0]);
        
		/** Create a reference of the updated rowTail. */
        Array2DNode<E> newColTailHolder = this.rowTail;
        
		/** Link the old rowTail to the new rowTail. */
        oldColTail.nextRow= newColTailHolder;
        
        int index = 1;
        while(index < values.length) {
            
			/** Adds the next value as the next column. */
        	newColTailHolder.nextCol = new Array2DNode<>(values[index]);
            
			/** sets newColTail to the next column. */
        	newColTailHolder = newColTailHolder.nextCol;
            
			/* Sets colColTail to the next column. */
            oldColTail = oldColTail.nextCol;
            
            //sets olColTail to next row to the new node.
            oldColTail.nextRow = newColTailHolder;
            index++;   
        }
        this.colSize++;
	}
	
	/** @param values Values to populate the last column to add. */
	public void addLastCol(E ... values) {
		if(values.length != this.rowSize) {
			throw new IllegalArgumentException("Error caused by values.length");
		}
		
		//current colTail
        Array2DNode<E> oldColTail = this.colTail;
        
		/** Update the colTail. */
        this.colTail = new Array2DNode<>(values[0]);
        
		/** Create a reference of the updated colTail. */
        Array2DNode<E> newColTailHolder = this.colTail;
        
		/** Link the old colTail to the new colTail. */
        oldColTail.nextCol = newColTailHolder;
        
        int index = 1;
        while(index < values.length) {
            
			/** Adds the next value as the next row. */
        	newColTailHolder.nextRow = new Array2DNode<>(values[index]);
            
			/** sets newColTail to the next row. */
        	newColTailHolder = newColTailHolder.nextRow;
            
			/* Sets colColTail to the next row. */
            oldColTail = oldColTail.nextRow;
            
            //sets olColTail to next col to the new node.
            oldColTail.nextCol = newColTailHolder;
            index++;   
        }
        this.colSize++;
	}
	
	/**Helper method.
	 * @return returns true if the linked list is empty, and false otherwise. */
	private boolean isEmpty() {
		return this.rowSize == 0 && colSize == 0 && this.head == null && this.rowTail == null && this.colTail == null;
	}
	
}