package main;

import java.util.*; 

public class Main {
	public static void main(String[] args) {
		List<Integer> l1 = new LinkedList<>(Arrays.asList(1,5,2,8,4));
		List<Integer> l2 = new LinkedList<>(Arrays.asList(6,2,9,1,5)); 
		System.out.println(new Solution().common(l1, l2)); 
	}
	
			
}

class Solution<T extends Comparable>{
	public List<T> common(List<T> l1, List<T> l2) {
		List<T> al1 = new ArrayList<>(l1);
		List<T> al2 = new ArrayList<>(l2); 
		
		Collections.sort(al1);
		Collections.sort(al2);
		
	
		List<T> common = new LinkedList<>(); 
		
		int i = 0; 
		int j = 0; 
		
		while (i < al1.size() && j < al2.size()) {
			if (al1.get(i).compareTo(al2.get(j)) < 0) {  // al1[i] < al2[j]
				i++;
			} else if (al1.get(i).compareTo(al2.get(j)) > 0) {
				j++;
			} else {
				common.add(al1.get(i));
				i++; 
				j++;
			}
		}
		
		return common; 
	}

	
}
    