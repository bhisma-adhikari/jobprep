package ctci.bitmanipulation;

public class FlipBitToWin {
    public static void main(String[] args) {
        System.out.println(flipBitToWin(1775));
    }

    private static int flipBitToWin(int num) {
        int max = 1;
        for (int i = 0; i <=31; i++) {
            if (findNthBit(num, i) == 0) {
                int len = maxLocal(num, i);
                if (len > max) {
                    max = len;
                }
            }
        }
        return max;

    }

    private static int maxLocal(int num, int pos) {
        int max = 1;
        for (int i = pos - 1; i >=0; i--) {
            if (findNthBit(num, i) == 0) {
                break;
            }
            max++;
        }
        for (int i = pos + 1; i <=31; i++) {
            if (findNthBit(num, i) == 0) {
                break;
            }
            max++;
        }
        return max;

    }

    // returns 1 or 0
    // n must be in range [0,31]
    private static int findNthBit(int num, int n) {
        return (num >> n) & 1;
    }

}
