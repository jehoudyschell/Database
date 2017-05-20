package Values;

import java.io.Serializable;

/**
 * A module used to compare table values.
 * 
 * @param <v>
 *            Type of value being compared.
 */
@SuppressWarnings("serial")
public abstract class AbstractValue<v> implements Comparable<v>, Serializable {

	/**
	 * Compares two value of the same type for order.
	 * 
	 * @param value
	 *            Value to be compared with.
	 * @return 1, 0, or -1 depending on order of this and the input
	 */
	public abstract int compareTo(v value);
}