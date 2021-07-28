//https://leetcode.com/problems/combination-sum/

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> combinations = combinationSum(candidates, target);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> candidatesList = new ArrayList<>();
        for (int c : candidates)
            candidatesList.add(c);

        Collections.sort(candidatesList);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSumHelper(ans, candidatesList, target, 0, temp);
        return ans;

    }

    private static void combinationSumHelper(List<List<Integer>> ans, List<Integer> candidates, int target, int index, List<Integer> temp) {
        // recursive base case
        if (target == 0) {
            // Adding deep copy of list to ans
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.size(); i++) {
            if (candidates.get(i) <= target) {
                // adding element which can contribute to sum
                temp.add(candidates.get(i));
                combinationSumHelper(ans, candidates, target - candidates.get(i), i, temp);  // we would pass i+1 instead of i if repeating same number was not allowed
                // removing element from list (backtracking)
                temp.remove(candidates.get(i));
            }
            // the else block improves efficiency of code by not checking the 'if' condition for further elements.
            // The code produces correct results without this block too.
            else {
                break;
            }
        }
    }
}
