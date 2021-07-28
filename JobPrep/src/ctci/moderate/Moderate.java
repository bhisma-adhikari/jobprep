package ctci.moderate;

import java.util.*;
import utils.*;

public class Moderate {
	public static void main(String[] args) {
//		System.out.println(decimalToBaseN(26, 3));
//		System.out.println(baseNToDecimal(222, 3));
//		odometer(3, 3);
//		List<String> nos = odometer1(3, 3); 
//		for (String s : nos) {
//			System.out.println(s);
//		}

//		System.out.println(countFactZeros(625));

//		System.out.println(sign(11));
//		System.out.println(flip(11));

//		System.out.println(numToWord(10012340));

//		System.out.println(bits(-1));

//		System.out.println(twoComp(-10));

		int[] birthYears = new int[] { 1, 10, 10, 12, 13, 20, 23, 75, 83, 90 };
		int[] deathYears = new int[] { 15, 72, 82, 90, 94, 98, 98, 98, 98, 99 };
		System.out.println(maxAliveYear(birthYears, deathYears, 1, 100));


	}

	public static int maxAliveYear(int[] birthYears, int[] deathYears, int minYear, int maxYear) {
		int maxAliveYear = birthYears[0];
		int maxAlive = 0;
		int currAlive = 0;
		int bp = 0; // pointer to birthYears
		int dp = 0; // pointer to deathYears
		while (bp < birthYears.length) {
			if (birthYears[bp] <= deathYears[dp]) {
				currAlive++;
				if (currAlive > maxAlive) {
					maxAlive = currAlive;
					maxAliveYear = birthYears[bp];
				}
				bp++;
			} else {
				currAlive--;
				dp++;
			}
		}
		System.out.println("max alive: " + maxAlive);
		return maxAliveYear;

	}

	public static int getCountYear(int[] years, int beginIndex) {
		int year = years[beginIndex];
		int count = 0;
		int index = beginIndex;
		while (years[index] == year && index < years.length) {
			count++;
			index++;
		}
		return count;
	}

	public static String bits(int x) {
		String bits = "";
		for (int i = 0; i < 32; i++) {
			char bit = (x & 1) == 1 ? '1' : '0';
			bits = bit + bits;
			x = x >> 1;
		}
		System.out.println(x);
		return bits;
	}

	public static int twoComp(int x) {
		int oneComp = x ^ -1;
		int twoComp = oneComp + 1;
		return twoComp;
	}

	public static String digitToWord(int digit) {
		if (digit == 1)
			return "one";
		if (digit == 2)
			return "two";
		if (digit == 3)
			return "three";
		if (digit == 4)
			return "four";
		if (digit == 5)
			return "five";
		if (digit == 6)
			return "six";
		if (digit == 7)
			return "seven";
		if (digit == 8)
			return "eight";
		if (digit == 9)
			return "nine";
		return null;
	}

	public static String digitToWordTens(int digit) {
		if (digit == 2)
			return "twenty";
		if (digit == 3)
			return "thirty";
		if (digit == 4)
			return "forty";
		if (digit == 5)
			return "fifty";
		if (digit == 6)
			return "sixty";
		if (digit == 7)
			return "seventy";
		if (digit == 8)
			return "eighty";
		if (digit == 9)
			return "ninety";
		return null;
	}

	public static String numToWordTensOnes(int num) {
		if (num == 10)
			return "ten";
		if (num == 11)
			return "eleven";
		if (num == 12)
			return "twelve";
		if (num == 13)
			return "thirteen";
		if (num == 14)
			return "fourteen";
		if (num == 15)
			return "fifteen";
		if (num == 16)
			return "sixteen";
		if (num == 17)
			return "seventeen";
		if (num == 18)
			return "eighteen";
		if (num == 19)
			return "nineteen";
		return null;
	}

	// number must be 3 digits (or less)
	public static String threeDigitNumToWord(int num) {
		int ones = num % 10;
		num = num / 10;
		int tens = num % 10;
		num = num / 10;
		int hunds = num % 10;

		String ans = "";
		if (hunds > 0) {
			ans += digitToWord(hunds) + " hundred ";
		}

		if (tens > 1) {
			ans += digitToWordTens(tens) + " ";
			if (ones > 0) {
				ans += digitToWord(ones) + " ";
			}
		} else if (tens == 1) {
			int tensOnes = tens * 10 + ones;
			ans += numToWordTensOnes(tensOnes) + " ";
		} else { // tens == 0
			if (ones > 0) {
				ans += digitToWord(ones) + " ";
			}
		}
		return ans;
	}

	public static String numToWord(int num) {
		if (num == 0)
			return "zero";
		String[] units = new String[] { "", "thousand", "million", "billion", "trillion" };
		int index = 0;
		String ans = "";
		while (num > 0) {
			int last3 = num % 1000;

			ans = threeDigitNumToWord(last3) + units[index++] + " " + ans;

			num /= 1000;
		}
		return ans;
	}

	// flips 1 to 0 and 0 to 1
	public static int flip(int bit) {
		return 1 ^ bit;
	}

	// returns 1 if a is +ve, else returns 0
	public static int sign(int a) {
		return flip((a >> 31) & 1);
	}

	public static int countFactZeros(int i) {
		int count = 0;
		int divider = 5;
		while (divider <= i) {
			count += i / divider;
			divider *= 5;
		}
		return count;
	}

	public static int convertBoardToInt(int[][] board) {
		int sum = 0;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				sum = sum * 3 + board[r][c];
			}
		}
		return sum;
	}

	// eg: 26, 3 --> 222
	public static int decimalToBaseN(int decimalNumber, int base) {
		Stack<Integer> digits = new Stack<>();
		while (decimalNumber > 0) {
			digits.push(decimalNumber % base);
			decimalNumber /= base;
		}
		int ans = 0;
		while (!digits.isEmpty()) {
			ans = ans * 10 + digits.pop();
		}
		return ans;
	}

	// eg: 222, 3 --> 26
	public static int baseNToDecimal(int baseNNumber, int base) {
		List<Integer> digits = new ArrayList<>();
		while (baseNNumber > 0) {
			digits.add(baseNNumber % 10);
			baseNNumber /= 10;
		}
		double ans = 0;
		for (int i = 0; i < digits.size(); i++) {
			ans = ans + digits.get(i) * Math.pow(base, i);
		}
		return (int) ans;
	}

	public static void odometer(int nDigits, int base) {
		int maxBaseN = 0;
		for (int i = 0; i < nDigits; i++) {
			maxBaseN = maxBaseN * 10 + (base - 1);
		}
		int maxDec = baseNToDecimal(maxBaseN, base);
		String baseNMaxDec = ((Integer) decimalToBaseN(maxDec, base)).toString();
		int len = baseNMaxDec.length();
		for (int i = 0; i <= maxDec; i++) {
			String baseNNumberStr = ((Integer) decimalToBaseN(i, base)).toString();
			if (baseNNumberStr.length() < len) {
				int diff = len - baseNNumberStr.length();
				for (int j = 0; j < diff; j++) {
					baseNNumberStr = "0" + baseNNumberStr;
				}
			}
			System.out.println(baseNNumberStr);

		}
	}

	public static List<String> odometer1(int nDigits, int base) {
		List<String> nos = new ArrayList<>();
		if (nDigits == 1) {
			for (int i = 0; i < base; i++) {
				nos.add(((Integer) i).toString());
			}
			return nos;
		}

		List<String> nosRec = odometer1(nDigits - 1, base);

		for (int i = 0; i < base; i++) {
			for (String s : nosRec) {
				String no = i + s;
				nos.add(no);
			}
		}
		return nos;
	}

}
