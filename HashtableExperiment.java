import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The driver class that simulates a Hashtable experiment by inserting from a number
 * of data sources into a Hashtable until a certain load factor is met. HashtableExperiment
 * then outputs the stats for the user of how well the Hashtable performed for Linear Probing
 * and Double Hashing.
 * 
 * @author Ellis Rodriguez
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
     * The main method that combines the different methods of the experiment to execute it. 
     * The main method accepts arguments from the user to determine how the experiment should
     * be ran. 
     * @param args The arguments provided by the user in the terminal. 
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
     * Inserts HashObjects with random Integer keys until the load factor is met. 
     * Does the insertions using both Linear Probing and Double Hashing. 
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
                if(this.debugLevel == 2) {
                    this.debug2Stats(object, true);
                }
                //if returnValue is -2 then there is a duplicate
                currN++; 
            } else if(this.debugLevel == 2) {
                this.debug2Stats(object, false);
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
                if(this.debugLevel == 2) {
                    this.debug2Stats(object, true);
                }
                //if returnValue is -2 then there is a duplicate
                currN++; 
            } else if(this.debugLevel == 2) {
                this.debug2Stats(object, false);
            }
        }
    }

    /**
     * Inserts HashObjects with Date object keys until the load factor is met. 
     * Does the insertions using both Linear Probing and Double Hashing.
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
                if(this.debugLevel == 2) {
                    this.debug2Stats(object, true);
                }
                //if returnValue is -2 then there is a duplicate
                currN++;
            } else if(this.debugLevel == 2){
                this.debug2Stats(object, false);
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
                if(this.debugLevel == 2) {
                    this.debug2Stats(object, true);
                }
                //if returnValue is -2 then there is a duplicate
                currN++;
            } else if(this.debugLevel == 2) {
                this.debug2Stats(object, false);
            }

            current += 1000;
        }
    }

    /**
     * Inserts HashObjects with String keys from the file word-list.txt until the load
     * factor is met. Does the insertions using both Linear Probing and Double Hashing.
     */
    private void stringSource() {
        //add try block to catch FileNotFound exceptions
        try {
            //first do Linear Probing
            //Use a scanner to read the file
            Scanner scanner = new Scanner(new File("word-list.txt")); 
            int currN = 0; //keeps track of the current number of unique objects in the Hash Table

            while(currN < this.n) {
                int returnValue;
                HashObject object;
                //check to see if scanner can scan another line
                if(scanner.hasNext()) {
                    object = new HashObject(scanner.nextLine());
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
                    if(this.debugLevel == 2) {
                        this.debug2Stats(object, true);
                    }
                    //if returnValue is -2 then there is a duplicate
                    currN++;
                } else if(this.debugLevel == 2) {
                    this.debug2Stats(object, false);
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
                HashObject object;
                //check to see if scanner can scan another line
                if(scanner.hasNext()) {
                    object = new HashObject(scanner.nextLine());
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
                    if(this.debugLevel == 2) {
                        this.debug2Stats(object, true);
                    }
                    //if returnValue is -2 then there is a duplicate
                    currN++;
                } else if(this.debugLevel == 2) {
                    this.debug2Stats(object, false);
                }
            }

            //close scanner so it does not leak
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Prints a message to the terminal on how to use HashtableExperiment. It includes 
     * the parameters HashtableExperiment has along with the values that it accepts for 
     * each parameter.
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
     * Prints the debugging output for debug levels 0-1. This includes all the statistics 
     * on how the LinearProbing and DoubleHashing hashtables performed. The statistics 
     * include things such as avg. number of probes, number of duplicates, size of the 
     * hashtable, etc. 
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
     * Processes the arguments the user gave. The processing includes checking for incorrect
     * output and assigning the arguments to the appropriate properties of the HashtableExperiment. 
     * @param args The arguments that are being processed
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
     * Determines which data source to use for the experiment based on the user's provided 
     * dataSource argument. The three possible data sources are Strings, Integers, and Date 
     * objects. 
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
     * Determines which debug stats are outputted based on the user's argument for 
     * debug level. 
     */
    private void determineDebugLevelOption() {
        if(this.debugLevel != 2) {
            this.debugStats();
        }
    }

    /**
     * The debug output method for debug level 2 that prints details on how each insertion
     * attempt did. The method either prints that the HashObject parameter element was successfully 
     * inserted or that it was a duplicate. 
     * 
     * @param element The HashObject that was attempted to be inserted
     * @param wasInserted A boolean that communicates whether the element was inserted 
     * or a duplicate
     */
    private void debug2Stats(HashObject element, boolean wasInserted) {
        //will implement this debug function later... not sure yet on what I need to do
        if(wasInserted) {
            System.out.println(element.getKey() + " was inserted");
        } else {
            System.out.println(element.getKey() + " was a duplicate");
        }
    }
}