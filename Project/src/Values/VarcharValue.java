package Values;

/**
 * A module used to compare table varchar values.
 */
@SuppressWarnings("serial")
public class VarcharValue extends AbstractValue<VarcharValue> {
	public String value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 */
	public VarcharValue(String val) {
		if (val.indexOf("'") == 0 || val.lastIndexOf("'") == (val.length() - 1))
			val = val.substring(1, val.length() - 1).trim();
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(VarcharValue o) {
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