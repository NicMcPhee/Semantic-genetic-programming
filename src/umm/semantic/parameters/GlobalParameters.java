package umm.semantic.parameters;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import umm.util.SharedPRNG;


/**
 * A static, global access point for the current set of parameters.
 * 
 * @author crane
 * @version $Revision: $
 */
public class GlobalParameters {
    private static Parameters _parameters = new Parameters();
    private static File _paramsFile;

    /*
     * private static File _logFile; private static Properties
     * _loggerProperties;
     */
    /**
     * A private constructor to prevent an instance of this from being
     * constructed.
     */
    private GlobalParameters() {
        // Do nothing
    }

    /**
     * Get the value of the specified parameter as a String.
     * 
     * @param name
     *            the name of the desired parameter
     * @return the value of this parameter as a String
     */
    public static String getStringValue(String name) {
        String valueString = _parameters.get(name);
        if (valueString == null) {
            throw new UnsetParameterException("Parameter " + name + " doesn't have a value");
        }
        return valueString;
    }

    /**
     * Determine if the given parameter has a value.
     * 
     * @param name
     *            the name of the parameter in question
     * @return true if the given parameter is set, false otherwise
     */
    public static boolean isSet(String name) {
        String valueString = _parameters.get(name);
        return valueString != null;
    }

    /**
     * Read the parameter values from the given Properties file.
     * 
     * @param params
     *            the file object containing the properties
     * @throws IOException
     *             if there are problems with the file
     */
    public static void setParameters(File params) throws IOException {
        _paramsFile = params;
        _parameters = new Parameters(_paramsFile);
        if (isSet(ByteArrayParameter.SEED.toString())) {
            SharedPRNG.setSeed(getStringValue(ByteArrayParameter.SEED.toString()));
        } else {
            SharedPRNG.updateGenerator();
            String seed = SharedPRNG.printSeedInfo();
            _parameters.put(ByteArrayParameter.SEED.toString(), seed);
        }
    }
    
    public static Parameters getParameters() {
        return (Parameters) _parameters.clone();
    }

    /**
     * Set the global parameters to the given Parameters object.
     * 
     * @param params
     *            the desired set of parameter values
     */
    public static void setParameters(Parameters params) {
        _paramsFile = null;
        _parameters = params;
    }

    /*    *//**
     * Set up the logging for the global parameters object. This should be
     * called at the beginning of the program to correctly enable the logging of
     * parameter values.
     * 
     * @throws IOException
     *             if there are problems with the logging files
     */
    /*
     * public static void setupLogging() throws IOException { _loggerProperties
     * = new Properties(); _logFile = new File(_paramsFile.getParent() +
     * File.separatorChar + StringParameter.LOG_FILE.getValue()); final
     * FileInputStream loggingPropertiesStream = new FileInputStream(_logFile);
     * _loggerProperties.load(loggingPropertiesStream);
     * loggingPropertiesStream.close(); }
     */

    /*   *//**
     * Return the logger properties used to configure the logger.
     * 
     * @return the logger configuration properties
     */
    /*
     * public static Properties getLoggerProperties() { return
     * _loggerProperties; }
     */

    /**
     * Return a set of all the parameter names defined in in the global
     * parameters object.
     * 
     * @return the set of all the parameter names
     */
    public static Set<String> getParameterNames() {
        return _parameters.keySet();
    }

    /**
     * Add a String entry to the shared parameters object.
     * 
     * @param key
     *            the key
     * @param value
     *            the associated value
     */
    public static void put(String key, String value) {
        _parameters.put(key, value);
    }

    public static int getIntValue(String string) {
        String valueString = getStringValue(string);
        return Integer.parseInt(valueString);
    }
    
    public static String[] getStringArrValue(String string) {
    	String[] valueString = getStringValue(string).trim().split(",");
    	String[] value = new String[valueString.length];
    	for(int i = 0; i < valueString.length; i++)
        {
            value[i] = valueString[i] + "";
        }
    	return valueString;
    }
}