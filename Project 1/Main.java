package hw05;

/**
 * 
 * @author Juan Tiguila.
 *
 * */

/**
 * Here is main where methods from Array2D are tested.
 * 
 * Test the following methods to ensure they produce the expected outputs:
 * 
 * 		delteCol(index) method.
 * 		delteRow(index) method.
 * 		insertCol(index, values) method.
 * 		insertRow(index, values) method.
 * 
 * 
 * */

public class Main {
	public static void main(String[] args) {
		
		deleteCol(); // delete col 1.
		insertRow(); // delete row 2.
		insertCol(); // delete col 1.
		deleteRow(); // delete row 2.
		
	}
	
	public static void deleteCol() {
		System.out.println("-------------Start of deleteCol()------------\n");
		
		Integer[][] a = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{3, 4, 5, 2},
				{2, 9, 1, 7}};
		Array2D<Integer> array2DNode = new Array2D<Integer>(a);
		System.out.println("Original:\n" + array2DNode.toString()+ "\n");
		
		int[][] expected = {
				{1, 3, 4},
				{5, 7, 8},
				{3, 5, 2},
				{2, 1, 7}};
		
		System.out.println("Expected result:");
		for(int r=0; r<expected.length; r++) {
			for(int c=0;c<expected[0].length; c++) {
				System.out.print(expected[r][c]+ " ");
			}
			System.out.println();
		}
		
		array2DNode.deleteCol(1);
		System.out.println("\nActual result: \n" + array2DNode.toString());
		
		System.out.println("\n-------------End of deleteCol()----------------");
	}
	
	public static void deleteRow() {
		System.out.println("-------------Start of deleteRow()------------\n");
		
		Integer[][] a = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{3, 4, 5, 2},
				{2, 9, 1, 7}};
		
		Array2D<Integer> array2DNode = new Array2D<Integer>(a);
		
		System.out.println("Original:\n" + array2DNode.toString()+ "\n");
		int[][] expected = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{2, 9, 1, 7} };
		
		System.out.println("Expected result:");
		for(int r=0; r<expected.length; r++) {
			for(int c=0;c<expected[0].length; c++) {
				System.out.print(expected[r][c]+ " ");
			}
			System.out.println();
		}
				
		array2DNode.deleteRow(2);
		System.out.println("\nActual result: \n" + array2DNode.toString());
		System.out.println("\n-------------End of deleteRow()----------------");
	
	}
	
	public static void insertCol() {
		System.out.println("-------------Start of insertCol()------------\n");	
		Integer[][] a = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{3, 4, 5, 2},
				{2, 9, 1, 7}};
		
		Array2D<Integer> array2DNode = new Array2D<Integer>(a);
		System.out.println("Original:\n" + array2DNode.toString()+"\n");
		
		int[][] expected = {
				{1, 9, 2, 3, 4},
				{5, 8, 6, 7, 8},
				{3, 7, 4, 5, 2},
				{2, 6, 9, 1, 7}
				};
		
		System.out.println("Expected result:");
		for(int r=0; r<expected.length; r++) {
			for(int c=0;c<expected[0].length; c++) {
				System.out.print(expected[r][c]+ " ");
			}
			System.out.println();
		}
		
		Integer[] c1 = {9, 8, 7, 6};
		
		array2DNode.insertCol(1, c1);
		System.out.println("\nActual result: \n" + array2DNode.toString());
		System.out.println("\n-------------End of insertCol()----------------");
	}
	
	public static void insertRow() {
		System.out.println("\n-------------Start of insertRow()----------------");
		Integer[][] a = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{3, 4, 5, 2},
				{2, 9, 1, 7}};
		Array2D<Integer> array2DNode = new Array2D<Integer>(a);
		
		System.out.println("Original:\n" + array2DNode.toString()+"\n");
		
		int[][] expected = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{0, 1, 2, 3},
				{3, 4, 5, 2},
				{2, 9, 1, 7} };
		
		System.out.println("Expected result:");
		for(int r=0; r<expected.length; r++) {
			for(int c=0;c<expected[0].length; c++) {
				System.out.print(expected[r][c]+ " ");
			}
			System.out.println();
		}
		
		Integer[] c1 = {0, 1, 2, 3};
		array2DNode.insertRow(2, c1);
		System.out.println("\nActual result: \n" + array2DNode.toString());
		System.out.println("\n-------------End of insertRow()----------------");
	}
	
}
