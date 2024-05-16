public class Polynomial {

    double[] coefficients;

    public Polynomial() {
        coefficients = new double[]{0};
    }

    public Polynomial(double[] x) {
        coefficients = new double[x.length];
        System.arraycopy(x, 0, coefficients, 0, x.length);
    }

    public Polynomial add(Polynomial newPoly) {
        int highest = Math.max(newPoly.coefficients.length, coefficients.length);
        int lowest = Math.min(newPoly.coefficients.length, coefficients.length);
        double[] result = new double[highest];
        int k = 0;
        for(int i=0; i<lowest; i++) {
            result[i] = newPoly.coefficients[i] + coefficients[i];
            k = i+1;
        }

        for(int j=k; j<highest; j++) {
            if(newPoly.coefficients.length == highest) result[j] = newPoly.coefficients[j];

            else result[j] = coefficients[j];
        }

        return new Polynomial(result);
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