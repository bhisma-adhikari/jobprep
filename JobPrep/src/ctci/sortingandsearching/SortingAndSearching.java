package ctci.sortingandsearching;

import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import main.Person;

public class SortingAndSearching {

	public static void main(String[] args) throws FileNotFoundException {
//		int[] sorted = radixSort(new int[] { 5, 9, 15, -2, 4, 9 });
//		Utils.printIntArray(sorted);
//		
//		Person[] persons = new Person[3]; 
//		persons[0] = new Person("John", 23); 
//		persons[1] = new Person("Jake", 21); 
//		persons[2] = new Person("Jane", 21); 
//		
//		persons = radixSort(persons); 
//		for (int i = 0; i < persons.length; i++) {
//			System.out.println(persons[i]);
//		}

//		int[] numbers = new int[] { 1, 2, 4, 3, 5 };
//		int[] numbers = new int[] { 5, 3};
//		int[] numbers = new int[] { 5, 4, 3, 2, 1 };
//		Utils.printIntArray(bubbleSort(numbers));

//		mergeSort(numbers); 
//		Utils.printIntArray(numbers);

//		String[] arr = new String[] {"hello", "world", "elloh", "iceman", "orld", "wrold", "cinema"}; 
//		Arrays.sort(arr, new AnagramComparator());
//		Utils.printArray(arr);

//		int[] numbers = new int[] { 10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		int index = searchInRotatedArray(numbers, 13);
//		System.out.println(index);

//		String[] arr = new String[] { "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "" };
//		System.out.println(sparseSearch("ball", arr));

//		System.out.println(missingInt("/Users/bhisma/tmp/tmp.txt"));

//		RankFromStream rfs = new RankFromStream();
//		rfs.track(5);
//		rfs.track(1);
//		rfs.track(4);
//		rfs.track(4);
//		rfs.track(5);
//		rfs.track(9);
//		rfs.track(7);
//		rfs.track(13);
//		rfs.track(3);
//		
//		System.out.println(rfs.getRank(1));
//		System.out.println(rfs.getRank(3));
//		System.out.println(rfs.getRank(4));
//		System.out.println(rfs.getRank(9));
		
		int[] numbers = new int[] {9, 1, 0, 4, 8, 7}; 
		sortValleyPeak(numbers);
		Utils.printIntArray(numbers);
		
	}

	public static void sortValleyPeak(int[] array) {
		for (int i = 1; i < array.length; i+=2) {
			int maxIndex = maxIndex(array, i-1, i, i+1); 
			if (maxIndex != i) {
				swap(array,  maxIndex,  i);
			}
		}
	}
	
	public static int maxIndex(int[] array, int a, int b, int c) {
		// index can be out of range
		int len = array.length; 
		int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE; 
		int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE; 
		int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE; 
		
		int maxValue = Math.max(aValue,  Math.max(bValue, cValue)); 
		if (maxValue == aValue) return a;
		if (maxValue == bValue) return b; 
		return c; 	
	}
	
	public static void swap(int[] array, int index1, int index2) {
		int tmp = array[index1]; 
		array[index1] = array[index2]; 
		array[index2] = tmp;
	}
	
	
	
//	public static RowCol sortedMatrixSearch(int[][] matrix, int i) {
//		int nRows = matrix.length;
//		if (nRows == 0)
//			return null;
//		int nCols = matrix[0].length;
//		int smaller = nRows < nCols ? nRows : nCols;
//
//		return sortedMatrixSearch(matrix, i, new RowCol(0, 0), new RowCol(nRows - 1, nCols - 1), new RowCol(0, 0),
//				new RowCol(smaller - 1, smaller - 1));
//	}
//
//	private static RowCol sortedMatrixSearch(int[][] matrix, int i, RowCol topLeft, RowCol bottomRight,
//			RowCol diagStart, RowCol diagEnd) {
//
//		// base case
//		if (diagStart.row == diagEnd.row)
//			return sortedMatrixSearchRow(matrix, i, diagStart.row, topLeft.col, bottomRight.col);
//		// base case
//		if (diagStart.col == diagEnd.col)
//			return sortedMatrixSearchCol(matrix, i, diagStart.col, topLeft.row, bottomRight.row);
//
//		// find mid diagonal RowCol
//		RowCol diagMid = new RowCol(diagStart.row + (diagStart.row + diagEnd.row) / 2,
//				diagStart.col + (diagStart.col + diagEnd.col) / 2);
//
//		int diagMidElem = matrix[diagMid.row][diagMid.col];
//		
//		if (diagMidelem == i)
//			return diagMid;
//
//		
//		if (diagMidElem > i) {
//			RowCol resTopLeft = sortedMatrixSearchTopLeft(matrix, i, topLeft, bottomRight, diagStart, diagEnd, diagMid);
//			if (resTopLeft != null) return resTopLeft;
//			
//			
//		}
//		
//		
//
//		return null;
//	}
//
//	private static RowCol sortedMatrixSearchTopLeft(int[][] matrix, int i, RowCol topLeft, RowCol bottomRight,
//			RowCol diagStart, RowCol diagEnd, RowCol diagMid) {
//		bottomRight = diagMid; 
//		diagEnd = diagMid;
//		return sortedMatrixSearch(matrix, i, topLeft, bottomRight, diagStart, diagEnd);
//	}
//	
//	private static RowCol sortedMatrixSearchTopRight(int[][] matrix, int i, RowCol topLeft, RowCol bottomRight,
//			RowCol diagStart, RowCol diagEnd, RowCol diagMid) {
//		topLeft = new RowCol(topLeft.row, diagMid.col+1);
//		bottomRight = new RowCol(diagMid.row, bottomRight.col); 
//		diagStart = new RowCol(diagStart.row, diagMid.col + 1); 
//		int rowSpan = bottomRight.row - topLeft.row;
//		diagEnd = new RowCol(diagMid.row, topLeft.col + rowSpan); // because for diagonal, rowSpan = colSpan 
//		return sortedMatrixSearch(matrix, i, topLeft, bottomRight, diagStart, diagEnd); 
//	}
//	
//	private static RowCol sortedMatrixSearchBottomLeft(int[][] matrix, int i, RowCol topLeft, RowCol bottomRight,
//			RowCol diagStart, RowCol diagEnd, RowCol diagMid) {
//		topLeft = new RowCol(diagMid.row + 1, topLeft.col); 
//		bottomRight = new RowCol(bottomRight.row, diagMid.col); 
//		diagStart = new RowCol(diagMid.row + 1, diagStart.col); 
//		diagEnd = new RowCol()
//		
//		
//		
//		topLeft = new RowCol(topLeft.row, diagMid.col+1);
//		bottomRight = new RowCol(diagMid.row, bottomRight.col); 
//		diagStart = new RowCol(diagStart.row, diagMid.col + 1); 
//		int rowSpan = bottomRight.row - topLeft.row;
//		diagEnd = new RowCol(diagMid.row, topLeft.col + rowSpan); // because for diagonal, rowSpan = colSpan 
//		return sortedMatrixSearch(matrix, i, topLeft, bottomRight, diagStart, diagEnd); 
//	}
//	
//	
//	
//	
//
//	// binary-search given row of matrix in range [colStart, colEnd] for number i
//	private static RowCol sortedMatrixSearchRow(int[][] matrix, int i, int row, int colStart, int colEnd) {
//
//	}
//
//	// binary-search given col of matrix in range [rowStart, rowEnd] for number i
//	private static RowCol sortedMatrixSearchCol(int[][] matrix, int i, int col, int rowStart, int rowEnd) {
//
//	}

	// filename is supposed to contain many (4 billion) +ve integers
	// returns an integer not contained in filename
	// if not found, returns -1
	public static int missingInt(String filename) throws FileNotFoundException {
		long numberOfInts = (long) Integer.MAX_VALUE + 1;
		byte[] bitfield = new byte[(int) (numberOfInts / 8)];

		Scanner in = new Scanner(new FileReader(filename));
		// set the bit-vector
		while (in.hasNextInt()) {
			int n = in.nextInt();
			bitfield[n / 8] |= 1 << (n % 8);
			// the following does the same function as above line but more efficiently
//			bitfield[n >> 3] |= 1 << (n & 7);
		}

		// return the first +ve integer not present in bit-vector
		for (int i = 0; i <= Integer.MAX_VALUE; i++) {
			if ((bitfield[i / 8] & (1 << (i % 8))) == 0) {
				return i;
			}

		}
		return -1;

	}

	// returns the index of string in strings, or -1 (if absent)
	// strings contains sorted strings and empty strings
	public static int sparseSearch(String string, String[] strings) {
		return sparseSearch(string, strings, 0, strings.length - 1);
	}

	private static int sparseSearch(String string, String[] strings, int start, int end) {
		if (start == end) {
			return strings[start].equals(string) ? start : -1;
		}
		if (string.equals(""))
			return -1;

		int mid = start + (end - start) / 2;

//		// find the index of immediate prev/next non-empty string
//		// this if-block first searches the right side of empty-string to find the next non-empty string
//		// if not found, it searches the left half.		
//		if (strings[mid].equals("")) {
//			int newMid = mid; 
//			while (strings[newMid].equals("") && newMid < end) {
//				newMid++;
//			}
//			// all following strings are empty, so go to smaller indices instead 
//			if (newMid == end && strings[newMid].equals("")) {
//				newMid = mid; 
//				while (strings[newMid].equals("") && newMid > start) {
//					newMid--;
//				}
//			}
//			mid = newMid; 
//		} 

		// instead of first searching only right of empty string to find the next
		// non-empty string,
		// this if-block advances pointers in either direction one point at a time so
		// that the "closest"
		// non-empty string is set to the next mid
		if (strings[mid].equals("")) {
			int newMidLeft = mid;
			int newMidRight = mid;

			for (int jump = 1; newMidLeft > start || newMidRight < end; jump++) {
				if (newMidLeft > start) {
					newMidLeft--;
					if (!strings[newMidLeft].equals("")) {
						mid = newMidLeft;
						break;
					}
				}
				if (newMidRight < end) {
					newMidRight++;
					if (!strings[newMidRight].equals("")) {
						mid = newMidRight;
						break;
					}
				}
			}

			if (newMidLeft == start && newMidRight == end) {
				return -1;
			}

		}

		if (strings[mid].equals(string)) {
			return mid;
		}

		if (string.compareTo(strings[mid]) > 0) {
			return sparseSearch(string, strings, mid + 1, end);
		} else {
			return sparseSearch(string, strings, start, mid - 1);
		}

	}

	// returns the index of num in numbers (if exists), otherwise returns -1
	public static int searchInRotatedArray(int[] numbers, int num) {
		return searchInRotatedArray(numbers, num, 0, numbers.length - 1);
	}

	private static int searchInRotatedArray(int[] numbers, int num, int startIndex, int endIndex) {
		// base case
		if (startIndex == endIndex && numbers[startIndex] == num)
			return startIndex;

		int midIndex = startIndex + (endIndex - startIndex) / 2;
		if (numbers[midIndex] == num)
			return midIndex;

		// either left half or right half is guaranteed to be sorted
		boolean leftSorted = numbers[startIndex] <= numbers[midIndex - 1];

		if (leftSorted) {
			if (numbers[startIndex] <= num && num <= numbers[midIndex - 1]) {
				return searchInRotatedArray(numbers, num, startIndex, midIndex - 1);
			} else {
				return searchInRotatedArray(numbers, num, midIndex + 1, endIndex);
			}
		} else {
			if (numbers[midIndex + 1] <= num && num <= numbers[endIndex]) {
				return searchInRotatedArray(numbers, num, midIndex + 1, endIndex);
			} else {
				return searchInRotatedArray(numbers, num, startIndex, midIndex - 1);
			}
		}
	}

	public static void mergeSort(int[] numbers) {
		mergeSort(numbers, 0, numbers.length - 1);
	}

	// both indices are inclusive
	private static void mergeSort(int[] numbers, int startIndex, int endIndex) {
		if (endIndex == startIndex)
			return;
		int midIndex = (endIndex - startIndex) / 2;
		mergeSort(numbers, startIndex, midIndex);
		mergeSort(numbers, midIndex + 1, endIndex);
		merge(numbers, startIndex, midIndex, endIndex);
	}

	// first array extends from startIndex-to-midIndex (inclusive)
	// second array extends form midIndex+1-to-endIndex (inclusive)
	private static void merge(int[] numbers, int startIndex, int midIndex, int endIndex) {
		int[] merged = numbers.clone(); // only the elements in range [startIndex, endIndex] will be potentially updated
		int p1 = startIndex;
		int p2 = midIndex + 1;
		int index = startIndex;
		while (p1 <= midIndex && p2 <= endIndex) {
			if (numbers[p1] <= numbers[p2]) {
				merged[index] = numbers[p1];
				p1++;
			} else {
				merged[index] = numbers[p2];
				p2++;
			}
			index++;
		}
		while (p1 <= midIndex) {
			merged[index++] = numbers[p1];
			p1++;
		}
		while (p2 <= endIndex) {
			merged[index++] = numbers[p2];
			p2++;
		}
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = merged[i];
		}
	}

	public static void selectionSort(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			int min = numbers[i];
			int swapIndex = -1;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < min) {
					min = numbers[j];
					swapIndex = j;
				}
			}
			if (swapIndex != -1) {
				int tmp = numbers[i];
				numbers[i] = numbers[swapIndex];
				numbers[swapIndex] = tmp;
			}
		}
	}

	public static void bubbleSort(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					int tmp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = tmp;
				}
			}
		}
	}

	// 2 optimizations:
	// - inner loop var goes upto numbers.length - 1 - i (rather than numbers.length
	// - 1)
	// - early termination if there occurs no swapping
	public static void bubbleSortOptimized(int[] numbers) {
		boolean swapped = true;
		for (int i = 0; i < numbers.length && swapped; i++) {
			swapped = false;
			for (int j = 0; j < numbers.length - 1 - i; j++) {
				if (numbers[j] > numbers[j + 1]) {
					swapped = true;
					int tmp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = tmp;
				}
			}
		}
	}

	public static Person[] radixSort(Person[] persons) {
		if (persons.length == 0)
			return persons;

		int min = persons[0].getAge();
		int max = persons[0].getAge();

		// find min, max age :O(n)
		for (int i = 0; i < persons.length; i++) {
			int age = persons[i].getAge();
			if (age < min)
				min = age;
			if (age > max)
				max = age;
		}

		Map<Integer, List<Person>> agePersons = new HashMap<>();
		// O(age_range)
		for (int i = min; i <= max; i++) {
			agePersons.put(i, new LinkedList<>());
		}

		// O(n)
		for (int i = 0; i < persons.length; i++) {
			Person p = persons[i];
			Integer age = p.getAge();
			agePersons.get(age).add(p);
		}

//		System.out.println(agePersons);

		Person[] sorted = new Person[persons.length];
		int index = 0;
		// O(age_range)
		for (int i = min; i <= max; i++) {
			for (Person p : agePersons.get(i)) {
				sorted[index++] = p;
			}
		}

		return sorted;
	}

	// O(n)
	public static int[] radixSort(int[] numbers) {
		if (numbers.length == 0) {
			return numbers;
		}
		int min = numbers[0];
		int max = numbers[0];

		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			if (n < min) {
				min = n;
			}
			if (n > max) {
				max = n;
			}
		}

		int[] histogram = new int[max - min + 1];
		for (int i = 0; i < numbers.length; i++) {
			int index = numbers[i] - min;
			histogram[index]++;
		}

		int[] sorted = new int[numbers.length];
		int index = 0;
		for (int i = 0; i < histogram.length; i++) {
			int freq = histogram[i];
			if (freq > 0) {
				int number = i + min; // min serves as offset
				for (int j = 0; j < freq; j++) {
					sorted[index++] = number;
				}
			}
		}
		return sorted;

	}
}

class AnagramComparator implements Comparator<String> {
	private String sortChars(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	@Override
	public int compare(String s1, String s2) {
		s1 = sortChars(s1);
		s2 = sortChars(s2);
		return s1.compareTo(s2);
	}
}

class RowCol {
	public int row;
	public int col;

	public RowCol(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}

class RankFromStream {
	Map<Integer, Integer> numRank; 
	
	public RankFromStream() {
		this.numRank = new HashMap<>(); 
	}
	
	public void track(int number) {
		int initialCount = 0; 
		for (Integer key : this.numRank.keySet()) {
			if (key >= number) {
				this.numRank.put(key,  this.numRank.get(key) + 1); 
			} else {
//				initialCount++;
				initialCount += this.numRank.get(key);
			}
			
		}
		if (! this.numRank.containsKey(number)) {
			this.numRank.put(number, initialCount); 
		}
	}
	
	public int getRank(int number) {
		if (this.numRank.containsKey(number)) {
			return this.numRank.get(number);
		}
		return -1; 
	}
}



