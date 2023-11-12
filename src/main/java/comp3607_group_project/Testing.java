package comp3607_group_project;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.AssertionError;

public class Testing{

    private Class<?> c;


    public void setup() throws ClassNotFoundException{
        c= Class.forName("comp3607_group_project.Joshua_Noel_816031055_A1.Flight");
    }
    @Test
    public void testMethodList(){
        Method[] m = c.getDeclaredMethods();
        assertNotNull(m);


    }




}