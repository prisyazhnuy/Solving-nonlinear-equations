package solving.nonlinear.equations;

public class Transcendental implements IFunction{
    private double a0;
    private double b0;

    @Override
    public double f(double x) {
        return 5*x-8*Math.log(x)-8;
    }

    @Override
    public double f1(double x) {
        return 5-8/x;
    }

    @Override
    public double f2(double x) {
        return 8/Math.pow(x,2);
    }

    @Override
    public void setStartPoint(double a0, double b0) {
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
