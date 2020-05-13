package p3hw3part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        File f = new File("bruh.txt");
        String MyTxt = "";
        Scanner s = null;
        try {
            s = new Scanner(f);

        } catch (FileNotFoundException ex) {
            System.out.println("Something went wrong....");
            System.exit(0);
        }
        while (s.hasNextLine()) {
            MyTxt += s.nextLine();
        }
        String[] txtarray = MyTxt.split(" ");
        Map<String, Integer> MyMap = new HashMap<>();
        for (int i = 0; i < txtarray.length; i++) {
            Integer count = MyMap.get(txtarray[i]);
            if (count != null) {
                MyMap.put(txtarray[i], count + 1);
            } else {
                MyMap.put(txtarray[i], 1);
            }
        }

        System.out.println("The words and number of their occurrences: ");
        for (String key : MyMap.keySet()) {
            System.out.println(key + " - " + MyMap.get(key));
        }

        char[] CharArray = MyTxt.toCharArray();
        Map<Character, Integer> MyMap2 = new HashMap<>();
        for (int i = 0; i < CharArray.length; i++) {
            Integer count = MyMap2.get(CharArray[i]);
            if (count != null) {
                MyMap2.put(CharArray[i], count + 1);
            } else {
                MyMap2.put(CharArray[i], 1);
            }
        }

        System.out.println("The letters and number of their occurrences: ");
        for (char key : MyMap2.keySet()) {
            if (key != ' ') {
                System.out.println(key + " - " + MyMap2.get(key));

            }
        }
    }
}
