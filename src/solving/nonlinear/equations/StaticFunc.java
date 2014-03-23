package solving.nonlinear.equations;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Arrays;

public class StaticFunc {
    private final static double E = 0.00001;
    private final static Matrix mE = new Matrix(new double[][]{{1, 0, 0}, {0, 1, 0},{0, 0, 1}});
   
    
  public static double[] gauss(double[][] A, double[] b)
	{
		int i, j, l;
		double t;
		int n = b.length;
		for (i = 0; i < n; i++)
		{
			t = A[i][i];
			for (j = i; j < n; j++)
				A[i][j] /= t;
			b[i] /= t;
			for (l = i + 1; l < n; l++)
			{
				for (j = i + 1; j < n; j++)
					A[l][j] -= A[i][j]*A[l][i];
				b[l] -= b[i]*A[l][i];
			}
		}
		
		double[] x = new double[n];
		
		x[n-1] = b[n-1];
		for (i = n-2; i >= 0; i--)
		{
			x[i] = b[i];
			for (j = i+1; j < n; j++)
				x[i] -= A[i][j]*x[j];
		}
		return x;
	}
    
 
    public static ArrayList<Matrix> zeydel(Matrix A, Matrix F){
        ArrayList<Matrix> result = new ArrayList<>();
        Matrix t = A.transpose().times(A);
        double norm = t.norm1();
        double alpha = 1/norm;
        Matrix B = mE.minus(t.times(alpha));
        Matrix g = F.times(A).times(alpha);
        result.add(g);
        do{
            Matrix x1= new Matrix(1, 3);
            x1.set(0, 0, B.get(0, 0)*result.get(result.size()-1).get(0, 0)+B.get(0, 1)*result.get(result.size()-1).get(0, 1)+B.get(0, 2)*result.get(result.size()-1).get(0, 2)+g.get(0, 0));
           System.out.print(""+x1.get(0, 0)+"        ");
            x1.set(0, 1, B.get(1, 0)*x1.get(0, 0)+B.get(1, 1)*result.get(result.size()-1).get(0, 1)+B.get(1, 2)*result.get(result.size()-1).get(0, 2)+g.get(0, 1));
           System.out.print(""+x1.get(0, 1)+"        ");
            x1.set(0, 2, B.get(2, 0)*x1.get(0, 0)+B.get(2, 1)*x1.get(0, 1)+B.get(2, 2)*result.get(result.size()-1).get(0, 2)+g.get(0, 2));
            System.out.println(x1.get(0, 2));
            result.add(x1);
        }while(result.get(result.size()-1).minus(result.get(result.size()-2)).norm1()>E);
        return result;
    }
    
    public static double[] SquareSqrt(Matrix matr){
        double[] result = new double[3];
        Matrix s = new Matrix(3, 3);
        double[] y = new double[3];
        for(int i=0; i<s.getRowDimension(); i++){
            for(int j=i; j<s.getRowDimension(); j++){
                if(j==i){
                    double sum = 0;
                    for(int k=0; k<i; k++){
                        sum+=Math.pow(s.get(k, i), 2);
                    }
                    s.set(i, j, Math.sqrt(matr.get(i, i)-sum));
                }else{
                    double sum = 0;
                    for(int k=0; k<i; k++){
                        sum+=s.get(k, i)*s.get(k, j);
                    }
                    s.set(i, j, (matr.get(i, j)-sum)/(s.get(i, i)));
                }
            }
        }
        double[][] output = s.getArray();
        for(int i=0; i<output[0].length; i++){
            for(int j=0; j<output[0].length; j++){
                System.out.print(""+output[i][j]+"  ");
            }
            System.out.println("");
        }
        
        s=s.transpose();
        
        
        y[0]=matr.get(0, 3)/s.get(0, 0);
        System.out.println(y[0]);
        y[1]=(matr.get(1, 3)-y[0]*s.get(1, 0))/s.get(1, 1);
        System.out.println(y[1]);
        y[2]=(matr.get(2, 3)-y[0]*s.get(2, 0)-y[1]*s.get(2, 1))/s.get(2, 2);
        System.out.println(y[2]);
        s=s.transpose();
         result[2]=y[2]/s.get(2, 2);
        System.out.println(result[2]);
        result[1]=(y[1]-result[2]*s.get(1, 2))/s.get(1, 1);
        System.out.println(result[1]);
        result[0]=(y[0]-result[1]*s.get(0, 1)-result[2]*s.get(0, 2))/s.get(0, 0);
        System.out.println(result[0]);
        return result;
    }
    
//    public static Complex[] SquareSqrtC(Matrix matr){
//        Complex[] result = new Complex[3];
//        Complex[][] syst = new Complex[3][3];
//        Matrix s = new Matrix(3, 3);
//        Complex[] y = new Complex[3];
////        for(int i=1; i<s.getRowDimension(); i++){
////            for(int j=0; j<i+1; j++){
////                s.set(i, j, 0);
////            }
////        }
//        for(int i=0; i<s.getRowDimension(); i++){
//            for(int j=i; j<s.getRowDimension(); j++){
//                if(j==i){
//                    Complex sum = new Complex(0, 0);
//                    for(int k=0; k<i; k++){
//                        sum.plus(syst[k][i].times(syst[k][i]));
//                        //sum+=Math.pow(s.get(k, i), 2);
//                    }
//                    if(matr.get(i, i)-sum<0){
//                        syst[i][j]=new Complex(0, Math.sqrt(Math.abs(sum)));
//                    }else{
//                        syst[i][j]=new Complex(Math.sqrt(sum), 0);
//                    }
//                }else{
//                    double sum = 0;
//                    for(int k=0; k<i; k++){
//                        sum+=s.get(k, i)*s.get(k, j);
//                    }
//                    if(matr.get(i, i)-sum<0){
//                        syst[i][j]=new Complex(0, Math.sqrt(Math.abs(sum)));
//                    }else{
//                        syst[i][j]=new Complex(Math.sqrt(sum), 0);
//                    }
//                }
//            }
//        }
//        //double[][] output = s.getArray();
//        for(int i=0; i<syst[0].length; i++){
//            for(int j=0; j<syst[0].length; j++){
//                System.out.print(""+syst[i][j]+"  ");
//            }
//            System.out.println("");
//        }
//        Complex[][] systT = new Complex[3][3];
//        for(int i =0; i<3; i++){
//            for(int j=0; j<3; j++){
//                systT[j][i]=syst[i][j];
//            }
//        }
//        //s=s.transpose();
//        
//        Complex tmp = new Complex(matr.get(0, 3), 0);
//        y[0]= tmp.divides(systT[0][0]);
//        System.out.println(y[0]);
//        tmp = new Complex(matr.get(1, 3), 0);
//        y[1]=tmp.minus(y[0].times(systT[1][0])).divides(systT[1][1]);
//        System.out.println(y[1]);
//        tmp = new Complex(matr.get(2, 3), 0);
//        y[2]=tmp.minus(y[0].times(systT[2][0])).minus(y[1].times(systT[2][1])).divides(systT[2][2]);
//        System.out.println(y[2]);
//        //s=s.transpose();
//         result[2]=y[2].divides(syst[2][2]);
//        System.out.println(result[2]);
//        result[1]=y[1].minus(result[2].times(syst[1][2])).divides(syst[1][1]);
//        //result[1]=(y[1]-result[2]*s.get(1, 2))/s.get(1, 1);
//        System.out.println(result[1]);
//        result[0]=y[0].minus(result[1].times(syst[0][1])).minus(result[2].times(syst[0][2])).divides(syst[0][0]);
//       // result[0]=(y[0]-result[1]*s.get(0, 1)-result[2]*s.get(0, 2))/s.get(0, 0);
//        System.out.println(result[0]);
//        return result;
//    }
    
    
    public static ArrayList<Iteration> bisection(IFunction func){
        ArrayList<Iteration> result = new ArrayList<>();
        double a = func.getA0();
        double b = func.getB0();
        while(Math.abs(a-b)/2.0>=E){
            double x = (a+b)/2.0;
            result.add(new Iteration(a, b, x));
            if(func.f(x)*func.f(a)<0){
                b=x;
            }else{
                a=x;
            }
        }
        return result;    
    }
    
    private static double m1(IFunction func, double a, double b){
        double step = Math.abs(a-b)/100.0;
        double min = Math.abs(func.f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(func.f1(t))<min){
                min=Math.abs(func.f1(t));
            }
        }
        return min;//Math.abs(f1(a))<Math.abs(f1(b)) ? Math.abs(f1(a)) : Math.abs(f1(b));
    }
    
    private static double M1(IFunction func, double a, double b){
        double step = Math.abs(a-b)/100.0;
        double max = Math.abs(func.f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(func.f1(t))>max){
                max=Math.abs(func.f1(t));
            }
        }
        return max;//Math.abs(f1(a))<Math.abs(f1(b)) ? Math.abs(f1(b)) : Math.abs(f1(a));
    }
    
    private static double M2(IFunction func, double a, double b){
        double step = Math.abs(a-b)/100.0;
        double max = Math.abs(func.f1(a));
        for(double t=a; t<=b; t+=step){
            if(Math.abs(func.f2(t))>max){
                max=Math.abs(func.f2(t));
            }
        }
        return max;//Math.abs(f2(a))<Math.abs(f2(b)) ? Math.abs(f2(b)) : Math.abs(f2(a));
    }
    
    public static ArrayList<Iteration> simpleIteration(IFunction func){
        ArrayList<Iteration> result = new ArrayList<>();
        double a0 = func.getA0();
        double b0 = func.getB0();
        double x = func.getB0();
        double l;
        if(func.f1(x)>0){
            l=2/(m1(func, a0, b0)+M2(func, a0, b0));
        }
        else{
            l=-2/(m1(func, a0, b0)+M2(func, a0, b0));
        }
        result.add(new Iteration(0, 0, x));
        while(Math.abs(func.f(x))/m1(func, a0, b0)>=E){
            x-=l*func.f(x);
            double tmp=(func.f(x))/m1(func, a0, b0);
            result.add(new Iteration(tmp, 0, x));
        }
        return result;
    }
    
    public static ArrayList<Iteration> nuton(IFunction func){
        double a0 = func.getA0();
        double b0 = func.getB0();
        ArrayList<Iteration> result = new ArrayList<>();
        double x = a0;
        if(func.f(b0)*func.f2(b0)>0){
            x=b0;
        }else{
            x=a0;
        }
        double tmp = func.f(x)/m1(func, a0, b0);
        result.add(new Iteration(tmp, 0, x));
        while(Math.abs(func.f(x))/m1(func, a0, b0)>=E){
            double Xold = x;
            x=x-func.f(Xold)/func.f1(Xold);
            double tmp2 = Math.abs(Xold-x);
            tmp = func.f(x)/m1(func, a0, b0);
            result.add(new Iteration(tmp, tmp2, x));
        }
        return result;
    }
    
    public static ArrayList<Iteration> chord(IFunction func){
        double a0 = func.getA0();
        double b0 = func.getB0();
        ArrayList<Iteration> result = new ArrayList<>();
        double x;
        boolean first;
        if(func.f(b0)*func.f2(b0)>0){
            x=a0;
            first=true;
        }else{
            x=b0;
            first=false;
        }
        double tmp = func.f(x)/m1(func, a0,b0);
        result.add(new Iteration(tmp, 0, x));
        double Xold = x;
        while((Math.abs(func.f(x))/m1(func, a0, b0)>=E)||(Math.abs(x-Xold)>=E)){
            Xold = x;
            if(first){
                x=x-(func.f(x)/(func.f(b0)-func.f(x)))*(b0-x);
            }else{
                x=x-(func.f(x)/(func.f(a0)-func.f(x)))*(a0-x);
            }
            double tmp2 = Xold-x;
            tmp = func.f(x)/m1(func, a0, b0);
            result.add(new Iteration(tmp, tmp2, x));
        }
        return result;
    }
}
