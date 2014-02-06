
package solving.nonlinear.equations;

import java.util.ArrayList;

public class Function {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private double a0 = 0.0;
    private double b0 = 0.0;
    private double E = 0.0001;
    
    public Function(double a, double b, double c, double d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }
    
    private double f(double x){
        return a*Math.pow(x, 3)+b*Math.pow(x, 2)+c*x+d;
    }
    
    public ArrayList<Iteration> bisection(){
        ArrayList<Iteration> result = new ArrayList<>();
        double a = a0;
        double b = b0;
        if(f(a)*f(b)<0.0){
            while(Math.abs(a-b)/2.0>=E){
                double x = (a+b)/2.0;
                result.add(new Iteration(a, b, x));
                if(f(x)>0){
                    b=x;
                }else{
                    a=x;
                }
            }
            return result;
        }else{
            return null;
        }
    }
    
    
}
