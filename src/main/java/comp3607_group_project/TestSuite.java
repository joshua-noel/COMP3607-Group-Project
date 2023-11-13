package comp3607_group_project;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.Hashtable;

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

    public TestSuite(String folder) {
        this.folder = folder;
        classInstances = analyzer.getInstances(folder);
        requiredAttrs = analyzer.extractReqAttributes(folder);
        requiredMethods = analyzer.extractReqMethods(folder);

    }

    public void markAttributes() {
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            Dictionary<String, String> classMethods = analyzer.getPrivateAttrs(classInstances.get(i)); //load methods

            Enumeration<String> k = classMethods.keys();

            while (k.hasMoreElements()) { //iterate across all attributes in class
                String key = k.nextElement();

                //look for attribute in list of required attributes
                for (int j = 0; j < requiredAttrs.size(); j++) {
                    if ((key.equals(requiredAttrs.get(j).getValue0())) && ((classMethods.get(key)).equals(requiredAttrs.get(j).getValue1()))) { //checks if method name and type matches required
                        attrMarks = attrMarks + requiredAttrs.get(j).getValue2();

                    }

                }

            }           

        }

    }

    public void markMethods() {
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            Dictionary<String, String> classMethods = analyzer.getMethods(classInstances.get(i)); //load methods

            Enumeration<String> k = classMethods.keys();

            while (k.hasMoreElements()) { //iterate across all class methods in class
                String key = k.nextElement();

                //look for method in list of required methods and constructors
                for (int j = 0; j < requiredMethods.size(); j++) {
                    if ((key.equals(requiredMethods.get(j).getValue0())) && ((classMethods.get(key)).equals(requiredMethods.get(j).getValue1()))) { //checks if method name and type matches required
                        methodMarks = methodMarks + requiredMethods.get(j).getValue2();
                        j = requiredMethods.size(); //exits loop at first occurance; a lil jank and probably will cause issues down the line
                        
                    }

                }

            }  

        }

    }

    public void markConstructors() {
        for (int i = 0; i < classInstances.size(); i++) { //iterate over all classes
            ArrayList<String> constructors = analyzer.getConstructors(classInstances.get(i)); //load constructors

            for (int j = 0; j < constructors.size(); j++) { //iterate across all class constructors
                String curr = constructors.get(j);

                //look for constructor in list of required methods and constructors
                for (int k = 0; k < requiredMethods.size(); k++) {
                    if (curr.equals(requiredMethods.get(k).getValue0())) {
                        constructorMarks = constructorMarks + requiredMethods.get(k).getValue2();

                    }

                }

            }

        }

    }

    public void computeTotalMarks() {
        totalMarks = attrMarks + methodMarks + constructorMarks;

    }

    //accessors
    public int getAttrMarks() { return attrMarks; }

    public int getMethodMarks() { return methodMarks; }

    public int getConstructorMarks() { return constructorMarks; }

    public int getTotalMarks() { return totalMarks; }
}
