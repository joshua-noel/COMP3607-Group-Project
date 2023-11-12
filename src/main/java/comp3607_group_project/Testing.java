package comp3607_group_project;

import org.junit.jupiter.api.*;
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
        Assertions.assertNotNull(m);
    }

    @Test
    public void testMett(){
        Method[] m = c.getDeclaredMethods();
        Assertions.assertNotNull(m);
    }




}