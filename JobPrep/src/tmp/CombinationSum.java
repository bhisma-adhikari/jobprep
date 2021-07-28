//https://leetcode.com/problems/combination-sum/

package tmp;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = combinationSum(candidates, target);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSumHelper(ans, candidates, target, 0, temp);
        return ans;

    }

    private static void combinationSumHelper(List<List<Integer>> ans, int[] candidates, int target, int index, List<Integer> temp) {
        if (target == 0){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.length; i++){
            if (candidates[i] <= target) {
                temp.add(candidates[i]);
                combinationSumHelper(ans, candidates, target - candidates[i], i, temp);
                // backtrack
                // casting to Integer is necessary to ensure temp.remove(Object) is called, rather than temp.remove(index)
                temp.remove((Integer)candidates[i]);
            }
        }
    }
}
