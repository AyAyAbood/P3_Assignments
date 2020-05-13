package p3hw3part1;

import java.util.Collections;
import java.util.LinkedList;

public class Q1 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < 30; i++) {
            int num = (int) (Math.random() * 100);
            if (list.contains(num)) {
                i--;
            } else {
                list.add(num);
            }
        }
        Collections.sort(list);
        System.out.println("LinkedList elements in ascending order: " + list);
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            sum += list.get(i);
        }
        System.out.println("the sum of the elements is: " + sum);
        System.out.println("the average of the elements is: " + sum / 30);
    }
}
