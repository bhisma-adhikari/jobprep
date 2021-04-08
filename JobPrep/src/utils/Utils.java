package utils; 

import java.util.*;


public class Utils {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(); 
		list.add(1);
		list.add(2); 
		Utils.printCol(list);
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

