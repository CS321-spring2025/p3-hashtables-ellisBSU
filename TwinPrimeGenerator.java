import java.util.Arrays;

/**
 * Helper class that is used to provide the static method generateTwinPrime() 
 * that generates a size for the array of a Hashtable. 
 * 
 * @author Ellis Rodriguez 
 */
public class TwinPrimeGenerator {
    
    /**
     * Function will generate prime numbers then return the greater value of a set of 
     * twin prime numbers. The set of twin prime numbers will be the smallest set in the 
     * range of parameter min to parameter max. 
     * @param min The minimum value that the prime number could be
     * @param max The maximum value that the prime number could be
     * @return The greater value of a twin set of prime numbers
     */
    public static int generateTwinPrime(int min, int max) {
        boolean primeNumbers[] = new boolean[max + 1]; 

        //fill the whole array as true
        Arrays.fill(primeNumbers, true);

        for(int p = 2; p * p<= max; p++) {
            
            if(primeNumbers[p]) {
                for(int i = p*p; i <= max; i+=p) {
                    primeNumbers[i] = false;
                }
            }
        }

        for(int i = min; i <= max; i++) {
            if(primeNumbers[i] && i + 2 <= max && primeNumbers[i + 2]) {
                return i + 2; 
            }
        }
        
        //return -1 to communicate that we could not find twin prime numbers
        return -1;
    }
}
