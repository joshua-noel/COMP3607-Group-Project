package comp3607_group_project;

 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Triplet;

public class TestSuite {
    private int attrMarks;
    private int methodMarks;
    private int constructorMarks;
    private int totalMarks;
    private String folder;

    AssignmentAnalyzer analyzer = new AssignmentAnalyzer();
    private ArrayList<Class<?>> classInstances;
    private ArrayList<Triplet<String, String, Integer>> requiredAttrs;
    private ArrayList<Triplet<String, String, Integer>> requiredMethods;

     /**
     * Constructs a TestSuite for the specified folder.
     *
     * @param folder The name of the folder associated with the assignment.
     */
    public TestSuite(String folder) {
        this.folder = folder;
        classInstances = analyzer.getInstances(folder);
        requiredAttrs = analyzer.extractReqAttributes(folder);
        requiredMethods = analyzer.extractReqMethods(folder);

    }

    /**
     * Marks attributes in the assignment and generates a PDF report for attributes.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void markAttributes() throws IOException{
        ArrayList<String> c = new ArrayList<>();
        c.add("Attribute Marks");

        CreatePDF file = new CreatePDF();
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            Dictionary<String, String> classMethods = analyzer.getPrivateAttrs(classInstances.get(i)); //load methods

            Enumeration<String> k = classMethods.keys();
            c.add("Class: " + classInstances.get(i).getSimpleName());


            while (k.hasMoreElements()) { //iterate across all attributes in class
                String key = k.nextElement();
                Boolean found = false;

                //look for attribute in list of required attributes
                for (int j = 0; j < requiredAttrs.size(); j++) {
                    if ((key.equals(requiredAttrs.get(j).getValue0())) && ((classMethods.get(key)).equals(requiredAttrs.get(j).getValue1()))) { //checks if method name and type matches required
                        attrMarks = attrMarks + requiredAttrs.get(j).getValue2();
                        c.add(key + " found and correct, marks awarded: " + requiredAttrs.get(j).getValue2());
                        found = true;
                        j = requiredAttrs.size(); //jump out of loop

                    }

                }

                if (!found) {
                    c.add(key + " not found, no marks awarded");

                }

            } 
                     

        }
        c.add("Marks for Attributes: " + attrMarks);
        file.writeText("MarkedAttributes.pdf", c);
    

    }

    /**
     * Marks methods in the assignment and generates a PDF report for methods.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void markMethods()throws IOException {
        //regex patterns for getters and setters
        Pattern pattern1 = Pattern.compile("get[A-Za-z0-9]+\\(\\)", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("set[A-Za-z0-9]+\\(\\)", Pattern.CASE_INSENSITIVE);

        ArrayList<String> c = new ArrayList<>();
        c.add("Method Marks");

        CreatePDF file = new CreatePDF();
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            Dictionary<String, String> classMethods = analyzer.getMethods(classInstances.get(i)); //load methods

            Enumeration<String> k = classMethods.keys();
            c.add("Class: " + classInstances.get(i).getSimpleName());
            
            while (k.hasMoreElements()) { //iterate across all class methods in class
                String key = k.nextElement();
                Boolean found = false;
                Matcher matcher1 = pattern1.matcher(key);
                Matcher matcher2 = pattern2.matcher(key);

                //look for method in list of required methods and constructors
                for (int j = 0; j < requiredMethods.size(); j++) {
                    if ((key.equals(requiredMethods.get(j).getValue0())) && ((classMethods.get(key)).equals(requiredMethods.get(j).getValue1()))) { //checks if method name and type matches required
                        methodMarks = methodMarks + requiredMethods.get(j).getValue2();
                        c.add(key + " found and correct, marks awarded: " + requiredMethods.get(j).getValue2());
                        found = true;
                        j = requiredMethods.size(); //jump out of loop

                    }

                }

                if ((!found) && (!matcher1.matches()) && (!matcher2.matches())) { //only triggers if method not found and method is not a getter/setter
                    c.add(key + " not found, no marks awarded");

                }                

            }

        }
        c.add("Marks for Methods: " + methodMarks);
        file.writeText("MarkedMethods.pdf", c);  

    }

    /**
     * Marks constructors in the assignment and generates a PDF report for constructors.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void markConstructors() throws IOException{
        ArrayList<String> c = new ArrayList<>();
        c.add("Constructor Marks");

        CreatePDF file = new CreatePDF();
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            ArrayList<String> constructors = analyzer.getConstructors(classInstances.get(i)); //load constructors
            c.add("Class: " + classInstances.get(i).getSimpleName());

            for (int j = 0; j < constructors.size(); j++) { //iterate across all class constructors
                String curr = constructors.get(j);
                Boolean found = false;

                //look for constructor in list of required methods and constructors
                for (int k = 0; k < requiredMethods.size(); k++) {
                    if (curr.equals(requiredMethods.get(k).getValue0())) {
                        constructorMarks = constructorMarks + requiredMethods.get(k).getValue2();
                        c.add(curr + " found and correct, marks awarded: " + requiredMethods.get(j).getValue2());
                        found = true;
                        k = requiredMethods.size(); //jump out of loop

                    }

                }

                if (!found) {
                    c.add(curr + " not found, no marks awarded");
                }

            }

        }
        c.add("Marks for Constructors: " + constructorMarks);
        //temp fix to get total for assignment in final report
        computeTotalMarks();
        c.add("Total Marks: " + getTotalMarks());
        file.writeText("MarkedConstructors.pdf", c);

    }

    /**
     * Computes the total marks by summing individual marks for attributes, methods, and constructors.
     */
    public void computeTotalMarks() {
        totalMarks = attrMarks + methodMarks + constructorMarks;

    }

    /**
     * Generates a PDF report for the entire assignment, including marks for attributes, methods, and constructors.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void generatePDF() throws IOException {
        CreatePDF f = new CreatePDF();
        f.mergePDFs(folder);

    }
    //accessors
    public int getAttrMarks() { return attrMarks; }

    public int getMethodMarks() { return methodMarks; }

    public int getConstructorMarks() { return constructorMarks; }

    public int getTotalMarks() { return totalMarks; }
}
