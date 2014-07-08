
package uwe.ac.uk.s2Vora.learningAid.Main;

import java.security.Permission;


public class AdminSecurityManager extends SecurityManager{
    
    
    //The AdminSeucurityManager is used to grant all permissions.
    public AdminSecurityManager(){
        super();
    }
    
    //Give me access to all features.
    @Override
    public void checkPermission(Permission perm) {}

}
