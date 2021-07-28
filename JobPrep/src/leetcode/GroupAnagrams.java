package leetcode;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
            String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};



//        System.out.println(new Solution120().areAnagrams("bat", "tac"));
    }
}



class Solution120 {
    public List<List<String>> groupAnagrams(String[] strs) {
        boolean[] used = new boolean[strs.length];

        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            if (!used[i]) {
                used[i] = true;
                List<String> entry = new ArrayList<>();
                entry.add(strs[i]);
                for (int j = i+1; j < strs.length; j++) {
                    if (!used[j] && areAnagrams(strs[i], strs[j])) {
                        used[j] = true;
                        entry.add(strs[j]);
                    }
                }
                res.add(entry);
            }
        }
        return res;
    }

//    private boolean areAnagrams(String s1, String s2) {
//        if (s1.length() != s2.length())
//            return false;
//
//        char[] c1 = s1.toCharArray();
//        Arrays.sort(c1);
//        s1 = new String(c1);
//
//        char[] c2 = s2.toCharArray();
//        Arrays.sort(c2);
//        s2 = new String(c2);
//
//        return s1.equals(s2);
//    }


    public boolean areAnagrams(String s1, String s2) {

        if (s1.length() != s2.length()){
            return false;
        }
        Map<Character, Integer> histogram = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if (!histogram.containsKey(c)) {
                histogram.put(c, 0);
            }
            histogram.put(c, histogram.get(c) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);
            if (!histogram.containsKey(c)){
                return false;
            }
            if (histogram.get(c) == 0) {
                return false;
            }
            histogram.put(c, histogram.get(c) - 1);
        }
        return true;
    }
}