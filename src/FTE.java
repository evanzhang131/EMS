import java.io.Serializable;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Evan Zhang
 */

// FULL TIME EMPLOYEE

public class FTE extends EmployeeInfo implements Serializable {
    
    /**
     *
     */
    public double yearlySalary;
    
    /**
     *
     * @param eN
     * @param fN
     * @param lN
     * @param s
     * @param wL
     * @param dR
     * @param yS
     */
    public FTE(int eN, String fN, String lN, String g, String wL, double dR, double yS, ImageIcon f) {
        super(eN, fN, lN, g, wL, dR, f);
        yearlySalary = yS;
        
    }
    
    public double getYearlySalary() {
        return yearlySalary;
    }
    
    
    public double calcAnnualNetIncome() {
        return (yearlySalary *(1.0 - deductRate));
    }
}
