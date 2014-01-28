package umm.util;

import java.util.Arrays;
import java.util.Random;

//import landscapeEC.parameters.GlobalParameters;
//import landscapeEC.parameters.StringParameter;

import org.uncommons.maths.random.CellularAutomatonRNG;



/**
 * A shared instance of a single pseudo-random number generator (PRNG) that is (or extends) java.util.Random.
 * 
 * @author mcphee
 * @version $Revision: $
 */
public class SharedPRNG
{

    /**
     * The shared instance of Random.
     */
    private static Random _instance = new CellularAutomatonRNG();

    /**
     * A private constructor to prevent people from constructing instances
     * of this singleton object.
     */
    private SharedPRNG()
    {
        // Do nothing
    }

    /**
     * Return the current instance of the pseudo-random number generator.
     * 
     * @return the current random number generator
     */
    public static Random instance()
    {
        return _instance;
    }

    /**
     * Set the instance of the pseudo-random number generator to be the specified generator.
     * 
     * @param instance
     *            the new random number generator
     */
    public static void setInstance(Random instance)
    {
        _instance = instance;
    }
 
    /**
     * Generate a new shared instance of the pseudo-random number generator with
     * a new seed.
     */
    public static void updateGenerator()
    {
        _instance = new CellularAutomatonRNG();
//        GlobalParameters.put(StringParameter.SEED.toString(), printSeedInfo());
        System.out.println("Updating the PRNG");
    }
    
    /**
     * Return a printable representation of the seed info for this pseudo-random
     * number generator.
     * 
     * @return a printable representation of the seed info for this pseudo-random
     * number generator
     */
    public static String printSeedInfo()
    {
        byte[] seed = ((CellularAutomatonRNG) _instance).getSeed();
        return Arrays.toString(seed);
    }
    
    /**
     * Set the seed for our shared pseudo-random number generator.
     * 
     * @param seed the desired seed
     */
    public static void setSeed(byte[] seed) {
        _instance = new CellularAutomatonRNG(seed);
    }

    public static void setSeed(String stringValue) {
        setSeed(parseBytes(stringValue));
    }

    /**
     * Parse the given string into an array of bytes that can be used as
     * a seed to our shared pseudo-random number generator.
     * 
     * @param seedString the string representing the array of bytes
     * @return the array of bytes
     */
    public static byte[] parseBytes(String seedString) {
        byte[] result;
        if (seedString.indexOf(',') != -1) {
            String[] parts = seedString.split(",");
            result = new byte[parts.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Byte.parseByte(parts[i]);
            }
        } else {
            result = parseHexBytes(seedString);
        }
        return result;
    }

    public static byte[] parseHexBytes(String seedString) {
        byte[] result = new byte[seedString.length() / 2];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) ((Character.digit(seedString.charAt(2*i), 16) << 4)
                               + Character.digit(seedString.charAt((2*i)+1), 16));
        }
        return result;
    }

}
