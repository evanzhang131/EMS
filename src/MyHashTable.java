import java.util.*;
import java.io.Serializable;

public class MyHashTable implements Serializable {

    // ATTRIBUTES

    // buckets is an array of ArrayList.  Each item in an ArrayList is an EmployeeInfo object.
    public ArrayList<EmployeeInfo>[] buckets;
    public int numInHashTable;


    // CONSTRUCTOR

    public MyHashTable(int howManyBuckets) {
        // Construct the hash table (open hashing/closed addressing) as an array of howManyBuckets ArrayLists.

        // Instantiate an array to have an ArrayList as each element of the array.
        buckets = new ArrayList[howManyBuckets];

        // For each element in the array, instantiate its ArrayList.
        for (int i = 0; i < howManyBuckets; i++) {
                buckets[i] = new ArrayList<>();  // Instantiate the ArrayList for bucket i.
        }

        numInHashTable = 0;
    }


    // METHODS

    public int getNumInHashTable() {
        return numInHashTable;
    }



    public int calcBucket(int keyValue) {
        // Returns the bucket number as the integer keyValue modulo the number of buckets for the hash table.
        return(keyValue % buckets.length);
    }

    public boolean validNumber(int num) {
        int hash = calcBucket(num);
        for (EmployeeInfo e : buckets[hash]) {
            if (e.empNum == num) {
                return false;
            }
        }
        return true;
    }

    public boolean addEmployee(EmployeeInfo theEmployee) {
        if (theEmployee == null) {
            return false;
        }
        else {
            int hash = calcBucket(theEmployee.getEmpNum());
            // Add the employee to the ArrayList for the targetBucket.
            for (EmployeeInfo e : buckets[hash]) {
                if (e.empNum == theEmployee.empNum) return false;
            }
            buckets[hash].add(theEmployee);
            numInHashTable++;
            return true;
        }

    } // end AddEmployee


    public EmployeeInfo removeEmployee(int employeeNum) {
        int hash = calcBucket(employeeNum);
        EmployeeInfo toRemove = null;
        for (int i = 0; i < buckets[hash].size(); i++) {
            if (buckets[hash].get(i).empNum == employeeNum) {
                toRemove = buckets[hash].remove(i);
                break;
            }
        }
        if (toRemove != null) {
            numInHashTable--;
        }
        return toRemove;
    }


    public EmployeeInfo getEmployee(int employeeNum) {
        int hash = calcBucket(employeeNum);
        EmployeeInfo getStudent = null;
        for (int i = 0; i < buckets[hash].size(); i++) {
            if (buckets[hash].get(i).empNum == employeeNum) {
                getStudent = buckets[hash].get(i);
                break;
            }
        }
        return getStudent;
    }

    public void displayContents() {

        System.out.println("\n\nDISPLAYING THE CONTENTS OF A HASH TABLE!");

        System.out.println("\n  This hash table contains " + numInHashTable + " entries.");

        // Print the employee numbers for the employees stored in each bucket's ArrayList,
        // starting with bucket 0, then bucket 1, and so on.

        for (int i = 0; i < buckets.length; i++) {

            // For the current bucket, print out the emp num for each item
            // in its ArrayList.

            System.out.println("\n  Examining the ArrayList for bucket " + i);
            int listSize = buckets[i].size();
            if (listSize == 0) {
                System.out.println("    Nothing in its ArrayList!");
            }
            else {
                for (int j = 0; j < listSize; j++) {
                   int theEmpNum = buckets[i].get(j).getEmpNum();
                   System.out.println("    Employee " + theEmpNum);
                }
            }

        }

    } // end displayContents


} // end MyHashTable
