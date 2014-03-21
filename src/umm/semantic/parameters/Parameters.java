package umm.semantic.parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

/**
 * A simple parameters class that maps keys (as Strings) to values (also as
 * Strings). The decoding classes (like DoubleParameter) are then used to decode
 * these strings into the appropriate values.
 * 
 * @author mcphee
 * @version $Revision: $
 */
public class Parameters extends HashMap<String,String> {
    /**
     * A generated serialization ID to make a warning go away.
     */
    private static final long serialVersionUID = 2148506734857698067L;

    /**
     * Constructs a Parameters object that will contain all the (key, value)
     * pairs in the specified properties file.
     * 
     * @param file
     *            a Java properties file
     * @throws IOException
     *             if there are problems with the file
     */
    @SuppressWarnings("unchecked")
	public Parameters(File file) throws IOException {
        ArrayList<String> includes = new ArrayList<String>();
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.startsWith("#include ")) {
                includes.add(line.substring(line.indexOf('"') + 1, line.lastIndexOf('"')));
                System.out.println(includes.get(includes.size() - 1));
            }
        }
        scan.close();

        Properties properties = new Properties();

        String path_prefix = file.getParent();
        if(path_prefix == null){
            path_prefix = "";
        }
        for (String s : includes) {
            File defaultProp = new File(path_prefix + File.separator + s);
            loadFile(defaultProp, properties);
        }
        loadFile(file, properties);

        for (Enumeration<String> propNames = (Enumeration<String>) properties.propertyNames(); propNames.hasMoreElements();) {
            String name = propNames.nextElement();
            put(name, properties.getProperty(name));
        }
    }

    private void loadFile(File file, Properties properties)
            throws FileNotFoundException, IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        fileInputStream.close();
    }

    /**
     * Constructs an empty Parameters object.
     */
    public Parameters() {
        // An empty constructor so we can construct a parameters object
        // and add entries by hand if/when needed.
    }
}