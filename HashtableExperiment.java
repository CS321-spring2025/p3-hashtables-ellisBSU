import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 */
public class HashtableExperiment {
    private int dataSource;
    private double loadFactor;
    private int debugLevel; 
    private int n;
    private int m;
    private Hashtable linearProbingTable;
    private Hashtable doubleHashingTable;

    /**
     * 
     * @param args
     */
     public static void main(String[] args) {
        if(args.length < 2) {
            printUsage();
            return;
        }

        HashtableExperiment experiment = new HashtableExperiment();
        experiment.processArguments(args);

        experiment.determineDataSourceExperiment();

        experiment.determineDebugLevelOption();

        //after printing the details for the debug the program should now be over
    }

    /**
     * 
     * @param table
     * @param loadFactor
     * @param m
     */
    private void integerSource() {
        //first do Linear Probing
        Random rand = new Random();
        int currN = 0; //keeps track of the current number of unique objects in the Hash Table
        
        while(currN < this.n) {
            HashObject object = new HashObject(rand.nextInt());
            int returnValue = this.linearProbingTable.insert(object); 

            if(returnValue == -1) {
                //return since value could not be inserted so loadFactor > 1
                return;
            } else if(returnValue != 2){
                //if returnValue is -2 then there is a duplicate
                currN++; 
            }
        }

        //now for Double Hashing but first reset values
        rand = new Random();
        currN = 0; 

        while(currN < this.n) {
            HashObject object = new HashObject(rand.nextInt());
            int returnValue = this.doubleHashingTable.insert(object); 

            if(returnValue == -1) {
                //return since value could not be inserted so loadFactor > 1
                return;
            } else if(returnValue != 2){
                //if returnValue is -2 then there is a duplicate
                currN++; 
            }
        }
    }

    /**
     * 
     * @param table
     * @param loadFactor
     * @param m
     */
    private void dateSource() {
        //first do Linear Probing
        long current = new Date().getTime(); 
        long initalVal = current; //keep value stored for double hashing trial
        int currN = 0; //keeps track of the current number of unique objects in the Hash Table

        while(currN < this.n) {
            HashObject object = new HashObject(new Date(current));
            int returnValue = this.linearProbingTable.insert(object);

            if(returnValue == -1) {
                //return since value could not be inserted so loadFactor > 1
                return;
            } else if(returnValue != -2) {
                //if returnValue is -2 then there is a duplicate
                currN++;
            }

            current += 1000;
        }

        //now for Double Hashing but first reset values
        current = initalVal;
        currN = 0;

        while(currN < this.n) {
            HashObject object = new HashObject(new Date(current));
            int returnValue = this.doubleHashingTable.insert(object);

            if(returnValue == -1) {
                //return since value could not be inserted so loadFactor > 1
                return;
            } else if(returnValue != -2) {
                //if returnValue is -2 then there is a duplicate
                currN++;
            }

            current += 1000;
        }
    }

    /**
     * 
     * @param table
     * @param loadFactor
     * @param m
     */
    private void stringSource() {
        //add try to catch exceptions for File class
        try {
            //first do Linear Probing
            //Use a scanner to read the file
            Scanner scanner = new Scanner(new File("word-list.txt")); 
            int currN = 0; //keeps track of the current number of unique objects in the Hash Table

            while(currN < this.n) {
                int returnValue;
                //check to see if scanner can scan another line
                if(scanner.hasNext()) {
                    HashObject object = new HashObject(scanner.nextLine());
                    returnValue = this.linearProbingTable.insert(object);
                } else {
                    System.out.println("ERROR: Scanner could not read word-list.txt");
                    scanner.close();
                    return;
                }

                if(returnValue == -1) {
                    //return since value could not be inserted so loadFactor > 1
                    scanner.close();
                    return;
                } else if(returnValue != -2) {
                    //if returnValue is -2 then there is a duplicate
                    currN++;
                }
            }
            //close scanner so it does not leak
            scanner.close();


            //Now do Double Hashing but first reset values

            //Use a scanner to read the file
            scanner = new Scanner(new File("word-list.txt")); 
            currN = 0; //keeps track of the current number of unique objects in the Hash Table

            while(currN < this.n) {
                int returnValue;
                //check to see if scanner can scan another line
                if(scanner.hasNext()) {
                    HashObject object = new HashObject(scanner.nextLine());
                    returnValue = this.doubleHashingTable.insert(object);
                } else {
                    scanner.close();
                    System.out.println("ERROR: Scanner could not read word-list.txt");
                    return;
                }

                if(returnValue == -1) {
                    //return since value could not be inserted so loadFactor > 1
                    scanner.close();
                    return;
                } else if(returnValue != -2) {
                    //if returnValue is -2 then there is a duplicate
                    currN++;
                }
            }
            //close scanner so it does not leak
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }
    }

    /**
     * 
     */
    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println("       <dataSource>: 1 ==> random numbers");
        System.out.println("                     2 ==> date value as a long");
        System.out.println("                     3 ==> word list");
        System.out.println("       <loadFactor>: The ratio of objects to table size,");
        System.out.println("                       denoted by alpha = n/m");
        System.out.println("       <debugLevel>: 0 ==> print summary of experiment");
        System.out.println("                     1 ==> save the two hash tables to a file at the end");
        System.out.println("                     2 ==> print debugging output for each insert");
    }

    /**
     * 
     * @param dataSource
     * @param m
     * @param loadFactor
     * @param table
     */
    private void debugStats() {
        String dataType = "";
        switch(this.dataSource) {
            case 1: 
                dataType = "Integer";
                break;
            case 2: 
                dataType = "Date";
                break;
            case 3: 
                dataType = "Word-List";
                break;
        }

        System.out.println("HashtableExperiment: Found a twin prime for table capacity: " + this.linearProbingTable.getTableCapacity());
        System.out.println("HashtableExperiment: Input: " + dataType + "   Loadfactor: " + this.loadFactor + "\n");

        System.out.println("      Using Linear Probing");
        this.linearProbingTable.getStats(debugLevel, 1);

        System.out.println("      Using Double Hashing");
        this.doubleHashingTable.getStats(debugLevel, 0);
    }

    /**
     * 
     * @param args
     */
    private void processArguments(String[] args) {
        this.dataSource = Integer.parseInt(args[0]);
        if(this.dataSource > 3 || this.dataSource < 1) {
            System.out.println("ERROR: dataSource must be a value between 1-3");
            return;
        }

        this.loadFactor = Double.parseDouble(args[1]);

        this.linearProbingTable = new LinearProbing(95500, 96000);
        this.doubleHashingTable = new DoubleHashing(95550, 96000);
        
        //the table capacity will be the same for both LinearProbing and DoubleHashing
        this.m = doubleHashingTable.getTableCapacity();

        this.n = (int) Math.ceil(this.loadFactor * this.m);

        if(args.length == 3) {
            this.debugLevel = Integer.parseInt(args[2]); 
            if(this.debugLevel > 2 || this.debugLevel < 0) {
                System.out.println("ERROR: debugLevel must be a value between 0-2");
                return;
            }
        } else {
            this.debugLevel = 0; 
        }
    }

    /**
     * 
     */
    private void determineDataSourceExperiment() {
        //no default case needed, already made sure dataSource is between 1-3
        switch(this.dataSource) {
            case 1: 
                this.integerSource();
                break;
            case 2: 
                this.dateSource();
                break;
            case 3: 
               this.stringSource();
               break;
        }
    }

    /**
     * 
     */
    private void determineDebugLevelOption() {
        //no default case needed, already made sure debugLevel is between 0-2
        switch(debugLevel) {
            case 0: 
                this.debugStats();
                break;
            case 1:
                this.debugStats();
                break;
            case 2: 
                System.out.println("print details for debug level 2...");
                break;
        }
    }

    /**
     * 
     */
    private static void debug2Stats() {
        //will implement this debug function later... not sure yet on what I need to do
    }
}