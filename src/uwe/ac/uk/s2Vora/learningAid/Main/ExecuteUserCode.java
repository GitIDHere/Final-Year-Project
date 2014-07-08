package uwe.ac.uk.s2Vora.learningAid.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class ExecuteUserCode{
    
    private Object classObject;
    private ClassLoader classLoader;
    private Method userMethod;
    
    private SecurityManager adminSM;

    public ExecuteUserCode(){}
    
    //This is the method which is used to execute the script editor code.
    // This method also implements a sandbox environment so that malicious 
    // code is not executed.
    public void runMethod(String packageFilePath, String className){
        
        try{
            String userDirectory = System.getProperty("user.dir");
            userDirectory = userDirectory.replace("\\", "/");
                        
            URI fileURI = new URI("file://"+userDirectory+"/"+packageFilePath);
            URL url = fileURI.toURL();
            URL[] urls = new URL[]{url};

            classLoader = new URLClassLoader(urls);

            Class theUserClass = classLoader.loadClass("UserClass");
           
            adminSM = System.getSecurityManager();
            System.setSecurityManager(new UserSecurityManager());
 
            classObject = theUserClass.newInstance();
            
            userMethod = theUserClass.getMethod("userMethod");
           
            try{
               userMethod.invoke(classObject, null);
            }catch(InvocationTargetException exc){
               exc.printStackTrace();
            }

           System.setSecurityManager(adminSM); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    } 
}

