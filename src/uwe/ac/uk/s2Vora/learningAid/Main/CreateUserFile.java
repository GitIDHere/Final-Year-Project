
package uwe.ac.uk.s2Vora.learningAid.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateUserFile {
    
    public CreateUserFile(){}
    
    //This class is used to create UserClass.java. It does this by retrieving a .txt
    // file containing the template to the UserClass.java. The template is then written to
    // a .java file and named UserClass.
    public void createFile(String packagePath, String userInput){
        
        try{
            
            String javaFilePath = packagePath + "UserClass.java";
            
            PrintWriter writer = new PrintWriter(javaFilePath, "UTF-8");
                
            String currentLine;
            BufferedReader bReader = new BufferedReader(new FileReader(packagePath + "Template.txt"));
            
            int counter = 0;
            
            while ((currentLine = bReader.readLine()) != null ) {

                if(counter == 22){
                    writer.println(userInput);
                }else{
                    writer.println(currentLine);
                }
                
                counter++;
            }
            
            writer.close();

        }catch(FileNotFoundException ex){
            System.out.println("FileNotFoundException Exception error: "+ex);
        }
        catch(IOException ex){
             System.out.println("IOException Exception error: "+ex);
        }

    }

}
