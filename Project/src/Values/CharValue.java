package Values;

import Errors.BadTestException;

/**
 * A module used to compare table char values.
 */
@SuppressWarnings("serial")
public class CharValue extends AbstractValue<CharValue> {
	public String value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 * @throws BadTestException
	 */
	public CharValue(String val, int length) throws BadTestException {
		if (val.indexOf("'") != 0 || val.lastIndexOf("'") != val.length() - 1)
			throw new BadTestException(val, "char(" + length + ")");
		val = val.substring(1, val.length() - 1).trim();
		if (!(val.length() == length))
			throw new BadTestException(val, "char(" + length + ")");
		value = val;
	}

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 */
	public CharValue(String val) {
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(CharValue o) {
		return value.compareTo(o.value);
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