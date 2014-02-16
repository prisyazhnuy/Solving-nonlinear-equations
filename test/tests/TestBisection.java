package tests;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import solving.nonlinear.equations.Function;
import solving.nonlinear.equations.Iteration;

public class TestBisection {
    
    public TestBisection() {
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
    public void X1(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(-9.0, -8.0);
        ArrayList<Iteration> iter = func.bisection();
        int tmp = (int) (iter.get(iter.size()-1).getX()*1000);
        assertTrue(tmp/1000.0==-8.140);
        //assertTrue(iter.get(0).getX()==-8.5);
        
    }
    
    @Test
    public void X2(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(-1.0, 0.0);
        ArrayList<Iteration> iter = func.bisection();
        int tmp = (int) (iter.get(iter.size()-1).getX()*1000.0);
        System.out.println(""+tmp);
        assertTrue(tmp/1000.0==-0.859);
        //assertTrue(iter.get(0).getX()==-8.5);
    }
    
    @Test
    public void X3(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(0.0, 1.2);
        ArrayList<Iteration> iter = func.bisection();
        int tmp = (int) (iter.get(iter.size()-1).getX()*1000);
        assertTrue(tmp/1000.0==1.000);
        //assertTrue(iter.get(0).getX()==-8.5);
    }
    
    @Test
    public void Nuton(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(0.0, 1.2);
        //ArrayList<Iteration> iter = func.bisection();
        //int tmp = (int) (iter.get(iter.size()-1).getX()*1000);
        System.out.println(""+func.nuton());
      //  assertTrue(func.nuton()==1.0);
        //assertTrue(iter.get(0).getX()==-8.5);
    }
    
     @Test
    public void Nuton2(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(-9.0, -8.0);
        //ArrayList<Iteration> iter = func.bisection();
        //int tmp = (int) (func.nuton()*1000);
        System.out.println(""+func.nuton());
     //   assertTrue(tmp/1000.0==-8.140);
        //assertTrue(iter.get(0).getX()==-8.5);
    }
    
     @Test
    public void Nuton3(){
        Function func = new Function(1.0, 8.0, -2.0, -7.0);
        func.setStartPoint(-1.0, 0.0);
        //ArrayList<Iteration> iter = func.bisection();
     //   int tmp = (int) (func.nuton()*1000);
        System.out.println(""+func.nuton());
      //  assertTrue(tmp/1000.0==-0.859);
        //assertTrue(iter.get(0).getX()==-8.5);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
