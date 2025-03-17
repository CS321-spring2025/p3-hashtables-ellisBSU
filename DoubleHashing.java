/**
 * A class the implements the Double Hashing technique for open addressing.
 * Double Hashing uses two hash functions for probing where one hash function 
 * is used for initial probing then for consecutive probes the second hash 
 * function is used instead. 
 * 
 * @author Ellis Rodriguez
 */
public class DoubleHashing extends Hashtable {

    public DoubleHashing(int min, int max) {
        super(min, max);
    }

     /**
     * The hash function used for Double Hashing. The function returns the position
     * to probe. 
     * 
     * @param element The HashObject the function is hashing for. The element will 
     * contain a key that the hash function uses for hashing.
     * @param index 
     * @return The position to probe.  
     */
    @Override
    public int hash(HashObject element, int index) {
        return (positiveMod(element.getKey().hashCode(), this.m) + index * (1 + positiveMod(element.getKey().hashCode(), this.m - 2))) % this.m;
    }
}
