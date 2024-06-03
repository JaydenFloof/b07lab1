import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Polynomial {

    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        coefficients = new double[0];
        exponents = new int[0];
    }

    public Polynomial(double[] x, int[] y) {
        coefficients = new double[x.length];
        exponents = new int[y.length];
        System.arraycopy(x, 0, coefficients, 0, x.length);
        System.arraycopy(y, 0, exponents, 0, y.length);
    }

    public Polynomial add(Polynomial newPoly) {
        int max_double = newPoly.coefficients.length + coefficients.length;
        int max_int = newPoly.exponents.length + exponents.length;
        int count = 0;
        boolean check = false;

        Polynomial old = new Polynomial(coefficients, exponents);
        old = old.sort(old);
        newPoly = newPoly.sort(newPoly);

        double[] result_double = new double[max_double];
        int[] result_int = new int[max_int];

        if(newPoly.exponents.length >= old.exponents.length) {
            for(int i=0; i<newPoly.exponents.length; i++) {
                check = false;

                for(int j=0; j<old.exponents.length; j++) {

                    if(newPoly.exponents[i] == old.exponents[j]) {
                        result_int[count] = newPoly.exponents[i];
                        result_double[count] = newPoly.coefficients[i] + old.coefficients[j];
                        result_int[count] = newPoly.exponents[i];
                        count += 1;
                        check = true;
                        break;
                    }

                    else if(newPoly.exponents[i] < old.exponents[j]) {
                        result_int[count] = newPoly.exponents[i];
                        result_double[count] = newPoly.coefficients[i];
                        count += 1;
                        check = true;
                        break;
                    }

                    else {
                        if((inside(old.exponents[j], result_int)) == false){
                            result_int[count] = old.exponents[j];
                            result_double[count] = old.coefficients[j];
                            count += 1;
                        }
                    }
                }
                
                if(check == false && (inside(newPoly.exponents[i], result_int)) == false) {
                    result_int[count] = newPoly.exponents[i];
                    result_double[count] = newPoly.coefficients[i];
                    count += 1;
                }
            }
        }

        else if(newPoly.exponents.length < old.exponents.length) {
            for(int i=0; i<old.exponents.length; i++) {
                check = false;

                for(int j=0; j<newPoly.exponents.length; j++) {

                    if(old.exponents[i] == newPoly.exponents[j]) {
                        result_int[count] = old.exponents[i];
                        result_double[count] = old.coefficients[i] + newPoly.coefficients[j];
                        result_int[count] = old.exponents[i];
                        count += 1;
                        check = true;
                        break;
                    }

                    else if(old.exponents[i] < newPoly.exponents[j]) {
                        result_int[count] = old.exponents[i];
                        result_double[count] = old.coefficients[i];
                        count += 1;
                        check = true;
                        break;
                    }

                    else {
                        if((inside(newPoly.exponents[j], result_int)) == false){
                            result_int[count] = newPoly.exponents[j];
                            result_double[count] = newPoly.coefficients[j];
                            count += 1;
                        }
                    }
                }
                
                if(check == false && (inside(old.exponents[i], result_int)) == false) {
                    result_int[count] = old.exponents[i];
                    result_double[count] = old.coefficients[i];
                    count += 1;
                }
            }
        }

        Polynomial result = new Polynomial(result_double, result_int);
        result = result.cut(result);

        return result;
    }

    public Polynomial sort(Polynomial poly) {
        int exp_len = poly.exponents.length;
        int temp_int = 0;
        double temp_double = 0;

        for(int i=0; i<exp_len; i++) {
            for(int j=i+1; j<exp_len; j++){
                if(poly.exponents[i] > poly.exponents[j]) {
                    temp_int = poly.exponents[i];
                    poly.exponents[i] = poly.exponents[j];
                    poly.exponents[j] = temp_int;

                    temp_double = poly.coefficients[i];
                    poly.coefficients[i] = poly.coefficients[j];
                    poly.coefficients[j] = temp_double;
                }
            }
        }

        return new Polynomial(poly.coefficients, poly.exponents);
    }

    public boolean inside(int num, int[] arr){
        boolean result = false;
        int k = 0;
        while(arr[k] != 0){
            if(arr[k] == num){
                result = true;
                break;
            }
            k += 1;
        }

        return result;
    }

    public Polynomial cut(Polynomial poly) {
        int count = 0;
        int k = 0;
        boolean check = false;
        
        while(poly.exponents[count] != 0){
            if(poly.coefficients[count] == 0){

                if(count+1 == poly.exponents.length){
                    poly.exponents[count] = 0;
                }

                else{
                    for(int i=count+1; i<poly.exponents.length; i++){
                        poly.exponents[i-1] = poly.exponents[i];
                        poly.coefficients[i-1] = poly.coefficients[i];
                        k = i;
                    }
                    poly.exponents[k] = 0;
                }
                count -= 1;
                check = true;
            }
            
            if(count+1 != poly.exponents.length){
                count += 1;
            }

            else {
                break;
            }
        }

        if(check == false){
            count = count + 1;
        }


        double[] result_double = new double[count];
        int[] result_int = new int[count];

        System.arraycopy(poly.exponents, 0, result_int, 0, count);
        System.arraycopy(poly.coefficients, 0, result_double, 0, count);

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
            result += (coefficients[i]) * Math.pow(x, exponents[i]);
        }
        return result; 
    }

    public boolean hasRoot(double x) {
        double result = evaluate(x);
        if(result == 0) return true;

        else return false;
    }

    public Polynomial multiply(Polynomial poly) {
        int max_double = poly.coefficients.length * coefficients.length;
        int max_int = poly.exponents.length * exponents.length;

        Polynomial old = new Polynomial(coefficients, exponents);
        old = old.sort(old);
        poly = poly.sort(poly);

        double[] result_double = new double[max_double];
        int[] result_int = new int[max_int];

        int count = 0;

        for(int i=0; i<poly.exponents.length; i++){
            
            for(int j=0; j<exponents.length; j++){
 
                result_int[count] = poly.exponents[i] + exponents[j];
                result_double[count] = poly.coefficients[i] * coefficients[j];
                count += 1;
            }
        }


        Polynomial result = new Polynomial(result_double, result_int);

        result = result.simplify(result);

        result = result.cut(result);

        result = result.sort(result);

        return result;
    }

    public Polynomial simplify(Polynomial poly){
        int count = 0;

        for(int i=0; i<poly.exponents.length; i++){

            for(int j=0; j<poly.exponents.length; j++){

                if(i != j && poly.exponents[i] == poly.exponents[j] && poly.exponents[i] != 0 && poly.coefficients[i] != 0 && poly.coefficients[j] != 0){
                    poly.coefficients[i] += poly.coefficients[j];
                    poly.coefficients[j] = 0;
                    count += 1;
                }
            }
        }

        return new Polynomial(poly.coefficients, poly.exponents);
    }

    public Polynomial(File text){
        try {
            Scanner input = new Scanner(text);
            String str = input.nextLine();
            String[] poly = str.split("(?=[+-])");
        }
        catch(FileNotFoundException ex){
            System.out.println("Error");
        }

        coefficients = new double[1];
        exponents = new int[1];
        
    }


}