package Values;

import Errors.BadTestException;

/**
 * A module used to compare table integer values.
 */
@SuppressWarnings("serial")
public class IntegerValue extends AbstractValue<IntegerValue> {
	public Integer value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 * @throws BadTestException
	 */
	public IntegerValue(String val) throws BadTestException {
		try {
			value = new Integer(val);
		} catch (NumberFormatException e) {
			throw new BadTestException(val, "integer");
		}
	}

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 */
	public IntegerValue(int val) {
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(IntegerValue o) {
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