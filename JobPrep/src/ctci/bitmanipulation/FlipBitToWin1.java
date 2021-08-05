package ctci.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class FlipBitToWin1 {
    public static void main(String[] args) {
        System.out.println(flipBitToWin(1775));
    }

    private static int flipBitToWin(int num) {
        List<Integer> continuous1s = new ArrayList<>();
        int curr1count = 0;  // current continuous 1 count
        for (int i = 0; i < 32; i++) {
            if (isNthBit1(num, i)) {
                curr1count += 1;
            } else {
                continuous1s.add(curr1count);
                curr1count = 0;
            }
        }

//        System.out.println(continuous1s);

        int max = 0;
        for (int i = 0; i < continuous1s.size() - 1; i++) {
            int curr = continuous1s.get(i) + continuous1s.get(i+1) + 1;
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }

    // n must be in range [0, 31]
    private static boolean isNthBit1(int num, int n){
        return ((num >> n) & 1) == 1;
    }
}
