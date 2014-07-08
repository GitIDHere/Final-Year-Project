package uwe.ac.uk.s2Vora.learningAid.Main;

import java.io.FilePermission;
import java.security.Permission;


public class UserSecurityManager extends SecurityManager{
    
    public UserSecurityManager(){
        super();
    }
    
    //This is the SecurityManager class which is used to restrict he execution of malicious code.
    @Override
        public void checkPermission(Permission perm) {
            if (perm instanceof FilePermission) {
                if ( perm.getActions().equals("read")) {
                    return;
                }else if(perm.getActions().equals("write") || perm.getActions().equals("delete") && perm.getName().indexOf("Temp")> 0){
                    return;
                }
                throw new SecurityException();
            }
        }

}
