package comp3607_group_project;

import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DumpMethods {
    public DumpMethods(){

    }
    public int Test(){
        return 0;

    }
    public static void main(String args[]){
        try {
            // Class<?> c= Class.forName("comp3607_group_project.DumpMethods");
            // Class<?> c= Class.forName(System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\comp3607_group_project.Joshua_Noel_816031055_A1.Flight");
            Class<?> c= Class.forName("comp3607_group_project.Joshua_Noel_816031055_A1.Flight");
            Method m[]= c.getDeclaredMethods();
            Field privatef[] = c.getDeclaredFields();
            Field publicf[] = c.getFields();

            System.out.println("Private Attributes:");
            for (int i = 0; i < privatef.length; i++) {
                Field privatefieldlist = privatef[i];
                System.out.println(privatefieldlist.getName());

            }

            System.out.println("\nPublic Attributes:");
            for (int i = 0; i < publicf.length; i++) {
                Field publicfieldlist = publicf[i];
                System.out.println(publicfieldlist.getName());

            }

            System.out.println("");
            for (int i=0; i<m.length;i++){
                Method methlist = m[i];
                String signature = methlist.getName() + "(";
                Class<?> parameters[] = methlist.getParameterTypes();
                
                for (int j = 0; j < parameters.length; j++) {
                    if (parameters.length - 1 != j) {
                        signature = signature + parameters[j].getName().replace("java.lang.", "").replace("java.time.", "").replace("comp3607_group_project.Joshua_Noel_816031055_A1.", "") + ", ";

                    } else {
                        signature = signature + parameters[j].getName().replace("java.lang.", "").replace("java.time.", "").replace("comp3607_group_project.Joshua_Noel_816031055_A1.", "");

                    }

                }

                System.out.println(signature + ")");

                //return type test
                Class<?> type = methlist.getReturnType();
                System.out.println("Return Type: " + type.getName().replace("java.lang.", "").replace("java.time.", "").replace("comp3607_group_project.Joshua_Noel_816031055_A1.", "") + "\n");

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
