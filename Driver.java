import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String [] args) {

        System.out.println("starting");
        double [] c1 = {4, 5, 6};
        int [] c2 = {3, 2, 1};
        System.out.println("Defining p1\n");
        Polynomial p1 = new Polynomial(c1, c2);

        double [] a1 = {6, 4, 1};
        int [] a2 = {1, 4, 3};
        System.out.println("Defining p2\n");
        Polynomial p2 = new Polynomial(a1, a2);
        
        System.out.println("Adding\n");
        Polynomial a = p1.add(p2);
        a.printy(a);
        
        Polynomial s = p1.multiply(p2);
        s.printy(s);

    }
}
