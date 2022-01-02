import java.io.Serializable;
import java.io.File;

public class EmployeeInfo implements Serializable {

    
    // ATTRIBUTES
    public int empNum;
    public String firstName;
    public String lastName;
    public String gender;
    public String workLoc;
    public double deductRate; // e.g. 0.21 for 21%
    public File profilePic;
    
    
    // CONSTRUCTORS
    
    public EmployeeInfo(int eN, String fN, String lN, String g, String wL, double dR, File pfp) {
    	empNum = eN;
    	firstName = fN;
    	lastName = lN;
    	gender = g;
    	workLoc = wL;
    	deductRate = dR;
        profilePic = pfp;
    }
    
    
    // METHODS
    
    public int getEmpNum() {
    	return empNum;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
}
