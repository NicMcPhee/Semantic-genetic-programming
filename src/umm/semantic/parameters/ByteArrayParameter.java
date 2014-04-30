package umm.semantic.parameters;

import umm.util.SharedPRNG;

public enum ByteArrayParameter
{
    SEED {
        @Override
        public byte[] getValue() {
            if (!GlobalParameters.isSet(toString())) {
                String seed = SharedPRNG.printSeedInfo();
                GlobalParameters.put(toString(), seed);
            }
            String stringValue = GlobalParameters.getStringValue(toString());
            String[] valueStrings = stringValue.trim().split(SEPARATOR);
            byte[] values = new byte[valueStrings.length];
            for(int i = 0; i < valueStrings.length; i++)
            {
                values[i] = Byte.parseByte(valueStrings[i]);
            }
             
            return values;
        }
    };

    private static final String SEPARATOR = ",";
    /**
     * Return the (String) value of this parameter. It will trim all whitespace
     * before returning the value.
     * 
     * @return the String value of this parameter
     */
    public byte[] getValue()
    {
		String[] valueStrings = GlobalParameters.getStringValue(toString()).trim().split(SEPARATOR);
		byte[] values = new byte[valueStrings.length];
        for(int i = 0; i < valueStrings.length; i++)
        {
            values[i] = Byte.parseByte(valueStrings[i]);
        }
         
        return values;
    }
}