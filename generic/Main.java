/** @ author 	Juan Tiguila */

/** In this main class we will test the methods from the generic class
 * MyArray.
 * 
 * We will call the methods:
 *  
 * sort(),
 * equals(),
 * put(start, end, ...), and
 * leftShift()
 * 
 * with Integer, String and Object data.
 * 
 * */
public class Main {
	public static void main(String[] args) {
		
		Integer[] ints = {1,9, 10,2,3,6,7,8,4,5};
		Integer[] subInts = {99,66,99};
		String[] strings = {"zzz","mmm","bbs", "As", "ccs"};
		String[] subStrings = {"juan", "java", "math"};
		
		/** Create two instance of the generic MyArray class. */
		MyArray<Integer> mi = new MyArray<>(ints);
		MyArray<String> ms = new MyArray<>(strings);
		
		
		
//		System.out.println(mi.toString());
		
		
		
//		/** Comparing String and Integers*/
//	    System.out.println("comparing integers and string: " + mi.equals(ms));
//	    
//		System.out.print("Original: " + mi.toString());
//		System.out.println();
//		
//		
//	    mi.sort();
//	    System.out.println("sorted: "+ mi.toString());
//	    
//	    mi.put(2,4, subInts);
//	    System.out.println("put: " + mi.toString());
//
//
//	    mi.leftShift(1);
//	    
//	    System.out.println("left shifted: " + mi.toString());
//		
//		
//		
//		
//		System.out.println("\n");
//		
//	    /**************************** With Strings ****************************/
//	    
//		System.out.print("Original: " + ms.toString());
//		System.out.println();
//		
//		
//	    ms.sort();
//	    System.out.println("sorted: "+ ms.toString());
//	    
//	    ms.put(2,4, subStrings);
//	    System.out.println("put: " + ms.toString());
//
//
//	    ms.leftShift(1);
//	    
//	    System.out.println("left shifted: " + ms.toString());
	    
	    
	    
		
	}




}




