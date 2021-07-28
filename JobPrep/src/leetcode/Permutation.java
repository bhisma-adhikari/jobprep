//https://leetcode.com/problems/permutations/

package leetcode;

import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> perms = new Solution101().permute(nums);
        for (List<Integer> perm : perms) {
            System.out.println(perm);
        }

//        List<Integer> perm = new ArrayList<>();
////        perm.add(2);
////        perm.add(3);
//
//        List<List<Integer>> perms = new Solution().insertIntoPerm(1, perm);
//        for (List<Integer> p : perms) {
//            System.out.println(p);
//        }
    }
}

class Solution101 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[] map = new boolean[nums.length];  // use array as dict (for efficiency)
        Arrays.fill(map, true);


        LinkedList<Integer> currPerm = new LinkedList<>();
        backtrack(res, map, currPerm, nums);
        return res;

    }

    private void backtrack(List<List<Integer>> res, boolean[] map, LinkedList<Integer> currPerm, int[] nums) {
        // recursive base case
        if (currPerm.size() == nums.length) {
            // must deep clone because currPerm will be overridden by backtracking
            res.add(new ArrayList<>(currPerm));
        }

        for (int i = 0; i < nums.length; i++){
            if (map[i]) {
                currPerm.add(nums[i]);
                map[i] = false;
                backtrack(res, map, currPerm, nums);
                // backtrack
                currPerm.removeLast();
                map[i] = true;
            }
        }

    }
}

class Solution100 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        // special case
        if (nums.length == 0){
            return perms;
        }

        // recursive base case
        // If we were to make nums.length = 0 as the recursive base case,
        // we could have removed this block.
        // However, then we would have to check if permsTail is empty or not
        // and write this logic somewhere else (before/after the for loop)
        if (nums.length == 1) {
            List<Integer> perm = new ArrayList<>();
            perm.add(nums[0]);
            perms.add(perm);
            return perms;
        }

        int head = nums[0];
        int[] tail = Arrays.copyOfRange(nums, 1, nums.length);
        List<List<Integer>> permsTail = permute(tail);

        for (List<Integer> permTail : permsTail){
            List<List<Integer>> permsWithHead = insertIntoPerm(head, permTail);
            perms.addAll(permsWithHead);
        }
        return perms;
    }

    // insert num to all possible points-of-insertion in perm to get new perms
    private List<List<Integer>> insertIntoPerm(Integer num, List<Integer> perm) {
        List<List<Integer>> newPerms = new ArrayList<>();
        for (int i = 0; i <= perm.size(); i++) {
            List<Integer> newPerm = new ArrayList<>(perm);
            newPerm.add(i, num);
            newPerms.add(newPerm);
        }
        return newPerms;
    }

}
