/**
 * A class the implements the Double Hashing technique for open addressing.
 * Double Hashing uses two hash functions for probing.  
 * 
 * @author Ellis Rodriguez
 */
public class DoubleHashing extends Hashtable {

    /**
     * Builds a DoubleHashing Hashtable that will contain an
     * array with a capacity between parameter min to parameter max. 
     * 
     * @param min The minimum capacity the array could be
     * @param max The maximum capacity the array could be
     */
    public DoubleHashing(int min, int max) {
        super(min, max);
    }

     /**
     * The hash function used for Double Hashing. The function returns the position
     * to probe. 
     * 
     * @param element The HashObject the function is hashing for. The element will 
     * contain a key that the hash function uses for hashing.
     * @param index The current probe the element is on. The index is included in the 
     * hash. 
     * @return The position to probe.  
     */
    @Override
    public int hash(HashObject element, int index) {
        return (positiveMod(element.getKey().hashCode(), this.m) + index * (1 + positiveMod(element.getKey().hashCode(), this.m - 2))) % this.m;
    }
}
