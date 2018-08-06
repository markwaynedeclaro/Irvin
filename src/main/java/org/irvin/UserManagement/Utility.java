package org.irvin.UserManagement;

import java.util.Arrays;
import java.util.TreeSet;

public class Utility {

	public int[] quickSort(int[] array, int wall, int pivot) 
	{
		System.out.println("------------------------");
		int originalWall = wall;
		int originalPivot = pivot;
		int index = wall;
		int temp;
		while(index <= (array.length - 1))
		{
			System.out.println("index : "+index+", wall : "+wall+", pivot : "+pivot);
			if (array[pivot] >= array[index]) {
				System.out.println("  "+array[index]+" is smaller than "+array[pivot]);
				System.out.println("  swap "+array[index]+" and "+array[wall]);
				temp = array[wall];
				array[wall++] = array[index];
				array[index] = temp;
				System.out.println("  "+Arrays.toString(array));
			}
			index++;
		}
		wall--;
		
		pivot = wall - 1;
		
		// check lower half
		if ( (pivot - originalWall) > 0) 
			array = quickSort(array, originalWall, pivot);		
		
		// check upper half
		if ( (originalPivot - pivot - 1) > 0) 
			array = quickSort(array, pivot+1, originalPivot);			
		
		return array;
	}
	
	
	public String intToBinaryString(int number) 
	{
        StringBuilder builder = new StringBuilder();

        do {
            builder.append(number % 2);
            number = number / 2;
        } while (number > 0);

	    return builder.reverse().toString();
	}
	
	
	public int[] getParentIndices(int[] array) 
	{
		int[] parentIndices = new int[array.length];
		String binaryString;
		StringBuilder builder;
		for(int i=1; i<= array.length; i++)
		{
			binaryString = intToBinaryString(i);
			//System.out.println(i);
			//System.out.println("  before: "+binaryString);
			builder = new StringBuilder(binaryString);
			builder.setCharAt(binaryString.lastIndexOf('1'), '0');
			//System.out.println("    after: "+builder.toString());
			//System.out.println("         : "+Integer.parseInt(builder.toString(),2));
			parentIndices[i-1] = Integer.parseInt(builder.toString(),2);
		}
		
		return parentIndices;
	}
	
	
	public int[] getBinaryIndexedTree(int[] array, int[] parentIndices) 
	{
		int[] binaryIndexedTree = new int[array.length];
		String wholeBinaryString;
		String toSumBinaryString;
		int toSumCount;
		int sum;

		for(int i=1; i<= array.length; i++)
		{
			wholeBinaryString = intToBinaryString(i);
			toSumBinaryString = wholeBinaryString.substring(wholeBinaryString.lastIndexOf('1'));
			toSumCount = Integer.parseInt(toSumBinaryString,2);
			sum = 0;
			
			for(int m=parentIndices[i-1]; m< parentIndices[i-1]+toSumCount; m++)
			{
				sum += array[m];
			}
			
			binaryIndexedTree[i-1] = sum;
			/*
			System.out.println(i);
			System.out.println("       parent index : "+parentIndices[i-1]);
			System.out.println("  toSumBinaryString : "+toSumBinaryString);
			System.out.println("         toSumCount : "+toSumCount);
			System.out.println("              value : "+sum);
			*/
		}
		
		return binaryIndexedTree;
	}	
	
	
	
		
	
	public TreeSet<Long> getPrimeFactors(TreeSet<Long> primeFactors, long pivot, long number)
	{
		System.out.println(Arrays.toString(primeFactors.toArray())+"  -- "+pivot+"  -  "+number);
		
		long otherFactor = 1;
		if (number % pivot == 0) {
			primeFactors.add(pivot);	// will be added if does net yet exist on the set
			otherFactor = number / pivot;
			if (!primeFactors.contains(otherFactor))
				primeFactors = getPrimeFactors(primeFactors, pivot, otherFactor);
		} else {
			
			do {
				pivot++;
			} 
			while (number % pivot != 0);
			
			primeFactors.add(pivot);	// will be added if does net yet exist on the set
			otherFactor = number / pivot;
			if (!primeFactors.contains(otherFactor) && otherFactor != 1)
				primeFactors = getPrimeFactors(primeFactors, pivot, otherFactor);
			
		}
		
		return primeFactors;
	}	
	
	
	public int getLargestPalindrome(int digit)
	{
		String palindrome;
		TreeSet<Integer> palindromes = new TreeSet<Integer>();
		int pivot = (int) Math.pow(10, digit) - 1;
		int testRange = (int) Math.pow(10, digit-1);
		int pivotEnd = pivot - testRange;

		do {
			System.out.println("          pivot :"+pivot);
			System.out.println("       pivotEnd :"+pivotEnd);			
			for (int i=pivot; i>pivotEnd; i--)
			{
				palindrome = Integer.toString(i*pivot);
				System.out.println(" i ("+i+") * pivot ("+pivot+") = "+palindrome);
				if (palindrome.equals(new StringBuilder(palindrome).reverse().toString())) {
					palindromes.add(Integer.parseInt(palindrome));
					System.out.println(Arrays.toString(palindromes.toArray()));
				}
			}
			pivot--;
			if (pivot == pivotEnd) {
				pivotEnd -= testRange;
				
				if (palindromes.size() > 1)
					break;
			}	
			
		} while (pivot > 0);
		
		return palindromes.last();
	}

}
