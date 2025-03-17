/**
 * The representation of an element inside of a Hashtable. A HashObject stores different
 * properties such as its frequency count, probe count, and key.  
 */
public class HashObject {
    private Object key;
    private int freqCount; 
    private int probeCount; 

    /**
     * Builds a new HashObject that stores a user inputted key. 
     * @param key The key that the HashObject will store. 
     */
    public HashObject(Object key) {
        this.key = key;
        this.freqCount = 1;
        this.probeCount = 0;
    }

    /**
     * Gets the key stored in the HashObject.
     * @return The key stored in the HashObject. The key is an
     * Object type. 
     */
    public Object getKey() {
        return this.key;
    }

    /**
     * Get the frequency count of the HashObject. In other words, 
     * find how many of the same HashObject are stored inside the 
     * Hashtable. 
     * @return The frequency of this HashObject.
     */
    public int getFreq() {
        return this.freqCount;
    }

    /**
     * Get the number of probes it took to insert the HashObject
     * inside the HashTable.  
     * @return The number of probes it took to insert this HashObject
     * inside the Hashtable. 
     */
    public int getProbes() {
        return this.probeCount;
    }

    /**
     * Sets the frequency count for this HashObject.
     * 
     * @param newFreq The new frequency count for this HashObject. 
     */
    public void setFreq(int newFreq) {
        //freq count has to be at least 1. 
        if(newFreq > 0) {
            this.freqCount = newFreq;
        }
    }

    /**
     * Sets the new probe count for this HashObject. 
     * 
     * @param newProbes The new probe count for this HashObject. 
     */
    public void setProbes(int newProbes) {
        //probe count has to be at least 0. 
        if(newProbes > -1) {
            this.probeCount = newProbes;
        }
    }


    /**
     * Returns a string representation of this HashObject. 
     * This includes this HashObject's key, frequency, and 
     * probe count. 
     * 
     * @return The String representation of this HashObject. 
     */
    @Override
    public String toString() {
        return this.key.toString() + " " + (this.freqCount) + " " + this.probeCount;
    }

    /**
     * Tests whether this HashObject's key is equal to 
     * Object parameter obj. Returns true if they are equal. 
     * 
     * @param obj The Object being compared to this HashObject's key.
     * @return True if this HashObject's key is equal to parameter obj.
     */
    @Override
    public boolean equals(Object obj) {
        //compares this.key with the parameter that should be a key
        //not a HashObject
        return this.key.equals(obj);
    }
}
