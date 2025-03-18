import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * An abstract class used to represent a hashtable. The class supports
 * the general set methods insert() and search().
 * 
 * @author Ellis Rodriguez
 */
public abstract class Hashtable {
    protected int m;
    protected HashObject[] array;
    protected int size;

    /**
     * Builds a Hashtable that will store elements in an array with
     * a size between the range of parameter min to parameter max. 
     * @param min The minimum size the array could be
     * @param max The maximum size the array could be
     */
    public Hashtable(int min, int max) {
        this.m = TwinPrimeGenerator.generateTwinPrime(min, max);
        this.array = new HashObject[this.m];
        this.size = 0;
    }

    /**
     * Searches for the HashObject parameter element inside the Hashtable. 
     * The function returns the position of the element if found else it returns 
     * -1
     * @param element The element that is searched for in the Hashtable. 
     * @return An index of where the element is stored if the element is 
     * found else -1 if the element is not found. 
     */
    public int search(HashObject element) {
        int i = 0;

        int q = hash(element, i); 

        while(q != -1 && i != this.m) {
            if(this.array[q] == element) {
                return q;
            }

            i++; 
        }

        return -1;
    }

    /**
     * Inserts the HashObject parameter element inside the the Hashtable.
     * The function returns the index of where the element was inserted 
     * else it returns -1 if the element could not be inserted or -2 if the 
     * element is a duplicate. 
     * @param element The HashObject being inserted into the Hashtable.
     * @return The index of where the element was inserted else -1 if the 
     * element could not be inserted or -2 if the element is a duplicate. 
     */
    public int insert(HashObject element) {
        int i = 0; 

        int q = hash(element, i);

        while(i != this.m) {
            if(this.array[q] == null) {
                this.array[q] = element;
                this.array[q].setProbes(i + 1);
                this.size++;
                return q;
            }

            if(this.array[q].equals(element.getKey())) {
                this.array[q].setFreq(this.array[q].getFreq() + 1);
                return -2;
            }
            
            i++;
            q = hash(element, i);
        }

        return -1;
    }

    /**
     * A helper method to the hash function that guarantees the mod operation
     * will always return a nonnegative value after the dividend is modded by 
     * the divisor. The function returns the quotient value after the dividend 
     * is modded by the divisor. 
     * @param dividend The value being modded. 
     * @param divisor The value that the dividend is being modded by. 
     * @return A nonnegative quotient value calculated from modding the
     * dividend by the divisor. 
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if(quotient < 0) {
            quotient += divisor;
        }

        return quotient;
    }

    /**
     * Returns the capacity of the Hashtable
     * @return The capacity of the Hashtable
     */
    public int getTableCapacity() {
        return this.m;
    }

    /**
     * Returns the size of the Hashtable. The
     * size is the number of unique elements inside 
     * the Hashtable. 
     * 
     * @return The size of the Hashtable
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Calculates the debug stats for this Hashtable. Prints those stats to the 
     * console. The stats include numbers such as the avg no. of probes, total number
     * of elements inserted, number of duplicate, etc. 
     * @param debugLevel The debugLevel of HashtableExperiment. If 1 then getStats() dumps
     * the properties of each element inside of another file. 
     * @param linearOrDouble A boolean that is used to determine which file we should dump 
     * the properties of each element inside of. 
     */
    public void getStats(int debugLevel, int linearOrDouble) {
        System.out.println("Hashtable Experiment: size of hash table is " + this.getSize());

        int totalInserted = 0;
        int totalNumOfProbes = 0;
        int duplicates = 0;
        for(int i = 0; i < this.m; i++) {
            if(this.array[i] != null) {
                totalInserted += this.array[i].getFreq();
                totalNumOfProbes += this.array[i].getProbes();
                duplicates += (this.array[i].getFreq() - 1);
            }
        }

        Double avgProbe = (double) totalNumOfProbes / this.getSize(); //average num of probes
        //figure out if we need to add a 0 at the end
        avgProbe = (double) Math.round(avgProbe * 100) / 100;
        String avgProbeString = avgProbe.toString();
        if(avgProbe * 100 % 10 == 0) {
            avgProbeString += '0';
        }

        System.out.println("      Inserted " + totalInserted + " elements, of which " + duplicates + " were duplicates");
        System.out.println("      Avg. no. of probes = " + avgProbeString);
        if(debugLevel == 1) {
            System.out.println("HashtableExperiment: Saved dump of hash table");
            if(linearOrDouble == 1) {
                System.out.println();
                this.dumpToFile("linear-dump.txt");
            } else {
                this.dumpToFile("double-dump.txt");
            }
        }
    }

    /**
     * Writes the statistics of each HashObject element to a text file. This is 
     * used for debug level 1 in the HashtableExperiment.
     * 
     * @param fileName The name of the file we are writing to. 
     */
    private void dumpToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            for(int i = 0; i < this.array.length; i++) {
                if(this.array[i] != null) {
                    out.println("table[" + i + "]: " + this.array[i].toString());
                }
            }
            out.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }
    }

    /**
     * The hash method that both LinearProbing and DoubleHashing must implement. 
     * This function is used to determine which location to probe for in a Hashtable.
     * The function returns the integer result of the hash function that is then used 
     * for probing.
     *  
     * @param element The HashObject the function is hashing for. The element will contain a key 
     * that the hash function uses for hashing.
     * @param index The current probe the element is on. The index is included in the 
     * hash. 
     * @return The integer result from the hash calculation.  
     */
    public abstract int hash(HashObject element, int index);
}
