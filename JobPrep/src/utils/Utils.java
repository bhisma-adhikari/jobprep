package utils; 

import java.util.*;


public class Utils {
	public static void main(String[] args) {
		boolean[][] arr = new boolean[4][5];
		print2dBooleanArray(arr);
	}

	public static <T> void printCol(Collection<T> col) {
		for(T t : col) {
			System.out.print(t + ", "); 
		}
		System.out.println();
	}

	public static <T> void printArray(T[] arr) {
		if (arr == null) {
			System.out.println(arr);
			return; 
		}

		for(T t : arr) {
			System.out.print(t + ", "); 
		}
		System.out.println();
	}
	
	public static<T> void print2dArray(T[][] arr) {
		if (arr == null) {
			System.out.println(arr); 
			return; 
		}
		
		for (T[] row : arr) {
			System.out.println();
			for (T t : row) {
				System.out.print(t + " ");
			}
		}
		System.out.println();
	}
	
	public static void print2dBooleanArray(boolean[][] arr) {
		if (arr == null) {
			System.out.println(arr); 
			return; 
		}
		
		for (boolean[] row : arr) {
			System.out.println();
			for (boolean b : row) {
				System.out.print(b + " ");
			}
		}
		System.out.println();
	}
	
	public static void print2dIntArray(int[][] arr) {
		if (arr == null) {
			System.out.println(arr); 
			return; 
		}
		
		for (int[] row : arr) {
			System.out.println();
			for (int i : row) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
	
	public static void print2dCharArray(char[][] arr) {
		if (arr == null) {
			System.out.println(arr); 
			return; 
		}
		
		for (char[] row : arr) {
			System.out.println();
			for (char c : row) {
				System.out.print(c + " ");
			}
		}
		System.out.println();
	}
	
	
	
	

	public static void printIntArray(int[] arr) {
		if (arr == null) {
			System.out.println(arr);
			return; 
		}

		for(int i : arr) {
			System.out.print(i + ", "); 
		}
		System.out.println();
	}

	public static <K,V> void printMap(Map<K,V> map) {
		for (K k : map.keySet()) {
			System.out.println(k + " : " + map.get(k));
		}
	}

}

