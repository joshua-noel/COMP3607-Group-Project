package comp3607_group_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSuiteTest {

    @Test
    public void testMarkAttributes() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("Joshua_Noel_816031055_A1");

        // Call the method you want to test
        try {
            testSuite.markAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assert the expected result
        assertEquals(17, testSuite.getAttrMarks());
    }

    @Test
    public void testMarkMethods() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("Joshua_Noel_816031055_A1");

        // Call the method you want to test
        try {
            testSuite.markMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assert the expected result
        assertEquals(35, testSuite.getMethodMarks());
    }

    @Test
    public void testMarkConstructors() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("Joshua_Noel_816031055_A1");

        // Call the method you want to 
        try {
            testSuite.markConstructors();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        

        // Assert the expected result
        assertEquals(14, testSuite.getConstructorMarks());
    }

    @Test
    public void testComputeTotalMarks() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("Joshua_Noel_816031055_A1");

        // Call the method you want to test
        try {
            testSuite.markAttributes();
            testSuite.markMethods();
            testSuite.markConstructors();
        } catch (Exception e) {
            e.printStackTrace();
        }   
        testSuite.computeTotalMarks();

        // Assert the expected result
        assertEquals(66, testSuite.getTotalMarks());
    }
}

