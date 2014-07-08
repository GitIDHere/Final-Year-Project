package uwe.ac.uk.s2Vora.learningAid.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LearningConfig {
    
    private static Properties propconfig;
    static LearningConfig config = null;
    
    private LearningConfig(){
        propconfig = new Properties();
    }
    
    public static LearningConfig getInstance(){
        if (config == null ){
            config = new LearningConfig();
            init();
        }
        return config;
    }
    
    static void init(){
        try{
            System.out.println("Init config");
            InputStream propertiesInputStream = config.getClass().getResourceAsStream("/Resources/config.properties");
            propconfig.load(propertiesInputStream);
        } catch (Exception ex) {
            Logger.getLogger(LearningConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getProperty(String property_name){
        return propconfig.getProperty(property_name);
    }
}
