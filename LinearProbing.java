/**
 * A class that implements the LinearProbing technique for open addressing. 
 * LinearProbing will probe linearly through the HashTable until an empty slot 
 * is found. 
 * 
 * @author Ellis Rodriguez
 */
public class LinearProbing extends Hashtable{

    /**
     * The hash function used for Linear Probing. The function returns the position
     * to probe. 
     * 
     * @param element The HashObject the function is hashing for. The element will 
     * contain a key that the hash function uses for hashing.
     * @return The position to probe.  
     */
    @Override
    public int hash(HashObject element) {
        int key = this.positiveMod(element.hashCode(), this.m);

        return key;
    }
    
}
