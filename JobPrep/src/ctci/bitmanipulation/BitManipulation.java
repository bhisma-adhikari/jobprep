package ctci.bitmanipulation;

import java.util.Map;
import java.util.HashMap;

public class BitManipulation {
	public static void main(String[] args) {
//		printPrevNext(13948);
		int i = 0xaaaaaaaa;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
		for (i = 0; i < 32; i++) {
			System.out.print("-");
		}

	}

	// prints the prev and next integer with same number of 1s in its binary form
	public static void printPrevNext(int n) {
		// build map
		// key : bit index (0 - 31)
		// value: bit value 0/1

		int num = n;
		Map<Integer, Integer> map = new HashMap<>();
		int LIMIT = Integer.BYTES * 8 - 1; // = 31 (exclude the sign bit)
		for (int i = 0; i < LIMIT; i++) {
			int bit = (num & 1) == 1 ? 1 : 0;
			map.put(i, bit);
			num = num >> 1;
		}
//		map.entrySet().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));

		// prev
		// position of 1 to shift to get prev number
		int from = -1;
		int to = -1;
		for (int i = 0; i < LIMIT; i++) {
			if (map.get(i) == 0) {
				to = i;
			}
			if (map.get(i) == 1) {
				from = i;
				if (to != -1) {
					break;
				}
			}
		}

		if (from == -1 || to == -1) {
			System.out.println("Prev number not  possible");
			return;
		}

		int prev = clearBit(n,  from);
		prev = setBit(prev, to); 
		
		// next 
		from = -1; 
		to = -1; 
		for (int i = 0; i < LIMIT; i++) {
			if (map.get(i) == 1) {
				from = i; 
			}
			if (map.get(i) == 0) {
				to = i;
				if (from != -1) {
					break; 
				}
			}
		}
		
		
		if (from == -1 || to == -1) {
			System.out.println("Next number not  possible");
			return;
		}

		int next = clearBit(n,  from);
		next = setBit(next, to); 
		
		
		
		
		System.out.println("Given number : " + Integer.toBinaryString(n));
		System.out.println("Prev  number : " + Integer.toBinaryString(prev));
		System.out.println("Next  number : " + Integer.toBinaryString(next));
		
		
		
	
	
	}

	// sets index bit to 1
	public static int setBit(int n, int index) {
		return n | (1 << index);
	}

	// sets index bit to 0
	public static int clearBit(int n, int index) {
		return n & ~(1 << index);
	}

	public static String printBinary(double num) {
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(num)));
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while (num > 0) {
			System.out.println(num);
			if (binary.length() >= 32) {
				return "ERROR";
			}
			double r = num * 2;
			if (r >= 1) {
				binary.append(1);
				num = r - 1;
			} else {
				binary.append(0);
				num = r;
			}
			System.out.println(binary);
		}
		return binary.toString();
	}

}
