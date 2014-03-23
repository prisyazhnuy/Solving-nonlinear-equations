package solving.nonlinear.equations;


public class Function implements IFunction{
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
    
    @Override
    public double f(double x){
        return a*Math.pow(x, 3)+b*Math.pow(x, 2)+c*x+d;
    }
    
    @Override
    public double f1(double x){
        return 3*a*Math.pow(x, 2)+2*b*x+c;
    }
    
    @Override
    public double f2(double x){
        return 6*a*x+2*b;
    }
    
    @Override
    public void setStartPoint(double a0, double b0){
        this.a0=a0;
        this.b0=b0;
    }

    @Override
    public double getA0() {
    return a0;
    }

    @Override
    public double getB0() {
        return b0;
    }
}
