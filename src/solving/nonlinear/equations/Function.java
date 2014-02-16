
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
    
    public double f(double x){
        return a*Math.pow(x, 3)+b*Math.pow(x, 2)+c*x+d;
    }
    
    private double f1(double x){
        return 3*a*Math.pow(x, 2)+2*b*x+c;
    }
    
    private double f2(double x){
        return 6*a*x+2*b;
    }
    
    public ArrayList<Iteration> bisection(){
        ArrayList<Iteration> result = new ArrayList<>();
        double a = a0;
        double b = b0;
        while(Math.abs(a-b)/2.0>=E){
            double x = (a+b)/2.0;
            result.add(new Iteration(a, b, x));
            if(f(x)*f(a)<0){
                b=x;
            }else{
                a=x;
            }
        }
        return result;    
    }
    
    private double m1(double a, double b){
        double step = Math.abs(a-b)/100.0;
        double min = Math.abs(f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(f1(t))<min){
                min=Math.abs(f1(t));
            }
        }
        return min;//Math.abs(f1(a))<Math.abs(f1(b)) ? Math.abs(f1(a)) : Math.abs(f1(b));
    }
    
    private double M1(double a, double b){
        double step = Math.abs(a-b)/100.0;
        double max = Math.abs(f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(f1(t))>max){
                max=Math.abs(f1(t));
            }
        }
        return max;//Math.abs(f1(a))<Math.abs(f1(b)) ? Math.abs(f1(b)) : Math.abs(f1(a));
    }
    
    private double M2(double a, double b){
        double step = Math.abs(a-b)/100.0;
        double max = Math.abs(f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(f2(t))>max){
                max=Math.abs(f2(t));
            }
        }
        return max;//Math.abs(f2(a))<Math.abs(f2(b)) ? Math.abs(f2(b)) : Math.abs(f2(a));
    }
    
    public ArrayList<Iteration> simpleIteration(){
        ArrayList<Iteration> result = new ArrayList<>();
        double x = a0;
        double l;
        if(f1(x)>0){
            l=2/(m1(a0, b0)+M2(a0, b0));
        }
        else{
            l=-2/(m1(a0, b0)+M2(a0, b0));
        }
        result.add(new Iteration(0, 0, x));
        while(Math.abs(f(x))/m1(a0, b0)>=E){
            x-=l*f(x);
            double tmp=(f(x))/m1(a0, b0);
            result.add(new Iteration(tmp, 0, x));
        }
        return result;
    }
    
    public ArrayList<Iteration> nuton(){
        ArrayList<Iteration> result = new ArrayList<>();
        double x = b0;
        if(b0*f2(b0)>0){
            x=b0;
        }else{
            x=a0;
        }
        double tmp = f(x)/m1(a0, b0);
        result.add(new Iteration(tmp, 0, x));
        while(Math.abs(f(x))/m1(a0, b0)>=E){
            double Xold = x;
            x=x-f(Xold)/f1(Xold);
            double tmp2 = Math.abs(Xold-x);
            tmp = f(x)/m1(a0, b0);
            result.add(new Iteration(tmp, tmp2, x));
        }
        return result;
    }
    
    public ArrayList<Iteration> chord(){
        ArrayList<Iteration> result = new ArrayList<>();
        double x;
        boolean first;
        if(f(b0)*f2(b0)>0){
            x=a0;
            first=true;
        }else{
            x=b0;
            first=false;
        }
        double tmp = f(x)/m1(a0,b0);
        result.add(new Iteration(tmp, 0, x));
        double Xold = x;
        while((Math.abs(f(x))/m1(a0, b0)>=E)||(Math.abs(x-Xold)>=E)){
            Xold = x;
            if(first){
                x=x-(f(x)/(f(b0)-f(x)))*(b0-x);
            }else{
                x=x-(f(x)/(f(a0)-f(x)))*(a0-x);
            }
            double tmp2 = Xold-x;
            tmp = f(x)/m1(a0,b0);
            result.add(new Iteration(tmp, tmp2, x));
        }
        return result;
    }
    
    public void setStartPoint(double a0, double b0){
        this.a0=a0;
        this.b0=b0;
    }
}
