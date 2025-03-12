/**
 * An abstract class used to represent a hashtable. The class 
 * supports general set methods such as insert(), search(), and
 * delete().
 * 
 * @author Ellis Rodriguez
 */
public abstract class Hashtable {
    protected int m;
    protected HashObject[] array;

    /**
     * Builds a Hashtable that will store elements in an array with
     * a size between the range of parameter min to parameter max. 
     * @param min The minimum size the array could be
     * @param max The maximum size the array could be
     */
    public Hashtable(int min, int max) {
        this.m = TwinPrimeGenerator.generateTwinPrime(min, max);
        this.array = new HashObject[this.m];
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

        int q = hash(element); 

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
     * else it returns -1 if the element could not be inserted. 
     * @param element The HashObject being inserted into the Hashtable.
     * @return The index of where the element was inserted else -1 if the 
     * element could not be inserted. 
     */
    public int insert(HashObject element) {
        int i = 0; 

        int q = hash(element);

        while(i != this.m) {
            if(this.array[q] == null) {
                this.array[q] = element;
                return q;
            }
            
            i++;
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
     * The hash method that both LinearProbing and DoubleHashing must implement. 
     * This function is used to determine which location to probe for in a Hashtable.
     * The function returns the integer result of the hash function. The HashObject parameter
     * element is used to determine which key to use for the hash function. 
     * @param element The HashObject the function is hashing for. The element will contain a key 
     * that the hash function uses for hashing.
     * @return The integer result from the hash calculation.  
     */
    public abstract int hash(HashObject element);
}
