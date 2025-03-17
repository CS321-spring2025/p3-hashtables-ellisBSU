/**
 * 
 */
public class HashObject {
    private Object key;
    private int freqCount; 
    private int probeCount; 

    public HashObject(Object key) {
        this.key = key;
        this.freqCount = 1;
        this.probeCount = 0;
    }

    public Object getKey() {
        return this.key;
    }

    public int getFreq() {
        return this.freqCount;
    }

    public int getProbes() {
        return this.probeCount;
    }

    public void setFreq(int newFreq) {
        //freq count has to be at least 1. 
        if(newFreq > 0) {
            this.freqCount = newFreq;
        }
    }

    public void setProbes(int newProbes) {
        //probe count has to be at least 0. 
        if(newProbes > -1) {
            this.probeCount = newProbes;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return this.key.toString() + " " + (this.freqCount) + " " + this.probeCount;
    }

    @Override 
    /*
     * return true if HashObjects are equal
     */
    public boolean equals(Object obj) {
        //compares this.key with the parameter that should be a key
        //not a HashObject
        return this.key.equals(obj);
    }
}
