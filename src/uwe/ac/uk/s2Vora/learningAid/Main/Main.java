package uwe.ac.uk.s2Vora.learningAid.Main;

import uwe.ac.uk.s2Vora.learningAid.UserInterface.MainFrame;


public class Main {

    
    public static void main(String[] args) {

       if(System.getSecurityManager() == null){
           System.setSecurityManager(new AdminSecurityManager());
        }   
       
       MainFrame mFrame = new MainFrame();
        
        //addShutdown hook, so that we can serialize our objects to appropriate locations
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                //remove the generated user provided code files
//                String usercode_path = conf.getProperty("userCodeDirectory");
//                File f = new File(usercode_path);
//                File [] list = f.listFiles();
//                for (File fl:list){
//                    if ( fl.getName().endsWith(".java") || fl.getName().endsWith(".class")){
//                        System.out.println("Removing file " + fl.getName());
//                        fl.delete();
//                    }
//                }
//
//            }
//        });
    }
}
