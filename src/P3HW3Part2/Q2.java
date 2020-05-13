package p3hw3part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        CharactersOccurences<File> myF = (file) -> {
            File f = file;
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

            System.out.println("The characters and number of their occurrences: ");
            for (char key : MyMap2.keySet()) {
                System.out.println(key + " - " + MyMap2.get(key));
            }
            ;
        };

        File f = new File("build.xml");
        myF.calc(f);
    }

    private interface CharactersOccurences<T> {

        void calc(T x);
    }
}
