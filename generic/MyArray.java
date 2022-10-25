/** @ author 	Juan Tiguila */

/**
 * This class is a generic class. It has genetic method and it extends 
 * Comparable to compare items from an generic array.
 * */

public class MyArray<E extends Comparable<E>> {
    //No other data fields necessary.
    private E[] data;

    public MyArray(int size) {
        this.data = (E[])(new Comparable[size]);
    }

    //This constructor can accept an array or a comma-separated list of values.
    public MyArray(E ... elements) {
        this.data = (E[])(new Comparable[elements.length]);
        //Make a deep copy to prevent shared references.
        System.arraycopy(elements, 0, this.data, 0, elements.length);
        
    }
    
    
 
    /** This method sorts from ascending order all elements. */
	public void sort() {
		/** Go through each item in the array. */
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				
				/** Create a temporary variable to hold each previous item to
				 * compare it to all the rest in the array. */
				E temp = data[i];
				
				/** If the previous item is less than the next one, swap their
				 * indices. */
				if (data[i].compareTo(data[j]) < 0) {
					
					/** Swap the values. */
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}
	
	
	
    /** 
     * @param myOtherArrayObject	The object to use in the comparison.
     * 
     * This method compares this class and the parameter object. */
    @Override
	public boolean equals(Object myOtherArrayObject) {
		/** Compare this class's content to the one passed one as parameter.
		 * If the have the same content, return true.*/
    	if (this == myOtherArrayObject) {
			return true;
		}
    	
    	/** Return false if the parameter object does not have any content or 
    	 * the class information as this class.*/
		if (myOtherArrayObject == null || myOtherArrayObject.getClass() != this.getClass()) {
			return false;
		}
		
		/** Return false for any other case. */
		return false;
	}
	
    
    
    /**This method replaces values between the start and end indices by the
     * given elements. 
     * 
     * @param start	The start index to start replacing values in the array.	
     * @param end	The ending index to stop replacing values in the array.
     * @param elemennts		The elements to place in the current array. */
   public void put(int start, int end, E... elements) {
    	
    	/** Create an array instance to copy the elements from the original
    	 * array to this copy. */
    	E[] result = (E[])(new Comparable[data.length]);    	
    	
    	/** Hold the start and end indices to reference them later. */
    	int temp = start;
    	int temp2 = end;
    	
    	/** Copy the elements before the start index from the original array to
    	 * the copy. */
		for (int index = 0; index < (start-1); index++) {
			result[index] = data[index];
		}

		/** Copy the elements between the start and end indices from the
		 * original array to the copy. */
		for(int i=temp-1, z=0;i<temp2;i++, z++) {
			result[i] = elements[z];
		}

		/** Copy the remaining elements from the original to copy array, if any.*/
		for(int i=(temp2); i<result.length;i++) {
			result[i] = data[i];
		}
		data=result;
   }

   /** This method left shifts all the values by the given shift distance.
    * 
    * @param shiftDistance 	The number of indices to left shift the 
    * elements in the array. */
   public void leftShift(int shiftDistance) {
	   
    	E[] deepCopy = (E[])(new Comparable[data.length]);
		
		/** Hold the given index for later reference. */
		int tempIndex = shiftDistance;
		
		/** Copy the values after the index from original array to a different
		 * array. */
		E[] listsubsetA = (E[])(new Comparable[data.length-shiftDistance]);
		
		for(int i=0;i<listsubsetA.length;i++, ++shiftDistance) {
			listsubsetA[i] = data[shiftDistance];
		}
		
		/** Copy the values before the index from original array to a different
		 * array. */
		E[] listsubsetB = (E[])(new Comparable[tempIndex]);
		for(int i=0;i<listsubsetB.length;i++) {
			listsubsetB[i] = data[i];
		}
		
		/** Copying the first subset of the original array to the deep copy
		 * thereof.*/
		for(int i=0;i<listsubsetA.length;i++) {
			deepCopy[i] = listsubsetA[i];
		}
		
		/** Copying the second subset of the original array to the deep copy
		 * thereof.*/
		for(int i=listsubsetA.length, z =0; i<data.length;i++, z++) {
			deepCopy[i] = listsubsetB[z];
		}
		data = deepCopy;
    }
    
    
    
    /*************************************************************************/
    
    
	// Returns a formatted string representation of an instance of this class.
	@Override
	public String toString() {
		
		/** Initialize a String to hold the each value in the array. */
		String n = "";
		
		/** Add each item in the array to the string.*/
		for(int i=0;i<data.length;i++) {
			
			/** If it is the last item in the array, simply append the item in 
			 * the array to the string. */
			if(i==data.length-1) {				
				n +=data[i].toString();
			}
			
			/** append the value in the the array at current index including a
			 * comma and a space */
			else {
				n +=data[i].toString()+ ", ";
			}
		}
		return n;
	}
	
	
	
    /** This method returns a new generic array between the parameters.
     * 
     * @param start		The starting index.
     * @param end		The ending index to for a new MyArray object.
     * */
    public <E> E[] get(int start, int end) {
    	
    	/**Create an a new genetic array of type E, length being the difference
    	 * of start and end indices.
    	 * */
    	E[] n = (E[])(new Comparable[(end)-(start-1)]); 
		for(int i=0; start<=end; i++, start++) {
			n[i] = (E) data[start-1];
		}
		
		/** Return a new genetic array of type E. */
    	return (E[]) n;
    }
    
    
    /** @return		The size of a MyArray object. */
	public <E> int size() {
		return data.length;
	}
    
	
    /** This method returns the minimum value in the generic type array using a 
     * nested for loop and swapping values herein. */
    public <E extends Comparable<E>> E min() {
    	/** Initialize a minimum value, letting it be the first value in the 
    	 * array. */
    	E minimum = (E)data[0];
    	
    	/** Start of sorting, sort the values the number of times as the 
    	 * array length. */
    	for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				
				/** Compare previous value to the item at index zero.  
				 * Set the minimum value to be the previous value if value at 0 
				 * is greater than or equal to zero to the previous value. */
				if(minimum.compareTo((E)data[i])>=0) {
					minimum=(E) data[i];
				}
				
				/** Compare the next value to the item at index zero.  
				 * Set the minimum value to be the next value if value at 0 
				 * is greater than or equal to zero to the next value. */
				else if(minimum.compareTo((E)data[j])>=0) {
					minimum=(E) data[j];
					
				}
			}
		}
    	
    	/** Once, for loops have finished, min value is found and return it. */
    	return (E) minimum;
    }
    
    
    /** This method returns the maximum value in the generic type array using a 
     * nested for loop and swapping values herein. */
    public <E extends Comparable<E>> E max() {
    	E max = (E)data[0];
    	for (int indexOne = 0; indexOne < data.length; indexOne++) {
			for (int indexTwo = 0; indexTwo < data.length; indexTwo++) {
				
				/** If comparing the item at index 0 is greater than or equal to
				 * zero, then the value at index 0 is greater than the previous
				 * item in the array. */
				if(max.compareTo((E)data[indexOne])<=0) {
					max=(E) data[indexOne];
				}
				
				/** if value at 0 is greater than */
				
				else if(max.compareTo((E)data[indexTwo])<=0) {
					max=(E) data[indexTwo];
				}
			}
		}
    	return (E) max;
    }

    
    
    /** @return The index of the object to return.
     * 
     * A generic method, takes an index & returns that Object.*/
    public <E> E get(int index) {
		/** Cast return to the generic type. */
    	return (E) data[index-1];
    }

    
    /** This method uses the Math function to generate a random index and
	 * swap the values.*/
    public void shuffle() {
    	/** Go through each item in the array using a for loop. */
		for (int i = 0; i < data.length; i++) {
			/** Generate a random number to be the index.*/ 
			int randomIndex = (int) (Math.random()*data.length);
			
			
			/** Swap the indices. */
			E temp = data[i];
			data[i] = data[randomIndex];
			data[randomIndex] = temp;
		}
		/** Current array will have indices changed.*/
    }
    
    

    
    
    /** This method reverses the existing array. */
    public void reverse() {
    	
    	/** Create an array instance with same length as the original one. */
    	E[] newData = (E[])(new Comparable[data.length]);
    	
    	/** Reverse all the indices, e.g,. bring the item at last index to index
    	 * 0, one before last to the second index and so on. */
    	for (int oldIndex = 0, newIndex = newData.length - 1; oldIndex < newData.length; oldIndex++, newIndex--) {
    		newData[newIndex] = data[oldIndex];
		}
    	
    	/** Make the original array equal to the copy reversed. */
    	data = newData;
    }
    
    
    /** This method changes a value in the current array.
     * 
     * @param index 	The index of the value to replace in the current array.
     * @param value 	The value to replace in the current array. */
    public void put(int index, E value) {
    	/** Make a deep copy of the original array and change one value from it.*/
		E[] result = (E[])(new Comparable[data.length]);
		for(int i=0;i<result.length;i++) {
			
			/** If the current item index equals the parameter index, replace 
			 * the value at this index.*/
			if(i==(index-1)) {
				result[i] = value;
			}
			
			/** If the above condition is false, copy the values from the 
			 * original array to the copy.*/
			else {
				result[i] = data[i];
			}
		}
		data = result;
    }
    


    /** @param shiftDistance 	The right shift distance to move all the
     * elements in the array.
     * */
	public void rightShift(int shiftDistance) {
		/** Create a copy of the original array. */
		E[] deepCopy = (E[])(new Comparable[data.length]);
		
		/** Add the items before the shift distance from the original array to 
		 * the a new generic array.*/
		E[] firstSubset = (E[])(new Comparable[data.length-shiftDistance]);
		for(int i=0;i<firstSubset.length;i++) {
			firstSubset[i] = data[i];
		}
		
		/** Create another array to store the values after the shift distance.*/
		E[] secondsubset = (E[])(new Comparable[deepCopy.length-firstSubset.length]);
		
		/** Add the remaining items from the original array to the new array. */
		for(int i=deepCopy.length-1, z=0;i>=firstSubset.length;i--, z++) {
			secondsubset[z] = data[i];
		}
		
		/** Copy the first segment from the original array to the generic
		 * array copy. */
		for(int i=0;i<secondsubset.length;i++) {
			deepCopy[i] = secondsubset[i];
		}
		
		/** Copy the second segment from the original array to the generic array
		 * copy. */
		for(int i=secondsubset.length, z=0; i<deepCopy.length;i++, z++) {
			deepCopy[i] = firstSubset[z];
		}
		
		data = deepCopy;
		
	}
}