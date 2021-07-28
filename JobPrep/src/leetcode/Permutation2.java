//https://leetcode.com/problems/permutations-ii/

package leetcode;

import java.util.*;

public class Permutation2 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> perms = new Solution3().permuteUnique(nums);
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


class Solution3 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num)) {
                counter.put(num, 0);
            }
            counter.put(num, counter.get(num) + 1);
        }
        LinkedList<Integer> currPerm = new LinkedList<>();
        backtrack(res, counter, currPerm, nums.length);
        return res;

    }

    private void backtrack(List<List<Integer>> res, Map<Integer, Integer> counter, LinkedList<Integer> currPerm, int lenNums) {
        // recursive base case
        if (currPerm.size() == lenNums) {
            // must deep clone because currPerm will be overridden by backtracking
            res.add(new ArrayList<>(currPerm));
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (value > 0) {
                currPerm.add(key);
                counter.put(key, value - 1);
                backtrack(res, counter, currPerm, lenNums);

                // backtrack
                currPerm.removeLast();
                counter.put(key, value);
            }
        }
    }
}



















class Solution2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        // count the occurrence of each number
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num))
                counter.put(num, 0);
            counter.put(num, counter.get(num) + 1);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        this.backtrack(comb, nums.length, counter, results);
        return results;
    }

    protected void backtrack(LinkedList<Integer> comb, Integer N, HashMap<Integer, Integer> counter, List<List<Integer>> results) {

        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0)
                continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);

            // continue the exploration
            backtrack(comb, N, counter, results);

            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }
}

class Solution1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        // special case
        if (nums.length == 0) {
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

        for (List<Integer> permTail : permsTail) {
            List<List<Integer>> permsWithHead = insertIntoPerm(head, permTail);
//            perms.addAll(permsWithHead);

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
