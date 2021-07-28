package main;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Test {
    public static void main(String[] args) {
        String s1 = "bat";
        String s2 = "tab";

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        s1 = new String(c1);
        s2 = new String(c2);

        System.out.println(s1.equals(s2));

    }

}