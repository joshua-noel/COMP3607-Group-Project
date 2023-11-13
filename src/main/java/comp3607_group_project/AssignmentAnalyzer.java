package comp3607_group_project;

import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class AssignmentAnalyzer {

    public Class<?> getInstance(String folder, String classname) {
        try {
            Class<?> c = Class.forName("comp3607_group_project." + folder + "." + classname); //create and return class object for manipulation
            return c;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
                        signature = signature + ", ";

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
                        signature = signature + ", ";

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

}
