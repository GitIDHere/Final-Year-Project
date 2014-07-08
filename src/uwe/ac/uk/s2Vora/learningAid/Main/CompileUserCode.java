package uwe.ac.uk.s2Vora.learningAid.Main;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CompileUserCode {

    private final JavaCompiler compiler;
    private ByteArrayOutputStream baos;
            
    public CompileUserCode(){
        compiler = ToolProvider.getSystemJavaCompiler();
    }
    
    //This is the class used for  compiling UserClass.java.
    public boolean compileClass(String pathToFile, String userClassName){
        
        baos = new ByteArrayOutputStream(2048);
        java.io.BufferedOutputStream errorStream = new java.io.BufferedOutputStream(baos);
        
        int compilationResult = compiler.run(null, null, errorStream, pathToFile+userClassName+".java");
       
            if(compilationResult == 0){
                System.out.println("Compilation Successful");
                return true;
            }else{
                System.out.println("Compilation Failed");
                try{
                    errorStream.close();
                    baos.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            }
    }
    
    //This function parses the error that might have thrown, and creates a concise error message.
    public String getErrors(){
        
        String error = new String(baos.toByteArray());
        StringBuilder strBuilder = new StringBuilder();

        String error_lines[] = error.split("src/uwe/ac/uk/s2Vora/learningAid/UserInputs/UserClass.java");
        int line = 0;
        for (String err : error_lines) {
            Pattern pattern = Pattern.compile(":(\\d+):\\serror:\\s(.*)\\s(.*)");
            Matcher matcher = pattern.matcher(err);

            while (matcher.find()) {
                strBuilder.append("Error on line: ");
                String line_num = matcher.group(1);
                strBuilder.append(Integer.parseInt(line_num) - 22);
                strBuilder.append(" - ");
                strBuilder.append(matcher.group(2));
                strBuilder.append("\n");
                strBuilder.append("\n");
            }
        }
        return strBuilder.toString();
    }

}
