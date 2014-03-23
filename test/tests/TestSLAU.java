
package tests;

import Jama.Matrix;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import solving.nonlinear.equations.StaticFunc;

public class TestSLAU {
    
    public TestSLAU() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void SquareSqrt() {
         double [][] arr = new double[][]{{7.0, 1.0, 1.0, 9.0},
                                          {1.0, 5.0, -2.0, 5.0},
                                          {1.0, -2.0, 4.0, 4.0}};
         Matrix matr = new Matrix(arr);
         StaticFunc.SquareSqrt(matr);
     }
     
     @Test
     public void SquareSqrt2() {
         double [][] arr = new double[][]{{7.0, 3.0, 9.0, 26.0},
                                          {3.0, 5.0, -2.0, 9.0},
                                          {9.0, -2.0, 4.0, 20.0}};
         Matrix matr = new Matrix(arr);
         StaticFunc.SquareSqrt(matr);
     }
     
      @Test
     public void zeydalTest() {
          double[][] test = new double[][]{{1,2,-1},{-1,-1,5},{1,1,1}};
         double[][] f = new double[][]{{2,3,3}};
         StaticFunc.zeydel(new Matrix(test), new Matrix(f));
     }
     
      @Test
     public void gaussTest() {
         
         double[][] test = new double[][]{{1,-3,2},{-3,10,-5},{2,-5,9}};
         double[] test2 = new double[]{2,1,-1,-3,1,2,-2,1,2,};
         double[] f = new double[]{10,12,14};
         double[] res =StaticFunc.gauss(test, f);
          System.out.println(res[0]);
          System.out.println(res[1]);
          System.out.println(res[2]);
     }
}
