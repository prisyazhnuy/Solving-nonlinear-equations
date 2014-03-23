package solving.nonlinear.equations;

public interface IFunction {
    double f(double x);
    
    double f1(double x);
    
    double f2(double x);
    
    void setStartPoint(double a0, double b0);
    
    double getA0();
    
    double getB0();
}
