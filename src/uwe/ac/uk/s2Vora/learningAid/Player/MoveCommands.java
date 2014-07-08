package uwe.ac.uk.s2Vora.learningAid.Player;


public interface  MoveCommands {
    

    //Right, left, up, down is clear
    //is moving?
    
    //Does the class name need to have the word "interface" to easily recognise that
    // it is an interface?
    
    //movement
    public void moveForwards(int tilesToMove);
    public void moveBackwards(int tilesToMove);
    public void turnLeft();
    public void turnRight();
    
}
