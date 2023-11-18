package comp3607_group_project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.javatuples.Triplet;
import java.io.IOException;
import java.util.ArrayList;

public class AssignmentAnalyzer {  

    //get methods, constructos and attributes for a given java file
    //get all class instances from assignment folder
    public ArrayList<Class<?>> getInstances(String folder) {
        ArrayList<Class<?>> classInstances = new ArrayList<Class<?>>();
        File dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder); //looks for java files in the extracted zip folder
		String[] extensions = new String[] { "java" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); //list of files with java extension in current directory

        for (File file: files) {
            try { //create class instance for each java file
                Class<?> c = Class.forName("comp3607_group_project." + folder + "." + file.getName().replaceAll(".java", ""));
                classInstances.add(c);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        return classInstances;
    }

    public Dictionary<String, String> getPrivateAttrs(Class<?> c) {
        Dictionary<String, String> attrsDict = new Hashtable<>();

        try {
            Field privateAttrs[] = c.getDeclaredFields(); //private attrs

            for (int i = 0; i < privateAttrs.length; i++) {
                Field attr = privateAttrs[i];
                attrsDict.put(attr.getName(), attr.getType().getSimpleName()); //form: Attribute Name, Atrribute Type

            }
        
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return attrsDict;
    }

    public ArrayList<String> getConstructors(Class<?> c) {
        ArrayList<String> constructors = new ArrayList<String>();

        try {
            Constructor<?> cons[] = c.getConstructors();
            
            for (int i = 0; i < cons.length; i++) {
                String signature = cons[i].getName().replaceAll((c.getPackageName() + "."), "") + "(";
                Class<?> parameters[] = cons[i].getParameterTypes();

                 //gets method name and parameters then rebuilds method signature
                for (int j = 0; j < parameters.length; j++) {
                    if (parameters.length - 1 != j) {
                        signature = signature + parameters[j].getSimpleName();
                        signature = signature + ",";

                    } else {
                        signature = signature + parameters[j].getSimpleName();

                    }

                }

                signature = signature + ")";
                constructors.add(signature);
            }

        } catch (Throwable e) {
            e.printStackTrace();

        }

        return constructors;
    }

    public Dictionary<String, String> getMethods(Class<?> c) {
        Dictionary<String, String> methodsDict = new Hashtable<>();

        try {
            Method methods[] = c.getDeclaredMethods();

            for (int i = 0; i < methods.length;i++){
                Method method = methods[i];
                String signature = method.getName() + "(";
                Class<?> parameters[] = method.getParameterTypes();
                
                //gets method name and parameters then rebuilds method signature
                for (int j = 0; j < parameters.length; j++) {
                    if (parameters.length - 1 != j) {
                        signature = signature + parameters[j].getSimpleName();
                        signature = signature + ",";

                    } else {
                        signature = signature + parameters[j].getSimpleName();

                    }

                }

                signature = signature + ")";
                methodsDict.put(signature, method.getReturnType().getSimpleName()); //form: Method Name, Method Return Type

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return methodsDict;
    }

    //get reuqired methods and attributes for entire assignment
    public ArrayList<Triplet<String, String, Integer>> extractReqAttributes(String folder) {
        ArrayList<Triplet<String, String, Integer>> attributes = new ArrayList<Triplet<String, String, Integer>>();
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\formattedData.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getCanonicalPath()));
            String line;

            while ((line = br.readLine()) != null) {
                String [] tokens = line.split("\\s+"); //splits line into array of words
                
                if (tokens.length == 3) {
                    if (!tokens[2].equals("MARKS")) { //ensures line is not table header
                        if (!tokens[0].contains("(")) { //filters attributes from methods
                            attributes.add(new Triplet<String, String, Integer>(tokens[0], tokens[1], Integer.parseInt(tokens[2])));
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attributes;
    }    

    public ArrayList<Triplet<String, String, Integer>> extractReqMethods(String folder) {
        ArrayList<Triplet<String, String, Integer>> methods = new ArrayList<Triplet<String, String, Integer>>();
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\formattedData.txt");
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+\\([^)]*\\)", Pattern.CASE_INSENSITIVE);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getCanonicalPath()));
            String line;

            while ((line = br.readLine()) != null) {
                String [] tokens = line.split("\\s+"); //splits line into array of words
                
                //normal method/attribute row
                if (tokens.length == 3) {
                    if (!tokens[2].equals("MARKS")) { //ensures line is not table header
                        Matcher matcher = pattern.matcher(tokens[0]);

                        if (matcher.matches()) { //filters methods from attributes
                            methods.add(new Triplet<String, String, Integer>(tokens[0], tokens[1], Integer.parseInt(tokens[2])));
                        }
                    }
                }

                //constructor row
                if (tokens.length == 2) {
                    Matcher matcher = pattern.matcher(tokens[0]);

                    if (matcher.matches()) { //filters methods from attributes
                        methods.add(new Triplet<String, String, Integer>(tokens[0], null, Integer.parseInt(tokens[1])));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return methods;
    }   

}
