package comp3607_group_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSuiteTest {

    @Test
    public void testMarkAttributes() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("comp3607_group_project");

        // Call the method you want to test
        testSuite.markAttributes();

        // Assert the expected result
        assertEquals(17, testSuite.getAttrMarks());
    }
// testing
    @Test
    public void testMarkMethods() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("comp3607_group_project");

        // Call the method you want to test
        testSuite.markMethods();

        // Assert the expected result
        assertEquals(35, testSuite.getMethodMarks());
    }

    @Test
    public void testMarkConstructors() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("comp3607_group_project");

        // Call the method you want to test
        testSuite.markConstructors();

        // Assert the expected result
        assertEquals(14, testSuite.getConstructorMarks());
    }

    @Test
    public void testComputeTotalMarks() {
        // Create a TestSuite instance with a folder containing test classes
        TestSuite testSuite = new TestSuite("comp3607_group_project");

        // Call the method you want to test
        testSuite.computeTotalMarks();

        // Assert the expected result
        assertEquals(66, testSuite.getTotalMarks());
    }
}

