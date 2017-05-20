package Values;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Errors.BadTestException;

/**
 * A module used to compare table date values.
 */
@SuppressWarnings("serial")
public class DateValue extends AbstractValue<DateValue> {
	public Date value;

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 *            Value to be used in comparisons.
	 * @throws BadTestException
	 */
	public DateValue(String val) throws BadTestException {
		if (val.indexOf("'") != 0 || val.lastIndexOf("'") != val.length() - 1)
			throw new BadTestException(val, "date");
		val = val.substring(1, val.length() - 1).trim();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			value = new Date(format.parse(val).getTime());
		} catch (ParseException e) {
			throw new BadTestException(val, "date");
		}
	}

	/**
	 * Creates object for comparisons.
	 * 
	 * @param val
	 */
	public DateValue(Date val) {
		value = val;
	}

	/*
	 * @see Values.AbstractValue#compareTo(v)
	 */
	public int compareTo(DateValue o) {
		return value.compareTo(o.value);
	}

	/**
	 * Returns the data for this Value.
	 * 
	 * @returns data
	 */
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return (format.format(value) + "\t");
	}
}