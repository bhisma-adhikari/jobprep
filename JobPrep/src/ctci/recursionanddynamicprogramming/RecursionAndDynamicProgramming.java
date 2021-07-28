package ctci.recursionanddynamicprogramming;

import java.util.*;

import utils.Utils;

public class RecursionAndDynamicProgramming {
	public static void main(String[] args) {
//		int nRows = 5;
//		int nCols = 7;
//		int[][] maze = new int[5][7]; // value 1 means restricted, 0 means accessible
//		maze[0][3] = 1;
//		maze[1][2] = 1;
//		maze[2][1] = 1;
//		maze[3][4] = 1; 
//		Utils.print2dIntArray(maze);
//
//		List<List<Integer>> path = findPath(maze);
//		System.out.println(path);
//		visualizePath(path, maze);

//		Set<Integer> set = new HashSet<>();
//		set.add(1);
//		set.add(2); 
//		set.add(3); 
//		
//		Set<Set<Integer>> ps = powerSet_1(set); 
//		for (Set<Integer> s : ps) {
//			System.out.println(s);
//		}

//		List<Integer> set = new ArrayList<>(Arrays.asList(1,2,3));
//		List<List<Integer>> ps = powerSet_2(set); 
//		System.out.println(ps);

//		System.out.println(recursiveMultiply(5,  7));

//		System.out.println(decomposeToPowersOf2(5));

//		System.out.println(multiply(5, 7));

//		Stack<Integer> first = new Stack<>();
//		Stack<Integer> second = new Stack<>();
//		Stack<Integer> third = new Stack<>();
//
//		first.push(3);
//		first.push(2);
//		first.push(1);
//
//		System.out.println(first);
//		System.out.println(second);
//		System.out.println(third);
//
//		towerOfHanoi(first, second, third);
//		
////		toh(first, second, third); 
//
//		System.out.println();
//		System.out.println(first);
//		System.out.println(second);
//		System.out.println(third);

//		System.out.println(insert("abc", "xy", 3));

//		System.out.println(permutations("abc"));
//		System.out.println(permutations_1("abc"));

//		System.out.println(getCharCount("hello"));

//		System.out.println(perms("aaba"));
//
//		System.out.println(validParens(3));

//		int nRows = 5;
//		int nCols = 7;
//		char[][] screen = new char[5][7]; 
//		for (char[] row : screen) {
//			Arrays.fill(row, '-');
//		}
//
//		screen[0][3] = '*';
//		screen[1][2] = '*';
//		screen[2][1] = '*';
//		screen[3][4] = '*';
//		screen[2][2] = '*';
//		Utils.print2dCharArray(screen);
//		fillPaint(screen, 1, 2, '#');
//		Utils.print2dCharArray(screen);

//		System.out.println(makeChange(10));

//		System.out.println(placeQueens(8));

//		List<Integer> list = new ArrayList<>();
//		list.add(1); 
//		list.add(2); 
//		list.add(3); 
//		list.add(4);
//		System.out.println(reverseList(list));

//		List<Boxx> boxes = new ArrayList<>(); 
//		
//		boxes.add(new Boxx(4,5,6)); 
//		boxes.add(new Boxx(1,2,3));
//		boxes.add(new Boxx(7,8,9)); 
//		
//		Collections.sort(boxes, new BoxComparator());
//		System.out.println(boxes.get(0).width);

//		System.out.println(countEval("1^0|0|1", false));
//		System.out.println(countEval("0&0&0&1^1|0", true));



	}



	public static int countEval(String expr, boolean result) {
		if (expr.length() == 0)
			return 0;

		if (expr.length() == 1)
			return stringToBool(expr) == result ? 1 : 0;

		int ways = 0;
		for (int i = 1; i < expr.length(); i += 2) {
			Character sign = expr.charAt(i);
			String leftExpr = expr.substring(0, i);
			String rightExpr = expr.substring(i + 1);

			int leftTrue = countEval(leftExpr, true);
			int leftFalse = countEval(leftExpr, false);
			int rightTrue = countEval(rightExpr, true);
			int rightFalse = countEval(rightExpr, false);
			int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

			int totalTrue = 0;
			if (sign.equals('&')) {
				totalTrue = leftTrue * rightTrue;
			} else if (sign.equals('|')) {
				totalTrue = leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
			} else if (sign.equals('^')) {
				totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
			}
			int totalFalse = total - totalTrue;
			int subWays = result ? totalTrue : totalFalse;
			ways += subWays;
		}
		return ways;

	}

	private static boolean stringToBool(String s) {
		return s.equals("1");
	}

	public static int nQueens(int n) {
		return 0;
	}

	// returns true if any two queens conflict
	private static boolean queenConflict(List<Square> squares) {
		for (int i = 0; i < squares.size(); i++) {
			for (int j = i + 1; j < squares.size(); j++) {
				Square sq1 = squares.get(i);
				Square sq2 = squares.get(j);
				if (queenConflict(sq1, sq2))
					return true;
			}
		}
		return false;
	}

	// returns true if the two queens conflict
	private static boolean queenConflict(Square sq1, Square sq2) {
		// row conflict
		if (sq1.row == sq2.row)
			return true;
		// col conflict
		if (sq1.col == sq2.col)
			return true;
		// diagonal conflict
		int rowDiff = Math.abs(sq1.row - sq2.row);
		int colDiff = Math.abs(sq2.col - sq2.col);
		if (rowDiff == colDiff)
			return true;

		return false;
	}

	private class Square {
		int row;
		int col;

		public Square(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static int placeQueens(int gridSize) {
		Integer[] columns = new Integer[gridSize];
		List<Integer[]> results = new ArrayList<>();
		placeQueens(gridSize, 0, columns, results);
		return results.size();
	}

	private static void placeQueens(int gridSize, int row, Integer[] columns, List<Integer[]> results) {
		if (row == gridSize) {
			results.add(columns.clone());
		} else {
			for (int col = 0; col < gridSize; col++) {
				if (checkValid(columns, row, col)) {
					columns[row] = col;
					placeQueens(gridSize, row + 1, columns, results);
				}
			}
		}
	}

	private static boolean checkValid(Integer[] columns, int row1, int column1) {
		for (int row2 = 0; row2 < row1; row2++) {
			int column2 = columns[row2];
			if (column1 == column2) {
				return false;
			}
			int columnDistance = Math.abs(column2 - column1);
			int rowDistance = row1 - row2;
			if (columnDistance == rowDistance) {
				return false;
			}
		}
		return true;
	}

	public static int makeChange(int amount) {
		int[] denoms = { 25, 10, 5, 1 };
		return makeChange(amount, denoms, 0);
	}

	private static int makeChange(int amount, int[] denoms, int index) {
		if (index == denoms.length - 1) { // base case -- make change using only lowest denom
			return 1;
		}

		int ways = 0;
		int denom = denoms[index];
		for (int i = 0; i * denom <= amount; i++) {
			int amountRemaining = amount - i * denom;
			ways += makeChange(amountRemaining, denoms, index + 1);
		}
		return ways;
	}

	public static void fillPaint(char[][] screen, int row, int col, char color) {
		boolean[][] visited = new boolean[screen.length][screen[0].length];
		fillPaint(screen, row, col, color, screen[row][col], visited);
	}

	private static void fillPaint(char[][] screen, int row, int col, char newColor, char orgColor,
			boolean[][] visited) {

		if (!visited[row][col]) {
			visited[row][col] = true;
			if (screen[row][col] == orgColor) {
				screen[row][col] = newColor;
				Pixel pixel = new Pixel(row, col);
				for (Pixel p : getChildren(pixel, screen.length, screen[0].length)) {
					fillPaint(screen, p.row, p.col, newColor, orgColor, visited);
				}
			}
		}

	}

	private static List<Pixel> getChildren(Pixel pixel, int nRows, int nCols) {
		List<Pixel> children = new ArrayList<>();
		// left
		if (pixel.row > 0)
			children.add(new Pixel(pixel.row - 1, pixel.col));
		// right
		if (pixel.row <= nRows - 2)
			children.add(new Pixel(pixel.row + 1, pixel.col));
		// top
		if (pixel.col > 0)
			children.add(new Pixel(pixel.row, pixel.col - 1));
		// bottom
		if (pixel.col <= nCols - 2)
			children.add(new Pixel(pixel.row, pixel.col + 1));
		return children;
	}

	// returns a list of valid parentheses containing n () pairs
	public static List<String> validParens(int n) {
		List<String> validParens = new ArrayList<>();
		validParens(n, "", 0, 0, validParens);
		return validParens;
	}

	private static void validParens(int n, String prefix, int s, int e, List<String> parens) {
		// s: no of '(' in prefix
		// e: no of ')' in prefix

		if (e == n) { // base case
			parens.add(prefix);
			return;
		}

		if (s > e) {
			validParens(n, prefix + ")", s, e + 1, parens);
			if (s < n) {
				validParens(n, prefix + "(", s + 1, e, parens);
			}
		} else { // s == e
			validParens(n, prefix + "(", s + 1, e, parens);
		}

	}

	// string can have duplicates
	public static List<String> perms(String s) {
		Map<Character, Integer> charCount = getCharCount(s);
		return perms(charCount);

	}

	private static List<String> perms(Map<Character, Integer> charCount) {
		List<String> perms = new ArrayList<>();
		Character prefix = null;
		for (Character c : charCount.keySet()) {
			if (charCount.get(c) > 0) {
				prefix = c;
				break;
			}
		}
		if (prefix == null) { // base case -- all char have 0 count i.e. empty string
			perms.add("");
			return perms;
		}

		for (Character c : charCount.keySet()) {
			if (charCount.get(c) > 0) {
				Map<Character, Integer> charCountNew = new HashMap<>(charCount);
				charCountNew.put(c, charCount.get(c) - 1);
				List<String> permsRec = perms(charCountNew);
				perms.addAll(prepend(c, permsRec));
			}
		}
		return perms;
	}

	private static List<String> prepend(char prefix, List<String> strings) {
		List<String> res = new ArrayList<>();
		for (String s : strings) {
			res.add(prefix + s);
		}
		return res;
	}

	private static Map<Character, Integer> getCharCount(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}
		return map;
	}

	// string cannot have duplicates
	public static List<String> permutations_1(String s) {
		List<String> perms = new ArrayList<>();
		if (s == "")
			return perms;
		perms.add(s.substring(0, 1)); // first char
		permutations_1(s.substring(1), perms);
		return perms;
	}

	private static void permutations_1(String s, List<String> perms) {
		if (s.length() == 0)
			return;

		System.out.println(s + ":" + s.length());
		List<String> prevPerms = new ArrayList<>(perms);
		perms.removeAll(perms);

		for (String prevPerm : prevPerms) {
			for (int i = 0; i <= prevPerm.length(); i++) {
				System.out.println(i);
				String perm = insert(prevPerm, s.substring(0, 1), i);
				perms.add(perm);
			}
		}
		permutations_1(s.substring(1), perms);
	}

	// string cannot have duplicates
	public static List<String> permutations(String s) {
		List<String> perms = new ArrayList<>();
		if (s.length() == 1) {
			perms.add(s);
			return perms;
		}
		String firstChar = s.substring(0, 1);
		List<String> permsRec = permutations(s.substring(1));
		for (String permRec : permsRec) {
			for (int i = 0; i <= permRec.length(); i++) {
				String perm = insert(permRec, firstChar, i);
				perms.add(perm);
			}
		}
		return perms;
	}

	private static String insert(String s, String insertedString, int index) {
		return s.substring(0, index) + insertedString + s.substring(index);
	}

	// moves elements from 'from' stack to 'to' stack.
	public static void towerOfHanoi(Stack<Integer> from, Stack<Integer> to, Stack<Integer> buffer) {
		towerOfHanoi(from, to, buffer, from.size());
	}

	private static void towerOfHanoi(Stack<Integer> from, Stack<Integer> to, Stack<Integer> buffer, int n) {
		if (n == 0) {
			return;
		}

		towerOfHanoi(from, buffer, to, n - 1);
		to.push(from.pop());
		towerOfHanoi(buffer, to, from, n - 1);

	}

	public static void toh(Stack<Integer> from, Stack<Integer> to, Stack<Integer> buffer) {
		tohMoveDisks(from.size(), from, to, buffer);
	}

	private static void tohMoveDisks(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> buffer) {
		if (n == 0)
			return;

		tohMoveDisks(n - 1, from, buffer, to);
		tohMoveTop(from, to);
		tohMoveDisks(n - 1, buffer, to, from);
	}

	private static void tohMoveTop(Stack<Integer> from, Stack<Integer> to) {
		to.push(from.pop());
	}

	public static int multiply(int x, int y) {
		int product = 0;
		for (Integer pow : decomposeToPowersOf2(x)) {
			product += y << pow;
		}
		return product;
	}

	private static List<Integer> decomposeToPowersOf2(int n) {
		// Examples:
		// 23 --> [0, 1, 2, 4]
		// 5 --> [0, 2]
		List<Integer> res = new ArrayList<>();
		for (int i = n, pow = 0; i > 0; i >>= 1, pow++) {
			if ((i & 1) == 1) {
				res.add(pow);
			}
		}
		return res;
	}

	// both x and y must be +ve
	public static int recursiveMultiply(int x, int y) {
		if (y == 1) {
			return x;
		}
		return x + recursiveMultiply(x, y - 1);
	}

	// uses combinatorics
	public static List<List<Integer>> powerSet_2(List<Integer> set) {
		List<List<Integer>> ps = new ArrayList<>();
		int n = 1 << set.size(); // no of subsets in powerset = 2^n
		for (int i = 0; i < n; i++) {
			ps.add(int2set(i, set));
		}
		return ps;
	}

	private static List<Integer> int2set(int i, List<Integer> set) {
		List<Integer> s = new ArrayList<>();
		for (int j = i, index = 0; j > 0; j >>= 1, index++) {
			if ((j & 1) == 1) {
				s.add(set.get(index));
			}
		}
		return s;
	}

	// uses recursion
	public static Set<Set<Integer>> powerSet(Set<Integer> set) {
		// base case
		if (set.isEmpty()) {
			// the power set has exactly 1 entry which is an empty set
			Set<Set<Integer>> ps = new HashSet<>();
			ps.add(new HashSet<Integer>());
			return ps;
		}

		Integer elem = set.iterator().next(); // random element from the set
		set.remove(elem);
		Set<Set<Integer>> ps = powerSet(set);

		Set<Set<Integer>> newSubsets = new HashSet<>();
		newSubsets.addAll(ps);
		for (Set<Integer> s : ps) {
			Set<Integer> newSubset = new HashSet<>(s);
			newSubset.add(elem);
			newSubsets.add(newSubset);
		}
		return newSubsets;
	}

	// updates powerSet recursively
	private static void powerSet_1(Set<Integer> restElems, Set<Set<Integer>> powerSet) {
		// if restElems is empty, that is the recursive base case
		// which we don't code (in an if statement) because this
		// function has a return type of void.

		if (!restElems.isEmpty()) {
			Integer elem = restElems.iterator().next();
			restElems.remove(elem);

			// make a deep copy of passed powerSet to iterate
			// without concurrent modification exception
			Set<Set<Integer>> ps = new HashSet<>(powerSet);
			for (Set<Integer> s : ps) {
				Set<Integer> set = new HashSet<>(s); // new entry to powerSet
				set.add(elem);
				powerSet.add(set);
			}
			powerSet_1(restElems, powerSet);
		}
	}

	public static Set<Set<Integer>> powerSet_1(Set<Integer> set) {
		Set<Set<Integer>> ps = new HashSet<>();
		ps.add(new HashSet<Integer>());
		powerSet_1(set, ps);
		return ps;
	}

	public static void visualizePath(List<List<Integer>> path, int[][] maze) {
		int nRows = maze.length;
		int nCols = maze[0].length;
		for (int i = 0; i < nRows; i++) {
			System.out.println();
			for (int j = 0; j < nCols; j++) {
				List<Integer> cell = new ArrayList<>(Arrays.asList(i, j));
				if (path.contains(cell)) {
					System.out.print("* ");
				} else {
					System.out.print(maze[i][j] + " ");
				}
			}
		}
		System.out.println();
	}

	public static List<List<Integer>> findPath(int[][] maze) {
		return findPathFromCell(0, 0, maze);
	}

	// returns the path from this cell to the bottom-right cell
	// returns null if there exists no path from this cell to the bottom-right cell
	public static List<List<Integer>> findPathFromCell(int row, int col, int[][] maze) {
		int nRows = maze.length;
		int nCols = maze[0].length;

		List<List<Integer>> path = new LinkedList<>();

		// recursive base case
		if (row == nRows - 1 && col == nCols - 1) {
			List<Integer> cell = new ArrayList<>(Arrays.asList(row, col));
			path.add(cell);
			return path;
		}

		// try to find path from the right child, if any
		if (col <= nCols - 2 && maze[row][col + 1] != 1) {
			path = findPathFromCell(row, col + 1, maze);
			if (path != null) {
				path.add(0, new ArrayList<>(Arrays.asList(row, col)));
				return path;
			}
		}

		// try to find path from the bottom child, if any
		if (row <= nRows - 2 && maze[row + 1][col] != 1) {
			path = findPathFromCell(row + 1, col, maze);
			if (path != null) {
				path.add(0, new ArrayList<>(Arrays.asList(row, col)));
				return path;
			}
		}

		// bottom-right cell not accessible from this cell
		return null;

	}
}

class Pixel {
	public int row;
	public int col;

	public Pixel(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Boxx {
	public int width;
	public int depth;
	public int height;

	public Boxx(int width, int depth, int height) {
		this.width = width;
		this.depth = depth;
		this.height = height;
	}
}

class BoxComparator implements Comparator<Boxx> {
	// compares in REVERSE order
	@Override
	public int compare(Boxx x, Boxx y) {
		return y.width - x.width;
	}
}
