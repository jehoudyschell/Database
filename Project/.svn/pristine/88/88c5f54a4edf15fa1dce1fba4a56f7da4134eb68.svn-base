package Values;

import Errors.BadTestException;

/**
 * A module used to compare table boolean values.
 */
@SuppressWarnings("serial")
public class BooleanValue extends AbstractValue<BooleanValue> {
	public Boolean value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 * @throws BadTestException
	 */
	public BooleanValue(String val) throws BadTestException {
		if (!val.equalsIgnoreCase("true") && !val.equalsIgnoreCase("false"))
			throw new BadTestException(val, "boolean");
		value = new Boolean(val);
	}

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 */
	public BooleanValue(boolean val) {
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(BooleanValue o) {
		if (value.toString().equals(o.value.toString()))
			return 0;
		else if (value == true)
			return -1;
		else
			return 1;
	}

	/**
	 * Returns the data for this Value.
	 * 
	 * @returns data
	 */
	public String toString() {
		return (value + "\t");
	}
}