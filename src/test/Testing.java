/*
package comp3607_group_project;

import org.junit.Assert;

import org.junit.*;

import java.lang.Class;
import java.lang.reflect.Method;

public class Testing{

    private Class<?> c;

    public static void main (String[] args) throws ClassNotFoundException{
        Testing t=new Testing();
        t.setup();
        t.testMethodList();


    }

    public void setup() throws ClassNotFoundException{
        System.out.println("Here");
        c= Class.forName("comp3607_group_project.DumpMethods");
        
    }
    @Test
    public void testMethodList(){
        Method[] m = c.getDeclaredMethods();
        Assert.assertNotNull(m);
    }

    @Test
    public void testMett(){
        Method[] m = c.getDeclaredMethods();
        Assert.assertNotNull(m);
    }




}*/

//import org.junit.api.Test;
//import static org.junit.Assert.assertEquals;
import org.junit.api.Test;
public class Testing {

    @Test
    public void testMarkAttributes() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("path/to/test/classes");

        // Call the method you want to test
        testSuite.markAttributes();

        // Assert the expected result
        assertEquals(attrMarks, testSuite.getAttrMarks());
    }

    @Test
    public void testMarkMethods() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("path/to/test/classes");

        // Call the method you want to test
        testSuite.markMethods();

        // Assert the expected result
        assertEquals(expectedMethodMarks, testSuite.getMethodMarks());
    }

    @Test
    public void testMarkConstructors() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("path/to/test/classes");

        // Call the method you want to test
        testSuite.markConstructors();

        // Assert the expected result
        assertEquals(expectedConstructorMarks, testSuite.getConstructorMarks());
    }

    @Test
    public void testComputeTotalMarks() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("path/to/test/classes");

        // Call the method you want to test
        testSuite.computeTotalMarks();

        // Assert the expected result
        assertEquals(expectedTotalMarks, testSuite.getTotalMarks());
    }
}
