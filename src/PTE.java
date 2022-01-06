import java.io.Serializable;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Evan Zhang
 */

/**
 * Part-Time Employee Class
 * 
 */
public class PTE extends EmployeeInfo implements Serializable{
    
    
    public double hourlyWage;
    public double hoursPerWeek;
    public double weeksPerYear;
    
    public PTE(int eN, String fN, String lN, String g, String wL, double dR, double hW, double hPW, double wPY, ImageIcon f) {
        super(eN, fN, lN, g, wL, dR, f);
        hourlyWage = hW;
        hoursPerWeek = hPW;
        weeksPerYear = wPY;
    }
    
    public double calcAnnualGrossIncome() {
        return hourlyWage * hoursPerWeek * weeksPerYear;
    }

    public double calcAnnualNetIncome() {
        return hourlyWage * hoursPerWeek * weeksPerYear * (1-deductRate);
    }
}
