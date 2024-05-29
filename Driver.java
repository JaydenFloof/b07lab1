public class Driver {
    public static void main(String [] args) {

        System.out.println("starting");
        double [] c1 = {5, 2, 3};
        int [] c2 = {1, 2, 3};
        System.out.println("Defining p1\n");
        Polynomial p1 = new Polynomial(c1, c2);

        double [] a1 = {6, 4, 1};
        int [] a2 = {1, 3, 4};
        System.out.println("Defining p2\n");
        Polynomial p2 = new Polynomial(a1, a2);
        
        System.out.println("Adding\n");
        Polynomial s = p1.add(p2);

        s.printy(s);
    }
}
