package p3hw1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class P3HW1 {

    public static void main(String[] args) {
        st S[] = new st[5]; 
        S[0] = new ITStudent(15, 12, 14);
        S[1] = new ITStudent(50, 40, 10);
        S[2] = new ITStudent(40, 30, 30);
        S[3] = new ArtStudent(40, 80, 40);
        S[4] = new ArtStudent(50, 60, 50);
        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            System.out.println("student" + (i + 1) + ": " + S[i].getGrade());
        }

        try {
            FileOutputStream fos = new FileOutputStream("D:\\uni\\Sem4\\Programming (3)\\HW\\hw1\\bruh.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(S);
            oos.close();

            FileInputStream fis = new FileInputStream("D:\\uni\\Sem4\\Programming (3)\\HW\\hw1\\bruh.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            st[] NS = (st[]) ois.readObject();
            ois.close();
            System.out.println(">>>>>>>>>>>>>>>>>Reading the file<<<<<<<<<<<<<<<<<<<<");
            for (int i = 0; i < NS.length; i++) {
                System.out.println("student" + (i + 1) + ": " + NS[i].getGrade());
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
