/**
 * A class that implements the LinearProbing technique for open addressing. 
 * LinearProbing will probe linearly through the HashTable until an empty slot 
 * is found. 
 * 
 * @author Ellis Rodriguez
 */
public class LinearProbing extends Hashtable{

    /**
     * Builds a LinearProbing Hashtable that will contain an
     * array with a capacity between parameter min to parameter max. 
     * 
     * @param min The minimum capacity the array could be
     * @param max The maximum capacity the array could be
     */
    public LinearProbing(int min, int max) {
        super(min, max);
    }

    /**
     * The hash function used for Linear Probing. The function returns the position
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
        int key = this.positiveMod(this.positiveMod(element.getKey().hashCode(), this.m) + index, this.m);

        return key;
    }
}
