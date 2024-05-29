public class Polynomial {

    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }

    public Polynomial(double[] x, int[] y) {
        coefficients = new double[x.length];
        exponents = new int[y.length];
        System.arraycopy(x, 0, coefficients, 0, x.length);
        System.arraycopy(y, 0, exponents, 0, y.length);
    }

    public Polynomial add(Polynomial newPoly) {
        int highest_double = Math.max(newPoly.coefficients.length, coefficients.length);
        // int lowest_double = Math.min(newPoly.coefficients.length, coefficients.length);
        // int highest_int = Math.max(newPoly.exponents.length, exponents.length);
        // int lowest_int = Math.min(newPoly.exponents.length, exponents.length);
        int max_double = newPoly.coefficients.length + coefficients.length;
        int max_int = newPoly.exponents.length + exponents.length;

        double[] result_double = new double[max_double];
        int[] result_int = new int[max_int];

        // boolean check = false;
        int gap = 0;

        for(int i=0; i<highest_double; i++) {

            if(newPoly.exponents[i] == exponents[i]) {
                result_double[i+gap] = newPoly.coefficients[i] + coefficients[i];
                result_int[i+gap] = exponents[i];
                System.out.println("exponents for " + i + " are equal for " + exponents[i] + " and " + newPoly.exponents[i]);
            }

            else if(newPoly.exponents[i] < exponents[i]) {
                System.out.println("exponents for " + i + " we have " + exponents[i] + " greater than " + newPoly.exponents[i]);

                result_double[i+gap] = newPoly.coefficients[i];
                result_int[i+gap] = newPoly.exponents[i];

                result_double[i+1+gap] = coefficients[i];
                result_int[i+1+gap] = exponents[i];

                gap += 1;

            }

            else if(newPoly.exponents[i] > exponents[i]) {
                System.out.println("exponents for " + i + " we have " + exponents[i] + " less than " + newPoly.exponents[i]);

                result_double[i+gap] = coefficients[i];
                result_int[i+gap] = exponents[i];

                result_double[i+1+gap] = newPoly.coefficients[i];
                result_int[i+1+gap] = newPoly.exponents[i];

                gap += 1;
            }
            
        }

        return new Polynomial(result_double, result_int);
    }

    public Polynomial printy(Polynomial poly) {
        int max = Math.max(poly.coefficients.length, poly.exponents.length);
        for(int i=0; i<max; i++){
            System.out.println("For " + i + " the coef is " + poly.coefficients[i] + " the exp is " + poly.exponents[i]);
        }
        return poly;
    }

    public double evaluate(double x) {
        double result = 0; 
        for(int i=0; i<coefficients.length; i++ ) {
            result += (coefficients[i]) * Math.pow(x, i);
        }

        return result; 
    }

    public boolean hasRoot(double x) {
        double result = evaluate(x);
        if(result == 0) return true;

        else return false;
    }

}