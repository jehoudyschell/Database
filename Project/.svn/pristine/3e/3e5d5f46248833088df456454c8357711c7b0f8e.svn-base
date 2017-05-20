package Values;

import Errors.BadTestException;

/**
 * A module used to compare table real values.
 */
@SuppressWarnings("serial")
public class RealValue extends AbstractValue<RealValue> {
	public Double value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 * @throws BadTestException
	 */
	public RealValue(String val) throws BadTestException {
		try {
			value = new Double(val);
		} catch (NumberFormatException e) {
			throw new BadTestException(val, "real");
		}
	}

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 */
	public RealValue(double val) {
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(RealValue o) {
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